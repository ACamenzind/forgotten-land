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
                config.width = DesktopLauncher.DEFAULT_WIDTH;
                config.height = DesktopLauncher.DEFAULT_HEIGHT;
                config.useHDPI = NewUtils.HDPI; // Change only this variable in Utils to turn on/off HDPI, don't touch the others.
                config.title = "A Strategy Game [Working title]";
                config.vSyncEnabled = true;
                config.resizable = false;
                new LwjglApplication(new StrategyGame(), config);
            }
        } );

        JButton NormalButton = new JButton("Normal Dispay"); // construct a JButton
        add( NormalButton );                     // add the button to the JFrame
        NormalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewUtils.HDPI = false;
                LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = DesktopLauncher.DEFAULT_WIDTH;
                config.height = DesktopLauncher.DEFAULT_HEIGHT;
                config.useHDPI = NewUtils.HDPI; // Change only this variable in Utils to turn on/off HDPI, don't touch the others.
                config.title = "A Strategy Game [Working title]";
                config.vSyncEnabled = true;
                config.resizable = false;
                new LwjglApplication(new StrategyGame(), config);
            }
        } );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}