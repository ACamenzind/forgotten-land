package com.strategy.game.desktop;

import java.awt.*;

// TODO: add texture packing which generates texture atlases

public class DesktopLauncher {
	public static boolean HDPI;
	public static void main (String[] arg) {
		NewGame frame = new NewGame("Resolution");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setSize( 250, 100 );
		frame.setVisible( true );
	}
}
