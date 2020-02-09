package com.fbtw.tetris.objects;

import java.io.Serializable;

public class HigthScore  implements Serializable,Comparable<HigthScore> {

	private static final long serialVersionUID = 1L;

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

		//return (score-o.score>=0)? 1:-1;
		return score-o.score;
	}

}
