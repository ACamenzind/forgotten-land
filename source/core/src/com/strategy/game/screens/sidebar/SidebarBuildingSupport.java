package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.buildings.Castle;
import com.strategy.game.buildings.Wall;

/**
 * Created by Amedeo on 09/05/16.
 */
public class SidebarBuildingSupport extends SidebarBuildingSelection {

    private static final GameButton castle = new GameButton(Assets.castle);//new GameButton("core/assets/castle.png");
    private static final GameButton wall = new GameButton(Assets.leftwall);//new GameButton("core/assets/Wall/Wall_L.png");

    public SidebarBuildingSupport() {
        super();

        title = Assets.makeLabel("Support", 20, Color.BLACK);
        addActor(title);

        buttons[0][0] = castle;
        castle.addListenerBuilding(new Castle());
        addActor(castle);

        buttons[1][0] = wall;
        wall.addListenerBuilding(new Wall());
        addActor(wall);

        update();
    }
}
