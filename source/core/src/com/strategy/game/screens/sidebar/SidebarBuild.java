package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;

/**
 * Created by Amedeo on 07/05/16.
 */
public class SidebarBuild extends SidebarOptionsSelection {

    private static final GameButton resourcesCollectors = new GameButton("core/assets/textures/gameScreen/sidebar_build_resources.png");
    private static final GameButton support = new GameButton("core/assets/textures/gameScreen/sidebar_build_support.png");
    private static final GameButton decorations = new GameButton("core/assets/textures/gameScreen/sidebar_build_decorations.png");
    private static final GameButton other = new GameButton("core/assets/textures/gameScreen/sidebar_build_other.png");


    public SidebarBuild() {

        title = Assets.makeLabel("Buildings", 20, Color.BLACK);
        addActor(title);

        buttons[0][0] = resourcesCollectors;
        resourcesCollectors.addListenerLink(Sidebar.DisplayType.BUILD_RESOURCES_COLLECTORS);
        addActor(resourcesCollectors);

        buttons[1][0] = support;
        support.addListenerLink(Sidebar.DisplayType.BUILD_SUPPORT);
        addActor(support);

        buttons[2][0] = decorations;
        addActor(decorations);

        buttons[3][0] = other;
        addActor(other);

        update();
    }
}
