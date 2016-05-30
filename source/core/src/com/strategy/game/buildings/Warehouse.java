package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;

/**
 * The warehouse building, which increases the maximum amount of storable resources.
 */
public class Warehouse extends Container {
    private static final String NAME = "Warehouse";
    private static final ResourceContainer COST = new ResourceContainer(40, 0, 10, 0, 0);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(10, 0, 0, 1, 0);
    private static final ResourceContainer MAINTENANCE = new ResourceContainer(1, 0, 0, 1, 0);
    private static final ResourceContainer RESOURCE_STORED = new ResourceContainer(100, 100, 100, 100, 0);
    private static final int MAX_LIFE = 100;
    private static final int MAX_WORKERS = 5;
    private static final int INFLUENCE_RADIUS = 5;
    private static final Texture TEXTURE = Assets.warehouse;
    private static final Vector2 COLLISION = new Vector2(2,2);
    private int life = MAX_LIFE;
    private int workers = 0;


    public Warehouse() {
        super(NAME,
                COST,
                PRODUCTION,
                MAINTENANCE,
                MAX_LIFE,
                MAX_WORKERS,
                INFLUENCE_RADIUS,
                TEXTURE,
                COLLISION,
                BuildingType.WAREHOUSE);
        this.resourcesStored = RESOURCE_STORED;
    }
}
