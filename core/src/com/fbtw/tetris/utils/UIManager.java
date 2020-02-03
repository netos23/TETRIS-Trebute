package com.fbtw.tetris.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.ui.ScoreUI;

public class UIManager {
    ScoreUI score;

    public UIManager(){
        score = new ScoreUI(-1000,-1000,150,300);
    }

    public void setScorePosition(int x,int y){
        score.setPosition(x,y);
    }

    public void render(SpriteBatch sb){
        score.render(sb);
    }

    public ScoreUI getScore() {
        return score;
    }
}
