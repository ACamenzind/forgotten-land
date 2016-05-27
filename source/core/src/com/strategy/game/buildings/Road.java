package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;

/**
 * The road tile, allows to quickly expand the influence area and build buildings further away.
 */
public class Road extends Building{
    //TODO: maybe put buildings properties into a single object.
    private static final String NAME = "Road";
    private static final ResourceContainer COST = new ResourceContainer(0, 0, 1, 0, 0);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(0, 0, 0, 0, 0);
    private static final ResourceContainer MAINTENANCE = new ResourceContainer(0, 0, 0, 0, 0);

    private static final int MAX_LIFE = 100;
    private static final int MAX_WORKERS = 0;
    private static final int INFLUENCE_RADIUS = 1;

    private static final Texture TEXTURE = Assets.road;
    private static final Vector2 COLLISION = new Vector2(1,1);

    public Road() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION, Building.BuildingType.DECORATION);
//        sliceTexture(mainTexture);
    }
}
