package com.daniel.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.outil.Astar;
import com.daniel.game.outil.noeud;
import com.daniel.game.screens.LoseScreen;
import com.daniel.game.view.WorldRenderer;

public abstract class Fantomes extends GameElement {

	protected boolean leftMove,rightMove,upMove = true,downMove;
	protected boolean estSortiMaison ;
	public boolean estMangable ;
	public boolean haveBeenEaten;
	protected long timeStart;
	public boolean estMort;
	
	public void GoHome(float delta){
		estMangable = false;
		estSortiMaison = false;
		haveBeenEaten = true;
		estMort = true;
	}
	public void tryIsCollidedWithPacMan(float delta){
		if(world.getPacman().getBody().overlaps(this.body))
			if(estMangable){
				GoHome(delta);
			}
			else{
				((Game)Gdx.app.getApplicationListener()).setScreen(new LoseScreen());
			}
	}

	public Fantomes(Vector2 position, World world) {
		super(position, world);
		body = new Rectangle(position.x, position.y, WorldRenderer.ppuX,WorldRenderer.ppuY);
		estSortiMaison = false;
		estMangable = false;
		haveBeenEaten = false;
		timeStart = world.timeStart;
		estMort = false;
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
		setBody(position.x,position.y-WorldRenderer.ppuX*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y+WorldRenderer.ppuX == this.getPosition().y){
				leftMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		tryIsCollidedWithPacMan(delta);
		if(!estTouche){
			GameElement b = world.getMaze().getTeleportation().get(0);
			if(this.isCollided(b.getBody())){
				position.y = WorldRenderer.ppuX*27;
			}
			else{
				position.y -=WorldRenderer.ppuX*delta;
				setBody(position.x,position.y);
			}
		}

	}
	protected void rightMove(float delta){
		boolean estTouche = false;
		setBody(position.x,position.y+WorldRenderer.ppuX*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y == this.getPosition().y+WorldRenderer.ppuX){
				rightMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		tryIsCollidedWithPacMan(delta);
		if(!estTouche){
			GameElement b = world.getMaze().getTeleportation().get(1);
			if(this.isCollided(b.getBody())){
				position.y = WorldRenderer.ppuX*0;
			}
			else{
				position.y +=WorldRenderer.ppuX*delta;
				setBody(position.x,position.y);
			}
		}
	}
	protected void upMove(float delta){
		boolean estTouche = false;
		setBody(position.x+WorldRenderer.ppuX*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x == this.getPosition().x+WorldRenderer.ppuX){
				upMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		tryIsCollidedWithPacMan(delta);
		if(!estTouche){
			position.x +=WorldRenderer.ppuX*delta;
			setBody(position.x,position.y);
		}
	}
	protected void downMove(float delta){
		boolean estTouche = false;
		setBody(position.x-WorldRenderer.ppuX*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x+WorldRenderer.ppuX == this.getPosition().x){
				downMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		tryIsCollidedWithPacMan(delta);
		if(!estTouche){
			position.x -=WorldRenderer.ppuX*delta;
			setBody(position.x,position.y);
		}
	}

	protected boolean tryLeftMove(float delta){
		setBody(position.x,position.y-WorldRenderer.ppuX*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y+WorldRenderer.ppuX == this.getPosition().y){
				return false;

			}
		}
		setBody(position.x,position.y);
		return true;
	}
	protected boolean tryRightMove(float delta){
		setBody(position.x,position.y+WorldRenderer.ppuX*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y == this.getPosition().y+WorldRenderer.ppuX){
				return false;

			}
		}
		setBody(position.x,position.y);
		return true;
	}
	protected boolean tryUpMove(float delta){
		setBody(position.x+WorldRenderer.ppuX*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x == this.getPosition().x+WorldRenderer.ppuX){
				return false;

			}
		}
		setBody(position.x,position.y);
		return true;
	}
	protected boolean tryDownMove(float delta){
		setBody(position.x-WorldRenderer.ppuX*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x+WorldRenderer.ppuX == this.getPosition().x){
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
		if(time - timeRef >= duration){
			upMove = true;
			if(position.x + WorldRenderer.ppuX * delta <= (30-11)*WorldRenderer.ppuX){
				position.x +=WorldRenderer.ppuX*delta;
				setBody(position.x,position.y);
			}
			else{
				estSortiMaison = true;
			}
		}
	}

	protected void leftMoveDEAD(float delta){
			position.y -=WorldRenderer.ppuX*delta;
			setBody(position.x,position.y);
	}
	protected void rightMoveDEAD(float delta){
		position.y +=WorldRenderer.ppuX*delta;
		setBody(position.x,position.y);
	}
	protected void upMoveDEAD(float delta){
		position.x +=WorldRenderer.ppuX*delta;
		setBody(position.x,position.y);
	}
	protected void downMoveDEAD(float delta){
		position.x -=WorldRenderer.ppuX*delta;
		setBody(position.x,position.y);
	}

	protected boolean tryLeftMoveDEAD(float delta){
		setBody(position.x,position.y-WorldRenderer.ppuX*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y+WorldRenderer.ppuX == this.getPosition().y){
				if(!(b instanceof DoorGhost))
					return false;
			}
		}
		setBody(position.x,position.y);
		return true;
	}
	protected boolean tryRightMoveDEAD(float delta){
		setBody(position.x,position.y+WorldRenderer.ppuX*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y == this.getPosition().y+WorldRenderer.ppuX){
				if(!(b instanceof DoorGhost))
					return false;

			}
		}
		setBody(position.x,position.y);
		return true;
	}
	protected boolean tryUpMoveDEAD(float delta){
		setBody(position.x+WorldRenderer.ppuX*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x == this.getPosition().x+WorldRenderer.ppuX){
				if(!(b instanceof DoorGhost))
					return false;
			}
		}
		setBody(position.x,position.y);
		return true;
	}
	protected boolean tryDownMoveDEAD(float delta){
		setBody(position.x-WorldRenderer.ppuX*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x+WorldRenderer.ppuX == this.getPosition().x){
				if(!(b instanceof DoorGhost) || ((b instanceof DoorGhost) && b.getPosition().y == 13*WorldRenderer.ppuX))
					return false;
			}
		}
		setBody(position.x,position.y);
		return true;
	}

	
	protected void backToHome(float delta){
		int Xf = 30-(Astar.arrondiPosX(this.position.x, this)/WorldRenderer.ppuX);
		int Yf = (Astar.arrondiPosY(this.position.y,this)/WorldRenderer.ppuX);
		int Xp = 14;
		int Yp = 14;
		int lab[][] = world.getMaze().labDemo;

		if(Xf == Xp && Yf == Yp){
			estMort = false;
		}
		else{
			noeud prochainNoeud = Astar.astar(lab,new noeud(Xf,Yf)  ,new noeud(Xp,Yp),this);

			if(prochainNoeud != null){

				boolean continueOldDir = false ; 
				if(prochainNoeud.getX() < Xf){
					if(tryUpMove(delta)){
						rightMove = leftMove = downMove = false;
						upMove = true;
						upMoveDEAD(delta);
					}
					else
						continueOldDir = true;
				}
				if(prochainNoeud.getX() > Xf){
					if(tryDownMoveDEAD(delta)){
						rightMove = upMove = leftMove = false;
						downMove = true;
						downMoveDEAD(delta);
					}
					else
						continueOldDir = true;
				}
				if(prochainNoeud.getY() > Yf){
					if(tryRightMoveDEAD(delta)){
						leftMove = upMove = downMove = false;
						rightMove = true;
						rightMoveDEAD(delta);
					}
					else
						continueOldDir = true;
				}
				if(prochainNoeud.getY() < Yf){
					if(tryLeftMoveDEAD(delta)){
						rightMove = upMove = downMove = false;
						leftMove = true;
						leftMoveDEAD(delta);
					}
					else
						continueOldDir = true;
				}
				if(continueOldDir){
					if(leftMove){
						leftMoveDEAD(delta);
					}
					else if(rightMove){
						rightMoveDEAD(delta);
					}
					else if(upMove){
						upMoveDEAD(delta);
					}
					else if(downMove){
						downMoveDEAD(delta);
					}
				}
			}
			else{
				if(leftMove){
					leftMoveDEAD(delta);
				}
				else if(rightMove){
					rightMoveDEAD(delta);
				}
				else if(upMove){
					upMoveDEAD(delta);
				}
				else if(downMove){
					downMoveDEAD(delta);
				}
			}
		}
	}
}
