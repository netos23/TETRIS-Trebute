package com.fbtw.tetris.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.objects.Block;
import com.fbtw.tetris.objects.Part;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import static com.fbtw.tetris.MainGame.BLOCK_SIZE_X;
import static com.fbtw.tetris.MainGame.BLOCK_SIZE_Y;

public class GameScreanManeger {
    private Part[] prefabs;
    private LinkedList<Part> queue;
    private Part activePart;

    private Block[][] grid;
    private int grid_size_x = 10;
    private int grid_size_y = 20;
    private int [] ground;
    private boolean isGameOver, isInDestraction;

    private PrefabLoader loader;


    private int speed = 1;
    private int timeCycle = 20;
    private int curTime = 0;
    private int score=0;

    private Random random;

    public GameScreanManeger() {
        random = new Random();
        loader = new PrefabLoader("prefabs.txt");

        try {
            prefabs = loader.getPrefabs();
        } catch (Exception e) {
            e.printStackTrace();
        }

        grid = new Block[grid_size_y][grid_size_x];
        ground = new int[grid_size_x];
        Arrays.fill(ground,0);
        isInDestraction = false;

        queue = new LinkedList<Part>();
        updateActivePart();
        activePart.setPosition(150, 600);
    }

    public void update(float dt) {
        if(!isGameOver||!isInDestraction) {
            curTime += speed;
            if (curTime >= timeCycle) {
                activePart.move(0, -BLOCK_SIZE_Y);
                updateGrid();
                curTime = 0;
            }
            handleInput();
        }
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            activePart.rotate();
            if(activePart.getPosX()+activePart.getLength()> (grid_size_x-1)*BLOCK_SIZE_X){
               // activePart.move(grid_size_x*BLOCK_SIZE_X-(activePart.getPosX()+activePart.getLength()),0);
                activePart.move(BLOCK_SIZE_X-activePart.getLength(),0);

            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if(activePart.getPosX()>=BLOCK_SIZE_X){
                activePart.move(-BLOCK_SIZE_X, 0);
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if(activePart.getPosX()+activePart.getLength()<BLOCK_SIZE_X*(grid_size_x-1)) {
                activePart.move(BLOCK_SIZE_X, 0);
            }
        }


    }

    private void updateGrid() {
        int [][] hitBox = activePart.getModel();

        int columnStart = activePart.getPosX()/BLOCK_SIZE_X;
        int currentY = activePart.getPosY()/BLOCK_SIZE_Y;

        int n = hitBox[0].length;

        for(int i = n - 1; i >= 0; i--){
            if(isInDestraction||isGameOver) {
                break;
            }
            for (int j=0;j<n;j++){
                if(hitBox[i][j]==1){
                    int maxY = currentY + (n - i - 1) + 1;

                    if(maxY ==ground[columnStart+j]){
                        if(maxY>grid_size_y){
                            isGameOver=true;
                            break;
                            //fixme : правильное определение луза
                        }
                        isInDestraction=true;
                        break;
                    }
                }
            }
        }
        if(isInDestraction){
            importGrid();
            activePart=null;
            updateActivePart();
            isInDestraction = false;
            inspectGrid();
        }
    }

    private void updateActivePart() {
        if(queue.size()<2){
            queue.add(generetePart());
            queue.add(generetePart());
        }
        activePart = queue.poll();
    }

    private void inspectGrid(){
        boolean flag;
        for(int i=grid_size_y-1;i>=0;i--){
            flag=true;
            for(int j=0;j<grid_size_x;j++){
                if(grid[i][j]==null){
                    flag=false;
                }
            }
            if(flag){
                score+=100;
                for(int k=i;k<grid_size_y;k++){
                    for(int j=0;j<grid_size_x;j++){
                        if(k==i){
                            grid[k][j]=null;
                            if(ground[j]>=1)ground[j]--;
                        }else{
                            Block block = grid[k][j];
                            if(block!=null) {
                                block.setPosition(block.getPosX(), block.getPosY() - BLOCK_SIZE_Y);
                            }
                            grid[k-1][j]=grid[k][j];
                            grid[k][j]=null;

                        }
                    }
                }
            }
        }
    }

    public void importGrid() {
        for (Block block : activePart.extractBlocks()) {
            int y = block.getPosY() / BLOCK_SIZE_Y;
            int x = block.getPosX() / BLOCK_SIZE_X;
            grid[y][x] = block;
            ground[x] = Math.max(ground[x],y+1);
        }
    }


    private Part generetePart() {

        try {
            return prefabs[random.nextInt(prefabs.length)].clone(150,600);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void render(SpriteBatch sb) {
        activePart.render(sb);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null) {
                    grid[i][j].render(sb);
                }
            }
        }
    }





}