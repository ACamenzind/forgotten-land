package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.Utils;
import com.strategy.game.screens.Display;
import com.strategy.game.world.World;

/**
 * Created by Amedeo on 07/05/16.
 */
public class SidebarGameInfo extends Table implements Display {

    private World world;

    private static final float MARGIN = 0.1f;

    private static final float TITLE_POSITION_X = 0.5f;
    private static final float TITLE_POSITION_Y = 0.9f;
    private static final Label title = Assets.makeLabel("Game Info", Utils.FONT_BIG_BLACK);

    private static final float TOTAL_POPULATION_POSITION_X = MARGIN;
    private static final float TOTAL_POPULATION_POSITION_Y = 0.8f;
    private Label total_population = Assets.makeLabel("Population: 5", Utils.FONT_MEDIUM_BLACK);

    private static final float WORKERS_POSITION_X = MARGIN;
    private static final float WORKERS_POSITION_Y = 0.75f;
    private Label workers = Assets.makeLabel("Workers: 0", Utils.FONT_MEDIUM_BLACK);

    private static final float UNEMPLYED_POSITION_X = MARGIN;
    private static final float UNEMPLYED_POSITION_Y = 0.7f;
    private Label unemployed = Assets.makeLabel("Unemployed: 0", Utils.FONT_MEDIUM_BLACK);

    public SidebarGameInfo(World world) {
        this.world = world;

        Assets.setBackground(this, Assets.sidebarDisplayMiddleBackground);

        addActor(title);
        addActor(total_population);
        addActor(workers);
        addActor(unemployed);

        update();
        updatePosition();
    }

    @Override
    public void update() {
//        total_population.setText("Population: " + world.getTotalPopulation());
//        workers.setText(("Workers: " + world.getWorkers()));
        unemployed.setText(("Unemployed: " + world.getResourceHandler().getTotalResources().people));

        total_population.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_BLACK));
        workers.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_BLACK));
        unemployed.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_BLACK));
    }

    @Override
    public void updatePosition() {
        Assets.setPositionRelative(title, TITLE_POSITION_X, TITLE_POSITION_Y, true, false);
        Assets.setPositionRelative(total_population, TOTAL_POPULATION_POSITION_X, TOTAL_POPULATION_POSITION_Y);
        Assets.setPositionRelative(workers, WORKERS_POSITION_X, WORKERS_POSITION_Y);
        Assets.setPositionRelative(unemployed, UNEMPLYED_POSITION_X, UNEMPLYED_POSITION_Y);
    }
}
