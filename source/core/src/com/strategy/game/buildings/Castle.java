package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;

/**
 * A type of Building
 */
public class Castle extends Building {
    //TODO: maybe put buildings properties into a single object.
    private static final String NAME = "Castle";
    private static final ResourceContainer COST = new ResourceContainer(100, 100, 100, 100, 0);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(0, 0, 0, 0, 1);
    private static final ResourceContainer MAINTENANCE = new ResourceContainer(1,0,1,1,0);

    private static final int MAX_LIFE = 100;
    private static final int MAX_WORKERS = 1;
    private static final int INFLUENCE_RADIUS = 10;

    private static final Texture TEXTURE = Assets.castle;
    private static final Vector2 COLLISION = new Vector2(4,4);

    public Castle() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION, BuildingType.MANUFACTURER);
    }
}
