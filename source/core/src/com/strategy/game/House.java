package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * A type of MapEntity
 */
public class House extends MapEntity {
    public House() {
        super();
        this.mainTexture = Assets.house1;
        sliceTexture(mainTexture);
    }
}
