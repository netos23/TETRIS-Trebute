package com.fbtw.tetris.screans;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;


import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.utils.GameScreanManeger;
import com.fbtw.tetris.utils.TextureManager;

public class GameScrean implements Screen {
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private GameScreanManeger maneger;

    public GameScrean(Game game) {
        super();
        this.game = game;
        TextureManager.init();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        camera.setToOrtho(false,300,600);

        maneger = new GameScreanManeger();


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        update(delta);

        batch.begin();
        maneger.render(batch);
        batch.end();
    }

    public void update(float delta){
        maneger.update(delta);
    }


    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width,height);
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
