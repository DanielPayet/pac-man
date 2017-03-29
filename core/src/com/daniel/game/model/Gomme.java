package com.daniel.game.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.view.TextureFactory;

public class Gomme extends GameElement {

	public float SIZE;
	public boolean estManger;
	public Gomme(Vector2 position, World world) {
		super(position, world);
		SIZE = 0;
		body = new Rectangle(position.x+7, position.y+7, 2, 2);
		estManger = false;
	}
	
	public TextureRegion getTexture(){
		TextureFactory.getInstance();
		return TextureFactory.iTexturable(this);
	}
	
	public float getWidth(){
		return position.x;
	}
	
	public float getHeight(){
		return position.y;
	}
}
