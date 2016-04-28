package com.strategy.game;

import com.badlogic.gdx.math.Vector2;

/**
 * A type of MapEntity
 */
public class Castle extends MapEntity {
    public Castle() {
        super();
        this.mainTexture = Assets.castle;
//        this.imgOffset = new Vector2(1,50);
        this.collisionSize = new Vector2(4, 5);
        sliceTexture(mainTexture);
    }
}
