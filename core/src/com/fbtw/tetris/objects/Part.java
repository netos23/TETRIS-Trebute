package com.fbtw.tetris.objects;

import com.badlogic.gdx.graphics.Color;
import com.fbtw.tetris.utils.TextureManager;



public  class Part {

    private Block[] blocks;
    private int [][] model;

    private int posX;
    private int posY;


    private Color colour;
    private boolean isRotete;

    public Part(int[][] model, int posX, int posY, boolean isRotete) {
        this.model = model;
        this.posX = posX;
        this.posY = posY;
        this.colour = TextureManager.getRandomColor();
        this.isRotete = isRotete;

        int n = splitModel();
        blocks = new Block[n];
        for (int i=0;i<n;i++){
            blocks[i]= new Block(0,0,colour);
        }
    }


    private int splitModel(){
        int counter=0;
        for (int i=0;i<model.length;i++){
            for(int j=0;j<model[0].length;j++){
                if(model[i][j]==1)counter++;
            }
        }
        return counter;
    }


    private void optimizeModel(){

    }

    //todo : grid 10*20;
}
