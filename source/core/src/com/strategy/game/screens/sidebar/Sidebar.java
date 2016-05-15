package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.buildings.Building;
import com.strategy.game.screens.GameScreen;

/**
 * Created by Amedeo on 02/05/16.
 */
public class Sidebar extends Table implements Display {
    Stage stage;
    GameScreen screen;

    private Actor displayTop;
    private Actor displayMiddle;
    private Actor displayBottom;

    private static final float DISPLAY_TOP_HEIGHT = 0.05f;

    public enum DisplayType { GAME_INFO, BUILD, BUILD_RESOURCES_COLLECTORS, BUILD_SUPPORT, GAME_MENU }


    public Sidebar(final Stage stage, final GameScreen screen) {
        this.stage = stage;
        this.stage.addActor(this);
        this.screen = screen;

        Assets.setBackground(this, "core/assets/textures/gameScreen/sidebar.png");

        displayTop = new SidebarMenu();
        addActor(displayTop);

        displayMiddle = new SidebarBuild();
        addActor(displayMiddle);

        displayBottom = new SidebarBuildingInfo();
        addActor(displayBottom);

        updatePosition();
    }

    public void setDisplayMiddle(DisplayType display) {
        if (displayMiddle != null) {
            displayMiddle.remove();
        }
        screen.getBuilder().untoggleSelectEntity();

        if (display == DisplayType.GAME_INFO) {
            displayMiddle = new SidebarGameInfo();
        }
        else if (display == DisplayType.BUILD) {
            displayMiddle = new SidebarBuild();
        }
        else if (display == DisplayType.BUILD_RESOURCES_COLLECTORS) {
            displayMiddle = new SidebarBuildResourcesCollectors();
        }
        else if (display == DisplayType.BUILD_SUPPORT) {
            displayMiddle = new SidebarBuildingSupport();
        }
        else if (display == DisplayType.GAME_MENU) {
            displayMiddle = new SidebarGameMenu();
        }

        addActor(displayMiddle);
        updatePosition();
    }

    public GameScreen getScreen() {
        return screen;
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
