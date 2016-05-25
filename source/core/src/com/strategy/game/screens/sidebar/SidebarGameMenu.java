package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;
import com.strategy.game.screens.FullScreen;
import com.strategy.game.screens.GameScreen;
import com.strategy.game.screens.MessageLog;

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
        newGame.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Sidebar sidebar = (Sidebar) getParent();
                if (sidebar != null) {
                    GameScreen screen = sidebar.getScreen();
                    Stage stage = screen.getStage();
                    sidebar.getScreen().getMessages().addActor(new MessageLog(stage, MessageLog.MessageType.NEW_GAME));
                }
                return false;
            }
        });
        addActor(newGame);

//        buttons[1][0] = loadGame;
//        addActor(loadGame);
//
//        buttons[2][0] = saveGame;
//        addActor(saveGame);

        buttons[1][0] = quitGame;
        quitGame.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Sidebar sidebar = (Sidebar) getParent();
                if (sidebar != null) {
                    GameScreen screen = sidebar.getScreen();
                    Stage stage = screen.getStage();
                    sidebar.getScreen().getMessages().addActor(new MessageLog(stage, MessageLog.MessageType.QUIT));
                }
                return false;
            }
        });
        addActor(quitGame);

        buttons[2][0] = credits;
        credits.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Sidebar sidebar = (Sidebar) getParent();
                if (sidebar != null) {
                    GameScreen screen = sidebar.getScreen();
                    Stage stage = screen.getStage();
                    stage.addActor(new FullScreen(screen, Assets.screenCredits));
                }
                return false;
            }
        });
        addActor(credits);

        update();
        updatePosition();
    }
}
