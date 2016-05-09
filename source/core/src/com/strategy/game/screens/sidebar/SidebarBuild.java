package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;

import java.awt.*;

/**
 * Created by Amedeo on 07/05/16.
 */
public class SidebarBuild extends Table implements Display {

    private static final Label buildings = Assets.makeLabel("Buildings", 20, Color.BLACK);

    private static final GameButton resourcesCollectors = new GameButton("core/assets/GameScreenTextures/sidebar_build_resources.png");
    private static final GameButton support = new GameButton("core/assets/GameScreenTextures/sidebar_build_support.png");
    private static final GameButton decorations = new GameButton("core/assets/GameScreenTextures/sidebar_build_decorations.png");
    private static final GameButton other = new GameButton("core/assets/GameScreenTextures/sidebar_build_other.png");

    private static final float MARGIN = 0.1f;
    private static final float BUTTON_HEIGHT = 0.05f;
    private static final float BUTTON_WIDTH = 1f - MARGIN * 2f;




    public SidebarBuild() {

        Assets.setBackground(this, "core/assets/GameScreenTextures/sidebar_build.png");

        addActor(buildings);

        addActor(resourcesCollectors);
        resourcesCollectors.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (hasParent()) {
                    Sidebar sidebar = (Sidebar) getParent();
                    sidebar.setDisplayMiddle(Sidebar.DisplayType.BUILD_RESOURCES_COLLECTORS);
                }
                return true;
            }
        });

        addActor(support);
        support.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (hasParent()) {
                    Sidebar sidebar = (Sidebar) getParent();
                    sidebar.setDisplayMiddle(Sidebar.DisplayType.BUILD_SUPPORT);
                }
                return true;
            }
        });

        addActor(decorations);
        addActor(other);

        updatePosition();
    }

    @Override
    public void updatePosition() {
//        Assets.setPositionRelative(buildings, 0.5f, 1f - BUTTON_HEIGHT * 2, true, true);
        Assets.setPositionRelative(buildings, 0.5f, 0.9f, true, true);

        Assets.setSizeRelative(resourcesCollectors, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(resourcesCollectors, 0.5f, 1f - BUTTON_HEIGHT * 4, true, true);

        Assets.setSizeRelative(support, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(support, 0.5f, 1f - BUTTON_HEIGHT * 6, true, true);

        Assets.setSizeRelative(decorations, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(decorations, 0.5f, 1f - BUTTON_HEIGHT * 8, true, true);

        Assets.setSizeRelative(other, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(other, 0.5f, 1f - BUTTON_HEIGHT * 10, true, true);
    }
}
