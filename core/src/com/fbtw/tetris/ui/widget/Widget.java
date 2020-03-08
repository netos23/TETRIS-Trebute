package com.fbtw.tetris.ui.widget;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.ui.Scalable;

public interface Widget extends Scalable {
	void render(SpriteBatch batch);

	void setPosition(int x, int y);

	void setFontSize(float fontSize);

	void setColor(Color colour);

	Widget clone();

	int getPosX();

	int getPosY();

	int getWidth();
	int getHeigth();

	void dispose();
}
