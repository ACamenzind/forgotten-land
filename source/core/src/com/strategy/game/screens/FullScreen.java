package com.strategy.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Created by Amedeo on 25/05/16.
 */
public class FullScreen extends Table {

    public FullScreen(final Stage stage, Texture texture, Runnable onClose) {

        Assets.setBackground(this, texture);

        setSize(stage.getWidth(), stage.getHeight());

        for (Actor actor : stage.getActors()) {
            actor.setVisible(false);
        }

        GameButton back = new GameButton(Assets.getTexture("goBack"));
        addActor(back);
        Assets.setSizeRelative(back, 0.02f, 0.02f * getWidth() / getHeight());

        back.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                remove();
                for (Actor actor : stage.getActors()) {
                    actor.setVisible(true);
                }
                onClose.run();
                return false;
            } });
        back.setPosition(getWidth() - back.getWidth() -10, getHeight() - back.getHeight() -10);
    }
}
