package com.daniel.game.ecouteur;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.daniel.game.model.World;
import com.daniel.game.screens.LoseScreen;

public class EcouteurClavier implements InputProcessor {
 
	private World world;
	public static int actualKey = Input.Keys.LEFT,oldKey = Input.Keys.LEFT;
	public static boolean aChangerDeSens = true;
	
	public EcouteurClavier(World world){
		this.world = world;
	}
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.SPACE)
			((Game)Gdx.app.getApplicationListener()).setScreen(new LoseScreen());
		if( actualKey != keycode && (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT || keycode == Input.Keys.UP ||
				keycode == Input.Keys.DOWN)){
			if(oldKey != keycode){
				if(aChangerDeSens){
					oldKey = actualKey;
					aChangerDeSens = false;
				}
			}
			actualKey = keycode;
			switch(oldKey){
			case Input.Keys.LEFT : 
				world.getPacman().setLeftMove(true);
				break;
			case Input.Keys.RIGHT : 
				world.getPacman().setRightMove(true);
				break;
			case Input.Keys.DOWN : 
				world.getPacman().setDownMove(true);
				break;
			case Input.Keys.UP : 
				world.getPacman().setUpMove(true);
				break;
			default :
				break;
			}
		}
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
