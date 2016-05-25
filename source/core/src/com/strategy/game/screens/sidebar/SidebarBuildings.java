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
        buttons[0][0].addListenerBuilding(new Castle());
        addActor(buttons[0][0]);

        buttons[0][1] = new GameButton(Assets.cows);
        buttons[0][1].addListenerBuilding(new CollectorFood());
        addActor(buttons[0][1]);

        buttons[0][2] = new GameButton(Assets.lumberjack);
        buttons[0][2].addListenerBuilding(new CollectorWood());
        addActor(buttons[0][2]);

        buttons[1][0] = new GameButton(Assets.mine);
        buttons[1][0].addListenerBuilding(new CollectorRock());
        addActor(buttons[1][0]);

        buttons[1][1] = new GameButton(Assets.market);
        buttons[1][1].addListenerBuilding(new CollectorGold());
        addActor(buttons[1][1]);

        buttons[1][2] = new GameButton(Assets.house1);
        buttons[1][2].addListenerBuilding(new House());
        addActor(buttons[1][2]);

        buttons[2][0] = new GameButton(Assets.warehouse);
        buttons[2][0].addListenerBuilding(new Warehouse());
        addActor(buttons[2][0]);

        buttons[2][1] = new GameButton(Assets.leftwall);
        buttons[2][1].addListenerBuilding(new Wall());
        addActor(buttons[2][1]);

        back.setVisible(false);

        update();
        updatePosition();
    }
}
