package com.daniel.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daniel.game.model.World;
import com.daniel.game.model.GameElement;

public class WorldRenderer{
	
	private SpriteBatch spriteBatch;
	private int ppuX;
	private int ppuY;
	private World world;
	
	public WorldRenderer(World world){
		this.world = world;
		ppuX = 0;
		ppuY = 0;
		spriteBatch = new SpriteBatch();
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		TextureFactory.getInstance();
		for(GameElement ge : world){
			spriteBatch.draw(TextureFactory.getTexture(ge),ge.getHeight(),ge.getWidth());
		}
		spriteBatch.end();
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public int getPpuX() {
		return ppuX;
	}

	public int getPpuY() {
		return ppuY;
	}

	public void setSpriteBatch(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
	}

	public void setPpuX(int ppuX) {
		this.ppuX = ppuX;
	}

	public void setPpuY(int ppuY) {
		this.ppuY = ppuY;
	}
	
}
