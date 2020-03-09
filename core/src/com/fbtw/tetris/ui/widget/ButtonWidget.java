package com.fbtw.tetris.ui.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.objects.Part;
import com.fbtw.tetris.ui.Disengageable;
import com.fbtw.tetris.ui.Selecteble;
import com.fbtw.tetris.utils.Expression;
import com.fbtw.tetris.utils.PrefabLoader;


public class ButtonWidget implements Widget, Selecteble, Disengageable {

	private Part shape;
	private String shapeName;

	private int x;
	private int y;
	private int width;
	private int heigth;

	private Button root;
	private TextField txt;

	private boolean isEnable;

	private Color color;

	private Expression onClick;

	public ButtonWidget(String name,String shapeName, int x, int y) {
		this.x = x;
		this.y = y;
		this.shapeName = shapeName;

		isEnable = true;

		PrefabLoader loader = new PrefabLoader(shapeName);

		try {
			this.shape = loader.getPrefabs()[0];
		} catch (Exception e) {
			e.printStackTrace();
		}

		root = new Button();
		//todo:естественное задания ширины


		width = MainGame.BLOCK_SIZE_X*shape.getN();
		heigth = MainGame.BLOCK_SIZE_Y;
		root.setSize(width,heigth);
		txt = new TextField(name,x,0,y,0);



		txt.setAlign(Align.bottom);
		txt.setOffsetX(MainGame.BLOCK_SIZE_X*shape.getN()/2-txt.getWidth()/2);
		txt.setColor(Color.BLACK);
		txt.setOffsetY(MainGame.BLOCK_SIZE_Y*shape.getHeigth()/2-txt.getHeigth()/4);
		setPosition(x,y);

		color = shape.getColour();

		onClick = new Expression() {
			@Override
			public void call(Object... params) {
				int a = 0;
			}
		};

		updateOnClick();





	}



	@Override
	public void setDisable(boolean disable) {
		isEnable=!disable;
	}

	@Override
	public void onSelect() {
		shape.setColour(Color.WHITE);
	}

	@Override
	public void onDeSelect() {
		setColor(color);
	}

	@Override
	public void push() {
		onClick.call();
	}

	public void setOnClick(Expression onClick){
		this.onClick = onClick;
	}

	public void updateOnClick(){
		root.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				push();
				return true;
			}

		});

	}

	@Override
	public void render(SpriteBatch batch) {
		//root.draw(batch,100);
		if(isEnable) {
			shape.render(batch);
			txt.render(batch);
		}
	}

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;


		shape.setPosition(x,y);
		txt.setPosition(x,y);
		root.setPosition(x,y);
	}

	@Override
	public void setFontSize(float fontSize) {
		txt.setFontSize(fontSize);
		txt.setAlign(Align.center);
		txt.setOffsetX(MainGame.BLOCK_SIZE_X*shape.getN()/2-txt.getWidth()/2);
	}

	@Override
	public void setColor(Color colour) {
		this.color = colour;
		shape.setColour(colour);
	}

	@Override
	public Widget clone() {
		return new ButtonWidget(txt.getTextString(),shapeName,x,y);
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
		return width;
	}

	@Override
	public int getHeigth() {
		return heigth;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void resize(int width, int height) {

	}

	public Stage getStage(){
		return root.getStage();
	}

	public Button getButton(){
		return root;
	}

	public String getText(){
		return txt.getTextString();
	}

	public int getN(){
		return shape.getN();
	}
}
