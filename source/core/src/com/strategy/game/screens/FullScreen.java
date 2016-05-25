package com.strategy.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;

/**
 * Created by Amedeo on 25/05/16.
 */
public class FullScreen extends Table {

    public FullScreen(final Stage stage, Texture texture) {
        Assets.setBackground(this, texture);
        setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for (Actor actor : stage.getActors()) {
            actor.setVisible(false);
        }
        setVisible(true);

        GameButton button = new GameButton(Assets.sidebarBuildBack);
        addActor(button);
        Assets.setSizeRelative(button, 0.05f, 0.05f);
        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                remove();
                for (Actor actor : stage.getActors()) {
                    actor.setVisible(true);
                }
                return false;
            }
        });

    }
}
