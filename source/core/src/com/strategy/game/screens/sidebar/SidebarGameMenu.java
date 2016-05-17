package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;

/**
 * Created by Amedeo on 07/05/16.
 */
public class SidebarGameMenu extends SidebarOptionsSelection {

    private static final GameButton loadGame = new GameButton("core/assets/textures/gameScreen/sidebar_main_load_game.png");
    private static final GameButton newGame = new GameButton("core/assets/textures/gameScreen/sidebar_main_new_game.png");
    private static final GameButton saveGame = new GameButton("core/assets/textures/gameScreen/sidebar_main_save_game.png");
    private static final GameButton quitGame = new GameButton("core/assets/textures/gameScreen/sidebar_main_quit_game.png");

    public SidebarGameMenu() {

        title = Assets.makeLabel("Menu", 20, Color.BLACK);
        addActor(title);

        buttons[0][0] = loadGame;
        addActor(loadGame);

        buttons[1][0] = newGame;
        addActor(newGame);

        buttons[2][0] = saveGame;
        addActor(saveGame);

        buttons[3][0] = quitGame;
        addActor(quitGame);

        update();
    }
}
