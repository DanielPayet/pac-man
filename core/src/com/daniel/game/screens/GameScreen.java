package com.daniel.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.model.GameElement;
import com.daniel.game.model.Maze;
import com.daniel.game.model.Pacman;
import com.daniel.game.model.World;
import com.daniel.game.view.WorldRenderer;

public class GameScreen  implements Screen {
	private World world;
	private WorldRenderer renderer;
	
	public GameScreen(){
		
	}
	
	public void show(){
		
	}
	
	public void render(float x){
		renderer.render();
	}
	
	public void resize(int a ,int b){
	}
	
	public void hide(){
		
	}
	
	public void pause(){
		
	}
	
	public void resume(){
		
	}
	
	public void dispose(){
		renderer.getSpriteBatch().dispose();
		for(GameElement ge : world){
			ge.getTexture().dispose();
		}
	}

	public void create() {
		world = new World();
		renderer = new WorldRenderer(world);
		world.setMaze(new Maze(world));
		world.setPacman(new Pacman(new Vector2((30-23)*16,14*16), world));
		world.getMaze().loadDemoLevel();
	}

	public void render() {
		renderer.render();
	}
}
