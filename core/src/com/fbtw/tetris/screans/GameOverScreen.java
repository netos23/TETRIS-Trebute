package com.fbtw.tetris.screans;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.fbtw.tetris.objects.HigthScore;
import com.fbtw.tetris.utils.HighScoreManager;

public class GameOverScreen implements Screen {

	public GameOverScreen(int score) {
		HighScoreManager.tryAddScores("ppp",score);
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
