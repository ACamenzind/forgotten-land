package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.strategy.game.Assets;

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

        SpriteDrawable buildingBg = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("core/assets/test.jpg"))));
        this.setBackground(buildingBg);

        displayTop = new DisplaySidebarMenu(stage);
        this.addActor(displayTop);

        updatePosition();
    }

    @Override
    public void updatePosition() {
//        setSize(Gdx.graphics.getWidth() * 0.15f, Gdx.graphics.getHeight());
        setSize(stage.getWidth() * 0.15f, stage.getHeight());
        setPosition(stage.getWidth() - getWidth(), 0);

        Assets.setSizeRelative(displayTop, 1f, DISPLAY_TOP_HEIGHT);
        Assets.setPositionRelative(displayTop, 0f, 1f - DISPLAY_TOP_HEIGHT);

        ((Display) displayTop).updatePosition();
    }
}
