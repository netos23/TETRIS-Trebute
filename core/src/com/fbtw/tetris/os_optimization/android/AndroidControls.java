package com.fbtw.tetris.os_optimization.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.ui.widget.ButtonWidget;
import com.fbtw.tetris.ui.widget.TextField;
import com.fbtw.tetris.utils.Expression;

public class AndroidControls {

    private ButtonWidget pause;
    private ButtonWidget menu;

    private ButtonWidget rotate_R;
    private ButtonWidget rotate_L;
    private ButtonWidget left;
    private ButtonWidget rigth;


    private Stage inputController;
    private boolean isPause;

    // private TextField debugPrint;

    public AndroidControls(float zoom, int w, int h){
        float multiplierX = w/1794f;
        float multiplierY = h/1080f;

        isPause=false;

        inputController = new Stage();

        /*debugPrint = new TextField("DEBUG",-100,0,300,0);
        debugPrint.setFontSize(2f);
        debugPrint.setColor(Color.WHITE);*/

        pause = new ButtonWidget(AndroidControlsSourses.PAUSE,"android_controls_1.txt", -125,500);
        pause.setFontSize(1.5f);
        pause.getButton().setPosition(235*multiplierX,905*multiplierY);
        pause.getButton().setSize(pause.getWidth()/zoom, MainGame.BLOCK_SIZE_Y*pause.getN() /zoom);
        /*setPause(new Expression() {
            @Override
            public void call(Object... params) {
                System.out.println("p");
                debugPrint.setTextString(pause.getText());
            }
        });*/


        menu = new ButtonWidget(AndroidControlsSourses.MENU,"android_controls_1.txt", 520,500);
        menu.setFontSize(2f);
        menu.getButton().setPosition(1400*multiplierX,905*multiplierY);
        menu.getButton().setSize(menu.getWidth()/zoom,MainGame.BLOCK_SIZE_Y*menu.getN()/zoom);
        /*setMenu(new Expression() {
            @Override
            public void call(Object... params) {
                debugPrint.setTextString(menu.getText());
            }
        });*/

        rotate_L = new ButtonWidget(AndroidControlsSourses.DOWN,"android_controls_1.txt", -125,200);
        rotate_L.setFontSize(2f);
        rotate_L.getButton().setPosition(235*multiplierX,360*multiplierY);
        rotate_L.getButton().setSize(rotate_L.getWidth()/zoom,MainGame.BLOCK_SIZE_Y*rotate_L.getN() /zoom);

        rotate_R = new ButtonWidget(AndroidControlsSourses.ROTATE,"android_controls_1.txt", 520,200);
        rotate_R.setFontSize(2f);
        rotate_R.getButton().setPosition(1400*multiplierX,360*multiplierY);
        rotate_R.getButton().setSize(rotate_R.getWidth()/zoom,MainGame.BLOCK_SIZE_Y*rotate_R.getN() /zoom);


       /* setRotate(new Expression() {
            @Override
            public void call(Object... params) {
                debugPrint.setTextString(rotate_L.getText());
            }
        });*/

        left = new ButtonWidget(AndroidControlsSourses.LEFT,"android_controls_2.txt", -150,70);
        left.setFontSize(3f);
        left.getButton().setPosition(190*multiplierX,120*multiplierY);
        left.getButton().setSize(left.getWidth()/zoom,MainGame.BLOCK_SIZE_Y*left.getN() /zoom);
        /*setLeft(new Expression() {
            @Override
            public void call(Object... params) {
                debugPrint.setTextString(left.getText());
            }
        });*/


        rigth = new ButtonWidget(AndroidControlsSourses.RIGTH,"android_controls_2.txt", 520,70);
        rigth.setFontSize(3f);
        rigth.getButton().setPosition(1400*multiplierX,120*multiplierY);
        rigth.getButton().setSize(rigth.getWidth()/zoom,MainGame.BLOCK_SIZE_Y*rigth.getN()/zoom);

        /*setRigth(new Expression() {
            @Override
            public void call(Object... params) {
                debugPrint.setTextString(rigth.getText());
            }
        });*/


        //debug screen point poser
        /*inputController.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println(x+" "+y);
                return true;
            }
        });*/

        inputController.addActor(pause.getButton());
        inputController.addActor(menu.getButton());
        inputController.addActor(rotate_R.getButton());
        inputController.addActor(rotate_L.getButton());
        inputController.addActor(left.getButton());
        inputController.addActor(rigth.getButton());

        Gdx.input.setInputProcessor(inputController);
    }

    public void render(SpriteBatch batch){
        pause.render(batch);
        menu.render(batch);
        rotate_L.render(batch);
        rotate_R.render(batch);
        left.render(batch);
        rigth.render(batch);

        //debugPrint.render(batch);

    }

    public void setPause(Expression expression){
        pause.setOnClick(expression);

    }

    public void setMenu(Expression expression){
        menu.setOnClick(expression);

    }

    public void setRotate(Expression expression){
        rotate_R.setOnClick(expression);
    }

    public void setDown(Expression expression){
        rotate_L.setOnClick(expression);
    }

    public void setLeft(Expression expression){
        left.setOnClick(expression);

    }

    public void setRigth(Expression expression){
        rigth.setOnClick(expression);

    }




    public Stage getInputController() {
        return inputController;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public ButtonWidget getDownBtn() {
        return rotate_L;
    }
}
