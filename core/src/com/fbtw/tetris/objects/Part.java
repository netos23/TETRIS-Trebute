package com.fbtw.tetris.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.utils.TextureManager;


import static com.fbtw.tetris.MainGame.BLOCK_SIZE_X;
import static com.fbtw.tetris.MainGame.BLOCK_SIZE_Y;


public  class Part {

    private Block[] blocks;
    private int [][] model;

    private int posX;
    private int posY;

    private int length;
    private int heigth;


    private Color colour;
    private boolean isRotete;
    private int n;

    public Part(int[][] model, int posX, int posY, boolean isRotete) throws Exception{
        this.model = model;
        this.posX = posX;
        this.posY = posY;
        this.colour = TextureManager.getRandomColor();
        this.isRotete = isRotete;

        length=0;
        heigth=0;

        int countBlocks = splitModel();
        blocks = new Block[countBlocks];
        for (int i=0;i<countBlocks;i++){
            blocks[i]= new Block(0,0,colour);
        }

        if(model.length!=model[0].length) throw new Exception("Invalid block format");
        this.n = model[0].length;

        optimizeModel();
        updateBlocks();
        setPosition(posX,posY);

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

    public void setPosition(int x, int y){
        //this.posX=x;
        //this.posY=y;

        int dX, dY;
        for (Block block:blocks){
            dX = block.getPosX() - posX;
            dY = block.getPosY() - posY;

            block.setPosition(x+dX, y+dY);
        }
        posX=x;
        posY=y;
    }

    public void move(int velocityX, int velocityY){
        setPosition(velocityX+posX,velocityY+posY);
    }

    public  void rotate(){

        if(isRotete) {
            int[][] res = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    res[j][n - 1 - i] = model[i][j];
                }
            }

            for (int i = 0; i < model[0].length; i++) {
                for (int j = 0; j < model[0].length; j++) {
                    model[i][j] = res[i][j];
                }

            }

            optimizeModel();
            updateBlocks();
        }
    }


    private void optimizeModel() {
        int mergeLeft = 0, mergeDown = 0;
        boolean flag;

        for (int i = n - 1; i >= 0; i--) {
            flag = true;
            for (int j = 0; j < n; j++) {
                if (model[i][j] == 1) {
                    flag = false;
                }
            }

            if (flag) {
                mergeDown++;
            }else {
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            flag = true;
            for (int j = 0; j < n; j++) {
                if (model[j][i] == 1) {
                    flag = false;
                }
            }
            if (flag) {
                mergeLeft++;
            }else {
                break;
            }
        }

        if (mergeDown != 0 || mergeLeft != 0) {
            for (int i = n - mergeDown-1; i >= 0; i--) {
                for (int j = mergeLeft; j < n; j++) {
                    model[i+mergeDown][j-mergeLeft]=model[i][j];
                    model[i][j]=0;
                }
            }
        }
    }

    private void updateBlocks(){
        int count=0;
        length=0;
        heigth=0;
        for(int i = n - 1; i >= 0; i--){
            for(int j=0;j<n;j++){
                if(model[i][j]==1){
                    int lengthMax = BLOCK_SIZE_X * j;
                    int heigthMax = BLOCK_SIZE_Y * (n - i);
                    blocks[count].setPosition(posX+ lengthMax,posY+ heigthMax);

                    count++;


                    heigth = Math.max(heigth,heigthMax);
                    length = Math.max(length,lengthMax);
                    if(n==4&&length==90)length=120;


                }
            }
        }
    }

    public void render(SpriteBatch spriteBatch){
        for(Block block : blocks){
            block.render(spriteBatch);
        }
    }

    //for debug
    private  void print(int [][] model){
        for(int i=0;i<model[0].length;i++){
            for(int j=0;j<model[0].length;j++){
                System.out.print(model[i][j]+" ");
            }
            System.out.println();
        }
    }

    public Part clone(int x, int y) throws Exception {
        int [][] modelCopy = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                modelCopy[i][j] = model[i][j];
            }
        }

        return new Part(modelCopy,x,y,isRotete);
    }

    public Block[] extractBlocks(){
        return blocks;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getLength() {
        return length;
    }

    public int getHeigth() {
        return heigth;
    }

    public int[][] getModel() {
        return model;
    }
}
