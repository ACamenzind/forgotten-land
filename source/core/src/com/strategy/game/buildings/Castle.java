package com.strategy.game.buildings;

import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.Building;
import com.strategy.game.ResourceContainer;

/**
 * A type of Building
 */
public class Castle extends Building {
    public Castle() {
        super("castle", new ResourceContainer(100,100,100,100,5), new ResourceContainer(0,0,0,0,0), 100, 10);
        this.mainTexture = Assets.castle;
//        this.imgOffset = new Vector2(1,50);
        this.collisionSize = new Vector2(4, 5);
        sliceTexture(mainTexture);
    }
}
