package com.daniel.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.screens.LoseScreen;

public abstract class Fantomes extends GameElement {

	protected boolean leftMove,rightMove,upMove = true,downMove;
	protected static final Vector2 positionDep = new Vector2((30-14)*16,14*16);;
	protected boolean estSortiMaison ;
	public boolean estMangable ;
	public boolean haveBeenEaten;
	protected long timeStart;
	
	public void GoHome(){
		position.x = positionDep.x;
		position.y = positionDep.y;
		body.x = positionDep.x;
		body.y = positionDep.y;
		estSortiMaison = false;
		estMangable = false;
		haveBeenEaten = true;
	}
	public void tryIsCollidedWithPacMan(){
		if(world.getPacman().getBody().overlaps(this.body))
			if(estMangable){
				GoHome();
			}
			else{
				((Game)Gdx.app.getApplicationListener()).setScreen(new LoseScreen());
			}
	}
	
	public Fantomes(Vector2 position, World world) {
		super(position, world);
		body = new Rectangle(position.x, position.y, 16,16);
		estSortiMaison = false;
		estMangable = false;
		haveBeenEaten = false;
		timeStart = world.timeStart;
	}
	
	public boolean isLeftMove() {
		return leftMove;
	}

	public boolean isRightMove() {
		return rightMove;
	}

	public boolean isUpMove() {
		return upMove;
	}

	public boolean isDownMove() {
		return downMove;
	}

	public boolean isCollided(Rectangle rect){
		return rect.overlaps(body);
	}
	
	public float getWidth(){
		return position.x;
	}
	
	public float getHeight(){
		return position.y;
	}
	
	protected void leftMove(float delta){
		boolean estTouche = false;
		setBody(position.x,position.y-16*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y+16 == this.getPosition().y){
				leftMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		tryIsCollidedWithPacMan();
		if(!estTouche){
			GameElement b = world.getMaze().getTeleportation().get(0);
			if(this.isCollided(b.getBody())){
				position.y = 16*27;
			}
			else{
				position.y -=16*delta;
				setBody(position.x,position.y);
			}
		}
		
	}
	protected void rightMove(float delta){
		boolean estTouche = false;
		setBody(position.x,position.y+16*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y == this.getPosition().y+16){
				rightMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		tryIsCollidedWithPacMan();
		if(!estTouche){
			GameElement b = world.getMaze().getTeleportation().get(1);
			if(this.isCollided(b.getBody())){
				position.y = 16*0;
			}
			else{
				position.y +=16*delta;
				setBody(position.x,position.y);
			}
		}
	}
	protected void upMove(float delta){
		boolean estTouche = false;
		setBody(position.x+16*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x == this.getPosition().x+16){
				upMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		tryIsCollidedWithPacMan();
		if(!estTouche){
			position.x +=16*delta;
			setBody(position.x,position.y);
		}
	}
	protected void downMove(float delta){
		boolean estTouche = false;
		setBody(position.x-16*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x+16 == this.getPosition().x){
				downMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		tryIsCollidedWithPacMan();
		if(!estTouche){
			position.x -=16*delta;
			setBody(position.x,position.y);
		}
	}
	
	protected boolean tryLeftMove(float delta){
		setBody(position.x,position.y-16*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y+16 == this.getPosition().y){
				return false;
				
			}
		}
		setBody(position.x,position.y);
		return true;
	}
	protected boolean tryRightMove(float delta){
		setBody(position.x,position.y+16*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y == this.getPosition().y+16){
				return false;
				
			}
		}
		setBody(position.x,position.y);
		return true;
	}
	protected boolean tryUpMove(float delta){
		setBody(position.x+16*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x == this.getPosition().x+16){
				return false;
				
			}
		}
		setBody(position.x,position.y);
		return true;
	}
	protected boolean tryDownMove(float delta){
		setBody(position.x-16*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x+16 == this.getPosition().x){
				return false;
			}
		}
		setBody(position.x,position.y);
		return true;
	}
	
	protected void setLeftMove(boolean t){
		leftMove = t;
	}
	protected void setRightMove(boolean t){
		rightMove = t;
	}
	protected void setUpMove(boolean t){
		upMove = t;
	}
	protected void setDownMove(boolean t){
		downMove = t;
	}

	public abstract TextureRegion getTexture();
	public abstract void update(float delta);
	public abstract void sortirMaison(float delta);
	
	protected void sortir(float delta, int duree){
		long time = System.currentTimeMillis();
		long timeRef = timeStart;
		int duration = duree;
		if(haveBeenEaten){
			duration = 5000;
			timeRef = world.timeStartFantomeEat;
		}
		
		if(time - timeRef >= duration){
			upMove = true;
			if(position.x + 16 * delta <= (30-11)*16){
				position.x +=16*delta;
				setBody(position.x,position.y);
			}
			else{
				estSortiMaison = true;
			}
		}
	}
}
