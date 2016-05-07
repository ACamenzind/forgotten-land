package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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

        Assets.setBackground(this, "core/assets/GameScreenTextures/sidebar.png");

        displayTop = new DisplaySidebarMenu(stage);
        addActor(displayTop);

        displayMiddle = new DisplayBuildings((stage));
        addActor(displayMiddle);

        displayBottom = new DisplayBuildingInfo(stage);
        addActor(displayBottom);

        updatePosition();
    }

    @Override
    public void updatePosition() {
        setSize(stage.getWidth() * 0.15f, stage.getHeight());
        setPosition(stage.getWidth() - getWidth(), 0);

        Assets.setSizeRelative(displayTop, 1f, DISPLAY_TOP_HEIGHT);
        Assets.setPositionRelative(displayTop, 0, 1f - DISPLAY_TOP_HEIGHT);

        Assets.setSizeRelative(displayBottom, 1f, getWidth() / getHeight());
        Assets.setPositionRelative(displayBottom, 0, 0);

        Assets.setSizeRelative(displayMiddle, 1f, 1f - DISPLAY_TOP_HEIGHT - (getWidth() / getHeight()));
        Assets.setPositionRelative(displayMiddle, 0, displayBottom.getHeight() / getHeight());

        ((Display) displayTop).updatePosition();
        ((Display) displayMiddle).updatePosition();
        ((Display) displayBottom).updatePosition();
    }
}
