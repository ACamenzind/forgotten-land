package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.strategy.game.Assets;
import com.strategy.game.screens.DisplayBuildingInfo;

/**
 * Created by Amedeo on 02/05/16.
 */
public class DisplaySidebar extends Table implements Display {
    Stage stage;

    private Actor displayTop;
    private Actor displayMiddle;
    private Actor displayBottom;

    private static final float DISPLAY_TOP_HEIGHT = 0.05f;


    public DisplaySidebar(final Stage stage) {
        this.stage = stage;
        this.stage.addActor(this);

        Assets.setBackground(this, "core/assets/GameScreenTextures/sidebar.png");

        displayTop = new DisplaySidebarMenu(stage);
        this.addActor(displayTop);

        displayBottom = new DisplayBuildingInfo(stage);
        this.addActor(displayBottom);

        updatePosition();
    }

    @Override
    public void updatePosition() {
        setSize(stage.getWidth() * 0.15f, stage.getHeight());
        setPosition(stage.getWidth() - getWidth(), 0);

        Assets.setSizeRelative(displayTop, 1f, DISPLAY_TOP_HEIGHT);
        Assets.setPositionRelative(displayTop, 0f, 1f - DISPLAY_TOP_HEIGHT);

        Assets.setSizeRelative(displayBottom, 1f, getWidth() / getHeight());
        Assets.setPositionRelative(displayBottom, 0, 0);

        ((Display) displayTop).updatePosition();
        ((Display) displayBottom).updatePosition();
    }
}
