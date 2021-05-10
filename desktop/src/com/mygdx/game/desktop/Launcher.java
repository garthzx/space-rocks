package com.mygdx.game.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class Launcher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.forceExit = false;
		config.title = "Space Rocks";
		config.width = 800;
		config.height = 600;
		Game myGame = new SpaceGame();
		LwjglApplication launcher = new LwjglApplication(myGame, config);
	}
}
