package com.daniel.game.outil;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class noeud {
	private int x,y; 
	private double heuristique,cout,coutTotal;
	private noeud pere;
	
	public noeud(int x,int y){
		this.x = x;
		this.y = y;
		heuristique = 0;
		cout = 1 ;
		pere = null;
		setCoutTotal(0);
	}
	
	public double getCout() {
		return cout;
	}

	public void setCout(double cout) {
		this.cout = cout;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getHeuristique() {
		return heuristique;
	}
	public void setHeuristique(noeud n) {
		heuristique = Math.sqrt((x - n.getX())*(x - n.getX()) + (y -n.getY())* (y -n.getY()));
	}

	public noeud getPere() {
		return pere;
	}

	public void setPere(noeud pere) {
		this.pere = pere;
	}

	public double getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(double coutTotal) {
		this.coutTotal = coutTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		noeud other = (noeud) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public boolean estDansClose(ArrayList<noeud> closedList) {
		
		for (noeud noeud : closedList) {
			if(this.equals(noeud))
				return true;
		}
		
		return false;
	}
	
	public boolean estDansOpenUClose(PriorityQueue<noeud> openList, ArrayList<noeud> closedList) {
		
		for (noeud noeud : closedList) {
			if(this.equals(noeud))
				return true;
		}
		
		for (noeud noeud : openList) {
			if(this.equals(noeud))
				return true;
		}
		
		return false;
	}
}

