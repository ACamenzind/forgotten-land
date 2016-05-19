package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;
import com.strategy.game.screens.Display;

/**
 * Created by Amedeo on 10/05/16.
 */
public class SidebarOptionsSelection extends Table implements Display {

    protected Label title;
    protected Actor[][] buttons = new Actor[BUTTONS_PER_COLUMN][BUTTONS_PER_ROW];

    private static final int BUTTONS_PER_COLUMN = 6;
    private static final int BUTTONS_PER_ROW = 1;

    private static final float MARGIN = 0.1f;
    private static final float BUTTON_WIDTH = (1f - (MARGIN * (BUTTONS_PER_ROW + 1f))) / BUTTONS_PER_ROW;//1f - MARGIN * 2f;
    private static final float BUTTON_HEIGHT = 0.05f;
    private static final float TITLE_POSITION_X = 0.5f;
    private static final float TITLE_POSITION_Y = 0.9f;

    public SidebarOptionsSelection() {
        Assets.setBackground(this, Assets.sidebarDisplayMiddleBackground);

    }

    @Override
    public void update() {
        Assets.setPositionRelative(title, TITLE_POSITION_X, TITLE_POSITION_Y, true, true);

        title.setStyle(Assets.makeLabelStyle(Utils.FONT_BIG_BLACK));

        for (int y = 0; y < buttons.length; y++) {
            final float POSITION_Y = 1f - BUTTON_HEIGHT * (y + 2) * 2;
            for (int x = 0; x < buttons[y].length; x++) {
                final float POSITION_X = MARGIN * (x + 1) + BUTTON_WIDTH * x;
                if (buttons[y][x] != null) {
                    Assets.setSizeRelative(buttons[y][x], BUTTON_WIDTH, BUTTON_HEIGHT);
                    Assets.setPositionRelative(buttons[y][x], POSITION_X, POSITION_Y, false, true);
                }
            }
        }
    }
}
