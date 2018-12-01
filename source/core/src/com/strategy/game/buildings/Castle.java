package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;
import com.strategy.game.world.ResourceContainerBuilder;

/**
 * A type of Building
 */
public class Castle extends Building {
    //TODO: maybe put buildings properties into a single object.
    private static final String NAME = "Castle";
    private static final ResourceContainer COST = new ResourceContainerBuilder().wood(100).food(100).rock(100).gold(100).build();
    private static final ResourceContainer PRODUCTION = new ResourceContainerBuilder().people(1).build();
    private static final ResourceContainer MAINTENANCE = new ResourceContainerBuilder().wood(1).rock(1).gold(1).build();

    private static final int MAX_LIFE = 100;
    private static final int MAX_WORKERS = 1;
    private static final int INFLUENCE_RADIUS = 10;

    private static final Texture TEXTURE = Assets.castle;
    private static final Vector2 COLLISION = new Vector2(4,4);

    public Castle() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION, BuildingType.MANUFACTURER);
    }
}
