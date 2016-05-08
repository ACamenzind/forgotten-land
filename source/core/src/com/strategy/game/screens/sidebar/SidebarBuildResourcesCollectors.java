package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.buildings.House;
import com.strategy.game.screens.GameScreen;

import static com.sun.java.swing.ui.CommonUI.BUTTON_HEIGHT;

/**
 * Created by Amedeo on 08/05/16.
 */
public class SidebarBuildResourcesCollectors extends Table implements Display {

    private static final Label resourcesCollectors = Assets.makeLabel("Resources Collectors", 20, Color.BLACK);

    private static final int BUTTONS_NUMBER = 3;
    private static final float MARGIN = 0.1f;
    private static final float IMAGE_BUTTON_WIDTH = (1f - (MARGIN * (BUTTONS_NUMBER + 1f))) / BUTTONS_NUMBER;
//    private static final float IMAGE_BUTTON_HEIGHT = 1f;//0.75f;


    private static final GameButton house1 = new GameButton("core/assets/house1.png");
    private static final GameButton house2 = new GameButton("core/assets/watchtower_lvl2-exp_full_size.png");
    private static final GameButton house3 = new GameButton("core/assets/castle.png");

    private static final GameButton mine1 = new GameButton("core/assets/Building_04_Normal.png");
    private static final GameButton mine2 = new GameButton("core/assets/house1.png");
    private static final GameButton mine3 = new GameButton("core/assets/house1.png");

    public SidebarBuildResourcesCollectors() {

        Assets.setBackground(this, "core/assets/GameScreenTextures/sidebar_build.png");

        addActor(resourcesCollectors);

        addActor(house1);
        house1.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (hasParent()) {
                    Sidebar parent = (Sidebar) getParent();
                    parent.screen.getBuilder().toggleSelectEntity(new House());
                }
                return true;
            }
        });

        addActor(house2);
        addActor(house3);

        addActor(mine1);
//        addActor(mine2);
//        addActor(mine3);

        updatePosition();
    }

    @Override
    public void updatePosition() {
        final float IMAGE_BUTTON_HEIGHT = getWidth() * IMAGE_BUTTON_WIDTH / getHeight();

        //final float ROW_N = 0.9f - MARGIN - IMAGE_BUTTON_HEIGHT * (N - 1) - 0.025f * (N - 1)
        final float ROW_1 = 0.9f - MARGIN;
        final float ROW_2 = 0.9f - MARGIN - IMAGE_BUTTON_HEIGHT - 0.025f;
        final float ROW_3 = 0.9f - MARGIN - IMAGE_BUTTON_HEIGHT * 2 - 0.025f * 2;

        final float COL_1 = MARGIN;
        final float COL_2 = MARGIN * 2 + IMAGE_BUTTON_WIDTH;
        final float COL_3 = MARGIN * 3 + IMAGE_BUTTON_WIDTH * 2;

        Assets.setPositionRelative(resourcesCollectors, 0.5f, 0.9f, true, true);

        Assets.setSizeRelative(house1, IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);
        Assets.setSizeRelative(house2, IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);
        Assets.setSizeRelative(house3, IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);

        Assets.setSizeRelative(mine1, IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);
        Assets.setSizeRelative(mine2, IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);
        Assets.setSizeRelative(mine3, IMAGE_BUTTON_WIDTH, IMAGE_BUTTON_HEIGHT);

        Assets.setPositionRelative(house1, COL_1, ROW_1, false, true);
        Assets.setPositionRelative(house2, COL_2, ROW_1, false, true);
        Assets.setPositionRelative(house3, COL_3, ROW_1, false, true);

        Assets.setPositionRelative(mine1, COL_1, ROW_2, false, true);
        Assets.setPositionRelative(mine2, COL_2, ROW_2, false, true);
        Assets.setPositionRelative(mine3, COL_3, ROW_2, false, true);
    }
}
