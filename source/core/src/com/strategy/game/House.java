package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * A type of MapEntity
 */
public class House extends MapEntity {
    public House() {
        super();
        this.mainTexture = Assets.house1;
        this.collisionSize = new Vector2(2,3);
        this.imgOffset = new Vector2(2,2);
        sliceTexture(mainTexture);
    }
}
