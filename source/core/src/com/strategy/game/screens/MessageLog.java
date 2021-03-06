package com.strategy.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;

/**
 * Created by Amedeo on 25/05/16.
 */
public class MessageLog extends Table {

    public enum MessageType { NEW_GAME, QUIT, GAME_OVER }

    private Label message;
    private GameButton ok = new GameButton(Assets.getTexture("messageOk"));
    private GameButton cancel = new GameButton(Assets.getTexture("messageCancel"));

    public MessageLog(final GameScreen screen, MessageType type) {

        Assets.setBackground(this, Assets.getTexture("resourcesBarBg"));
        setSize(Gdx.graphics.getWidth() / 3.6f, Gdx.graphics.getHeight() / 5f);
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2f, (Gdx.graphics.getHeight() - getHeight()) / 2f);

        if (type == MessageType.GAME_OVER) {
            message = Assets.makeLabel("You lost. Press 'OK' to start a new game.", Utils.FONT_MEDIUM_WHITE);
            ok.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    screen.dispose();
                    screen.getGame().setScreen(new GameScreen(screen.getGame()));
                    return false;
                }
            });
            addActor(ok);
            Assets.setSizeRelative(ok, 0.175f, 0.2f);
            Assets.setPositionRelative(ok, 0.5f, 0.2f, true, false);
        }
        else {
            if (type == MessageType.NEW_GAME) {
                message = Assets.makeLabel("Are you sure you want to start a new game?", Utils.FONT_MEDIUM_WHITE);
                ok.addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        screen.dispose();
                        screen.getGame().setScreen(new GameScreen(screen.getGame()));
                        return false;
                    }
                });
            } else if (type == MessageType.QUIT) {
                message = Assets.makeLabel("Are you sure you want to quit the game?", Utils.FONT_MEDIUM_WHITE);
                ok.addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        screen.dispose();
                        screen.getGame().setScreen(new MainMenuScreen(screen.getGame()));
                        return false;
                    }
                });
            }
            cancel.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    setVisible(false);
                    return false;
                }
            });
            addActor(ok);
            Assets.setSizeRelative(ok, 0.175f, 0.2f);
            Assets.setPositionRelative(ok, 0.3f, 0.2f, true, false);

            addActor(cancel);
            Assets.setSizeRelative(cancel, 0.175f, 0.2f);
            Assets.setPositionRelative(cancel, 0.7f, 0.2f, true, false);
        }

        addActor(message);
        Assets.setPositionRelative(message, 0.5f, 0.65f, true, true);
    }
}
