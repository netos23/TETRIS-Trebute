package com.fbtw.tetris.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.objects.Part;
import com.fbtw.tetris.utils.TextSourses;

public class ScoreUI extends UI {

	private final int OffsetX;
	private final int OffsetY;
	private final int padding;
	private int score=0;
	private int scoreLen = 6;
	private Part nextPart;
	private int speed = 1;
	private boolean isHi = false;

	private TextField appName;
	private TextField hiIndicator;
	private TextField scoreName;
	private TextField scoreText;
	private TextField speedName;
	private TextField speedText;
	private TextField pauseIndicator;
	private int nextPartPadding;

	public ScoreUI(int x, int y, int width, int height) {
		super(x, y, width, height);
		isEneble = true;

		OffsetX = 25;
		OffsetY = -20;
		padding = -10;
		nextPartPadding = 15;

		appName = new TextField(TextSourses.TETRIS_NAME,x, OffsetX, y ,height+ OffsetY);
		appName.setColor(Color.BLACK);
		appName.setWidth(100);

		hiIndicator = new TextField(TextSourses.HI_NAME,x,OffsetX,y , height+ 2*OffsetY+2*padding);
		hiIndicator.setColor(Color.RED);
		hiIndicator.setWidth(15);
		hiIndicator.setDisable(true);

		scoreName = new TextField(TextSourses.SCORE_NAME,x,2*OffsetX,y , height+ 2*OffsetY+2*padding);
		scoreName.setColor(Color.BLACK);
		scoreName.setWidth(50);

		scoreText = new TextField(getScore(),x,OffsetX,y, height+ 3*OffsetY+2*padding);
		scoreText.setColor(Color.BLACK);
		scoreText.setWidth(100);

		speedName = new TextField(TextSourses.SPEED_NAME,x,OffsetX,y, height+ 4*OffsetY+3*padding);
		speedName.setColor(Color.BLACK);
		speedName.setWidth(100);

		speedText = new TextField(speed+"",x,OffsetX,y , height+ 5*OffsetY+3*padding);
		speedText.setColor(Color.BLACK);
		speedText.setWidth(100);

		pauseIndicator = new TextField(TextSourses.PAUSE_TEXT,x,OffsetX,y,height+ 6*OffsetY+3*padding);
		pauseIndicator.setColor(Color.RED);
		pauseIndicator.setWidth(100);
		pauseIndicator.setDisable(true);
	}

	public void setNextPart(Part nextPart) {
		try {
			float l = nextPart.getLength();
			if(l==2){
				l=1.5f;
			}else if(l==0){
				l=0.5f;
			}
			int temp = (int) (width / 2 - l * MainGame.BLOCK_SIZE_X);
			this.nextPart = nextPart.clone(x + temp,y+ nextPartPadding);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setBackGround(Texture texture, Color color) {
		setBackGround(texture);
		backGround.setColor(color);

	}

	@Override
	public void setBackGround(Texture texture) {
		backGround = new Sprite(texture);
		backGround.setPosition(x,y);
		backGround.setSize(width,height);
	}

	@Override
	public void render(SpriteBatch sb) {
		if(isEneble){

			backGround.draw(sb);

			appName.render(sb);
			hiIndicator.render(sb);
			scoreName.render(sb);
			scoreText.render(sb);
			speedName.render(sb);
			speedText.render(sb);
			pauseIndicator.render(sb);

			if(nextPart!=null){
				nextPart.render(sb);
			}
		}
	}

	public void setSpeed(int speed) {
		this.speed = speed;
		speedText.setTextString(speed+"");
	}

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;

		appName.setPosition(x, y);
		hiIndicator.setPosition(x,y);
		scoreName.setPosition(x,y);
		scoreText.setPosition(x,y);
		speedName.setPosition(x,y);
		speedText.setPosition(x,y);
		pauseIndicator.setPosition(x,y);

		if(backGround!=null){
			backGround.setPosition(x,y);
		}

		if(nextPart!=null){
			float l = nextPart.getLength();
			if(l==2){
				l=1.5f;
			}else if(l==0){
				l=0.5f;
			}
			int temp = (int) (width / 2 - l * MainGame.BLOCK_SIZE_X);
			nextPart.setPosition(x+ temp,y+ nextPartPadding);
		}
	}

	@Override
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void setDisable(boolean disable) {
		isEneble = !disable;
	}

	@Override
	public void resize(int width, int height) {

	}


	public String getScore() {
		double count =  Math.floor(Math.log10(score))+1;

		count = (count < 0) ? 1 : count;
		double additional = scoreLen - count;

		StringBuilder res = new StringBuilder();

		for (int i = 0; i < additional; i++) {
			res.append('0');
		}

		res.append(score);

		return res.toString();
	}

	public void setHI(){
		isHi = true;
		hiIndicator.setDisable(false);
	}

	public void setScore(int score) {
		this.score = score;
		scoreText.setTextString(getScore());
	}

	public void setPause(boolean isPause) {
		pauseIndicator.setDisable(!isPause);
	}
}
