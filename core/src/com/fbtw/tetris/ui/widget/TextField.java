package com.fbtw.tetris.ui.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.fbtw.tetris.ui.Disengageable;


public class TextField implements Widget,Disengageable {
    //private BitmapFont text;
    private Label text;

    private String textString;

    private int x;
    private int y;

    private int localX;
    private int localY;


    private Color color;

    protected boolean isEneble;




    public TextField(String textString, int x, int localX, int y, int localY) {
        this.textString = textString;
        this.x = x;
        this.y = y;
        this.localX = localX;
        this.localY = localY;


        Skin skin = new Skin(Gdx.files.internal("font/skin.json"));
        //todo: системные разделители
        text = new Label(textString,skin.get("default", Label.LabelStyle.class));

        text.setColor(Color.BLACK);
        text.setFontScale(0.5f);

        isEneble = true;
    }

    @Override
    public void render(SpriteBatch batch){
        if(isEneble) {
            text.setPosition(x+localX, y+localY);
            text.draw(batch,100);
           // text.draw(batch, textString, x+localX, y+localY, width, 1, false);
        }
    }

    public void setAlign(int algin) {
        text.setAlignment(algin);
    }


    public String getTextString() {
        return textString;
    }

    public void setTextString(String textString) {
        this.textString = textString;
        text.setText(textString);
    }

    @Override
    public int getPosX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getPosY() {
        return y;
    }

	@Override
	public int getWidth() {
		return (int) text.getWidth();
	}

	@Override
	public int getHeigth() {
		return (int) text.getHeight();
	}

    @Override
    public void dispose() {

    }

    public void setY(int y) {
        this.y = y;
    }



    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        text.setColor(color);
    }

	@Override
	public Widget clone() {
		return null;
	}

	@Override
    public void setDisable(boolean disable) {
        isEneble=!disable;
    }
    @Override
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
	public void setFontSize(float fontSize) {
        text.setFontScale(fontSize);
	}

    @Override
    public void resize(int width, int height) {

    }

    public void setOffsetX(int localX) {
        this.localX = localX;
    }

    public void setOffsetY(int localY) {
        this.localY = localY;
    }
}
