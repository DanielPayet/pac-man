package com.daniel.game.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.view.TextureFactory;
import com.daniel.game.view.WorldRenderer;

public class Block extends GameElement {

	public float SIZE;
	public Block(Vector2 position, World world) {
		super(position, world);
		SIZE = 0;
		body = new Rectangle(position.x, position.y,WorldRenderer.ppuX, WorldRenderer.ppuY);
	}
	
	public TextureRegion getTexture(){
		return TextureFactory.iTexturable(this);
	}
	
	public float getWidth(){
		return position.x;
	}
	
	public float getHeight(){
		return position.y;
	}
}
