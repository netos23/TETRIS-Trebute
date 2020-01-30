package com.fbtw.tetris.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fbtw.tetris.MainGame;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 450;
		config.height = 900;
		//config.fullscreen = true;

		//config.resizable = false;
		new LwjglApplication(new MainGame(config.width,config.height), config);


	}
}
