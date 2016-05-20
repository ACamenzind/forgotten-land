package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.ResourceContainer;

/**
 * Created on 20/05/2016.
 */
public abstract class Collector extends Building {
    protected String resourceCollected;

    public String getResourceCollected() {
        return resourceCollected;
    }

    public Collector(String name,
                     ResourceContainer costs,
                     ResourceContainer productions,
                     ResourceContainer maintenanceCosts,
                     int maxLife,
                     int maxWorkers,
                     int influenceRadius,
                     Texture mainTexture,
                     Vector2 collisionSize,
                     BuildingType type) {
        super(name,
                costs,
                productions,
                maintenanceCosts,
                maxLife,
                maxWorkers,
                influenceRadius,
                mainTexture,
                collisionSize,
                type);
    }
}
