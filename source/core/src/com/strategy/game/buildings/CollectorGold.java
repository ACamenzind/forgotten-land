package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;

/**
 * The market, which produces gold.
 */
public class CollectorGold extends Building{
    private static final String NAME = "Market";
    private static final ResourceContainer COST = new ResourceContainer(50, 0, 50, 0, 0);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(0, 0, 0, 5, 0);
    private static final ResourceContainer MAINTENANCE = new ResourceContainer(1, 0, 1, 0, 0);
    private static final int MAX_LIFE = 100;
    private static final int MAX_WORKERS = 5;
    private static final int INFLUENCE_RADIUS = 5;
    private static final Texture TEXTURE = Assets.market;
    private static final Vector2 COLLISION = new Vector2(5,5);

    public CollectorGold() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION, Building.BuildingType.MANUFACTURER);
    }
}
