package com.strategy.game.buildings;

import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.Building;
import com.strategy.game.ResourceContainer;

/**
 * A type of MapEntity
 */
public class House extends Building {
    //TODO: maybe put buildings properties into a single object.
    private static final String NAME = "House";
    private static final ResourceContainer COST = new ResourceContainer(100, 100, 100, 100, 5);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(0, 0, 0, 0, 1);
    private static final int MAX_LIFE = 10;
    private static final int MAX_WORKERS = 5;
    public House() {
        super(NAME, COST, PRODUCTION, MAX_LIFE, MAX_WORKERS);
        this.mainTexture = Assets.house1;
        this.collisionSize = new Vector2(2,2);
        sliceTexture(mainTexture);
    }
}
