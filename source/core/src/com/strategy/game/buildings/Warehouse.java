package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;
import com.strategy.game.world.ResourceContainerBuilder;

/**
 * The warehouse building, which increases the maximum amount of storable resources.
 */
public class Warehouse extends Container {
    private static final String NAME = "Warehouse";
    private static final ResourceContainer COST = new ResourceContainerBuilder()
            .wood(40).rock(10).build();
    private static final ResourceContainer PRODUCTION =  new ResourceContainerBuilder().build();

    private static final ResourceContainer MAINTENANCE = new ResourceContainerBuilder().wood(1).gold(1).build();

    private static final ResourceContainer RESOURCE_STORED = new ResourceContainerBuilder().wood(100).food(100).rock(100).gold(100).build();

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
