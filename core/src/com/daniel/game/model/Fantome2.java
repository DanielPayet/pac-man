package com.daniel.game.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.outil.heuristiqueCompare;
import com.daniel.game.outil.noeud;
import com.daniel.game.view.TextureFactory;

@SuppressWarnings("unused")
public class Fantome2 extends Fantomes {
	
	public Fantome2(Vector2 position, World world) {
		super(position, world);
	}

	public TextureRegion getTexture(){
		return TextureFactory.iTexturable(this);
	}
	
	public void update(float delta){
		if(!estSortiMaison){
			sortirMaison(delta);
		}
		else{
			if(leftMove){
				MoveDirection(delta);
			}
			else if(rightMove){
				MoveDirection(delta);
			}
			else if(upMove){
				MoveDirection(delta);
			}
			else if(downMove){
				MoveDirection(delta);
				
			}
		}
	}

	private void MoveDirection(float delta) {
		Random r = new Random();
		int valeur;
		boolean TryOk = false;
		do{
			valeur = 0 + r.nextInt(4 - 0);
			switch(valeur){
			case 0 : 
				if(!rightMove && tryLeftMove(delta))
					TryOk = true;
				break;
			case 1 : 
				if(!leftMove && tryRightMove(delta))
					TryOk = true;
				break;
			case 2 :
				if(!downMove && tryUpMove(delta))
					TryOk = true;
				break;
			case 3 : 
				if(!upMove && tryDownMove(delta))
					TryOk = true;
				break;
			}
		}while(!TryOk);
		
		switch(valeur){
		case 0 : 
			rightMove = upMove = downMove = false;
			leftMove = true;
			leftMove(delta);
			break;
		case 1 : 
			leftMove = upMove = downMove = false;
			rightMove = true;
			rightMove(delta);
			break;
		case 2 : 
			rightMove = leftMove = downMove = false;
			upMove = true;
			upMove(delta);
			break;
		case 3 : 
			rightMove = upMove = leftMove = false;
			downMove = true;
			downMove(delta);
			break;
		}

	}
/*
	public void update(float delta){
		if(!estSortiMaison){
			sortirMaison(delta);
		}
		else{
			int Xf = 30-(arrondiPosX(this.position.x, this)/16);
			int Yf = (arrondiPosY(this.position.y,this)/16);
			int Xp = 30-(arrondiPosX(world.getPacman().position.x,world.getPacman())/16);
			int Yp = (arrondiPosY(world.getPacman().position.y,world.getPacman())/16); 
			int lab[][] = world.getMaze().labDemo;
			lab[Xf][Yf] = 3 ; 
			lab[Xp][Yp] = 4 ; 
			noeud prochainNoeud = astar(lab,new noeud(Xp,Yp)  ,new noeud(Xf,Yf));
			
		
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
					update(delta);
				}
				else if(downMove){
					downMove(delta);
				}
			}
		}
	}
	
	private ArrayList<noeud> voisin(int lab[][], noeud fin,noeud u){
		ArrayList<noeud> voisin = new ArrayList<noeud>();
		noeud n;
		
		if(u.getY()-1 > 0 && u.getY() +1 < 28 && u.getX()-1 > 0 && u.getX()+1 < 32){
			if(lab[u.getX()][u.getY()-1] != -1 && lab[u.getX()][u.getY()-1] != 2){
				n = new noeud(u.getX(),u.getY()-1);
				n.setHeuristique(fin);
				voisin.add(n);
			}
			if(lab[u.getX()-1][u.getY()] != 1 && lab[u.getX()-1][u.getY()] != 2){
				n = new noeud(u.getX()-1,u.getY());
				n.setHeuristique(fin);
				voisin.add(n);
			}
			if(lab[u.getX()+1][u.getY()] != 1 && lab[u.getX()+1][u.getY()] != 2){
				n = new noeud(u.getX()+1,u.getY());
				n.setHeuristique(fin);
				voisin.add(n);
			}
			if(lab[u.getX()][u.getY()+1] != 1 && lab[u.getX()][u.getY()+1] != 2){
				n = new noeud(u.getX(),u.getY()+1);
				n.setHeuristique(fin);
				voisin.add(n);
			}
		}
		return voisin;
		
	}
	
	private noeud astar(int[][] tab, noeud dep, noeud fin) {
		Comparator<noeud> comp = new heuristiqueCompare();
		PriorityQueue<noeud> openList = new PriorityQueue<noeud>(20, comp);
		ArrayList<noeud> closedList= new ArrayList<noeud>();
		openList.add(dep);
		dep.setHeuristique(fin);
		noeud u = null;
		boolean ok = false;
		while(!ok && !openList.isEmpty()){
			u = openList.poll();
			closedList.add(u);
			if(u.getX() == fin.getX() && u.getY() == fin.getY()){
				ok = true;
			}
			else{
				ArrayList<noeud> voisin= voisin(tab,fin, u);
				
				for (noeud succ : voisin) {
					if(!closedList.contains(succ) && !openList.contains(succ) || succ.getCout() > u.getCout() + 1 ){
						succ.setCout(u.getCout()+1);
						succ.setCoutTotal(succ.getCout() + succ.getHeuristique());
						succ.setPere(u);
						openList.add(succ);
					}
				}
			}
			
		}
		return u;
	}
	
	private int arrondiPosY(float x, GameElement ge){
		if(x % 16 == 0)
			return (int)(x);
		else{
			if(ge instanceof Pacman){
				Pacman pm = (Pacman) ge;
				if(pm.isRightMove()){
					return (int) (x + (16- (x % 16)));
				}
				else if(pm.isLeftMove()){
					return (int) (x - (x % 16));
				}
			}
			else if(ge instanceof Fantomes){
				Fantomes f = (Fantomes) ge;
				if(f.isRightMove()){
					return (int) (x + (16- (x % 16)));
				}
				else if(f.isLeftMove()){
					return (int) (x - (x % 16));
				}
			}
		}
		return 0;
	}

	private int arrondiPosX(float y, GameElement ge){
		if(y % 16 == 0)
			return (int)(y);
		else{
			if(ge instanceof Pacman){
				Pacman pm = (Pacman) ge;
				if(pm.isUpMove()){
					return (int) (y + (16- (y % 16)));
				}
				else if(pm.isDownMove()){
					return (int) (y - (y % 16));
				}
			}
			else if(ge instanceof Fantomes){
				Fantomes f = (Fantomes) ge;
				if(f.isUpMove()){
					return (int) (y + (16- (y % 16)));
				}
				else if(f.isDownMove()){
					return (int) (y - (y % 16));
				}
			}
		}
		return 0;
	}*/
	@Override
	public void sortirMaison(float delta) {
		sortir(delta,5000);	
	}
}
