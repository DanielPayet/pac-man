package com.daniel.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.daniel.game.screens.GameScreen;

public class PacmanGame extends ApplicationAdapter {
	
	GameScreen gs;
	
	public void create(){
		gs = new GameScreen();
		gs.create();
	}
	
	public void render(){
		gs.render();
	}
	
	public void dispose(){
		gs.dispose();
	}
}
