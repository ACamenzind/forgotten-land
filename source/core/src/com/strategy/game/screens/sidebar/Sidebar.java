package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;

/**
 * Created by Amedeo on 02/05/16.
 */
public class Sidebar extends Table implements Display {
    Stage stage;

    private Actor displayTop;
    private Actor displayMiddle;
    private Actor displayBottom;

    private static final float DISPLAY_TOP_HEIGHT = 0.05f;


    public Sidebar(final Stage stage) {
        this.stage = stage;
        this.stage.addActor(this);

        Assets.setBackground(this, "core/assets/GameScreenTextures/sidebar.png");

        displayTop = new SidebarMenu(stage);
        addActor(displayTop);

        displayMiddle = new SidebarBuild((stage));
        addActor(displayMiddle);

        displayBottom = new SidebarBuildingInfo(stage);
        addActor(displayBottom);

        updatePosition();
    }

    public void setDisplayInfo() {
        displayMiddle.remove();
        displayMiddle = new SidebarGameInfo(stage);
        addActor(displayMiddle);
        updatePosition();
    }

    public void setDisplayBuildings() {
        displayMiddle.remove();
        displayMiddle = new SidebarBuild(stage);
        addActor(displayMiddle);
        updatePosition();
    }

    public void setDisplayMainMenu() {
        displayMiddle.remove();
        displayMiddle = new SidebarGameMenu(stage);
        addActor(displayMiddle);
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
