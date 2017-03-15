package com.daniel.game.model;

import com.daniel.game.model.Maze;
import com.daniel.game.model.Pacman;

import java.util.ArrayList;
import java.util.Iterator;

import com.daniel.game.model.GameElement;

public class World implements Iterable<GameElement>{

	private Maze laby; 
	private Pacman pac;
	
	public World(){
	}
	
	public int getHeight(){
		return laby.getHeight() ;
	}
	
	public int getWidth(){
		return laby.getWidth();
	}
	
	public Maze getMaze(){
		return laby;
	}
	
	public Pacman getPacman(){
		return pac;
	}

	public void setMaze(Maze laby) {
		this.laby = laby;
	}
	
	public void setPacman(Pacman pac) {
		this.pac = pac;
	}

	public Iterator<GameElement> iterator(){
		ArrayList<GameElement> l = new ArrayList<GameElement>();
		l.add(getPacman());
		for(GameElement ge : laby){
			l.add(ge);
		}
		return l.iterator();
	}
}
