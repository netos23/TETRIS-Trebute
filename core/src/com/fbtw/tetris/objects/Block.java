package com.fbtw.tetris.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.utils.TextureManager;


import static com.fbtw.tetris.MainGame.BLOCK_SIZE_X;
import static com.fbtw.tetris.MainGame.BLOCK_SIZE_Y;

public class Block {
    private static int weidth = BLOCK_SIZE_X;
    private static int heigth = BLOCK_SIZE_Y;
    private int posX;
    private int posY;
    private Sprite blockSprite;
    private Color color;


    public Block(int posX, int posY, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;


        blockSprite = new Sprite(TextureManager.blockTexture);
        blockSprite.setColor(color);
        blockSprite.setSize(weidth,heigth);

        setPosition(posX,posY);
    }




    public void setPosition(int x, int y){
        this.posX = x;
        this.posY = y;
        blockSprite.setPosition(x,y);
    }

    public void resize(int w, int h){
        blockSprite.setSize(w,h);
    }

    public void render(SpriteBatch sb){
        blockSprite.draw(sb);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Color getColor() {
        return color;
    }
}
