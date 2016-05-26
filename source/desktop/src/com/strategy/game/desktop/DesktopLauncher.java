package com.strategy.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.strategy.game.NewGame;
import com.strategy.game.StrategyGame;
import com.strategy.game.Utils;

// TODO: add texture packing which generates texture atlases

public class DesktopLauncher {
	public static boolean HDPI;
	public static void main (String[] arg) {
		NewGame frame = new NewGame("Resolution");
		frame.setSize( 250, 100 );
		frame.setVisible( true );
	}
}
