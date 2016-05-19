package com.strategy.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.strategy.game.StrategyGame;
import com.strategy.game.Utils;

// TODO: add texture packing which generates texture atlases

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Utils.DEFAULT_WIDTH;
		config.height = Utils.DEFAULT_HEIGHT;
        config.useHDPI = Utils.HDPI;
		config.title = "A Strategy Game [Working title]";
		config.vSyncEnabled = true;
		config.resizable = true;
		new LwjglApplication(new StrategyGame(), config);
	}
}
