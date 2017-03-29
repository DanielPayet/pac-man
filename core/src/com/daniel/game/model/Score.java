package com.daniel.game.model;

import com.daniel.game.screens.GameScreen;
import com.daniel.game.screens.WinScreen;
 
public class Score {
	
	private static int score;
	private static int nbGomme;
	private static long time;
	private static long timeStart;
	private static Score INSTANCE = null;
	
	private Score(){
		score = 0;
		nbGomme = 0;
		timeStart = System.currentTimeMillis();
		time = 0 ;
	}

	public static Score getInstance(){
		if(INSTANCE == null)
			INSTANCE = new Score();
		return INSTANCE;
	}
	public static void reset(){
		score = 0;
		nbGomme = 0;
		time = 0 ; 
		timeStart = System.currentTimeMillis();
	}
	
	public static void MiseAJourScore(){
		nbGomme++;
		if(nbGomme == 295){
			GameScreen.game.setScreen(new WinScreen());
		}
		time  = System.currentTimeMillis() - timeStart;
		score += 50;
	}
	
	public static int getTimeSeconde(){
		time  = System.currentTimeMillis() - timeStart;
		return (int) (time/1000) % 60  ;
	}
	
	public static int getTimeMinute(){
		return (int) ((time/1000)/60) ;
	}
	
	public static int getScore(){
		return score;
	}
}
