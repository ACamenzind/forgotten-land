package com.strategy.game.world;

import com.strategy.game.buildings.MapEntity;

/**
 * Represents the resources (e.g. minerals, wood) on the map.
 */
public class Resource extends MapEntity {
    private String type;
    private int maxAmount, amountLeft;
    public Resource(String type, int maxAmount, int amountLeft) {
        this.type = type;
        this.maxAmount = maxAmount;
        this.amountLeft = amountLeft;
    }
}
