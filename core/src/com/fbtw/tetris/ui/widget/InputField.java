package com.fbtw.tetris.ui.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.ui.Disengageable;
import com.fbtw.tetris.utils.PlatformsVariants;

public class InputField implements Widget,Disengageable, Input.TextInputListener {

	private Label[] symbols;
	private int cursor;
	private int length;

	private int timeCycle;
	private int curTime;

	private int offsetX;
	private int symbolWidth;
	private int x;
	private int y;

	private int width;
	private int heigth;

	private boolean isVisibleCursor = true;

	private boolean isEnable = true;

	private static char SYMBOL_DEFOULT = '_';

	private boolean isTyping, isCommit;

	public InputField(int len, int x, int y, int offsetX, String androidMSG) {
		this.x = x;
		this.y = y;
		this.offsetX = offsetX;

		this.length = len;

		timeCycle = 10;
		curTime = 0;

		symbolWidth = 20;


		symbols = new Label[len];
		for (int i = 0; i < symbols.length; i++) {

			Skin skin = new Skin(Gdx.files.internal("font/skin.json"));
			//todo: системные разделители
			symbols[i] = new Label("" + SYMBOL_DEFOULT, skin.get("default", Label.LabelStyle.class));
			symbols[i].setColor(Color.WHITE);
			symbols[i].setFontScale(0.5f);
			symbols[i].setAlignment(Align.left);
		}
		setPosition(x, y);

		if (MainGame.platform == PlatformsVariants.ANDROID) {
			Stage inputController = new Stage();
			isCommit = false;
			InputField listner = this;
			inputController.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					float mX = Gdx.graphics.getWidth() / 1794f;
					float mY = Gdx.graphics.getHeight() / 1080f;

					if (y > 360 * mY && y < 600 * mY && x > 700 * mX && x < 1100 * mX) {
						Gdx.input.getTextInput(listner,androidMSG,"","");
						isTyping = true;
					}else{
						isCommit = true;
					}

					return true;
				}
			});

			Gdx.input.setInputProcessor(inputController);

		}


	}


	private boolean handleInput() {
		boolean result = false;

		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			symbols[cursor].setText("A");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
			symbols[cursor].setText("B");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
			symbols[cursor].setText("C");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			symbols[cursor].setText("D");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			symbols[cursor].setText("E");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
			symbols[cursor].setText("F");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
			symbols[cursor].setText("G");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
			symbols[cursor].setText("H");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
			symbols[cursor].setText("I");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
			symbols[cursor].setText("J");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
			symbols[cursor].setText("K");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
			symbols[cursor].setText("L");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
			symbols[cursor].setText("M");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
			symbols[cursor].setText("N");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
			symbols[cursor].setText("O");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
			symbols[cursor].setText("P");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			symbols[cursor].setText("Q");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			symbols[cursor].setText("R");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			symbols[cursor].setText("S");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
			symbols[cursor].setText("T");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
			symbols[cursor].setText("U");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.V)) {
			symbols[cursor].setText("V");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
			symbols[cursor].setText("W");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
			symbols[cursor].setText("X");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
			symbols[cursor].setText("Y");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
			symbols[cursor].setText("Z");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
			symbols[cursor].setText("0");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			symbols[cursor].setText("1");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
			symbols[cursor].setText("2");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
			symbols[cursor].setText("3");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
			symbols[cursor].setText("4");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
			symbols[cursor].setText("5");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
			symbols[cursor].setText("6");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) {
			symbols[cursor].setText("7");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) {
			symbols[cursor].setText("8");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)) {
			symbols[cursor].setText("9");
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			previous();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			next();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			result = true;

		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
			if (cursor != length - 1) {
				previous();

				symbols[cursor].setText(SYMBOL_DEFOULT + "");
			} else {
				if (symbols[cursor].getText().toString().equals("" + SYMBOL_DEFOULT)) {
					previous();
				}
				symbols[cursor].setText(SYMBOL_DEFOULT + "");

			}
		}

		return result||isCommit;
	}

	public boolean update() {
		if (isEnable) {
			if (curTime > timeCycle) {
				curTime = 0;

				isVisibleCursor = !isVisibleCursor;
			}
			curTime++;

			return handleInput();
		} else {
			return false;
		}
	}

	private void next() {

		if (cursor < length - 1) {
			isVisibleCursor = true;
			cursor++;
		}
	}

	private void previous() {
		if (cursor > 0) {
			isVisibleCursor = true;
			cursor--;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		if (isEnable) {
			for (int i = 0; i < symbols.length; i++) {
				Label s = symbols[i];
				if (i != cursor || (isVisibleCursor)) {
					s.draw(batch, 100);
				}
			}
		}
	}

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;

		for (int i = 0; i < symbols.length; i++) {
			symbols[i].setPosition(x + i * (offsetX + symbolWidth), y);
			//System.out.println(x+i*(offsetX+symbolWidth)+" "+y);
		}
	}

	@Override
	public void setFontSize(float fontSize) {
		for (Label l : symbols) {
			l.setFontScale(fontSize);
		}
	}

	@Override
	public void setColor(Color color) {
		for (Label l : symbols) {
			l.setColor(color);
		}
	}

	@Override
	public Widget clone() {
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

	//todo: переопределить
	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeigth() {
		return 0;
	}

	@Override
	public void dispose() {

	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (Label s : symbols) {
			String symbol = s.getText().toString();
			if (!symbol.equals("" + SYMBOL_DEFOULT)) {
				builder.append(symbol);
			}
		}
		String result = builder.toString().trim();
		return (result.equals("")) ? "UNKNOWN" : result;
	}


	private void trim() {
		StringBuilder builder = new StringBuilder();

		for (Label s : symbols) {
			String symbol = s.getText().toString();
			if (!symbol.equals("" + SYMBOL_DEFOULT)) {
				builder.append(symbol);
			}
		}
		String temp = builder.toString();

		for (int i = 0; i < symbols.length; i++) {
			if (i < temp.length()) {
				symbols[i].setText("" + temp.charAt(i));
			} else {
				symbols[i].setText("" + SYMBOL_DEFOULT);
			}
		}
	}

	@Override
	public void setDisable(boolean disable) {
		isEnable = !disable;
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void input(String text) {
		for (int i = 0; i < symbols.length; i++) {
			if (i < text.length()) {
				symbols[i].setText(text.charAt(i) + "");
			} else {
				symbols[i].setText(SYMBOL_DEFOULT + "");
			}
		}

		isTyping = false;
		isCommit = true;
	}

	@Override
	public void canceled() {
		isTyping = false;
	}
}
