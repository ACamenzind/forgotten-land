package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;

/**
 * Created by francescosani on 12/05/16.
 */
public class CollectorFood extends Building {
    private static final String NAME = "Farm";
    private static final ResourceContainer COST = new ResourceContainer(30, 0, 10, 10, 0);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(0, 5, 0, 0, 0);
    private static final ResourceContainer MAINTENANCE = new ResourceContainer(5, 0, 1, 1, 0);
    private static final int MAX_LIFE = 100;
    private static final int MAX_WORKERS = 5;
    private static final int INFLUENCE_RADIUS = 5;
    private static final Texture TEXTURE = Assets.cows;
    private static final Vector2 COLLISION = new Vector2(3,3);

    public CollectorFood() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION, BuildingType.MANUFACTURER);
    }
}
