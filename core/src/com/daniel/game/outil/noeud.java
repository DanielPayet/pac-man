package com.daniel.game.outil;

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
}

