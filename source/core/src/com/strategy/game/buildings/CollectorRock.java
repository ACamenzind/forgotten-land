package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;
import com.strategy.game.world.ResourceContainerBuilder;

/**
 * Created by francescosani on 12/05/16.
 */
public class CollectorRock extends Collector {
    private static final String NAME = "Mine";
    private static final ResourceContainer COST = new ResourceContainerBuilder().wood(40).gold(10).build();
    private static final ResourceContainer PRODUCTION = new ResourceContainerBuilder().rock(5).build();
    private static final ResourceContainer MAINTENANCE = new ResourceContainerBuilder().gold(1).build();
    private static final int MAX_LIFE = 100;
    private static final int MAX_WORKERS = 5;
    private static final int INFLUENCE_RADIUS = 5;
    private static final Texture TEXTURE = Assets.mine;
    private static final Vector2 COLLISION = new Vector2(3,3);

    public CollectorRock() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION, BuildingType.MANUFACTURER);
        this.resourceCollected = "rock";
    }
}
