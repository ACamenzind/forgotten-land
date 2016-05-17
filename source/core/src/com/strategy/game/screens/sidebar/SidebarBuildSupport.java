package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.buildings.House;
import com.strategy.game.buildings.WarehouseFood;

/**
 * Created by Amedeo on 09/05/16.
 */
public class SidebarBuildSupport extends SidebarBuildSelection {

    public SidebarBuildSupport() {
        super();

        title = Assets.makeLabel("Support", 20, Color.BLACK);
        addActor(title);

        buttons[0][0] = new GameButton(Assets.house1);
        buttons[0][0].addListenerBuilding(new House());
        addActor(buttons[0][0]);

        buttons[1][0] = new GameButton(Assets.warehouse);
        buttons[1][0].addListenerBuilding(new WarehouseFood());
        addActor(buttons[1][0]);

        update();
    }
}
