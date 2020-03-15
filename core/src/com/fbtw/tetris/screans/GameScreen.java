package com.fbtw.tetris.screans;

import com.badlogic.gdx.*;


import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.utils.Expression;
import com.fbtw.tetris.utils.GameScreanManeger;
import com.fbtw.tetris.utils.HighScoreManager;
import com.fbtw.tetris.utils.PlatformsVariants;
import com.fbtw.tetris.utils.TextureManager;
import com.fbtw.tetris.utils.UIManager;

public class GameScreen implements Screen {
    private boolean isFullscrean;
    private MainGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private GameScreanManeger maneger;
    private Sprite background;
    private Sprite gameBackground;

    private UIManager uiManager;

    private int speed;

    private boolean isPause;

    private Music theme;

    private boolean disposed;

    public GameScreen(MainGame game, int speed) {
        super();
        HighScoreManager.initHigthScoreManager();
        HighScoreManager.loadScores();
        this.game = game;
        this.speed = speed;
        //TextureManager.init();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        camera.setToOrtho(false,MainGame.SCREAN_SIZE_X,MainGame.SCREAN_SIZE_Y);
        maneger = new GameScreanManeger(speed);

        background = new Sprite(TextureManager.blockTexture);
        background.setSize(MainGame.BLOCK_SIZE_X*maneger.getGrid_size_x(),MainGame.BLOCK_SIZE_Y*maneger.getGrid_size_y());
        background.setColor(Color.BLACK);

        gameBackground = new Sprite(TextureManager.background);
        gameBackground.setSize(MainGame.SCREAN_SIZE_X,MainGame.SCREAN_SIZE_Y);

        uiManager = new UIManager();

        maneger.setUiManager(uiManager);

        uiManager.getScore().setPosition(MainGame.BLOCK_SIZE_X*(maneger.getGrid_size_x()+1), (int) (camera.viewportHeight/2-150));
        uiManager.getScore().setBackGround(TextureManager.blockTexture,Color.WHITE);
        uiManager.getScore().setSpeed(speed);

        isPause = false;

        isFullscrean = Gdx.graphics.isFullscreen();

        theme = Gdx.audio.newMusic(Gdx.files.internal("sound/game_theme.mp3"));
        theme.setVolume(0.15f);
        theme.setLooping(true);
        theme.play();

        if(MainGame.platform== PlatformsVariants.ANDROID){
            maneger.getControls().setPause(new Expression() {
                @Override
                public void call(Object... params) {

                    if(!isPause){
                        pause();
                    }else {
                        resume();
                    }

                    maneger.getControls().setPause(isPause);
                }
            });

            maneger.getControls().setMenu(new Expression() {
                @Override
                public void call(Object... params) {
                    theme.stop();
                    dispose();
                    game.setScreen(new MenuScreen(game));
                }
            });
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0,0,1,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);



        batch.begin();
        gameBackground.draw(batch);
        background.draw(batch);
        maneger.render(batch);
        uiManager.render(batch);
        batch.end();

        update(delta);
    }

    public void update(float delta){
        if(!isPause) {
            if(maneger.update(delta)){
                theme.stop();
                game.setScreen(new GameOverScreen(game,maneger.getScore()));
                dispose();
            }
        }

        hendleInput();
    }


    @Override
    public void resize(int width, int height) {
        int offsetX =  uiManager.getScore().getWidth()+MainGame.BLOCK_SIZE_X ;
        if(width<MainGame.BLOCK_SIZE_X*maneger.getGrid_size_x()+offsetX){
            Gdx.graphics.setWindowedMode(MainGame.BLOCK_SIZE_X * maneger.getGrid_size_x()+offsetX,
                    MainGame.BLOCK_SIZE_Y*maneger.getGrid_size_y());

        }else{
            if(height<MainGame.BLOCK_SIZE_Y*maneger.getGrid_size_y()){
                Gdx.graphics.setWindowedMode(width,
                        MainGame.BLOCK_SIZE_Y*maneger.getGrid_size_y());
            }
        }


        float zooom =   (1.0f* MainGame.BLOCK_SIZE_Y * maneger.getGrid_size_y())/(1.0f*height);
        camera.setToOrtho(false,width,height);



        camera.position.x = (MainGame.BLOCK_SIZE_X*maneger.getGrid_size_x()+offsetX)/2;
        camera.zoom = zooom;
        camera.position.y =/* y*zooom+*/camera.viewportHeight*zooom/2;
        camera.update();

        int x = (int) Math.floor(camera.position.x-camera.viewportWidth*zooom/2);

        gameBackground.setSize(width*zooom+1,height*zooom);
        gameBackground.setPosition(x,0);


    }

    @Override
    public void pause() {
        isPause = true;
        theme.pause();
        uiManager.getScore().setPause(true);
    }

    @Override
    public void resume() {
        theme.play();
        isPause = false;
        uiManager.getScore().setPause(false);
    }

    @Override
    public void hide() {
        pause();
    }

    @Override
    public void dispose() {
        batch.dispose();
        maneger.dispose();
        theme.dispose();
        disposed = true;
        //TextureManager.dispose();
    }

    private void hendleInput(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            if(!isPause){
                pause();
            }else {
                resume();
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F9)){
            isFullscrean=!isFullscrean;

            if(isFullscrean) {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            }else{
                Gdx.graphics.setWindowedMode(MainGame.SCREAN_SIZE_X,MainGame.SCREAN_SIZE_Y);
            }
        }

    }
}
