package com.fbtw.tetris.screans;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.objects.HigthScore;
import com.fbtw.tetris.ui.widget.FleshingText;
import com.fbtw.tetris.ui.widget.InputField;
import com.fbtw.tetris.ui.widget.TextField;
import com.fbtw.tetris.ui.widget.TextTable;
import com.fbtw.tetris.utils.HighScoreManager;
import com.fbtw.tetris.utils.TextSourses;
import com.fbtw.tetris.utils.TextureManager;

public class GameOverScreen implements Screen {
	private SpriteBatch batch;
	private OrthographicCamera camera;


	private TextField gameOver;
	private TextField highScoreMsg;
	private TextTable<HigthScore> higthScoreTextTable;
	private InputField enterName;
	private FleshingText continueName;

	private int gameOverPadding;
	private boolean isFullscrean;

	private int score=186*2;
	private int width=207;

	private boolean release;
	private MainGame game;

	public GameOverScreen(MainGame game,int score) {
		this.score = score;
		this.game = game;

		batch = new SpriteBatch();
		camera = new OrthographicCamera();

		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		release = false;

		gameOverPadding = -25;

		gameOver = new TextField(TextSourses.GAME_OVER,gameOverPadding,0,340,0);
		gameOver.setColor(Color.WHITE);
		gameOver.setFontSize(1.6f);
		gameOver.setAlign(Align.left);


		enterName = new InputField(7,35,170,0);
		enterName.setFontSize(0.8f);
		enterName.setDisable(false);




		highScoreMsg = new TextField(TextSourses.HIGH_SCORE_0,gameOverPadding,0,280,0);
		highScoreMsg.setColor(Color.WHITE);
		highScoreMsg.setAlign(Align.bottomLeft);
		highScoreMsg.setFontSize(0.8f);

		continueName = new FleshingText(TextSourses.PRESS_ENTER,0,0,10,0,40);
		continueName.setColor(Color.WHITE);

		/*highScoreMsg.setTextString(TextSourses.HIGH_SCORE_1);
		highScoreMsg.setFontSize(0.7f);
		highScoreMsg.setPosition((int) (-0.5f*gameOverPadding),260);*/


		if(HighScoreManager.getLow()<score){
			pushScore();
		}else{
			showTable();
		}


		/*for(HigthScore s: HighScoreManager.getScoresTable()){
			System.out.println(s);
		}*/

		/*camera.position.x =  higthScoreTextTable.getWidth()/2;
		camera.update();*/


	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

		update();

		batch.begin();
		gameOver.render(batch);
		highScoreMsg.render(batch);
		enterName.render(batch);
		continueName.render(batch);
		if(higthScoreTextTable!=null) {
			higthScoreTextTable.render(batch);
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if(higthScoreTextTable!=null) {
			this.width = higthScoreTextTable.getWidth();
			//System.out.println(Gdx.graphics.getHeight());
		}
		int offsetX =  this.width + MainGame.BLOCK_SIZE_X ;
		if(width<this.width+offsetX){
			Gdx.graphics.setWindowedMode(this.width+offsetX,
					this.width);

		}else{
			if(height<this.width){
				Gdx.graphics.setWindowedMode(width,
						this.width);
			}
		}


		float zooom =   ( this.width*2)/(1.0f*height);
		camera.setToOrtho(false,width,height);

		isFullscrean = Gdx.graphics.isFullscreen();

		camera.position.x =  this.width/2;
		camera.zoom = zooom;
		camera.position.y =/* y*zooom+*/camera.viewportHeight*zooom/2;
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
		batch.dispose();
		TextureManager.dispose();
		higthScoreTextTable = null;
	}

	private void hendleInput(){

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

		if(release){

			if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
				game.setScreen(new GameScreen(game,1));
			}
		}

	}

	public void showTable(){
		release = true;
		continueName.setTextString(TextSourses.PRESS_ANY);
		continueName.setPosition(-15,continueName.getPosY());
		enterName.setDisable(true);
		higthScoreTextTable = new TextTable<>(HighScoreManager.getScoresTable(),-15,30,true);
		higthScoreTextTable.setColor(Color.WHITE);
		higthScoreTextTable.setDisable(true);


		highScoreMsg.setTextString(TextSourses.HIGH_SCORE_1);
		highScoreMsg.setPosition(0,highScoreMsg.getPosY());
		highScoreMsg.setFontSize(1f);
		highScoreMsg.setPosition((int) (-0.3f*gameOverPadding),260);
		higthScoreTextTable.setDisable(false);
	}

	public void pushScore(){
		continueName.setTextString(TextSourses.PRESS_ENTER);
		highScoreMsg = new TextField(TextSourses.HIGH_SCORE_0,gameOverPadding-65,0,230,0);
		highScoreMsg.setColor(Color.WHITE);
		highScoreMsg.setAlign(Align.bottomLeft);
		highScoreMsg.setFontSize(0.4f);
		enterName.setDisable(false);

	}

	public void update(){
		hendleInput();
		if(enterName.update()){
			HighScoreManager.tryAddScores(enterName.toString(),score);
			HighScoreManager.printScores();
			showTable();
		}
	}
}
