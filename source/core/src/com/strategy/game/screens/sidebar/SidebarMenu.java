package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;

/**
 * Created by Amedeo on 02/05/16.
 */
public class SidebarMenu extends Table implements Display {
    private GameButton gameInfoButton;
    private GameButton buildingsButton;
    private GameButton mainMenuButton;

    private static final int BUTTONS_NUMBER = 3;
    private static final float MARGIN = 0;//.05f;
    private static final float BUTTON_WIDTH = 1f / 3f;//(1f - (MARGIN * (BUTTONS_NUMBER + 1f))) / BUTTONS_NUMBER;
    private static final float BUTTON_HEIGHT = 1f;//0.75f;


    public SidebarMenu() {

        gameInfoButton = new GameButton("core/assets/textures/gameScreen/sidebar_menu_info.png");
        gameInfoButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (hasParent()) {
                    Sidebar sidebar = (Sidebar) getParent();
                    sidebar.setDisplayMiddle(Sidebar.DisplayType.GAME_INFO);
                }
                return true;
            }
        });
        addActor(gameInfoButton);

        buildingsButton = new GameButton("core/assets/textures/gameScreen/sidebar_menu_build.png");
        buildingsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (hasParent()) {
                    Sidebar sidebar = (Sidebar) getParent();
                    sidebar.setDisplayMiddle(Sidebar.DisplayType.BUILD);
                }
                return false;
            }
        });
        addActor(buildingsButton);

        mainMenuButton = new GameButton("core/assets/textures/gameScreen/sidebar_menu_menu.png");
        mainMenuButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (hasParent()) {
                    Sidebar sidebar = (Sidebar) getParent();
                    sidebar.setDisplayMiddle(Sidebar.DisplayType.GAME_MENU);
                }
                return false;
            }
        });
        addActor(mainMenuButton);

        updatePosition();
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
}
