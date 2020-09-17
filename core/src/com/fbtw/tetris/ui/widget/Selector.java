package com.fbtw.tetris.ui.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Stage;


import com.badlogic.gdx.utils.Disposable;
import com.fbtw.tetris.ui.Disengageable;
import com.fbtw.tetris.utils.Expression;


import java.util.ArrayList;

public class Selector implements Widget, Disengageable {
	private int x;
	private int y;

	private int offsetY;

	private int heigth;
	private int width;

	private Stage controller;
	private ArrayList<ButtonWidget> buttons;

	String shapeName;

	private boolean isEnable;

	private int current;

	private Sound beepSound;

	public Selector(String shapeName, int x, int y) {
		this.x = x;
		this.y = y;

		isEnable = true;

		offsetY = 20;

		beepSound = Gdx.audio.newSound(Gdx.files.internal("sound/beep.mp3"));
		beepSound.setVolume(0,0.9f);

		buttons = new ArrayList<>();
		controller = new Stage(/*new FillViewport(1920,1080)*/);
		controller.getViewport().apply();
		this.shapeName = shapeName;

		current = -1;

	}

	public void addButton(String name){

		buttons.add( new ButtonWidget(name,shapeName,0,0));
		setPosition(x,y);
	}
	public void addButton(ButtonWidget buttonWidget){

		buttons.add(buttonWidget);

	}


	public ButtonWidget getButton(int buttonId){
		return buttons.get(buttonId);
	}

	public void setOnClickListner(int buttonId, Expression onClick){

		buttons.get(buttonId).setOnClick(onClick);

	}

	@Override
	public void render(SpriteBatch batch) {
		if (isEnable){
			if(buttons.size()!=0){
				for(ButtonWidget button : buttons){
					button.render(batch);
				}
			}
		}
	}

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		if(buttons.size()!=0){
			int dY = offsetY+buttons.get(0).getHeigth();
			for(int i = 0; i < buttons.size(); i++){
				buttons.get(i).setPosition(x,y+dY*i);
			}
		}
	}

	@Override
	public void setFontSize(float fontSize) {
		if(buttons.size()!=0){
			for(int i = 0; i < buttons.size(); i++){
				buttons.get(i).setFontSize(fontSize);
			}
		}
	}

	@Override
	public void setColor(Color colour) {

	}

	@Override
	public Widget clone() {

		//todo: переделать клонирование
	///	return new Selector(x,y);
		return null;
	}

	@Override
	public int getPosX() {
		return x;
	}

	@Override
	public int getPosY() {
		return y;
	}

	@Override
	public int getWidth() {
		if(buttons.size()!=0){
			return (buttons.get(0).getWidth());
		}else {
			return 0;
		}
	}

	@Override
	public int getHeigth() {
		if(buttons.size()!=0){
			return (buttons.get(0).getHeigth()+offsetY)*buttons.size();
		}else {
			return 0;
		}
	}

	@Override
	public void resize(int width, int height) {
		//controller.dispose();
		controller = new Stage();


		for(ButtonWidget widget : buttons){
			widget.updateOnClick();
			controller.addActor(widget.getButton());
		}
		controller.act();
	}

	@Override
	public void setDisable(boolean disable) {
		isEnable = !disable;
	}

	public Stage getController() {
		return controller;
	}

	public void commit(){
		for(ButtonWidget buttonWidget:buttons){
			controller.addActor(buttonWidget.getButton());
		}



	}

	public void dispose() {
		controller.dispose();
		beepSound.dispose();
	}

	private void handleInput(){
		/*if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)||Gdx.input.isKeyJustPressed(Input.Keys.UP)){
			current = 0;
		}
*/
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
				beepSound.play();
				chengeItem(true);

		}else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
				beepSound.play();
				chengeItem(false);

		}else if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
			if(current!=-1){
				buttons.get(current).push();
			}else{
				buttons.get(buttons.size()-1).push();
			}
			beepSound.play();
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)||Gdx.input.justTouched()){
			if(current!=-1) {
				buttons.get(current).onDeSelect();
				current = -1;
			}
		}
	}

	private void chengeItem(boolean isForward){
		if(current!=-1) {


			buttons.get(current).onDeSelect();
			current = (isForward) ? ++current : --current;

			if(current>buttons.size()-1) {
				current=0;
			}

			if(current<0) {
				current = buttons.size()-1;
			}

			buttons.get(current).onSelect();
		}else{
			buttons.get(0).onSelect();
			current=0;
		}
	}

	public void setButtonsPosition(int x,int y){
		if(buttons.size()!=0){
			int dY = offsetY+buttons.get(0).getHeigth();
			for(int i = 0; i < buttons.size(); i++){
				buttons.get(i).getButton().setPosition(x,y+dY*i);
			}
		}
	}


	public void update(){
		handleInput();
	}


	public int getButtonsCount(){
	    return buttons.size();
    }

}
