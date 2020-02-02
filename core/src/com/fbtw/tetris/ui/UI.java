package com.fbtw.tetris.ui;

public abstract class UI {
    private boolean isVisible;

    public abstract void setVisible(boolean isVisible);
    public abstract void resize(int width, int height);
}
