package com.daniel.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daniel.game.model.Score;

public class WinScreen  implements Screen {
		
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture logo;
	private BitmapFont pressEnter;
	private BitmapFont copy;
	
	public WinScreen(){
	batch = new SpriteBatch();
	Pixmap bloc48 = new Pixmap(Gdx.files.internal("logo.png"));
	Pixmap bloc16 = new Pixmap(320,70, bloc48.getFormat());
	bloc16.drawPixmap(bloc48, 0,0, bloc48.getWidth(), bloc48.getHeight(),0,0,bloc16.getWidth(),bloc16.getHeight());
	logo = new Texture(bloc16);
	
	pressEnter = new BitmapFont();
	copy = new BitmapFont();
	font = new BitmapFont();
	
	pressEnter.setColor(Color.BLACK);
	copy.setColor(Color.BLACK);
	font.setColor(Color.BLACK);
	
	Gdx.input.setInputProcessor(new InputProcessor() {
		
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
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean keyDown(int keycode) {
			if(keycode == Input.Keys.ENTER){
				Score.reset();
				((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
			}
			else if(keycode == Input.Keys.SPACE){
				Gdx.app.exit();
			}
			return false;
		}
	});

}
public void show(){
	
}

public void render(float x){
	Gdx.gl.glClearColor(1, 1, 1, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	batch.begin();
	batch.draw(logo,150,300);
	font.draw(batch,"Gagné !", 280, 280 );
	font.draw(batch,"Score : " + Score.getScore(), 260, 250 );
	pressEnter.draw(batch, "Appuyer sur Entrer pour rejouez, espace pour quitter.", 140, 150);
	copy.draw(batch, "Copyright Daniel Payet. ", 220, 50);
	batch.end();
}
	
	public void resize(int a ,int b){
	}
	
	public void hide(){
		
	}
	
	public void pause(){
		
	}
	
	public void resume(){
		
	}
	
	public void dispose(){
		batch.dispose();
	}
}
