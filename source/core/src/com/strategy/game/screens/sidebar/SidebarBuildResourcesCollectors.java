package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.buildings.House;

/**
 * Created by Amedeo on 08/05/16.
 */
public class SidebarBuildResourcesCollectors extends SidebarBuildingSelection {

    private static final GameButton house = new GameButton(Assets.house1);
    private static final GameButton house2 = new GameButton(Assets.house1);

    public SidebarBuildResourcesCollectors() {
        super();

        title = Assets.makeLabel("Resources Collectors", 20, Color.BLACK);
        addActor(title);

        buttons[0][0] = house;
        house.addListenerBuilding(new House());
        addActor(house);

//        buttons[5][2] = house2;
//        house2.addListenerBuilding(new House());
//        addActor(house2);

        updatePosition();
    }
}
