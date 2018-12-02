package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;
import com.strategy.game.world.ResourceContainerBuilder;

/**
 * The market, which produces gold.
 */
public class CollectorGold extends Structure {
    private static final String NAME = "Market";
    private static final ResourceContainer COST = new ResourceContainerBuilder().wood(50).rock(50).build();
    private static final ResourceContainer PRODUCTION = new ResourceContainerBuilder().gold(5).build();
    private static final ResourceContainer MAINTENANCE = new ResourceContainerBuilder().wood(1).food(1).build();
    private static final int MAX_LIFE = 100;
    private static final int MAX_WORKERS = 5;
    private static final int INFLUENCE_RADIUS = 5;
    private static final Texture TEXTURE = Assets.getTexture("market");
    private static final Vector2 COLLISION = new Vector2(5,5);

    public CollectorGold() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION, Structure.BuildingType.MANUFACTURER);
    }
}
