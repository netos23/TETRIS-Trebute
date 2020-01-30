package com.fbtw.tetris.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class TextureManager {
    public static  Texture blockTexture;
    public static  Texture background;
    public static Color [] colors = {Color.YELLOW,Color.GREEN,Color.RED,Color.BLUE,Color.ORANGE};
    private static Random random;

    public static void init(){
        random = new Random();
        blockTexture = new Texture("badlogic.jpg");
        background = new Texture("background.jpg");
    }

    public static void dispose(){
        random = null;
        colors = null;
        blockTexture.dispose();
        background.dispose();
    }

    public static Color getRandomColor(){
        return colors[random.nextInt(colors.length)];
    }

}
