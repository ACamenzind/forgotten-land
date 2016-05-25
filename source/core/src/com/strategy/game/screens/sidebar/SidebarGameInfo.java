package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;
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
    private static final float TITLE_POSITION_Y = 0.92f;
    private static final Label title = Assets.makeLabel("Game Info", Utils.FONT_BIG_WHITE);

    private static final float TOTAL_POPULATION_POSITION_X = 0.5f;//MARGIN;
    private static final float TOTAL_POPULATION_POSITION_Y = 0.8f;
    private Label total_population = Assets.makeLabel("Population: 5", Utils.FONT_MEDIUM_WHITE);

    private static final float WORKERS_POSITION_X = 0.5f;//MARGIN;
    private static final float WORKERS_POSITION_Y = 0.75f;
    private Label workers = Assets.makeLabel("Workers: 0", Utils.FONT_MEDIUM_WHITE);

    private static final float UNEMPLYED_POSITION_X = 0.5f;//MARGIN;
    private static final float UNEMPLYED_POSITION_Y = 0.7f;
    private Label unemployed = Assets.makeLabel("Unemployed: 0", Utils.FONT_MEDIUM_WHITE);

    private static final float RESOURCES_POSITION_X = 0.5f;//MARGIN;
    private static final float RESOURCES_POSITION_Y = 0.6f;
    private Label resources = Assets.makeLabel("Resources", Utils.FONT_MEDIUM_WHITE);

    private ResourcesTable resourcesTable = new ResourcesTable(3, Utils.FONT_SMALL_WHITE);
    private static final float RESOURCES_TABLE_WIDTH = 0.8f;
    private static final float RESOURCES_TABLE_HEIGHT = 0.25f;
    private static final float RESOURCES_TABLE_POSITION_X = 0.525f;
    private static final float RESOURCES_TABLE_POSITION_Y = 0.33f;

    public SidebarGameInfo(World world) {
        this.world = world;

        Assets.setBackground(this, Assets.sidebarBgInfo);

        addActor(title);
        addActor(total_population);
        addActor(workers);
        addActor(unemployed);

        addActor(resources);

        String[] resourcesTableTitles = { "Total", "Max", "Var." };
        int[][] resourcesTableValues = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
        resourcesTable.set(resourcesTableTitles, resourcesTableValues);
        addActor(resourcesTable);

        update();
        updatePosition();
    }

    @Override
    public void update() {
        total_population.setText("Population: " + world.getResourceHandler().getTotalResources().people);
        workers.setText("Workers: " + world.getResourceHandler().getTotalWorkers());
        unemployed.setText("Unemployed: " + world.getResourceHandler().getUnemployed());

        total_population.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_WHITE));
        workers.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_WHITE));
        unemployed.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_WHITE));

        ResourceContainer[] resourceProfitContainer = { world.getResourceHandler().getTotalResources(),
                                                        world.getResourceHandler().getMaximumResources(),
                                                        world.getResourceHandler().getVariance() };
        resourcesTable.set(resourceProfitContainer);
    }

    @Override
    public void updatePosition() {
        Assets.setPositionRelative(title, TITLE_POSITION_X, TITLE_POSITION_Y, true, true);
        Assets.setPositionRelative(total_population, TOTAL_POPULATION_POSITION_X, TOTAL_POPULATION_POSITION_Y, true, false);
        Assets.setPositionRelative(workers, WORKERS_POSITION_X, WORKERS_POSITION_Y, true, false);
        Assets.setPositionRelative(unemployed, UNEMPLYED_POSITION_X, UNEMPLYED_POSITION_Y, true, false);
        Assets.setPositionRelative(resources, RESOURCES_POSITION_X, RESOURCES_POSITION_Y, true, false);
        Assets.setSizeRelative(resourcesTable, RESOURCES_TABLE_WIDTH, RESOURCES_TABLE_HEIGHT);
        Assets.setPositionRelative(resourcesTable, RESOURCES_TABLE_POSITION_X, RESOURCES_TABLE_POSITION_Y, true, false);
    }
}
