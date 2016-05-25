package com.strategy.game.screens;

import com.badlogic.gdx.Game;
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
        Stage stage = screen.getStage();
        setSize(stage.getWidth(), stage.getHeight());

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
        Assets.setSizeRelative(button, 0.05f, 0.05f * getWidth() / getHeight());
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

        // RESOLUTION
        Label resolutionLabel = Assets.makeLabel("Resolution:", Utils.FONT_BIG_WHITE);
        container.add(resolutionLabel).expand().right();

        Table resolutionButtons = new Table();
        GameButton res720p = new GameButton(Assets.settingsRes720p);
        res720p.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.graphics.setWindowedMode(1280, 720);
                return false;
            }
        });
        GameButton res1080p = new GameButton(Assets.settingsRes1080p);
        res1080p.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.graphics.setWindowedMode(1920, 1080);
                return false;
            }
        });
        GameButton resFull = new GameButton(Assets.settingsResFull);
        resFull.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.graphics.setWindowedMode((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                        (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight());
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
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

    public Settings(Game game) {
        final MainMenuScreen screen = (MainMenuScreen) game.getScreen();

        Assets.setBackground(this, Assets.resourcesBarBg);

        Stage stage = screen.getStage();
        setSize(stage.getWidth(), stage.getHeight());

        for (Actor actor : screen.getStage().getActors()) {
            actor.setVisible(false);
        }

        // TITLE
        Label title = Assets.makeLabel("Settings", Utils.FONT_HUGE_WHITE);
        addActor(title);
        Assets.setPositionRelative(title, 0.5f, 0.7f, true, true);

        // BUTTON BACK
        GameButton button = new GameButton(Assets.sidebarBuildBack);
        addActor(button);
        Assets.setSizeRelative(button, 0.05f, 0.05f * getWidth() / getHeight());
        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                remove();
                for (Actor actor : screen.getStage().getActors()) {
                    actor.setVisible(true);
                }
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

        // RESOLUTION
        Label resolutionLabel = Assets.makeLabel("Resolution:", Utils.FONT_BIG_WHITE);
        container.add(resolutionLabel).expand().right();

        Table resolutionButtons = new Table();
        GameButton res720p = new GameButton(Assets.settingsRes720p);
        res720p.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.graphics.setWindowedMode(1280, 720);
                return false;
            }
        });
        GameButton res1080p = new GameButton(Assets.settingsRes1080p);
        res1080p.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.graphics.setWindowedMode(1920, 1080);
                return false;
            }
        });
        GameButton resFull = new GameButton(Assets.settingsResFull);
        resFull.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.graphics.setWindowedMode((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                        (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight());
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
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
