package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.screens.Display;

/**
 * Created by Amedeo on 09/05/16.
 */
public class SidebarBuildSelection extends Table implements Display {

    protected Label title;
    protected GameButton[][] buttons;
    protected GameButton rotate;
    protected GameButton cancel;
    protected GameButton back;

    private static final int BUTTONS_PER_COLUMN = 6;
    private static final int BUTTONS_PER_ROW = 3;
    private static final float MARGIN = 0.1f;
    private static final float BUTTON_WIDTH = (1f - (MARGIN * (BUTTONS_PER_ROW + 1f))) / BUTTONS_PER_ROW;
    private static final float TITLE_POSITION_X = 0.5f;
    private static final float TITLE_POSITION_Y = 0.9f;

    public SidebarBuildSelection() {
        Assets.setBackground(this, "core/assets/textures/gameScreen/sidebar_build.png");
        buttons = new GameButton[BUTTONS_PER_COLUMN][BUTTONS_PER_ROW];

        rotate = new GameButton("core/assets/textures/gameScreen/sidebar_build_rotate.png");
        rotate.addListenerBuildingRotate();
        addActor(rotate);

        cancel = new GameButton("core/assets/textures/gameScreen/sidebar_build_cancel.png");
        cancel.addListenerBuilding(null);
        addActor(cancel);

        back = new GameButton("core/assets/textures/gameScreen/sidebar_build_back.png");
        back.addListenerLink(Sidebar.DisplayType.BUILD);
        addActor(back);
    }


    @Override
    public void update() {

        Assets.setPositionRelative(title, TITLE_POSITION_X, TITLE_POSITION_Y, true, true);

        final float BUTTON_HEIGHT = getWidth() * BUTTON_WIDTH / getHeight();
        for (int y = 0; y < buttons.length; y++) {
            final float POSITION_Y = TITLE_POSITION_Y - MARGIN - (BUTTON_HEIGHT + 0.025f) * y;
            for (int x = 0; x < buttons[y].length;  x++) {
                final float POSITION_X = MARGIN * (x + 1) + BUTTON_WIDTH * x;
                if(buttons[y][x] != null) {
                    Assets.setSizeRelative(buttons[y][x], BUTTON_WIDTH, BUTTON_HEIGHT);
                    Assets.setPositionRelative(buttons[y][x], POSITION_X, POSITION_Y, false, true);
                }
            }
        }

        Assets.setSizeRelative(rotate, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(rotate, MARGIN, 0.1f, false, true);

        Assets.setSizeRelative(cancel, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(cancel, MARGIN * 2 + BUTTON_WIDTH, 0.1f, false, true);

        Assets.setSizeRelative(back, BUTTON_WIDTH, BUTTON_HEIGHT);
        Assets.setPositionRelative(back, MARGIN * 3 + BUTTON_WIDTH * 2, 0.1f, false, true);
    }
}
