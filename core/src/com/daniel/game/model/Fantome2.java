package com.daniel.game.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.outil.Astar;
import com.daniel.game.outil.noeud;
import com.daniel.game.view.TextureFactory;
import com.daniel.game.view.WorldRenderer;

public class Fantome2 extends Fantomes {
	
	public Fantome2(Vector2 position, World world) {
		super(position, world);
	}

	public TextureRegion getTexture(){
		return TextureFactory.iTexturable(this);
	}
	
	public void update(float delta){
		if(estMort){
			backToHome(delta);
		}
		else if(!estSortiMaison){
			sortirMaison(delta);
		}
		else{
			int Xf = 30-(Astar.arrondiPosX(this.position.x, this)/WorldRenderer.ppuX);
			int Yf = (Astar.arrondiPosY(this.position.y,this)/WorldRenderer.ppuX);
			int Xp = 30-(Astar.arrondiPosX(world.getPacman().position.x,world.getPacman())/WorldRenderer.ppuX);
			int Yp = (Astar.arrondiPosY(world.getPacman().position.y,world.getPacman())/WorldRenderer.ppuX); 
			int lab[][] = world.getMaze().labDemo;
			lab[Xf][Yf] = 3 ; 
			lab[Xp][Yp] = 4 ;
			
			noeud prochainNoeud = Astar.astar(lab,new noeud(Xf,Yf)  ,new noeud(Xp,Yp),this);
			
			if(prochainNoeud != null){
				boolean continueOldDir = false ; 
				if(prochainNoeud.getX() < Xf){
					if(tryUpMove(delta)){
						rightMove = leftMove = downMove = false;
						upMove = true;
						upMove(delta);
					}
					else
						continueOldDir = true;
				}
				if(prochainNoeud.getX() > Xf){
					if(tryDownMove(delta)){
						rightMove = upMove = leftMove = false;
						downMove = true;
						downMove(delta);
					}
					else
						continueOldDir = true;
				}
				if(prochainNoeud.getY() > Yf){
					if(tryRightMove(delta)){
						leftMove = upMove = downMove = false;
						rightMove = true;
						rightMove(delta);
					}
					else
						continueOldDir = true;
				}
				if(prochainNoeud.getY() < Yf){
					if(tryLeftMove(delta)){
						rightMove = upMove = downMove = false;
						leftMove = true;
						leftMove(delta);
					}
					else
						continueOldDir = true;
				}
				if(continueOldDir){
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
			}
			else{
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
		}
	}
	
	@Override
	public void sortirMaison(float delta) {
		sortir(delta,5000);	
	}
}
