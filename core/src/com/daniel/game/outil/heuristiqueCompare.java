package com.daniel.game.outil;

import java.util.Comparator;

public class heuristiqueCompare implements Comparator<noeud> {

	@Override
	public int compare(noeud arg0, noeud arg1) {
		if(arg0.getCoutTotal() < arg1.getCoutTotal())
			return -1;
		else
			return 1;
	}

}
