package com.fbtw.tetris.utils;

import com.fbtw.tetris.MainGame;
import com.fbtw.tetris.objects.HigthScore;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;



public  class HighScoreManager {

	public static final int HIGTH_SCORE_QUTE = 10;


	private static ArrayList<HigthScore> scoresTable;
	private static Integer registred;
	private static boolean init;


	public static void initHigthScoreManager(){

		scoresTable = new ArrayList<HigthScore>();
		registred = 0;
		init = true;
	}

	public static void loadScores(){

		try {
			File scores = new File(TextSourses.SERIALIZED_SCORES_FILENAME);

			switch (MainGame.platform){
				case PC_MAC:
				case PC_LINUX:
				case PC_WIN:
					FileInputStream in = new FileInputStream(scores);
					ObjectInputStream reader = new ObjectInputStream(in);
					scoresTable = new ArrayList<>();
					registred = (Integer) reader.readObject();
					for(int i=0;i<registred;i++){
						scoresTable.add((HigthScore)reader.readObject());
					}
						reader.close();
						in.close();
					break;
				case ANDROID:

					break;

			}

		} catch (IOException ex){
			ex.printStackTrace();
			scoresTable = new ArrayList<>();
			registred=0;
		} catch (ClassNotFoundException ex){
			ex.printStackTrace();
		}
	}

	public static void printScores(){
		try {
			File scores = new File(TextSourses.SERIALIZED_SCORES_FILENAME);

			switch (MainGame.platform){
				case PC_MAC:
				case PC_LINUX:
				case PC_WIN:
					FileOutputStream out = new FileOutputStream(scores);
					ObjectOutputStream printer = new ObjectOutputStream(out);



					printer.writeObject(registred);

					for (HigthScore score : scoresTable) {
						printer.writeObject(score);
					}

					printer.close();
					out.close();

					break;
				case ANDROID:

					break;

			}

		}catch (IOException ex){
			ex.printStackTrace();
		}
	}


	public static void tryAddScores(String name, int count){
		if(registred<HIGTH_SCORE_QUTE) {
			scoresTable.add(new HigthScore(name, count));
			Collections.sort(scoresTable);
			registred++;
		}else{
			HigthScore lowest = scoresTable.get(0);
			if(lowest.getScore()<count){

				scoresTable.remove(0);
				scoresTable.add(new HigthScore(name,count));
				Collections.sort(scoresTable);

			}
		}

	}

	public static ArrayList<HigthScore> getScoresTable() {
		return scoresTable;
	}

	public static void dispose(){
		scoresTable = null;
		registred = 0;
	}


	public static int getHigh(){
		if(scoresTable.size()>0){
			return scoresTable.get(registred-1).getScore();
		}else {
			return 0;
		}

	}

	public static int getLow(){
		if(scoresTable.size()>HIGTH_SCORE_QUTE-1){
			return scoresTable.get(0).getScore();
		}else {
			return -1;
		}

	}



	public static boolean isInit(){
		return init;
	}
}
