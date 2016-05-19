package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;
import com.strategy.game.buildings.Wall;

/**
 * Created by Amedeo on 17/05/16.
 */
public class SidebarBuildDecorations extends SidebarBuildSelection {

    public SidebarBuildDecorations() {
        super();

        title = Assets.makeLabel("Decorations", Utils.FONT_BIG_BLACK);
        addActor(title);

        buttons[0][0] = new GameButton(Assets.leftwall);
        buttons[0][0].addListenerBuilding(new Wall());
        addActor(buttons[0][0]);

        update();
    }
}
