package com.fbtw.tetris.screans;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.objects.HigthScore;
import com.fbtw.tetris.ui.widget.TextTable;
import com.fbtw.tetris.utils.HighScoreManager;

public class GameOverScreen implements Screen {
	private SpriteBatch batch;
	private OrthographicCamera camera;

	private TextTable<HigthScore> higthScoreTextTable;


	public GameOverScreen(int score) {
		batch = new SpriteBatch();

		HighScoreManager.tryAddScores("ppp",score);
		higthScoreTextTable = new TextTable<>(HighScoreManager.getScoresTable(),0,0,true);
		higthScoreTextTable.setColor(Color.PINK);
		HighScoreManager.printScores();
		for(HigthScore s: HighScoreManager.getScoresTable()){
			System.out.println(s);
		}
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		higthScoreTextTable.render(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

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

	}
}
