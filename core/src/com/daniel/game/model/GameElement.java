package com.daniel.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.model.World;

public abstract class GameElement {
	
	protected Vector2 position ;
	@SuppressWarnings("unused")
	private World world;
	
	public GameElement(Vector2 position, World world ){
		this.position = position;
		this.world = world;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	abstract public float getWidth();
	abstract public float getHeight();
	
	abstract public Texture getTexture();
}
