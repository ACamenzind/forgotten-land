package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;
import com.strategy.game.buildings.Castle;
import com.strategy.game.buildings.CollectorFood;
import com.strategy.game.buildings.CollectorRock;
import com.strategy.game.buildings.CollectorWood;

/**
 * Created by Amedeo on 08/05/16.
 */
public class SidebarBuildManufacturers extends SidebarBuildSelection {

    public SidebarBuildManufacturers() {
        super();

        title = Assets.makeLabel("Manufacturers", Utils.FONT_BIG_WHITE);
        addActor(title);

        buttons[0][0] = new GameButton(Assets.getTexture("castle"));
        buttons[0][0].addListenerBuilding(new Castle());
        addActor(buttons[0][0]);

        buttons[0][1] = new GameButton(Assets.getTexture("cows"));
        buttons[0][1].addListenerBuilding(new CollectorFood());
        addActor(buttons[0][1]);

        buttons[0][2] = new GameButton(Assets.getTexture("lumberjack"));
        buttons[0][2].addListenerBuilding(new CollectorWood());
        addActor(buttons[0][2]);

        buttons[1][0] = new GameButton(Assets.getTexture("mine"));
        buttons[1][0].addListenerBuilding(new CollectorRock());
        addActor(buttons[1][0]);

        update();
        updatePosition();
    }
}
