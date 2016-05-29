package com.strategy.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.StrategyGame;
import com.strategy.game.Utils;
import com.strategy.game.screens.sidebar.Sidebar;
import com.strategy.game.world.World;

import java.awt.*;

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
        GameButton button = new GameButton(Assets.goBack);
        addActor(button);
        Assets.setSizeRelative(button, 0.040f, 0.040f * getWidth() / getHeight());
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
        addActor(container);
        Assets.setSizeRelative(container, 0.3f, 0.2f);
        Assets.setPositionRelative(container, 0.5f, 0.5f, true, true);

        // VOLUME
        Label volumeLabel = Assets.makeLabel("Volume:", Utils.FONT_BIG_WHITE);
        container.add(volumeLabel).expand().uniform().right();

        final Slider volumeSlider = new Slider(0f, 1f, 0.1f, false, new Slider.SliderStyle(
                new SpriteDrawable(new Sprite(Assets.settingsSliderBg)),
                new SpriteDrawable(new Sprite(Assets.settingsSliderKnob))));
        volumeSlider.setValue(0.5f);

        volumeSlider.getStyle().knob.setMinHeight(container.getHeight() / 8f);
        volumeSlider.getStyle().knob.setMinWidth(volumeSlider.getStyle().knob.getMinHeight());
        volumeSlider.getStyle().background.setMinHeight(container.getHeight() * 0.1f);

        container.add(volumeSlider).expand().uniform().width(container.getWidth() / 3f);
        volumeSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                screen.getGame().getSoundManager().setMasterVolume(volumeSlider.getValue());
            }
        });

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
                Gdx.graphics.setWindowedMode((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                        (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()*9/16);
//                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                return false;
            }
        });

        float resolutionButtonWidth = container.getWidth() * 0.2f;
        float resolutionButtonHeight = container.getHeight() * 0.27f;
        float padding = container.getWidth() * 0.023f;
        float paddingLeft = container.getWidth() * 0.32f;
        resolutionButtons.add(res720p).padRight(padding).padLeft(paddingLeft).size(resolutionButtonWidth, resolutionButtonHeight);
        resolutionButtons.add(res1080p).padRight(padding).size(resolutionButtonWidth, resolutionButtonHeight);;
        resolutionButtons.add(resFull).size(resolutionButtonWidth, resolutionButtonHeight);;
        container.add(resolutionButtons).expand().uniform();


    }

    public Settings(final StrategyGame game) {
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
        GameButton button = new GameButton(Assets.goBack);
        addActor(button);
        Assets.setSizeRelative(button, 0.04f, 0.04f * getWidth() / getHeight());
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
        addActor(container);
        Assets.setSizeRelative(container, 0.3f, 0.2f);
        Assets.setPositionRelative(container, 0.5f, 0.5f, true, true);

        // VOLUME
        Label volumeLabel = Assets.makeLabel("Volume:", Utils.FONT_BIG_WHITE);
        container.add(volumeLabel).expand().uniform().right();

        final Slider volumeSlider = new Slider(0f, 1f, 0.1f, false, new Slider.SliderStyle(
                new SpriteDrawable(new Sprite(Assets.settingsSliderBg)),
                new SpriteDrawable(new Sprite(Assets.settingsSliderKnob))));
        volumeSlider.setValue(0.5f);

        volumeSlider.getStyle().knob.setMinHeight(container.getHeight() / 8f);
        volumeSlider.getStyle().knob.setMinWidth(volumeSlider.getStyle().knob.getMinHeight());
        volumeSlider.getStyle().background.setMinHeight(container.getHeight() * 0.1f);

        container.add(volumeSlider).expand().uniform().width(container.getWidth() / 3f);
        volumeSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.getSoundManager().setMasterVolume(volumeSlider.getValue());
            }
        });

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
                Gdx.graphics.setWindowedMode((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                        (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()*9/16);
//                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                return false;
            }
        });

        float resolutionButtonWidth = container.getWidth() * 0.2f;
        float resolutionButtonHeight = container.getHeight() * 0.27f;
        float padding = container.getWidth() * 0.023f;
        float paddingLeft = container.getWidth() * 0.32f;
        resolutionButtons.add(res720p).padRight(padding).padLeft(paddingLeft).size(resolutionButtonWidth, resolutionButtonHeight);
        resolutionButtons.add(res1080p).padRight(padding).size(resolutionButtonWidth, resolutionButtonHeight);;
        resolutionButtons.add(resFull).size(resolutionButtonWidth, resolutionButtonHeight);;
        container.add(resolutionButtons).expand().uniform();
    }
}
