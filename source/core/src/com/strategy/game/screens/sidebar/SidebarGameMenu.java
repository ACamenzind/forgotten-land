package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;
import com.strategy.game.screens.*;

/**
 * Created by Amedeo on 07/05/16.
 */
public class SidebarGameMenu extends SidebarOptionsSelection {

    private static final GameButton loadGame = new GameButton(Assets.getTexture("sidebarMenuLoad"));
    private static final GameButton newGame = new GameButton(Assets.getTexture("sidebarMenuNew"));
    private static final GameButton saveGame = new GameButton(Assets.getTexture("sidebarMenuSave"));
    private static final GameButton quitGame = new GameButton(Assets.getTexture("sidebarMenuQuit"));
    private static final GameButton credits = new GameButton(Assets.getTexture("sidebarMenuCredits"));
    private static final GameButton settings = new GameButton(Assets.getTexture("sidebarMenuSettings"));
    private static final GameButton instructions = new GameButton(Assets.getTexture("sidebarMenuInstructions"));

    public SidebarGameMenu() {

        Assets.setBackground(this, Assets.getTexture("sidebarBgMenu"));

        title = Assets.makeLabel("Menu", Utils.FONT_BIG_WHITE);
        addActor(title);

        buttons[0][0] = newGame;
        newGame.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Sidebar sidebar = (Sidebar) getParent();
                if (sidebar != null) {
                    sidebar.getScreen().toggleNewGame();
                }
                return false;
            }
        });
        addActor(newGame);

        buttons[1][0] = instructions;
        instructions.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Sidebar sidebar = (Sidebar) getParent();
                if (sidebar != null) {
                    GameScreen screen = sidebar.getScreen();
                    Stage stage = screen.getStage();
                    screen.getWorld().toggleRunning();
                    stage.addActor(new Instructions(screen.getStage(),()->screen.getWorld().toggleRunning())
                    );
                }
                return false;
            }
        });
        addActor(instructions);

        buttons[2][0] = settings;
        settings.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Sidebar sidebar = (Sidebar) getParent();
                if (sidebar != null) {
                    GameScreen screen = sidebar.getScreen();
                    Stage stage = screen.getStage();
                    stage.addActor(new Settings(screen));
                }
                return false;
            }
        });
        addActor(settings);

        buttons[3][0] = credits;
        credits.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Sidebar sidebar = (Sidebar) getParent();
                if (sidebar != null) {
                    GameScreen screen = sidebar.getScreen();
                    Stage stage = screen.getStage();
                    stage.addActor(new FullScreen(screen.getStage(), Assets.getTexture("screenCredits"),()->{
                        screen.getWorld().toggleRunning();
                    }));
                }
                return false;
            }
        });
        addActor(credits);

        buttons[4][0] = quitGame;
        quitGame.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Sidebar sidebar = (Sidebar) getParent();
                if (sidebar != null) {
                    sidebar.getScreen().toggleQuitGame();
                }
                return false;
            }
        });
        addActor(quitGame);

        update();
        updatePosition();
    }
}
