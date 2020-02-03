package com.fbtw.tetris.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class TextField implements Disengageable{
    private BitmapFont text;

    private String textString;

    private int x;
    private int y;

    private int localX;
    private int localY;

    private int width;
    private int hegth;


    private Color color;

    private boolean isEneble;


    public TextField(String textString, int x, int localX, int y, int localY) {
        this.textString = textString;
        this.x = x;
        this.y = y;
        this.localX = localX;
        this.localY = localY;
        this.width = width;
        this.hegth = hegth;

        this.text = new BitmapFont();

        isEneble = true;
    }

    public void render(SpriteBatch batch){
        if(isEneble) {
            text.draw(batch, textString, x+localX, y+localY, width, 1, false);
        }
    }

    public BitmapFont getText() {
        return text;
    }

    public void setText(BitmapFont text) {
        this.text = text;
    }

    public String getTextString() {
        return textString;
    }

    public void setTextString(String textString) {
        this.textString = textString;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHegth() {
        return hegth;
    }

    public void setHegth(int hegth) {
        this.hegth = hegth;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        text.setColor(color);
    }

    @Override
    public void setDisable(boolean disable) {
        isEneble=!disable;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}
