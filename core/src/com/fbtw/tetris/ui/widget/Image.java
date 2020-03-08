package com.fbtw.tetris.ui.widget;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.ui.Disengageable;
import com.fbtw.tetris.ui.Scalable;

public class Image implements Widget, Disengageable {
	private Sprite image;
	private int x;
	private int y;

	private int width;
	private int height;

	private boolean isEnable;


	public Image(Texture texture,int x, int y, int width,int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		isEnable = true;

		image = new Sprite(texture);

		setPosition(x,y);
		resize(width,height);
	}

	Image(Sprite sprite,int x,int y){
		this.x = x;
		this.y = y;
		isEnable = true;

		image = new Sprite(sprite);

		setPosition(x,y);
	}

	@Override
	public void setDisable(boolean disable) {
		isEnable=!disable;
	}

	@Override
	public void resize(int width, int height) {
		image.setSize(width,height);
	}

	@Override
	public void render(SpriteBatch batch) {
		if(isEnable) {
			image.draw(batch);
		}
	}

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;

		image.setPosition(x,y);
	}

	@Override
	public void setFontSize(float fontSize) {
		//redundant
	}

	@Override
	public void setColor(Color colour) {
		image.setColor(colour);
	}

	@Override
	public Widget clone() {
		return new Image(image,x,y);
	}

	@Override
	public int getPosX() {
		return x;
	}

	@Override
	public int getPosY() {
		return y;
	}

	@Override
	public int getWidth() {
		return (int) image.getWidth();
	}

	@Override
	public int getHeigth() {
		return (int) image.getHeight();
	}

	@Override
	public void dispose() {

	}
}
