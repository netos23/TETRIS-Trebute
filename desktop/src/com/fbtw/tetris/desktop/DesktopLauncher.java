package com.fbtw.tetris.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.utils.PlatformsVariants;


public class DesktopLauncher {
	public static void main (String[] arg){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 700;
		config.height = 600;
		config.title = "TETRIS";
		//config.fullscreen = true;

		//config.resizable = false;
		new LwjglApplication(new MainGame(config.width,config.height, PlatformsVariants.PC_WIN), config);


	}
}
