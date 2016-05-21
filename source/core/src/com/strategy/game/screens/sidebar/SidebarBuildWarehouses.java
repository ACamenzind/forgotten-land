package com.strategy.game.screens.sidebar;

import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;
import com.strategy.game.buildings.House;
import com.strategy.game.buildings.Warehouse;

/**
 * Created by Amedeo on 09/05/16.
 */
public class SidebarBuildWarehouses extends SidebarBuildSelection {

    public SidebarBuildWarehouses() {
        super();

        title = Assets.makeLabel("Warehouses", Utils.FONT_BIG_BLACK);
        addActor(title);

        buttons[0][0] = new GameButton(Assets.house1);
        buttons[0][0].addListenerBuilding(new House());
        addActor(buttons[0][0]);

        buttons[1][0] = new GameButton(Assets.warehouse);
        buttons[1][0].addListenerBuilding(new Warehouse());
        addActor(buttons[1][0]);
//
        update();
        updatePosition();
    }
}
