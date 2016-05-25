package com.strategy.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;
import com.strategy.game.screens.sidebar.Sidebar;
import com.strategy.game.world.World;

/**
 * Created by Amedeo on 25/05/16.
 */
public class Settings extends Table {

    public Settings(final GameScreen screen) {
        Assets.setBackground(this, Assets.resourcesBarBg);
        setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for (Actor actor : screen.getStage().getActors()) {
            actor.setVisible(false);
        }
        screen.getWorld().toggleRunning();

        // TITLE
        Label title = Assets.makeLabel("Settings", Utils.FONT_HUGE_WHITE);
        addActor(title);
        Assets.setPositionRelative(title, 0.5f, 0.7f, true, true);

        // BUTTON BACK
        GameButton button = new GameButton(Assets.sidebarBuildBack);
        addActor(button);
        Assets.setSizeRelative(button, 0.05f, 0.05f);
        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                remove();
                for (Actor actor : screen.getStage().getActors()) {
                    actor.setVisible(true);
                }
                screen.getWorld().toggleRunning();
                return false;
            }
        });

        // CONTAINER TABLE
        Table container = new Table();

        // VOLUME
        Label volumeLabel = Assets.makeLabel("Volume:", Utils.FONT_BIG_WHITE);
        container.add(volumeLabel).expand().uniform().right();

        Slider volumeSlider = new Slider(0, 10, 1, false, new Slider.SliderStyle(
                        new SpriteDrawable(new Sprite(Assets.settingsSliderBg)),
                        new SpriteDrawable(new Sprite(Assets.settingsSliderKnob))));
        container.add(volumeSlider).expand().uniform();

        container.row();

        // GAME SPEED
        Label speedLabel = Assets.makeLabel("Game speed:", Utils.FONT_BIG_WHITE);
        container.add(speedLabel).expand().right();

        Table speedButtons = new Table();
        GameButton speedNormal = new GameButton(Assets.settingsSpeedNormal);
        speedNormal.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                screen.getWorld().setGameSpeed(World.GameSpeed.NORMAL);
                return false;
            }
        });
        GameButton speedFast = new GameButton(Assets.settingsSpeedFast);
        speedFast.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                screen.getWorld().setGameSpeed(World.GameSpeed.FAST);
                return false;
            }
        });
        speedButtons.add(speedNormal).padRight(10);
        speedButtons.add(speedFast);
        container.add(speedButtons).expand();
        Assets.setSizeRelative(speedButtons, 0.3f, 0.5f);

        container.row();

        // RESOLUTION
        Label resolutionLabel = Assets.makeLabel("Resolution:", Utils.FONT_BIG_WHITE);
        container.add(resolutionLabel).expand().right();

        Table resolutionButtons = new Table();
        GameButton res720p = new GameButton(Assets.settingsRes720p);
        res720p.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                screen.getWorld().setGameSpeed(World.GameSpeed.NORMAL);
                return false;
            }
        });
        GameButton res1080p = new GameButton(Assets.settingsRes1080p);
        res1080p.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                screen.getWorld().setGameSpeed(World.GameSpeed.FAST);
                return false;
            }
        });
        GameButton resFull = new GameButton(Assets.settingsResFull);
        resFull.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                screen.getWorld().setGameSpeed(World.GameSpeed.FAST);
                return false;
            }
        });
        resolutionButtons.add(res720p).padRight(10);
        resolutionButtons.add(res1080p).padRight(10);
        resolutionButtons.add(resFull);
        container.add(resolutionButtons).expand();


        addActor(container);
        Assets.setSizeRelative(container, 0.3f, 0.2f);
        Assets.setPositionRelative(container, 0.5f, 0.5f, true, true);
    }
}
