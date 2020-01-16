package com.fbtw.tetris.screans;

import com.badlogic.gdx.Screen;


import com.fbtw.tetris.utils.TextureManager;

public class GameScrean implements Screen {


    public GameScrean() {
        TextureManager.init();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        TextureManager.dispose();
    }
}
