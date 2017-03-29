package com.daniel.game.model;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.ecouteur.EcouteurClavier;
import com.daniel.game.view.TextureFactory;
import com.daniel.game.view.WorldRenderer;

public class Pacman extends GameElement {

	public float SIZE;
	private boolean leftMove,rightMove,upMove,downMove; 
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

	public Pacman(Vector2 position, World world) {
		super(position, world);
		SIZE = 0;
		body = new Rectangle(position.x, position.y, WorldRenderer.ppuX, WorldRenderer.ppuX);
		leftMove = true;
	}

	public boolean isCollided(Rectangle rect){
		return rect.overlaps(body);
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
	
	private void leftMove(float delta){
		boolean estTouche = false;
		setBody(position.x,position.y-WorldRenderer.ppuX*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y+WorldRenderer.ppuX == this.getPosition().y){
				leftMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		if(!estTouche){
			tryEat();
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
	private void rightMove(float delta){
		boolean estTouche = false;
		setBody(position.x,position.y+WorldRenderer.ppuX*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y == this.getPosition().y+WorldRenderer.ppuX){
				rightMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		if(!estTouche){
			tryEat();
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
	private void upMove(float delta){
		boolean estTouche = false;
		setBody(position.x+WorldRenderer.ppuX*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x == this.getPosition().x+WorldRenderer.ppuX){
				upMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		if(!estTouche){
			tryEat();
			position.x +=WorldRenderer.ppuX*delta;
			setBody(position.x,position.y);
		}
	}
	private void downMove(float delta){
		boolean estTouche = false;
		setBody(position.x-WorldRenderer.ppuX*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x+WorldRenderer.ppuX == this.getPosition().x){
				downMove = false;
				estTouche = true;
				setBody(position.x,position.y);
			}
		}
		if(!estTouche){
			tryEat();
			position.x -=WorldRenderer.ppuX*delta;
			setBody(position.x,position.y);
		}
	}
	
	private boolean tryLeftMove(float delta){
		setBody(position.x,position.y-WorldRenderer.ppuX*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y+WorldRenderer.ppuX == this.getPosition().y){
				return false;
				
			}
		}
		setBody(position.x,position.y);
		return true;
	}
	private boolean tryRightMove(float delta){
		setBody(position.x,position.y+WorldRenderer.ppuX*delta);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().y == this.getPosition().y+WorldRenderer.ppuX){
				return false;
				
			}
		}
		setBody(position.x,position.y);
		return true;
	}
	private boolean tryUpMove(float delta){
		setBody(position.x+WorldRenderer.ppuX*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x == this.getPosition().x+WorldRenderer.ppuX){
				return false;
				
			}
		}
		setBody(position.x,position.y);
		return true;
	}
	private boolean tryDownMove(float delta){
		setBody(position.x-WorldRenderer.ppuX*delta,position.y);
		for(GameElement b : world.getMaze()){
			if(this.isCollided(b.getBody()) && b.getPosition().x+WorldRenderer.ppuX == this.getPosition().x){
				return false;
			}
		}
		setBody(position.x,position.y);
		return true;
	}

	private void tryEat(){
		for(GameElement ge : world.getMaze().getGommes()){
			if(ge instanceof Gomme){
				Gomme g = (Gomme) ge;
				if ( !g.estManger && g.getBody().overlaps(body)){;
					g.estManger = true;
					g.body = null;
					Score.MiseAJourScore();
				}
			}
			if(ge instanceof SuperGomme){
				SuperGomme G = (SuperGomme) ge;
				if ( !G.estManger && G.getBody().overlaps(body)){;
					G.estManger = true;
					G.body = null;
					Score.MiseAJourScore();
					world.fantomeMangeable();
				}
			}
		}
	}
	public void update(float delta){
		if(EcouteurClavier.actualKey == EcouteurClavier.oldKey){
			if(leftMove){
				leftMove(delta);
			}
			else if(rightMove){
				rightMove(delta);
			}
			else if(upMove){
				upMove(delta);
			}
			else if(downMove){
				downMove(delta);
			}
		}
		else{
			boolean stayDir = true;
			if(leftMove){
				switch(EcouteurClavier.actualKey){
					case Input.Keys.RIGHT : 
						if(tryRightMove(delta)){
							rightMove = true;
							upMove = downMove = leftMove = false;
							rightMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false;
						}
						break;
					case Input.Keys.DOWN : 
						if(tryDownMove(delta)){
							rightMove = upMove = leftMove = false;
							downMove = true;
							downMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false;
						}
						break;
					case Input.Keys.UP : 
						if(tryUpMove(delta)){
							rightMove = downMove = leftMove=false;
							upMove = true;
							upMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false;
						}
						break;
					default :
						break;
				}
				if(stayDir){
					leftMove(delta);
				}
			}
			else if(rightMove){
				switch(EcouteurClavier.actualKey){
					case Input.Keys.LEFT : 
						if(tryLeftMove(delta)){
							leftMove = true;
							upMove = downMove = rightMove=false;
							leftMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false;
						}
						break;
					case Input.Keys.DOWN : 
						if(tryDownMove(delta)){
							upMove = leftMove = rightMove = false;
							downMove = true;
							downMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false;
						}
						break;
					case Input.Keys.UP : 
						if(tryUpMove(delta)){
							downMove = leftMove = rightMove=false;
							upMove = true;
							upMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false;
						}
						break;
					default :
						break;
				}
				if(stayDir){
					rightMove(delta);
				}
			}
			else if(upMove){
				switch(EcouteurClavier.actualKey){
					case Input.Keys.LEFT : 
						if(tryLeftMove(delta)){
							rightMove = downMove = upMove=false;
							leftMove = true;
							leftMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false;
						}
						break;
					case Input.Keys.DOWN : 
						if(tryDownMove(delta)){
							downMove = true;
							leftMove = rightMove = upMove = false;
							downMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false;
						}
						break;
					case Input.Keys.RIGHT : 
						if(tryRightMove(delta)){
							upMove=false;
							rightMove =true;
							rightMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false;
						}
						break;
					default :
						break;
				}
				if(stayDir){
					upMove(delta);
				}
			}
			else if(downMove){
				switch(EcouteurClavier.actualKey){
					case Input.Keys.LEFT : 
						if(tryLeftMove(delta)){
							upMove = rightMove = downMove=false;
							leftMove = true;
							leftMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false;
						}
						break;
					case Input.Keys.UP : 
						if(tryUpMove(delta)){
							leftMove = rightMove = downMove = false;
							upMove = true;
							upMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false ;
						}
						break;
					case Input.Keys.RIGHT : 
						if(tryRightMove(delta)){
							leftMove = upMove = downMove = false;
							rightMove = true;
							rightMove(delta);
							EcouteurClavier.oldKey=EcouteurClavier.actualKey;
							EcouteurClavier.aChangerDeSens = true;
							stayDir = false;
							
						}
						break;
					default :
						break;
				}
				if(stayDir){
					downMove(delta);
				}
			}
		}
		
	}
	
	public void setLeftMove(boolean t){
		leftMove = t;
	}
	public void setRightMove(boolean t){
		rightMove = t;
	}
	public void setUpMove(boolean t){
		upMove = t;
	}
	public void setDownMove(boolean t){
		downMove = t;
	}
}
