package com.strategy.game.screens;

import com.badlogic.gdx.Game;
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

    public FullScreen(final GameScreen screen, Texture texture) {

        Assets.setBackground(this, texture);

        Stage stage = screen.getStage();
        setSize(stage.getWidth(), stage.getHeight());

        for (Actor actor : screen.getStage().getActors()) {
            actor.setVisible(false);
        }
        screen.getWorld().toggleRunning();

        GameButton back = new GameButton(Assets.sidebarBuildBack);
        addActor(back);
        Assets.setSizeRelative(back, 0.03f, 0.03f * getWidth() / getHeight());
        back.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                remove();
                for (Actor actor : screen.getStage().getActors()) {
                    actor.setVisible(true);
                }
                screen.getWorld().toggleRunning();
                return false;
            }
        });
    }

    public FullScreen(Game game, Texture texture) {
        final MainMenuScreen screen = (MainMenuScreen) game.getScreen();

        Assets.setBackground(this, texture);

        Stage stage = screen.getStage();
        setSize(stage.getWidth(), stage.getHeight());

        for (Actor actor : screen.getStage().getActors()) {
            actor.setVisible(false);
        }
//
        GameButton back = new GameButton(Assets.sidebarBuildBack);
        addActor(back);
        Assets.setSizeRelative(back, 0.03f, 0.03f * getWidth() / getHeight());
        back.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                remove();
                for (Actor actor : screen.getStage().getActors()) {
                    actor.setVisible(true);
                }
                return false;
            }
        });
    }
}
