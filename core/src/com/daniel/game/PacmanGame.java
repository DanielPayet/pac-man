package com.daniel.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.model.Maze;
import com.daniel.game.model.Pacman;
import com.daniel.game.model.World;
import com.daniel.game.screens.GameScreen;
import com.daniel.game.view.WorldRenderer;

public class PacmanGame extends ApplicationAdapter {
	
	GameScreen gs;
	World world;
	WorldRenderer renderer;
	Maze maze;
	Pacman pacman;
	
	public void create(){
		world = new World();
		renderer = new WorldRenderer(world);
		maze = new Maze(world);
		pacman = new Pacman(new Vector2((30-23)*16,14*16), world);
		gs = new GameScreen(world,renderer);
		world.getMaze().loadDemoLevel();
	}
	
	public void render(){
		renderer.render();
	}
	
	public void dispose(){
		gs.dispose();
	}
}
