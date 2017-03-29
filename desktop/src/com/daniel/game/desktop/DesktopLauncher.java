package com.daniel.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.daniel.game.PacmanGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Pac-Man";
		config.width = 600;
		config.height = 496;
		config.resizable = false;
		new LwjglApplication(new PacmanGame(), config);
	}
}
