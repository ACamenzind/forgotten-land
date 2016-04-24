package com.strategy.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.strategy.game.StrategyGame;

// TODO: add texture packing which generates texture atlases

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.title = "A Strategy Game [Working title]";
		config.vSyncEnabled = true;
		new LwjglApplication(new StrategyGame(), config);
	}
}
