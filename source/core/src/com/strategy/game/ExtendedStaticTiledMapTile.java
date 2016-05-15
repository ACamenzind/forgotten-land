package com.strategy.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.strategy.game.buildings.MapEntity;

/**
 * Created by alex on 26/04/16.
 */
public class ExtendedStaticTiledMapTile extends StaticTiledMapTile {
    private MapEntity object;
    private boolean isObstacle;
    private int buildingsNearby;
    private TextureRegion textureRegion;
    private int id;

    public ExtendedStaticTiledMapTile(TextureRegion textureRegion) {
        super(textureRegion);
        this.isObstacle = false;
        this.buildingsNearby = 0;
    }

    public ExtendedStaticTiledMapTile(StaticTiledMapTile copy) {
        super(copy);
        if (copy.getProperties() != null) {
            getProperties().putAll(copy.getProperties());
        }
        this.textureRegion = copy.getTextureRegion();
        this.id = copy.getId();
        if (copy instanceof ExtendedStaticTiledMapTile) {
            this.object = ((ExtendedStaticTiledMapTile) copy).getObject();
//            this.isObstacle
        }
    }

    public int getBuildingsNearby() {
        return buildingsNearby;
    }

    public void incBuildingsNearby() {
        this.buildingsNearby += 1;
    }

    public void decBuildingsNearby() {
        this.buildingsNearby -= 1;
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
