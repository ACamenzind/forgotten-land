package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;
import com.strategy.game.world.ResourceContainerBuilder;
import com.strategy.game.world.World;

/**
 * Created by batta on 28/04/16.
 */
public class CollectorWood extends Collector {
    private static final String NAME = "Lumber camp";
    private static final ResourceContainer COST = new ResourceContainerBuilder().wood(25).rock(10).gold(5).build();
    private static final ResourceContainer PRODUCTION = new ResourceContainerBuilder().wood(5).build();
    private static final ResourceContainer MAINTENANCE = new ResourceContainerBuilder().rock(5).gold(5).build();
    private static final int MAX_LIFE = 100;
    private static final int MAX_WORKERS = 5;
    private static final int INFLUENCE_RADIUS = 5;

    private static final Texture TEXTURE = Assets.getTexture("lumberjack");
    private static final Vector2 COLLISION = new Vector2(2,2);

    public CollectorWood() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION, BuildingType.MANUFACTURER);
        this.resourceCollected = "wood";
    }

}
