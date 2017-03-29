package com.daniel.game.model;

import com.daniel.game.model.Maze;
import com.daniel.game.model.Pacman;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.daniel.game.model.GameElement;

public class World implements Iterable<GameElement>{

	private Maze laby; 
	private Pacman pac;
	private Fantomes fantome1;
	private Fantomes fantome2;
	private Fantomes fantome3;
	public long timeStartFantomeEat;
	public long timeStart = System.currentTimeMillis();
	
	public World(){
		createWorld();
	}
	
	public int getHeight(){
		return laby.getHeight() ;
	}
	
	public int getWidth(){
		return laby.getWidth();
	}

	public Maze getMaze() {
		return laby;
	}

	public Pacman getPacman() {
		return pac;
	}

	public Fantomes getFantome1() {
		return fantome1;
	}
	
	public Fantomes getFantome2() {
		return fantome2;
	}
	
	public Fantomes getFantome3() {
		return fantome3;
	}

	public Iterator<GameElement> iterator(){
		ArrayList<GameElement> l = new ArrayList<GameElement>();
				
		// Blocs laby
		for(GameElement ge : laby){
			l.add(ge);
		}
		
		// Pac-Gom
		for(GameElement ge : laby.getGommes())
			l.add(ge);
		
		//Pac Man
		l.add(pac);
		
		//Fantomes
		l.add(fantome1);
		l.add(fantome2);
		l.add(fantome3);
		
		return l.iterator();
	}
	
	public void createWorld(){
		laby = new Maze(this);
		fantome1 = new Fantome1(new Vector2((30-11)*16,14*16),this);
		fantome2 = new Fantome2(new Vector2((30-14)*16,14*16),this);
		fantome3 = new Fantome3(new Vector2((30-14)*16,13*16),this);
		pac = new Pacman(new Vector2((30-23)*16,14*16), this);
		laby.loadDemoLevel();
	}

	public void fantomeMangeable() {
		timeStartFantomeEat = System.currentTimeMillis();
		if(fantome1.estSortiMaison)
			fantome1.estMangable = true;
		if(fantome2.estSortiMaison)
			fantome2.estMangable = true;
		if(fantome3.estSortiMaison)
			fantome3.estMangable = true;
	}
	
	public void testTempsFantomeMange(){
		long time = System.currentTimeMillis();
		if(time - timeStartFantomeEat >= 10000){
			fantome1.estMangable = false;
			fantome1.haveBeenEaten = false;
			fantome2.estMangable = false;
			fantome2.haveBeenEaten = false;
			fantome3.estMangable = false;
			fantome2.haveBeenEaten = false;
		}
		timeStart = time;
	}
	
	public boolean getTempsFinFantomeFaible(){
		return System.currentTimeMillis() - timeStartFantomeEat >= 8000;
	}
}
