package com.myfirstgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myfirstgame.GameTest;
import com.myfirstgame.DesertBattle;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		// to change to game test only(without start screen) switch out DesertBattle with GameTest
		new LwjglApplication(new DesertBattle(), config);
	}
}
