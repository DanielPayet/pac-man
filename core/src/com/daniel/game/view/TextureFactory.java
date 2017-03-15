package com.daniel.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.daniel.game.model.Block;
import com.daniel.game.model.GameElement;
import com.daniel.game.model.Pacman;

public class TextureFactory {
	private TextureFactory(){
		
	}
	
	private static Texture pacman;
	private static Texture bloc;
	private static TextureFactory INSTANCE = null; 
	
	public static TextureFactory getInstance(){
		if(INSTANCE == null)
			INSTANCE = new TextureFactory();
		return INSTANCE;
	}
	
	public static Texture getTexturePacman(){
		if( pacman == null )
			pacman = new Texture(Gdx.files.internal("pacmanRight.png"));
		return pacman;
	}
	
	public static Texture getTextureBloc(){
		if( bloc == null ){
			Pixmap bloc48 = new Pixmap(Gdx.files.internal("bloc.png"));
			Pixmap bloc16 = new Pixmap(16,16, bloc48.getFormat());
			bloc16.drawPixmap(bloc48, 0,0, bloc48.getWidth(), bloc48.getHeight(),
					0,0,bloc16.getWidth(),bloc16.getHeight());
			bloc = new Texture(bloc16);
		}
		return bloc;
	}
	
	public static Texture getTexture(GameElement ge){
		if(ge instanceof Pacman){
			return getTexturePacman();
		}
		else if(ge instanceof Block){
			return getTextureBloc();
		}
		return null;
	}
}
