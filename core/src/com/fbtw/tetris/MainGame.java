package com.fbtw.tetris;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.screans.GameScrean;


public class MainGame extends Game {
public static final int BLOCK_SIZE_X = 30;
public static final int BLOCK_SIZE_Y = 30;

	@Override
	public void create() {
		setScreen(new GameScrean(this));


	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {


	}
}
