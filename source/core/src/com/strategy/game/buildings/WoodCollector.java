package com.strategy.game.buildings;

import com.strategy.game.ResourceContainer;

/**
 * Created by batta on 28/04/16.
 */
public class WoodCollector extends Building {
    private static final String NAME = "Lumber camp";
    private static final ResourceContainer COST = new ResourceContainer(40, 0, 10, 0, 0);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(10, 0, 0, 1, 0);
    private static final ResourceContainer MAINTENANCE = new ResourceContainer(10, 0, 0, 1, 0);
    private static final int MAX_LIFE = 10;
    private static final int MAX_WORKERS = 5;
    private static final int INFLUENCE_RADIUS = 5;

    public WoodCollector() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS);
    }
}
