package com.strategy.game.buildings;

import com.strategy.game.ResourceContainer;

/**
 * Created by francescosani on 12/05/16.
 */
public class CollectorFood extends Building {
    private static final String NAME = "Farm";
    private static final ResourceContainer COST = new ResourceContainer(40, 0, 10, 0, 0);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(10, 0, 0, 1, 0);
    private static final ResourceContainer MAINTENANCE = new ResourceContainer(10, 0, 0, 1, 0);
    private static final int MAX_LIFE = 10;
    private static final int MAX_WORKERS = 5;
    private static final int INFLUENCE_RADIUS = 5;

    public CollectorFood() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS);
    }
}
