package com.fbtw.tetris.ui.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.fbtw.tetris.ui.Disengageable;
import com.fbtw.tetris.utils.HighScoreManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TextTable<T> implements Disengageable {

	private ArrayList<T> information;
	private ArrayList<Label> rows;
	private ArrayList<Label> enumeration;

	private int offsetY;
	private int offsetX;

	private int x;
	private int y;
	private float width;
	private int heigth;

	private Color color;


	private boolean isEnumerable;
	private boolean isEnable;
	private int n;

	public TextTable(ArrayList<T> information, int x, int y, boolean isEnumerable) {
		this.information = information;
		this.x = x;
		this.y = y;
		this.isEnumerable = isEnumerable;
		isEnable = true;

		offsetX = 10;
		offsetY = 20;

		n = information.size();
		rows = new ArrayList<>();
		enumeration = new ArrayList<>();

		Skin skin = new Skin(Gdx.files.internal("font/skin.json"));

		//todo: системные разделители

		int i = information.size();
		for (T element : information) {
			Label row = new Label(element.toString(), skin.get("default", Label.LabelStyle.class));
			row.setWidth(450);
			row.setWrap(true);
			row.setAlignment(Align.left);
			row.setFontScale(0.5f);
			row.setColor(Color.BLACK);
			row.setWrap(true);
			row.setWidth(250);
			rows.add(row);

			Label index = new Label(i + "", skin.get("default", Label.LabelStyle.class));
			index.setAlignment(Align.left);
			index.setFontScale(0.5f);
			index.setColor(Color.BLACK);
			enumeration.add(index);
			i--;

		}

		setPosition(x, y);
		width = enumeration.get(0).getWidth() + rows.get(0).getWidth()-offsetX*9;

	}

	public void render(SpriteBatch batch){
		if(isEnable){

			for(int i=0;i< n;i++){
				rows.get(i).draw(batch,100);
				if(isEnumerable){
					enumeration.get(i).draw(batch,100);
				}

			}
		}
	}


	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;


		for (int i=0;i<n;i++){
			rows.get(i).setPosition(x+offsetX*5,y+offsetY*i);
			enumeration.get(i).setPosition(x,y+offsetY*i);
		}

	}

	@Override
	public void setDisable(boolean disable) {
		isEnable=!isEnable;
	}

	public void setColor(Color color) {
		this.color = color;

		for (int i = 0; i < rows.size(); i++) {

			rows.get(i).setColor(color);
			enumeration.get(i).setColor(color);
		}
	}

	public int getWidth() {
		return (int)width;
	}
}
