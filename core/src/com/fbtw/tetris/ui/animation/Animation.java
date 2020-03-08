package com.fbtw.tetris.ui.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.ui.Disengageable;
import com.fbtw.tetris.ui.Scalable;

public abstract class Animation implements Disengageable {

	int timeCycle;
	int currentTime;
	int velosity;

	boolean isPlayed;
	boolean isEnable;

	public Animation(int timeCycle, int velosity) {
		this.timeCycle = timeCycle;
		this.velosity = velosity;

		isPlayed = false;
		isEnable = true;
	}

	public abstract void resize(int x,int y);

	public abstract void show();

	public abstract void play();

	public abstract void stop();

	public  abstract void pause();

	public abstract void update();

	public abstract void render(SpriteBatch batch);
}
