package com.fbtw.tetris.ui.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.ui.widget.Widget;

public class HorMoveAnimation extends  Animation {

	private Widget obj;
	private Widget copy;
	private int startX;
	private int startY;

	public HorMoveAnimation(int timeCycle, int velosity, Widget obj) {
		super(timeCycle, velosity);
		this.obj = obj;
		copy = obj.clone();

		startX = obj.getPosX();
		startY = obj.getPosY();

		copy.setPosition(startX - obj.getWidth(), startY);

		isPlayed = false;
	}

	@Override
	public void resize(int width, int height) {
		obj.resize(width, height);
		obj.setPosition(startX, obj.getPosY());
		copy.resize(width, height);
		copy.setPosition(startX - obj.getWidth(), copy.getPosY());
	}

	@Override
	public void show() {

	}

	@Override
	public void play() {
		isPlayed = true;
	}

	@Override
	public void stop() {
		currentTime = 0;
		isPlayed = false;
	}

	@Override
	public void pause() {
		isPlayed = false;
	}

	@Override
	public void update() {
		if (isEnable) {
			if (isPlayed) {

				int x = copy.getPosX();
				int y = copy.getPosY();

				if (x > startX + obj.getWidth()) {
					x = startX - obj.getWidth() + velosity;
				}
				copy.setPosition(x + velosity, y);

				x = obj.getPosX();
				y = obj.getPosY();

				if (x > startX + obj.getWidth()) {
					x = startX - obj.getWidth() + velosity;
				}

				obj.setPosition(x + velosity, y);


			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		obj.render(batch);
		copy.render(batch);
	}

	@Override
	public void setDisable(boolean disable) {
		isEnable = !disable;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}
}
