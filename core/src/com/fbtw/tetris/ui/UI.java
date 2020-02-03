package com.fbtw.tetris.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class UI implements Disengageable, Scalable {
   boolean isEneble;
   protected int x;
   protected int y;
   protected int width;
   protected int height;

   protected Sprite backGround;
   protected boolean isBackground;

    public UI(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void setBackGround(Texture texture, Color color);
    public abstract void setBackGround(Texture texture);

    public abstract void render(SpriteBatch sb);

    public abstract void setPosition(int x,int y);

    public abstract void setSize(int width, int height);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
