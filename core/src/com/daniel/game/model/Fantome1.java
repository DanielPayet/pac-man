package com.daniel.game.model;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.daniel.game.view.TextureFactory;

public class Fantome1 extends Fantomes {

	public Fantome1(Vector2 position, World world) {
		super(position, world);
		leftMove = true;
		estSortiMaison = true;
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

	@Override
	public void sortirMaison(float delta) {
		sortir(delta,0);	
	}
}
