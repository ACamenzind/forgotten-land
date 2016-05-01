package com.strategy.game.buildings;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;

/**
 * Created on 01/05/2016.
 */
public class Wall extends MapEntity {
    //FIXME: can overlap with part of house
    public Wall() {
        super();
        this.textures.add(Assets.leftwall);
        this.textures.add(Assets.rightwall);
        this.textures.add(Assets.middlewall);
        this.mainTexture = textures.get(0);
        this.collisionSize = new Vector2(1,1);
        sliceTexture(mainTexture);
    }
}
