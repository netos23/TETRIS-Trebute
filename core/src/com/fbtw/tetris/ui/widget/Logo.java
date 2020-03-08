package com.fbtw.tetris.ui.widget;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.objects.Part;
import com.fbtw.tetris.ui.Disengageable;
import com.fbtw.tetris.utils.PrefabLoader;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;



public class Logo implements Widget, Disengageable {

	private int x;
	private int y;

	private Part[] parts;
	private int[] offsets;

	private boolean isEnabled;

	public Logo(int x, int y) {
		this.x = x;
		this.y = y;
		parts = new Part[6];

		isEnabled = true;

		PrefabLoader loader =new PrefabLoader("decor.txt");

		Part[] partContainer;

		try {
			partContainer = loader.getPrefabs();

			//набор текста TETRIS
			parts[0] = partContainer[0].clone(-100,-100);  //t
			parts[1] = partContainer[1].clone(-100,-100);  //e
			parts[2] = partContainer[0].clone(-100,-100);  //t
			parts[3] = partContainer[2].clone(-100,-100);  //r
			parts[4] = partContainer[3].clone(-100,-100);  //i
			parts[5] = partContainer[4].clone(-100,-100);  //s


		} catch (Exception e) {
			e.printStackTrace();

		}


		//задание смещений
		offsets = new int[parts.length];
		offsets[0] = 0;
		offsets[1] = MainGame.BLOCK_SIZE_X*4;
		offsets[2] = MainGame.BLOCK_SIZE_X*8;
		offsets[3] = MainGame.BLOCK_SIZE_X*12;
		offsets[4] = MainGame.BLOCK_SIZE_X*16;
		offsets[5] = MainGame.BLOCK_SIZE_X*18;

		setPosition(x,y);

	}

	@Override
	public void render(SpriteBatch batch) {
		if(isEnabled){
			for(Part part : parts){
				part.render(batch);
			}
		}

	}

	@Override
	public void setPosition(int x, int y) {
		for(int i=0;i<parts.length;i++){
			parts[i].setPosition(x+offsets[i],y);
		}
	}

	@Override
	public void setFontSize(float fontSize){
		//throw new NotImplementedException();
	}

	@Override
	public void setColor(Color colour) {
		for (Part part:parts){
			part.setColour(colour);
		}
	}

	@Override
	public Widget clone() {
		return new Logo(x,y);
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
		return MainGame.BLOCK_SIZE_X*21;
	}

	@Override
	public int getHeigth() {
		return MainGame.BLOCK_SIZE_Y*5;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void resize(int width, int height) {
		//throw new NotImplementedException();
	}

	@Override
	public void setDisable(boolean disable) {
		isEnabled = !disable;
	}
}
