package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tooltip;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;
import com.strategy.game.buildings.*;

/**
 * Created by Amedeo on 24/05/16.
 */
public class SidebarBuildings extends SidebarBuildSelection {

    public SidebarBuildings() {
        super();

        Assets.setBackground(this, Assets.sidebarBgBuild);

        title = Assets.makeLabel("Buildings", Utils.FONT_BIG_WHITE);
        addActor(title);

        buttons[0][0] = new GameButton(Assets.castle);
        addActor(buttons[0][0]);
        buttons[0][0].addListenerBuilding(new Castle());

        buttons[0][1] = new GameButton(Assets.lumberjack);
        addActor(buttons[0][1]);
        buttons[0][1].addListenerBuilding(new CollectorWood());

        buttons[0][2] = new GameButton(Assets.cows);
        addActor(buttons[0][2]);
        buttons[0][2].addListenerBuilding(new CollectorFood());

        buttons[1][0] = new GameButton(Assets.mine);
        addActor(buttons[1][0]);
        buttons[1][0].addListenerBuilding(new CollectorRock());

        buttons[1][1] = new GameButton(Assets.market);
        addActor(buttons[1][1]);
        buttons[1][1].addListenerBuilding(new CollectorGold());

        buttons[1][2] = new GameButton(Assets.house1);
        addActor(buttons[1][2]);
        buttons[1][2].addListenerBuilding(new House());

        buttons[2][0] = new GameButton(Assets.warehouse);
        addActor(buttons[2][0]);
        buttons[2][0].addListenerBuilding(new Warehouse());

        buttons[2][1] = new GameButton(Assets.leftwall);
        addActor(buttons[2][1]);
        buttons[2][1].addListenerBuilding(new Wall());

        buttons[2][2] = new GameButton(Assets.road);
        addActor(buttons[2][2]);
        buttons[2][2].addListenerBuilding(new Road());

        back.setVisible(false);

        update();
        updatePosition();
    }
}
