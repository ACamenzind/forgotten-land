package com.strategy.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.strategy.game.buildings.MapEntity;

/**
 * Created by alex on 26/04/16.
 */
public class ExtendedStaticTiledMapTile extends StaticTiledMapTile {
    private MapEntity object;
    private boolean isObstacle;

    public ExtendedStaticTiledMapTile(TextureRegion textureRegion) {
        super(textureRegion);
    }



    public ExtendedStaticTiledMapTile(ExtendedStaticTiledMapTile copy) {
        super(copy);
    }

    public MapEntity getObject() {
        return object;
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public void setObject(MapEntity object) {
        this.object = object;
    }

    public void setObstacle(boolean obstacle) {
        isObstacle = obstacle;
    }
}
