package com.fbtw.tetris.screans;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.ui.animation.HorMoveAnimation;
import com.fbtw.tetris.ui.widget.Image;
import com.fbtw.tetris.ui.widget.Logo;

import com.fbtw.tetris.ui.widget.Selector;
import com.fbtw.tetris.utils.Expression;
import com.fbtw.tetris.utils.PlatformsVariants;
import com.fbtw.tetris.utils.TextureManager;

public class MenuScreen implements Screen {
	private static final float BUTTONS_K = 1.71f;


	private SpriteBatch batch;
	private OrthographicCamera camera;
	private HorMoveAnimation anim;
	private Image background;
	private Logo logo;

	private Selector s;

	private MainGame game;

	//private ButtonWidget widget;

	private Music theme;

	public MenuScreen(MainGame game) {
		this.game = game;

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		background = new Image(TextureManager.background, 0, 0, Gdx.graphics.getWidth() + 6, Gdx.graphics.getHeight());
		anim = new HorMoveAnimation(0, 1, background);
		anim.play();

		theme = Gdx.audio.newMusic(Gdx.files.internal("sound/menu_theme_classic.mp3"));
		theme.setLooping(true);
		theme.setVolume(0.2f);
		theme.play();

		//logo = new Logo(50,200); //for posters


		/*if (MainGame.platform == PlatformsVariants.ANDROID) {
			logo = new Logo(Gdx.graphics.getWidth()/2-MainGame.BLOCK_SIZE_X*10,Gdx.graphics.getHeight()*2/3);

			s = new Selector("button.txt", Gdx.graphics.getWidth()/2-MainGame.BLOCK_SIZE_X*2, Gdx.graphics.getHeight()/3);
		} else {*/
			logo = new Logo(50, 400);
			s = new Selector("button.txt", 310, 200);
		//}

		s.addButton("QUIT"); //id = 0
		/*s.addButton("SCORE"); //id = 1
		s.addButton("SETTINGS");*/ //id = 2
		s.addButton("PLAY"); //id = 3
		s.setOnClickListner(1, new Expression() {
			@Override
			public void call(Object... params) {
				theme.stop();
				dispose();
				game.setScreen(new GameScreen(game, 1));
			}
		});
		s.getButton(0).setFontSize(0.7f);
		s.setOnClickListner(0, new Expression() {
			@Override
			public void call(Object... params) {
				Gdx.app.exit();
			}
		});

		if(MainGame.platform==PlatformsVariants.ANDROID){
			float multiplierX = Gdx.graphics.getWidth()/1794f;
			float multiplierY = Gdx.graphics.getHeight()/1080f;



			for(int i=0;i<s.getButtonsCount();i++){
				Button btn = s.getButton(i).getButton();
				btn.setSize(btn.getWidth()*multiplierX* BUTTONS_K,btn.getHeight()*multiplierY*BUTTONS_K);
				btn.setPosition(800*multiplierX,(360+i*90)*multiplierY);

			}
		}

		s.commit();

		s.getController().addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println(x+" "+y);
				return true;
			}
		});

		/*if(MainGame.platform==PlatformsVariants.ANDROID){
			s.setButtonsPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		}*/

		Gdx.input.setInputProcessor(this.s.getController());

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

		update();

		batch.begin();
		anim.render(batch);
		logo.render(batch);

		s.render(batch);
		//widget.render(batch);
		batch.end();
	}

	private void update() {

		anim.update();
		s.update();


	}

	@Override
	public void resize(int width, int height) {
		//s.getController().getViewport().update(width,height);
		//s.getController().act();
		//s.resize(width, height);
		//System.out.println(s.getController().getWidth());
		//if (MainGame.platform != PlatformsVariants.ANDROID) {

			s.getController().getViewport().update(width, height, true);
			int current_heigth = 600;

			float zooom = (1.0f * current_heigth) / (1.0f * height);
			camera.setToOrtho(false, width, height);


			int current_width = logo.getWidth() + logo.getPosX() * 2;


			if (width < current_width / zooom) {
				Gdx.graphics.setWindowedMode((int) (current_width / zooom),
						height);

			} else {

				if (height < current_heigth) {
					Gdx.graphics.setWindowedMode(width,
							current_heigth);
				}
			}


			camera.position.x = (current_width) / 2;
			camera.zoom = zooom;
			camera.position.y = camera.viewportHeight * zooom / 2;
			camera.update();


			anim.resize((int) (width * zooom + 5), (int) (height * zooom));
			int x = (int) Math.floor(camera.position.x - camera.viewportWidth * zooom / 2);
			anim.setStartX(x);
		//}

	}

	@Override
	public void pause() {
		theme.pause();
	}

	@Override
	public void resume() {
		theme.play();
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
	    //TextureManager.dispose();
		batch.dispose();
		s.dispose();
		theme.dispose();
	}
}
