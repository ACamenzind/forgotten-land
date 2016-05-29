package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.buildings.Building;
import com.strategy.game.buildings.MapEntity;
import com.strategy.game.screens.Display;
import com.strategy.game.screens.GameScreen;

/**
 * Created by Amedeo on 02/05/16.
 */
public class Sidebar extends Table implements Display {
    Stage stage;
    GameScreen screen;

    private SidebarMenu menu;
    private Actor middle;
    private SidebarBuildingInfo buildingInfo;

    private static final float DISPLAY_TOP_HEIGHT = 0.05f;

    public enum DisplayType { GAME_INFO, BUILDINGS, BUILD, BUILD_MANUFACTURERS, BUILD_WAREHOUSES, BUILD_DECORATIONS,  GAME_MENU }


    public Sidebar(final Stage stage, final GameScreen screen) {
        this.stage = stage;
        this.stage.addActor(this);
        this.screen = screen;

        menu = new SidebarMenu();
        addActor(menu);

        middle = new SidebarBuildings();
        addActor(middle);

        buildingInfo = new SidebarBuildingInfo();
        addActor(buildingInfo);

        update();
        updatePosition();
    }

    public void setMiddle(DisplayType display) {
        if (middle != null) {
            middle.remove();
        }
        screen.getBuilder().removeSelectedEntity();

        if (display == DisplayType.GAME_INFO) {
            middle = new SidebarGameInfo(screen.getWorld());
            menu.setSelectedButton(SidebarMenu.MenuButton.GAME_INFO_BUTTON);
        }
        else if (display == DisplayType.BUILDINGS) {
            middle = new SidebarBuildings();
            menu.setSelectedButton(SidebarMenu.MenuButton.BUILD_BUTTON);
        }
        else if (display == DisplayType.BUILD) {
            middle = new SidebarBuild();
            menu.setSelectedButton(SidebarMenu.MenuButton.BUILD_BUTTON);
        }
        else if (display == DisplayType.BUILD_MANUFACTURERS) {
            middle = new SidebarBuildManufacturers();
            menu.setSelectedButton(SidebarMenu.MenuButton.NONE);
        }
        else if (display == DisplayType.BUILD_WAREHOUSES) {
            middle = new SidebarBuildWarehouses();
            menu.setSelectedButton(SidebarMenu.MenuButton.NONE);
        }
        else if (display == DisplayType.BUILD_DECORATIONS) {
            middle = new SidebarBuildDecorations();
            menu.setSelectedButton(SidebarMenu.MenuButton.NONE);
        }
        else if (display == DisplayType.GAME_MENU) {
            middle = new SidebarGameMenu();
            menu.setSelectedButton(SidebarMenu.MenuButton.MENU_BUTTON);
        }

        addActor(middle);
        update();
        updatePosition();
    }

    public void setEntity(MapEntity entity, boolean preview) {
        buildingInfo.setEntity(entity, preview);
    }

    public GameScreen getScreen() {
        return screen;
    }

    @Override
    public void update() {
        menu.update();
        ((Display) middle).update();
        buildingInfo.update();
    }

    @Override
    public void updatePosition() {
        setSize(stage.getWidth() * 0.15f, stage.getHeight());
        setPosition(stage.getWidth() - getWidth(), 0);

        Assets.setSizeRelative(menu, 1f, DISPLAY_TOP_HEIGHT);
        Assets.setPositionRelative(menu, 0, 1f - DISPLAY_TOP_HEIGHT);

        Assets.setSizeRelative(buildingInfo, 1f, getWidth() / getHeight());
        Assets.setPositionRelative(buildingInfo, 0, 0);

        Assets.setSizeRelative(middle, 1f, 1f - DISPLAY_TOP_HEIGHT - (getWidth() / getHeight()));
        Assets.setPositionRelative(middle, 0, buildingInfo.getHeight() / getHeight());

        menu.updatePosition();
        ((Display) middle).updatePosition();
        buildingInfo.updatePosition();
    }
}
