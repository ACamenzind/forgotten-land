package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Amedeo on 07/05/16.
 */
public class SidebarGameInfo extends Table implements Display {

    Stage stage;

    public SidebarGameInfo(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void updatePosition() {

    }
}
