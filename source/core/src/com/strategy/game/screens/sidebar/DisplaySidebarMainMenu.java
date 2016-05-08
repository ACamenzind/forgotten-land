package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;

/**
 * Created by Amedeo on 07/05/16.
 */
public class DisplaySidebarMainMenu extends Table implements Display {

    private Stage stage;

    private static final Label menu = Assets.makeLabel("Menu", 20, Color.BLACK);

    private static final GameButton loadGame = new GameButton("core/assets/GameScreenTextures/sidebar_main_load_game.png");
    private static final GameButton newGame = new GameButton("core/assets/GameScreenTextures/sidebar_main_new_game.png");
    private static final GameButton saveGame = new GameButton("core/assets/GameScreenTextures/sidebar_main_save_game.png");
    private static final GameButton quitGame = new GameButton("core/assets/GameScreenTextures/sidebar_main_quit_game.png");

    private static final float MARGIN = 0.1f;
    private static final float BUTTON_HEIGHT = 0.04f;
    private static final float BUTTON_WIDTH = 1f - MARGIN * 2f;

    public DisplaySidebarMainMenu(Stage stage) {
        this.stage = stage;

        Assets.setBackground(this, "core/assets/GameScreenTextures/sidebar_build.png");

        addActor(menu);
        addActor(loadGame);
        addActor(newGame);
        addActor(saveGame);
        addActor(quitGame);

        updatePosition();
    }

    @Override
    public void updatePosition() {
        Assets.setPositionRelative(menu, 0.5f, 1f - BUTTON_HEIGHT * 2, true, true);

        Assets.setSizeRelative(loadGame, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(loadGame, 0.5f, 1f - BUTTON_HEIGHT * 4, true, true);

        Assets.setSizeRelative(newGame, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(newGame, 0.5f, 1f - BUTTON_HEIGHT * 6, true, true);

        Assets.setSizeRelative(saveGame, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(saveGame, 0.5f, 1f - BUTTON_HEIGHT * 8, true, true);

        Assets.setSizeRelative(quitGame, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(quitGame, 0.5f, 1f - BUTTON_HEIGHT * 10, true, true);
    }
}
