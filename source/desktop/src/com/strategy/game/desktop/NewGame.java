package com.strategy.game.desktop;
//import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
//import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.strategy.game.NewUtils;
import com.strategy.game.StrategyGame;
import com.strategy.game.Utils;
import com.strategy.game.desktop.DesktopLauncher;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by samuelebischof on 26/05/16.
 */
public class NewGame extends JFrame implements ActionListener{

    public NewGame(String title) {

        super( title );                     // invoke the JFrame constructor
        setLayout( new FlowLayout() );      // set the layout manager

        JButton RetinaButton = new JButton("Retina Display"); // construct a JButton
        add( RetinaButton );                     // add the button to the JFrame
        RetinaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewUtils.HDPI = true;
                LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = ((9/16 * screenSize.getWidth()) > (screenSize.getHeight()) ?
                        (int) (16/9 * (screenSize.getHeight())) : (int) (screenSize.getWidth()));
                config.width = width - (80* 16/9);
                config.height = (width * 9/16) - 80;
                config.useHDPI = NewUtils.HDPI; // Change only this variable in Utils to turn on/off HDPI, don't touch the others.
                config.title = "The forgotten land";
                config.vSyncEnabled = true;
                config.resizable = false;
                new LwjglApplication(new StrategyGame(), config);
                NewGame.super.dispose();
            }
        } );

        JButton NormalButton = new JButton("Normal Display"); // construct a JButton
        add( NormalButton );                     // add the button to the JFrame
        NormalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewUtils.HDPI = false;
                LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = ((9/16 * screenSize.getWidth()) > (screenSize.getHeight())) ?
                        (int) (16/9 * (screenSize.getHeight())) : (int) (screenSize.getWidth());
                config.width = width - (80* 16/9);
                config.height = (width * 9/16) -80;
                config.useHDPI = NewUtils.HDPI; // Change only this variable in Utils to turn on/off HDPI, don't touch the others.
                config.title = "The forgotten land";
                config.vSyncEnabled = true;
                config.resizable = false;
                new LwjglApplication(new StrategyGame(), config);
                NewGame.super.dispose();
            }
        } );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}