package com.strategy.game.desktop;

import java.awt.*;

// TODO: add texture packing which generates texture atlases

public class DesktopLauncher {
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
