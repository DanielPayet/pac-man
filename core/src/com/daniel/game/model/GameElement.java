package com.daniel.game.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.model.World;

public abstract class GameElement {
	
	protected Vector2 position ;
	protected World world;
	protected Rectangle body;
	
	public GameElement(Vector2 position, World world ){
		this.position = position;
		this.world = world;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public World getWorld(){
		return world;
	}

	public Rectangle getBody() {
		return body;
	}

	protected void setBody(float a, float b) {
		body.setPosition(a, b);
	}

	abstract public float getWidth();
	abstract public float getHeight();
	
	abstract public TextureRegion getTexture();
}
