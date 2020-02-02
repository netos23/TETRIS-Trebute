package com.fbtw.tetris.screans;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.utils.GameScreanManeger;
import com.fbtw.tetris.utils.TextureManager;

public class GameScrean implements Screen {
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private GameScreanManeger maneger;
    private Sprite background;
    private Sprite gameBackground;

    public GameScrean(Game game) {
        super();
        this.game = game;
        TextureManager.init();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        camera.setToOrtho(false,MainGame.SCREAN_SIZE_X,MainGame.SCREAN_SIZE_Y);
        maneger = new GameScreanManeger();

        background = new Sprite(TextureManager.blockTexture);
        background.setSize(MainGame.BLOCK_SIZE_X*maneger.getGrid_size_x(),MainGame.BLOCK_SIZE_Y*maneger.getGrid_size_y());
        background.setColor(Color.BLACK);

        gameBackground = new Sprite(TextureManager.background);
        gameBackground.setSize(MainGame.SCREAN_SIZE_X,MainGame.SCREAN_SIZE_Y);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0,0,1,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        update(delta);

        batch.begin();
        gameBackground.draw(batch);
        background.draw(batch);
        maneger.render(batch);
        batch.end();
    }

    public void update(float delta){
        maneger.update(delta);
    }


    @Override
    public void resize(int width, int height) {
        int offsetX =  100;
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
       // width = (int)(width*zooom);
        camera.setToOrtho(false,width,height);


        int x = (width - MainGame.BLOCK_SIZE_X * maneger.getGrid_size_x())/2-offsetX;
        float y =(MainGame.BLOCK_SIZE_Y * maneger.getGrid_size_y()
                -(height)/2f);
        gameBackground.setSize(width,height);
        gameBackground.setPosition(-x,-(height-MainGame.BLOCK_SIZE_Y * maneger.getGrid_size_y()) );


        camera.position.x = MainGame.BLOCK_SIZE_X*maneger.getGrid_size_x()/2+offsetX;
        camera.zoom = zooom;
        camera.position.y = y/zooom;
        camera.update();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        TextureManager.dispose();
    }
}
