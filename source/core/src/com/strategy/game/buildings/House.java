package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;
import com.strategy.game.world.ResourceContainerBuilder;

/**
 * A type of MapEntity
 */
public class House extends Container {
    //TODO: maybe put buildings properties into a single object.
    private static final String NAME = "House";
    private static final ResourceContainer COST = new ResourceContainerBuilder().wood(50).food(50).rock(50).gold(50).build();
    private static final ResourceContainer PRODUCTION = new ResourceContainerBuilder().build();
    private static final ResourceContainer MAINTENANCE = new ResourceContainerBuilder().wood(1).rock(1).gold(1).build();
    private static final ResourceContainer RESOURCE_STORED = new ResourceContainerBuilder().people(5).build();

    private static final int MAX_LIFE = 100;
    private static final int MAX_WORKERS = 5;
    private static final int INFLUENCE_RADIUS = 5;
    private static final Texture TEXTURE = Assets.house1;
    private static final Vector2 COLLISION = new Vector2(2,2);
    public House() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION, BuildingType.WAREHOUSE);
        this.resourcesStored = RESOURCE_STORED;
    }
}
