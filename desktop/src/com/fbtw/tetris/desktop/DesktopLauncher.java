package com.fbtw.tetris.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fbtw.tetris.MainGame;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 500;
		config.height = 1000;
		//config.fullscreen = true;

		//config.resizable = false;
		new LwjglApplication(new MainGame(config.width,config.height), config);


	}
}
