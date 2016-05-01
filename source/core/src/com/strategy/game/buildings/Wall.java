package com.strategy.game.buildings;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;

/**
 * Created on 01/05/2016.
 */
public class Wall extends MapEntity {
    public Wall() {
        super();
        this.textures.add(Assets.leftwall);
        this.textures.add(Assets.rightwall);
        this.textures.add(Assets.middlewall);
//        changeTexture();
        this.mainTexture = textures.get(0);
//        this.mainTexture = Assets.leftwall;
        this.collisionSize = new Vector2(1,1);
//        this.imgOffset = new Vector2(0,1);
        sliceTexture(mainTexture);
    }
}
