package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;

/**
 * Created by francescosani on 12/05/16.
 */
public class CollectorRock extends Collector {
    private static final String NAME = "Mine";
    private static final ResourceContainer COST = new ResourceContainer(40, 0, 10, 5, 0);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(0, 0, 10, 0, 0);
    private static final ResourceContainer MAINTENANCE = new ResourceContainer(0, 1, 0, 1, 0);
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
