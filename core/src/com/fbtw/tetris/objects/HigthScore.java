package com.fbtw.tetris.objects;

import java.io.Serializable;

public class HigthScore  implements Serializable,Comparable<HigthScore> {

	private static final long serialVersionUID = 1L;


	private static final int ALL_SYMBOLS = 20;
	private static final char SPLIT_SYMBOL = '.';

	private String name;
	private Integer score;

	public HigthScore(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(HigthScore o) {


		return score-o.score;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);

		int additional = ALL_SYMBOLS - (name.length()+(score+"").length());

		for (int i=0;i<additional;i++){
			builder.append(SPLIT_SYMBOL);
		}
		builder.append(score);

		return builder.toString();
	}
}
