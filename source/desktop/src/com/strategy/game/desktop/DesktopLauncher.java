package com.strategy.game.desktop;

import com.strategy.game.Utils;

import java.awt.*;

// TODO: add texture packing which generates texture atlases

public class DesktopLauncher {
	public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int DEFAULT_WIDTH = ((9/16 * screenSize.getWidth()) > screenSize.getHeight()) ?
			(int) (16/9 * screenSize.getHeight()) : (int) (screenSize.getWidth());
	public static final int DEFAULT_HEIGHT = (int) (DEFAULT_WIDTH * 9/16);
//	public static boolean HDPI;

	public static void main (String[] arg) {
		int width = 250;
		int height = 100;
		NewGame frame = new NewGame("Resolution");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2 - width/2, dim.height/2-frame.getSize().height/2 - height/2);
		frame.setSize( width, height );
		frame.setVisible( true );
	}
}
