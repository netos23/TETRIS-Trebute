package com.fbtw.tetris.utils;

import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.objects.HigthScore;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;

public class HighScoreManagerTest {


	@Test
	public void testSort(){
		HighScoreManager.initHigthScoreManager();

		HighScoreManager.tryAddScores("nam",3);
		HighScoreManager.tryAddScores("nam",2);
		ArrayList<Integer> integers = new ArrayList<>();


		for (HigthScore s: HighScoreManager.getScoresTable()){
			integers.add(s.getScore());
		}

		ArrayList<Integer> expected = new ArrayList<>();

		expected.add(2);
		expected.add(3);

		for(int i=0;i<integers.size();i++){
			Assert.assertEquals(expected.get(i),integers.get(i));


		}

		HighScoreManager.dispose();
	}

	@Test
	public void testTryAdd(){
		HighScoreManager.initHigthScoreManager();
		ArrayList<Integer> integers = new ArrayList<>();
		for(int i=0;i<10;i++){
			HighScoreManager.tryAddScores("yu",i);
			if(i>0) integers.add(i);
		}

		integers.add(9);

		HighScoreManager.tryAddScores("yu",9);


		ArrayList<Integer> exp = new ArrayList<>();
		for (HigthScore s :
				HighScoreManager.getScoresTable()) {
			exp.add(s.getScore());

		}


		for(int i=0;i<integers.size();i++){
			Assert.assertEquals(integers.get(i),exp.get(i));

		}
		HighScoreManager.dispose();

	}

	@Test
	public void testSerialization(){
		MainGame game = new MainGame(100,100,PlatformsVariants.PC_WIN);
		HighScoreManager.initHigthScoreManager();

		for(int i=0;i<10;i++){
			HighScoreManager.tryAddScores("yu",i);
		}
		ArrayList<HigthScore> expected = HighScoreManager.getScoresTable();
		HighScoreManager.printScores();
		HighScoreManager.loadScores();
		ArrayList<HigthScore> curent = HighScoreManager.getScoresTable();

		for(int i=0;i<expected.size();i++){
			try {
				HigthScore exp = expected.get(i);
				HigthScore act = curent.get(i);

				Assert.assertEquals(exp.getName(),act.getName());
				Assert.assertEquals(exp.getScore(),act.getScore());

			}catch (ArrayIndexOutOfBoundsException ex){
				ex.printStackTrace();
			}
		}
		HighScoreManager.dispose();

	}

}