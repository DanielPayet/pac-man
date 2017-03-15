package com.daniel.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.view.TextureFactory;

public class Pacman extends GameElement {

	public float SIZE;
	public Pacman(Vector2 position, World world) {
		super(position, world);
		SIZE = 0;
	}
	
	public Texture getTexture(){
		TextureFactory.getInstance();
		return TextureFactory.getTexturePacman();
	}

	public float getWidth(){
		return Math.round(position.x);
	}
	
	public float getHeight(){
		return Math.round(position.y);
	}
	
	public void update(float a){
		
	}
}
