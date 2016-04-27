package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * A type of MapEntity
 */
public class House extends MapEntity {
    public House() {
        super();
        //TODO: load from Assets
        this.mainTexture = new Texture(Gdx.files.internal("core/assets/house1.png"));
        sliceTexture(mainTexture);
    }
}
