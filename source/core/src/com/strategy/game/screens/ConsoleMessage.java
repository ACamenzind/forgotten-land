package com.strategy.game.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.Utils;

/**
 * Created by Amedeo on 25/05/16.
 */
public class ConsoleMessage extends Table {

    private Label message;
    private boolean active = false;
    private float time = 0;
    private static final float MAX_TIME = 5;

    public ConsoleMessage(Stage stage) {

        setSize(stage.getWidth() * 0.85f, stage.getHeight() * 0.05f);
        Assets.setBackground(this, Assets.bottomBarBg);

        message = Assets.makeLabel("", Utils.FONT_MEDIUM_RED);
        addActor(message);
        Assets.setPositionRelative(message, 0.5f, 0.5f, true, true);

        setVisible(false);
    }

    public void setMessage(String text) {
        message.remove();
        message = Assets.makeLabel(text, Utils.FONT_MEDIUM_RED);
        addActor(message);
        Assets.setPositionRelative(message, 0.5f, 0.5f, true, true);
        setVisible(true);
        active = true;
    }

    public void update(float delta) {
        if (active) {
            time += delta;
            if (time > MAX_TIME) {
                active = false;
                setVisible(false);
                time = 0;
            }
        }
    }
}
