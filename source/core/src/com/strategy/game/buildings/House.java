package com.strategy.game.buildings;

import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;

/**
 * A type of MapEntity
 */
public class House extends MapEntity {
    public House() {
        super();
        this.mainTexture = Assets.house1;
        this.collisionSize = new Vector2(3,3);
//        this.imgOffset = new Vector2(0,1);
        sliceTexture(mainTexture);
    }
}
