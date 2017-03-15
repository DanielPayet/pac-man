package com.daniel.game.screens;

import com.badlogic.gdx.Screen;
import com.daniel.game.model.GameElement;
import com.daniel.game.model.World;
import com.daniel.game.view.WorldRenderer;

public class GameScreen  implements Screen {
	private World world;
	private WorldRenderer renderer;
	
	public GameScreen(World world, WorldRenderer renderer){
		this.world = world;
		this.renderer = renderer;
	}
	
	public void show(){
	}
	
	public void render(float x){
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
}
