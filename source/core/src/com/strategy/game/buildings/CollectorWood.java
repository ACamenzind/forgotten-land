package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;
import com.strategy.game.world.World;

/**
 * Created by batta on 28/04/16.
 */
public class CollectorWood extends Collector {
    private static final String NAME = "Lumber camp";
    private static final ResourceContainer COST = new ResourceContainer(40, 0, 10, 5, 0);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(1, 0, 0, 0, 0);
    private static final ResourceContainer MAINTENANCE = new ResourceContainer(0, 0, 1, 1, 0);
    private static final int MAX_LIFE = 10;
    private static final int MAX_WORKERS = 5;
    private static final int INFLUENCE_RADIUS = 5;

    private static final Texture TEXTURE = Assets.lumberjack;
    private static final Vector2 COLLISION = new Vector2(2,2);

    public CollectorWood() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION, BuildingType.MANUFACTURER);
        this.resourceCollected = "wood";
    }

}
