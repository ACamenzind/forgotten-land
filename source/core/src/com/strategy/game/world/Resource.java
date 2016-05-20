package com.strategy.game.world;

import com.badlogic.gdx.math.Vector2;
import com.strategy.game.buildings.MapEntity;

/**
 * Represents the resources (e.g. minerals, wood) on the map.
 */
public class Resource extends MapEntity {
    private String type;
//    private int maxAmount, amountLeft;
    public Resource(String type, int maxAmount) {
        this.type = type;
        this.maxLife = maxAmount;
        this.life = maxAmount;
        this.collisionSize = new Vector2(1,1);
    }

    public String getType() {
        return type;
    }
}
