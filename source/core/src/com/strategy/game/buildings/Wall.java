package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;

/**
 * Created on 01/05/2016.
 */
public class Wall extends Building {
    //TODO: maybe put buildings properties into a single object.
    private static final String NAME = "Wall";
    private static final ResourceContainer COST = new ResourceContainer(0, 0, 0, 0, 0);
    private static final ResourceContainer PRODUCTION = new ResourceContainer(0, 0, 0, 0, 0);
    private static final ResourceContainer MAINTENANCE = new ResourceContainer(0, 1, 1, 1, 0);

    private static final int MAX_LIFE = 100;
    private static final int MAX_WORKERS = 0;
    private static final int INFLUENCE_RADIUS = 1;

    private static final Texture TEXTURE = Assets.leftwall;
    private static final Vector2 COLLISION = new Vector2(1,1);

    public Wall() {
        super(NAME, COST, PRODUCTION, MAINTENANCE, MAX_LIFE, MAX_WORKERS,INFLUENCE_RADIUS, TEXTURE, COLLISION, BuildingType.DECORATION);
        this.textures.add(Assets.leftwall);
        this.textures.add(Assets.rightwall);
        this.textures.add(Assets.middlewall);
//        sliceTexture(mainTexture);
    }
}
