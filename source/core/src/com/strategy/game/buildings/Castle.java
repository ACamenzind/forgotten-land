package com.strategy.game.buildings;

import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;

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
