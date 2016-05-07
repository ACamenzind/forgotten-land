package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;

/**
 * Created by Amedeo on 02/05/16.
 */
public class DisplaySidebarMenu extends Table implements Display {
    private Stage stage;
    private GameButton gameInfoButton;
    private GameButton buildingsButton;
    private GameButton mainMenuButton;

    private static final int BUTTONS_NUMBER = 3;
    private static final float MARGIN = 0.05f;
    private static final float BUTTON_WIDTH = (1f - (MARGIN * (BUTTONS_NUMBER + 1f))) / BUTTONS_NUMBER;
    private static final float BUTTON_HEIGHT = 0.75f;


    public DisplaySidebarMenu(Stage stage) {
        this.stage = stage;

        gameInfoButton = new GameButton("core/assets/GameScreenTextures/sidebar_menu_info.png");
        addActor(gameInfoButton);

        buildingsButton = new GameButton("core/assets/GameScreenTextures/sidebar_menu_build.png");
        addActor(buildingsButton);

        mainMenuButton = new GameButton("core/assets/GameScreenTextures/sidebar_menu_menu.png");
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
