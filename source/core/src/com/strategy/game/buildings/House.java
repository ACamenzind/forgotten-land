package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;

/**
 * A type of MapEntity
 */
public class House extends Building {
    //TODO: maybe put buildings properties into a single object.
    private static final String NAME = "House";
    private static final ResourceContainer COST = new ResourceContainer(100, 100, 100, 100, 5);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(0, 0, 0, 0, 1);
    private static final ResourceContainer MAINTENANCE = new ResourceContainer(1, 1, 1, 1, 0);

    private static final int MAX_LIFE = 10;
    private static final int MAX_WORKERS = 5;
    private static final int INFLUENCE_RADIUS = 5;
    private static final Texture TEXTURE = Assets.house1;
    private static final Vector2 COLLISION = new Vector2(2,2);
    public House() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION);
    }
}
