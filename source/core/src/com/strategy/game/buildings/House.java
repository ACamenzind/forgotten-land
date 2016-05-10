package com.strategy.game.buildings;

import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.Building;
import com.strategy.game.ResourceContainer;

/**
 * A type of MapEntity
 */
public class House extends Building {
    public House() {
        super("castle", new ResourceContainer(100,100,100,100,5), new ResourceContainer(0,0,0,0,0), 100, 10);
        this.mainTexture = Assets.house1;
        this.collisionSize = new Vector2(3,3);
//        this.imgOffset = new Vector2(0,1);
        sliceTexture(mainTexture);
    }
}
