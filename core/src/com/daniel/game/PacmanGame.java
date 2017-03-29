package com.daniel.game;

import com.badlogic.gdx.Game;
import com.daniel.game.screens.LaunchScreen;

public class PacmanGame extends Game {
		
	public void create(){
		setScreen(new LaunchScreen());
	}
}
