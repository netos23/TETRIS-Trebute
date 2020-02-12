package com.fbtw.tetris.ui.widget;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FleshingText extends TextField {
	private int flashTime;
	private int curTime;

	private boolean stopDisable;

	public FleshingText(String textString, int x, int localX, int y, int localY, int flashTime) {
		super(textString, x, localX, y, localY);
		this.flashTime = flashTime;
		curTime=0;
		stopDisable=true;
	}

	@Override
	public void render(SpriteBatch batch) {
		if(stopDisable) {
			flesh();
			super.render(batch);
		}
	}

	private void flesh(){
		if(curTime>=flashTime){
			isEneble = !isEneble;
			curTime = 0;
		}else{
			curTime++;
		}
	}

	@Override
	public void setDisable(boolean disable) {
		stopDisable = !disable;
	}
}
