package com.daniel.game.view;

import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daniel.game.model.World;
import com.daniel.game.model.GameElement;
import com.daniel.game.model.Score;

public class WorldRenderer{
	
	private SpriteBatch spriteBatch;
	private int ppuX;
	private int ppuY;
	private World world;
	private BitmapFont font;
	FPSLogger fps;
	
	public WorldRenderer(World world){
		this.world = world;
		ppuX = 0;
		ppuY = 0;
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		fps = new FPSLogger();
	}
	
	public void render(){
		TextureFactory.getInstance();
		world.testTempsFantomeMange();
		world.getPacman().update(.125f);
		world.getFantome1().update(.125f*.5f);
		world.getFantome2().update(.125f*.5f);
		world.getFantome3().update(.125f*.5f);	
		spriteBatch.begin();
		//fps.log();
		for(GameElement ge : world){
			try{
			spriteBatch.draw(TextureFactory.iTexturable(ge),ge.getHeight(),ge.getWidth());
			}
			catch(Exception e){
			}
		}
		font.draw(spriteBatch,"Score : " + Score.getScore(), 455, 248 );
		
		font.draw(spriteBatch,"Temps : " + Score.getTimeMinute() + "min " + Score.getTimeSeconde() + "sec", 455, 230 );
		
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
