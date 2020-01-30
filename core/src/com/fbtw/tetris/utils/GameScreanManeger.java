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
import static com.fbtw.tetris.MainGame.SCREAN_SIZE_X;
import static com.fbtw.tetris.MainGame.SCREAN_SIZE_Y;

public class GameScreanManeger {
    private Part[] prefabs;
    private LinkedList<Part> queue;
    private Part activePart;

    private Block[][] grid;
    private int grid_size_x = 10;
    private int grid_size_y = 20;
    private int[] ground;
    private boolean isGameOver, isInDestraction, isUpdatePart, isInspectd;

    private PrefabLoader loader;


    private int speed = 1;
    private int timeCycle = 20;
    private int superTimeCycle = 1;
    private int defaultTimeCycle = 20;
    private int curTime = 0;
    private int score = 0;

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
        Arrays.fill(ground, 0);

        isUpdatePart = false;

        queue = new LinkedList<Part>();
        updateActivePart();
        activePart.setPosition(SCREAN_SIZE_X/2, SCREAN_SIZE_Y);
    }

    public void update(float dt) {
        if (!isGameOver ) {
            curTime += speed;
            handleInput();
            if (curTime >= timeCycle) {


                isUpdatePart = isOverlapsModel(activePart.getColumn(),
                        activePart.getModel(),
                        activePart.getLength()) == 1;

                if(isUpdatePart){

                    importGrid();
                    inspectGrid();
                    updateActivePart();


                }else {
                    activePart.move(0, -BLOCK_SIZE_Y);
                }

                curTime = 0;
                isInspectd=false;
            }

        }else{
            System.out.println("game over");
        }
    }

    private void handleInput() {
        int length = activePart.getLength();
        if(!isUpdatePart) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                isInspectd = true;

                int[][] newModel = activePart.rotate();
                int newX;
                length = activePart.getLength();
                if (length + activePart.getColumn() < grid_size_x) {
                    newX = activePart.getColumn();
                } else {
                    newX = grid_size_x - length - 1;
                }
                switch (isOverlapsModel(newX, newModel, length)) {
                    case -1:
                        break;
                    case 0:
                        activePart.apply(newModel);
                        activePart.setPosition(newX * BLOCK_SIZE_X, activePart.getPosY());

                        break;
                    case 1:
                        activePart.apply(newModel);
                        isUpdatePart = true;
                        activePart.setPosition(newX * BLOCK_SIZE_X, activePart.getPosY());

                        break;
                    default:
                }

            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                length = activePart.getLength();
                isInspectd = true;
                if (activePart.getColumn() > 0) {

                    isInspectd = true;
                    int newX = activePart.getColumn() - 1;
                    switch (isOverlapsModel(newX, activePart.getModel(), length)) {
                        case -1:
                            break;
                        case 0:
                            activePart.move(-BLOCK_SIZE_X, 0);
                            break;
                        case 1:
                            activePart.move(-BLOCK_SIZE_X, 0);
                            isUpdatePart = true;
                            break;

                    }
                }

            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                isInspectd = true;
                length = activePart.getLength();
                if (length + activePart.getColumn() < grid_size_x - 1) {
                    int newX = activePart.getColumn() + 1;
                    int overlapsModel = isOverlapsModel(newX, activePart.getModel(), length);

                    switch (overlapsModel) {
                        case -1:
                            break;
                        case 0:
                            activePart.move(BLOCK_SIZE_X, 0);
                            break;
                        case 1:
                            activePart.move(BLOCK_SIZE_X, 0);
                            isUpdatePart = true;
                            break;

                    }
                }
            }

            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                timeCycle = superTimeCycle;
            }else{
                timeCycle = defaultTimeCycle;
            }

        }
    }


    private void updateActivePart() {
        if (queue.size() < 2) {
            queue.add(generetePart());
            queue.add(generetePart());
        }
        activePart = queue.poll();
    }

    private int isOverlapsModel(int x, int[][] model, int len) {

        int result = 0;
        int  offsetY = activePart.getRow();
        if(offsetY==0){
            result = 1;
        }
        boolean flag = false;
            int minH = activePart.getRow() + activePart.getHeigth();
            if (minH > grid_size_y - 1) {
                minH = grid_size_y - 1;
                flag = true;
            }



            int n = model.length;
            for(int i = n-1; i>-1; i--){
                for(int j=0;j<n;j++){
                    if(model[i][j]==1) {
                        int indexX = x + j;
                        int indexY = activePart.getRow() + (n-1-i);

                        if(indexX<grid_size_x){
                            if(indexY<grid_size_y){
                                if(grid[indexY][indexX]!=null){
                                    result = -1;
                                    return result;
                                }
                                if(indexY>0){
                                    if(grid[indexY-1][indexX]!=null){
                                        result=1;
                                        if(flag) {
                                            isGameOver = true;
                                        }
                                    }
                                }
                            }else{
                                if(indexY-1<grid_size_y){
                                    if(grid[indexY-1][indexX]!=null){
                                        result=1;
                                        if(flag) {
                                            isGameOver = true;
                                        }
                                    }
                                }
                            }
                        }else {
                            result = -1;

                            return result;
                        }
                    }
                }
            }

        return result;
    }

    private void debugPrint(int x, int[][] model, int len, int offsetY, int minH) {
        System.out.println("________");
        char cym=0;
        for(int i = 0; i < grid_size_y; i++){
            for(int j=0;j<grid_size_x;j++){
                if(j>=x&&j <= x + len&&i>=offsetY&&i<=minH){
                    if (model[(i - offsetY)][j - x] == 1){
                        if (grid[i][j] != null) {
                            cym='@';
                        }else{
                            cym='*';
                        }
                    }else{
                        cym='-';
                    }
                }else{
                    if (grid[i][j] != null) {
                        cym='#';
                    }else{
                        cym='-';
                    }
                }
                System.out.print(cym);
            }
            System.out.println();
        }
        System.out.println("________");
    }

    private void inspectGrid() {
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

    private void importGrid() {
        if(!isGameOver) {
            for (Block block : activePart.extractBlocks()) {
                int y = block.getPosY() / BLOCK_SIZE_Y;
                int x = block.getPosX() / BLOCK_SIZE_X;
                grid[y][x] = block;
                ground[x] = Math.max(ground[x], y + 1);
            }
        }
    }

    public void resize(int w,int h){
        activePart.resize(w,h);
        for (int i=0;i<grid_size_y;i++) {
            for (int j=0;j<grid_size_x;j++){
                if(grid[i][j]!=null){
                    grid[i][j].resize(w,h);
                }
            }
        }
    }


    private Part generetePart() {

        try {
            return prefabs[random.nextInt(prefabs.length)].clone(SCREAN_SIZE_X/2, SCREAN_SIZE_Y);
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

    public int getGrid_size_x() {
        return grid_size_x;
    }

    public int getGrid_size_y() {
        return grid_size_y;
    }
}