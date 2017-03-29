package com.daniel.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.daniel.game.model.Block;
import com.daniel.game.model.DoorGhost;
import com.daniel.game.model.Fantome1;
import com.daniel.game.model.Fantome2;
import com.daniel.game.model.Fantome3;
import com.daniel.game.model.Fantomes;
import com.daniel.game.model.GameElement;
import com.daniel.game.model.Pacman;
import com.daniel.game.model.SuperGomme;
import com.daniel.game.model.Gomme;

public class TextureFactory {
	private TextureFactory(){
		
	}
	private static TextureRegion black;
	private static TextureRegion bloc;
	private static TextureRegion gomme;
	private static TextureRegion fantome1;
	private static TextureRegion door;
	private static TextureFactory INSTANCE = null; 
	private static float temps;
	private static TextureRegion regionCourante;
	private static TextureRegion tabRegionDown[];
	private static TextureRegion tabRegionUp[];
	private static TextureRegion tabRegionLeft[];
	private static TextureRegion tabRegionRight[];
	private static TextureRegion tabRegionGomme[];
	private static Animation<TextureRegion> animationDown;
	private static Animation<TextureRegion> animationUp;
	private static Animation<TextureRegion> animationLeft;
	private static Animation<TextureRegion> animationRight;
	private static Animation<TextureRegion> animationGomme;
	private static TextureRegion fantome2;
	private static TextureRegion fantome3;
	private static TextureRegion fantomeEscape;
	private static TextureRegion fantomedead;
	private static TextureRegion regionCourante2;
	private static TextureRegion tabRegionFantome1[];
	private static Animation<TextureRegion> animationFantome1;
	private static TextureRegion regionCourante3;

	private static TextureRegion tabRegionFantome2[];
	private static Animation<TextureRegion> animationFantome2;
	private static TextureRegion regionCourante4;

	private static TextureRegion tabRegionFantome3[];
	private static Animation<TextureRegion> animationFantome3;
	private static TextureRegion regionCourante5;
	
	
	public static TextureFactory getInstance(){
		if(INSTANCE == null)
			INSTANCE = new TextureFactory();
		return INSTANCE;
	}
	
	public static TextureRegion getTextureGomme(){
		if( gomme == null ){
			gomme = new TextureRegion(new Texture(Gdx.files.internal("pellet.png")));
		}
		return gomme;
	}
	
	public static TextureRegion getTextureVide(){
		if( black == null ){
			black = new TextureRegion(new Texture(Gdx.files.internal("dark.png")));
		}
		return black;
	}
	
	public static TextureRegion getTextureDoorGhost(){
		if( door == null ){
			door = getTextureVide();
		}
		return door;
	}
	
	public static TextureRegion getTextureFantome1(){
		if( fantome1 == null ){
			fantome1 = new TextureRegion(new Texture(Gdx.files.internal("ghost1.png")));
		}
		return fantome1;
	}
	
	public static TextureRegion getTextureFantome2(){
		if( fantome2 == null ){
			fantome2 = new TextureRegion(new Texture(Gdx.files.internal("ghost2.png")));
		}
		return fantome2;
	}
	
	public static TextureRegion getTextureFantome3(){
		if( fantome3 == null ){
			fantome3 = new TextureRegion(new Texture(Gdx.files.internal("ghost3.png")));
		}
		return fantome3;
	}
	
	public static TextureRegion getTextureFantomeEscpae(){
		if( fantomeEscape == null ){
			fantomeEscape = new TextureRegion(new Texture(Gdx.files.internal("ghostEscaping.png")));
		}
		return fantomeEscape;
	}
	
	public static TextureRegion getTextureFantomeDEAD(){
		if( fantomedead == null ){
			fantomedead = new TextureRegion(new Texture(Gdx.files.internal("ghostDead.png")));
		}
		return fantomedead;
	}
	
	public static TextureRegion getTextureBloc(){
		if( bloc == null ){
			Pixmap bloc48 = new Pixmap(Gdx.files.internal("bloc.png"));
			Pixmap bloc16 = new Pixmap(WorldRenderer.ppuX,WorldRenderer.ppuX, bloc48.getFormat());
			bloc16.drawPixmap(bloc48, 0,0, bloc48.getWidth(), bloc48.getHeight(),
					0,0,bloc16.getWidth(),bloc16.getHeight());
			bloc = new TextureRegion(new Texture(bloc16));
		}
		return bloc;
	}
	
	public static TextureRegion iTexturable(GameElement ge){
		if(ge instanceof Pacman){
			if(((Pacman) ge).isRightMove())
				return animationPacmanRight();
			else if(((Pacman) ge).isUpMove())
				return animationPacmanUp();
			else if(((Pacman) ge).isLeftMove())
				return animationPacmanLeft();
			else if(((Pacman) ge).isDownMove())
				return animationPacmanDown();
			else{
				return regionCourante;
			}
		}
		else if(ge instanceof Block){
			return getTextureBloc();
		}
		else if(ge instanceof Gomme){
			if(((Gomme) ge).estManger){
				return getTextureVide();
			}
			else{
				return getTextureGomme();
			}
		}
		else if(ge instanceof SuperGomme){
			if(((SuperGomme) ge).estManger){
				return getTextureVide();
			}
			else{
				return animationGomme();
			}
		}
		else if(ge instanceof Fantome1){
			if(((Fantomes) ge).estMangable)
				if(ge.getWorld().getTempsFinFantomeFaible())
					return animationEscape1();
				else
					return getTextureFantomeEscpae();
			else if(((Fantomes) ge).estMort)
				return getTextureFantomeDEAD();
			else{
				return getTextureFantome1();
			}
		}
		else if(ge instanceof Fantome2){
			if(((Fantomes) ge).estMangable)
				if(ge.getWorld().getTempsFinFantomeFaible())
					return animationEscape2();
				else
					return getTextureFantomeEscpae();
			else if(((Fantomes) ge).estMort)
				return getTextureFantomeDEAD();
			else{
				return getTextureFantome2();
			}
		}
		else if(ge instanceof Fantome3){
			if(((Fantomes) ge).estMangable)
				if(ge.getWorld().getTempsFinFantomeFaible())
					return animationEscape3();
				else
					return getTextureFantomeEscpae();
			else if(((Fantomes) ge).estMort)
				return getTextureFantomeDEAD();
			else{
				return getTextureFantome3();
			}
		}
		else if(ge instanceof DoorGhost){
			return getTextureDoorGhost();
		}
		return null;
	}
	

	private static float timeAnim = 1f/5f;
	
	private static TextureRegion animationPacmanLeft() {
		if( tabRegionLeft == null){
			tabRegionLeft = new TextureRegion[4];
			tabRegionLeft[0] = new TextureRegion(new Texture(Gdx.files.internal("pacman-3.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionLeft[1] = new TextureRegion(new Texture(Gdx.files.internal("pacmanLeft.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionLeft[2] = new TextureRegion(new Texture(Gdx.files.internal("pacmanLeft-2.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionLeft[3] = new TextureRegion(new Texture(Gdx.files.internal("pacmanLeft.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			animationLeft = new Animation<TextureRegion>(timeAnim, tabRegionLeft);
		}
		
		temps += Gdx.graphics.getDeltaTime();
		regionCourante = animationLeft.getKeyFrame(temps, true);		
		return regionCourante;
	}

	private static TextureRegion animationPacmanUp() {
		if( tabRegionUp == null){
			tabRegionUp = new TextureRegion[4];
			tabRegionUp[0] = new TextureRegion(new Texture(Gdx.files.internal("pacman-3.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionUp[1] = new TextureRegion(new Texture(Gdx.files.internal("pacmanUp.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionUp[2] = new TextureRegion(new Texture(Gdx.files.internal("pacmanUp-2.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionUp[3] = new TextureRegion(new Texture(Gdx.files.internal("pacmanUp.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			animationUp = new Animation<TextureRegion>(timeAnim, tabRegionUp);
		}
		
		temps += Gdx.graphics.getDeltaTime();
		regionCourante = animationUp.getKeyFrame(temps, true);		
		return regionCourante;
	}

	private static TextureRegion animationPacmanRight() {
		if( tabRegionRight == null){
			tabRegionRight = new TextureRegion[4];
			tabRegionRight[0] = new TextureRegion(new Texture(Gdx.files.internal("pacman-3.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionRight[1] = new TextureRegion(new Texture(Gdx.files.internal("pacmanRight.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionRight[2] = new TextureRegion(new Texture(Gdx.files.internal("pacmanRight-2.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionRight[3] = new TextureRegion(new Texture(Gdx.files.internal("pacmanRight.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			animationRight = new Animation<TextureRegion>(timeAnim, tabRegionRight);
		}
		
		temps += Gdx.graphics.getDeltaTime();
		regionCourante = animationRight.getKeyFrame(temps, true);		
		return regionCourante;
	}

	private static TextureRegion animationPacmanDown(){
		
		if( tabRegionDown == null){
			tabRegionDown = new TextureRegion[4];
			tabRegionDown[0] = new TextureRegion(new Texture(Gdx.files.internal("pacman-3.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionDown[1] = new TextureRegion(new Texture(Gdx.files.internal("pacmanDown.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionDown[2] = new TextureRegion(new Texture(Gdx.files.internal("pacmanDown-2.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionDown[3] = new TextureRegion(new Texture(Gdx.files.internal("pacmanDown.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			animationDown = new Animation<TextureRegion>(timeAnim, tabRegionDown);
		}
		
		temps += Gdx.graphics.getDeltaTime();
		regionCourante = animationDown.getKeyFrame(temps, true);		
		return regionCourante;
	}
	
	private static TextureRegion animationGomme() {
		if( tabRegionGomme == null){
			tabRegionGomme = new TextureRegion[2];
			tabRegionGomme[0] = new TextureRegion(new Texture(Gdx.files.internal("superpellet.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionGomme[1] = new TextureRegion(new Texture(Gdx.files.internal("superpellet-2.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			animationGomme = new Animation<TextureRegion>(2f/1f, tabRegionGomme);
		}
		
		temps += Gdx.graphics.getDeltaTime();
		regionCourante2 = animationGomme.getKeyFrame(temps, true);		
		return regionCourante2;
	}
	
	private static TextureRegion animationEscape1() {
		if( tabRegionFantome1 == null){
			tabRegionFantome1 = new TextureRegion[2];
			tabRegionFantome1[0] = new TextureRegion(new Texture(Gdx.files.internal("ghostEscaping.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionFantome1[1] = new TextureRegion(new Texture(Gdx.files.internal("ghost1.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			animationFantome1 = new Animation<TextureRegion>(1f/2f, tabRegionFantome1);
		}
		
		temps += Gdx.graphics.getDeltaTime();
		regionCourante3 = animationFantome1.getKeyFrame(temps, true);		
		return regionCourante3;
	}
	
	private static TextureRegion animationEscape2() {
		if( tabRegionFantome2 == null){
			tabRegionFantome2 = new TextureRegion[2];
			tabRegionFantome2[0] = new TextureRegion(new Texture(Gdx.files.internal("ghostEscaping.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionFantome2[1] = new TextureRegion(new Texture(Gdx.files.internal("ghost2.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			animationFantome2 = new Animation<TextureRegion>(1f/2f, tabRegionFantome2);
		}
		
		temps += Gdx.graphics.getDeltaTime();
		regionCourante4 = animationFantome2.getKeyFrame(temps, true);		
		return regionCourante4;
	}
	
	private static TextureRegion animationEscape3() {
		if( tabRegionFantome3 == null){
			tabRegionFantome3 = new TextureRegion[2];
			tabRegionFantome3[0] = new TextureRegion(new Texture(Gdx.files.internal("ghostEscaping.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			tabRegionFantome3[1] = new TextureRegion(new Texture(Gdx.files.internal("ghost3.png")),WorldRenderer.ppuX,WorldRenderer.ppuX);
			animationFantome3 = new Animation<TextureRegion>(1f/2f, tabRegionFantome3);
		}
		
		temps += Gdx.graphics.getDeltaTime();
		regionCourante5 = animationFantome3.getKeyFrame(temps, true);		
		return regionCourante5;
	}
}
