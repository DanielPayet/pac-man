package com.daniel.game.outil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import com.daniel.game.model.Fantomes;
import com.daniel.game.model.GameElement;
import com.daniel.game.model.Pacman;
import com.daniel.game.view.WorldRenderer;

public class Astar {
	public static noeud astar(int[][] tab, noeud dep, noeud fin, Fantomes fe) {
		LinkedList<noeud> lifo = new LinkedList<noeud>();
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

			ArrayList<noeud> voisin;
				if(fe.estMort)
					voisin = voisinDEAD(tab,fin, u);
				else
					voisin = voisin(tab,fin, u);

			for (noeud succ : voisin) {
				if(u.getX() == fin.getX() && u.getY() == fin.getY()){
					ok = true;
				}
				else if(!succ.estDansOpenUClose(openList, closedList) || succ.getCout() > u.getCout() + 1 ){
					succ.setCout(u.getCout()+1);
					succ.setCoutTotal(succ.getCout() + succ.getHeuristique());
					openList.add(succ);
				}
				else if (succ.estDansClose(closedList))
					closedList.add(succ);
			}
		}
		if (u == null)
			return dep;

		if(u != null){
			while(u.getPere() != null){
				lifo.addFirst(u);
				u = u.getPere();
				
			}
		}
		return lifo.pollFirst();
	}
	
	private static ArrayList<noeud> voisin(int lab[][], noeud fin,noeud u){
		ArrayList<noeud> voisin = new ArrayList<noeud>();
		noeud n;
		
		if(u.getY()-1 >= 0 && u.getY() +1 < 28 && u.getX()-1 >= 0 && u.getX()+1 < 31){
			if(lab[u.getX()][u.getY()-1] != 1 && lab[u.getX()][u.getY()-1] != 2){
				n = new noeud(u.getX(),u.getY()-1);
				n.setHeuristique(fin);
				n.setPere(u);
				voisin.add(n);
			}
			if(lab[u.getX()-1][u.getY()] != 1 && lab[u.getX()-1][u.getY()] != 2){
				n = new noeud(u.getX()-1,u.getY());
				n.setHeuristique(fin);
				n.setPere(u);
				voisin.add(n);
			}
			if(lab[u.getX()+1][u.getY()] != 1 && lab[u.getX()+1][u.getY()] != 2){
				n = new noeud(u.getX()+1,u.getY());
				n.setHeuristique(fin);
				n.setPere(u);
				voisin.add(n);
			}
			if(lab[u.getX()][u.getY()+1] != 1 && lab[u.getX()][u.getY()+1] != 2){
				n = new noeud(u.getX(),u.getY()+1);
				n.setHeuristique(fin);
				n.setPere(u);
				voisin.add(n);
			}
		}
		return voisin;
		
	}
	
	private static ArrayList<noeud> voisinDEAD(int lab[][], noeud fin,noeud u){
		ArrayList<noeud> voisin = new ArrayList<noeud>();
		noeud n;
		
		if(u.getY()-1 >= 0 && u.getY() +1 < 28 && u.getX()-1 >= 0 && u.getX()+1 < 31){
			if(lab[u.getX()][u.getY()-1] != 1){
				n = new noeud(u.getX(),u.getY()-1);
				n.setHeuristique(fin);
				n.setPere(u);
				voisin.add(n);
			}
			if(lab[u.getX()-1][u.getY()] != 1){
				n = new noeud(u.getX()-1,u.getY());
				n.setHeuristique(fin);
				n.setPere(u);
				voisin.add(n);
			}
			if(lab[u.getX()+1][u.getY()] != 1){
				n = new noeud(u.getX()+1,u.getY());
				n.setHeuristique(fin);
				n.setPere(u);
				voisin.add(n);
			}
			if(lab[u.getX()][u.getY()+1] != 1){
				n = new noeud(u.getX(),u.getY()+1);
				n.setHeuristique(fin);
				n.setPere(u);
				voisin.add(n);
			}
		}
		return voisin;
		
	}
	
	public static int arrondiPosY(float x, GameElement ge){
		if(x % WorldRenderer.ppuX == 0)
			return (int)(x);
		else{
			if(ge instanceof Pacman){
				Pacman pm = (Pacman) ge;
				if(pm.isRightMove()){
					return (int) (x + (WorldRenderer.ppuX- (x % WorldRenderer.ppuX)));
				}
				else if(pm.isLeftMove()){
					return (int) (x - (x % WorldRenderer.ppuX));
				}
			}
			else if(ge instanceof Fantomes){
				Fantomes f = (Fantomes) ge;
				if(f.isRightMove()){
					return (int) (x + (WorldRenderer.ppuX- (x % WorldRenderer.ppuX)));
				}
				else if(f.isLeftMove()){
					return (int) (x - (x % WorldRenderer.ppuX));
				}
			}
		}
		return 0;
	}

	public static int arrondiPosX(float y, GameElement ge){
		if(y % WorldRenderer.ppuX == 0)
			return (int)(y);
		else{
			if(ge instanceof Pacman){
				Pacman pm = (Pacman) ge;
				if(pm.isUpMove()){
					return (int) (y + (WorldRenderer.ppuX- (y % WorldRenderer.ppuX)));
				}
				else if(pm.isDownMove()){
					return (int) (y - (y % WorldRenderer.ppuX));
				}
			}
			else if(ge instanceof Fantomes){
				Fantomes f = (Fantomes) ge;
				if(f.isUpMove()){
					return (int) (y + (WorldRenderer.ppuX- (y % WorldRenderer.ppuX)));
				}
				else if(f.isDownMove()){
					return (int) (y - (y % WorldRenderer.ppuX));
				}
			}
		}
		return 0;
	}
}
