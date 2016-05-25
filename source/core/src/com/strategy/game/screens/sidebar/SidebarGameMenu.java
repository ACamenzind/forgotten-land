package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;
import com.strategy.game.screens.FullScreen;

/**
 * Created by Amedeo on 07/05/16.
 */
public class SidebarGameMenu extends SidebarOptionsSelection {

    private static final GameButton loadGame = new GameButton(Assets.sidebarMenuLoad);
    private static final GameButton newGame = new GameButton(Assets.sidebarMenuNew);
    private static final GameButton saveGame = new GameButton(Assets.sidebarMenuSave);
    private static final GameButton quitGame = new GameButton(Assets.sidebarMenuQuit);
    private static final GameButton credits = new GameButton(Assets.sidebarMenuCredits);

    public SidebarGameMenu() {

        Assets.setBackground(this, Assets.sidebarBgMenu);

        title = Assets.makeLabel("Menu", Utils.FONT_BIG_WHITE);
        addActor(title);

        buttons[0][0] = newGame;
        addActor(newGame);

        buttons[1][0] = loadGame;
        addActor(loadGame);

        buttons[2][0] = saveGame;
        addActor(saveGame);

        buttons[3][0] = quitGame;
        addActor(quitGame);

        buttons[4][0] = credits;
        credits.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Stage stage = ((Sidebar) getParent()).getScreen().getStage();
                stage.addActor(new FullScreen(stage, Assets.screenCredits));
                return false;
            }
        });
        addActor(credits);

        update();
        updatePosition();
    }
}
