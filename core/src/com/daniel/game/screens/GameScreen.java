package com.daniel.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.daniel.game.PacmanGame;
import com.daniel.game.ecouteur.EcouteurClavier;
import com.daniel.game.model.GameElement;
import com.daniel.game.model.Score;
import com.daniel.game.model.World;
import com.daniel.game.view.WorldRenderer;

public class GameScreen  implements Screen {
	public static PacmanGame game;
	private World world;
	private WorldRenderer renderer;
		
	public GameScreen(){
		GameScreen.game =  (PacmanGame) ((Game)Gdx.app.getApplicationListener());
		Score.getInstance();
	}
	public void show(){
		world = new World();
		renderer = new WorldRenderer(world);
		world.createWorld();
		Gdx.input.setInputProcessor(new EcouteurClavier(world));
	}
	
	public void render(float x){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
			ge.getTexture().getTexture().dispose();
		}
	}
}
