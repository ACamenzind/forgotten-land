package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.screens.Display;

/**
 * Created by Amedeo on 02/05/16.
 */
public class SidebarMenu extends Table implements Display {

    enum MenuButton { NONE, GAME_INFO_BUTTON, BUILD_BUTTON, MENU_BUTTON }

    private GameButton gameInfoButton = new GameButton(Assets.sidebarMenuInfo, Assets.sidebarMenuInfoClicked, Assets.sidebarMenuInfoClicked);
    private GameButton buildingsButton = new GameButton(Assets.sidebarMenuBuild, Assets.sidebarMenuBuildClicked, Assets.sidebarMenuBuildClicked);
    private GameButton mainMenuButton = new GameButton(Assets.sidebarMenuMenu, Assets.sidebarMenuMenuClicked, Assets.sidebarMenuMenuClicked);

    private static final float MARGIN = 0;
    private static final float BUTTON_WIDTH = 1f / 3f;
    private static final float BUTTON_HEIGHT = 1f;


    public SidebarMenu() {

        buildingsButton.setChecked(true);

        gameInfoButton.setDisabled(true);
        gameInfoButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (hasParent()) {
                    Sidebar sidebar = (Sidebar) getParent();
                    sidebar.setMiddle(Sidebar.DisplayType.GAME_INFO);
                }
                return true;
            }
        });
        addActor(gameInfoButton);

        buildingsButton.setDisabled(true);
        buildingsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (hasParent()) {
                    Sidebar sidebar = (Sidebar) getParent();
//                    sidebar.setMiddle(Sidebar.DisplayType.BUILD);
                    sidebar.setMiddle(Sidebar.DisplayType.BUILDINGS);
                }
                return false;
            }
        });
        addActor(buildingsButton);

        mainMenuButton.setDisabled(true);
        mainMenuButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (hasParent()) {
                    Sidebar sidebar = (Sidebar) getParent();
                    sidebar.setMiddle(Sidebar.DisplayType.GAME_MENU);
                }
                return false;
            }
        });
        addActor(mainMenuButton);

        update();
        updatePosition();
    }

    @Override
    public void update() {

    }

    @Override
    public void updatePosition() {
        Assets.setSizeRelative(gameInfoButton, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(gameInfoButton, MARGIN, 0.5f, false, true);

        Assets.setSizeRelative(buildingsButton, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(buildingsButton, MARGIN + BUTTON_WIDTH + MARGIN, 0.5f, false, true);

        Assets.setSizeRelative(mainMenuButton, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(mainMenuButton, MARGIN + BUTTON_WIDTH + MARGIN + BUTTON_WIDTH + MARGIN, 0.5f, false, true);
    }

    public void setSelectedButton(MenuButton button) {
        if (button == MenuButton.NONE) {
            gameInfoButton.setChecked(false);
            buildingsButton.setChecked(false);
            mainMenuButton.setChecked(false);
        }
        else if (button == MenuButton.GAME_INFO_BUTTON) {
            gameInfoButton.setChecked(true);
            buildingsButton.setChecked(false);
            mainMenuButton.setChecked(false);
        }
        else if (button == MenuButton.BUILD_BUTTON) {
            gameInfoButton.setChecked(false);
            buildingsButton.setChecked(true);
            mainMenuButton.setChecked(false);
        }
        else if (button == MenuButton.MENU_BUTTON) {
            gameInfoButton.setChecked(false);
            buildingsButton.setChecked(false);
            mainMenuButton.setChecked(true);
        }
    }
}
