package com.fbtw.tetris;


import com.badlogic.gdx.Game;

import com.fbtw.tetris.screans.GameScreen;
import com.fbtw.tetris.utils.PlatformsVariants;


public class MainGame extends Game {
public static  int BLOCK_SIZE_X = 30;
public static  int BLOCK_SIZE_Y = 30;
public static  int SCREAN_SIZE_X = 300;
public static  int SCREAN_SIZE_Y = 600;
public static PlatformsVariants platform;

	public MainGame(int X,int Y,PlatformsVariants variants) {
		SCREAN_SIZE_X = X;
		SCREAN_SIZE_Y = Y;

		platform = variants;

	}

	@Override
	public void create() {
		setScreen(new GameScreen(this,1));


	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {


	}


}
