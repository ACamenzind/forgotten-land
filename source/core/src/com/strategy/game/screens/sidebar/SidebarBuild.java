package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;

/**
 * Created by Amedeo on 07/05/16.
 */
public class SidebarBuild extends SidebarOptionsSelection {

    private static final GameButton manufacturers = new GameButton(Assets.sidebarBuildManufacturers);
    private static final GameButton warehouses = new GameButton(Assets.sidebarBuildWarehouses);
    private static final GameButton decorations = new GameButton(Assets.sidebarBuildDecorations);
    private static final GameButton other = new GameButton(Assets.sidebarBuildOther);


    public SidebarBuild() {

        Assets.setBackground(this, Assets.sidebarBgBuild);

        title = Assets.makeLabel("Buildings", Utils.FONT_BIG_WHITE);
        addActor(title);

        buttons[0][0] = manufacturers;
        manufacturers.addListenerLink(Sidebar.DisplayType.BUILD_MANUFACTURERS);
        addActor(manufacturers);

        buttons[1][0] = warehouses;
        warehouses.addListenerLink(Sidebar.DisplayType.BUILD_WAREHOUSES);
        addActor(warehouses);

        buttons[2][0] = decorations;
        decorations.addListenerLink(Sidebar.DisplayType.BUILD_DECORATIONS);
        addActor(decorations);

        buttons[3][0] = other;
        addActor(other);

        update();
        updatePosition();
    }
}
