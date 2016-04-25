package com.strategy.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

/**
 * Handles the creation and placement of static entities (e.g. buildings)
 * TODO: implement
 */
public class StaticEntityBuilder {
    private World world;
    private MapEntity selectedEntity;
    public StaticEntityBuilder(World world) {
        this.world = world;
    }

    public void selectEntity(MapEntity entity) {
        this.selectedEntity = entity;

    }
    public void placeSelectedEntity(TiledMapTileLayer layer, int x, int y) {
        selectedEntity.placeOnLayer(layer, x, y);
    }
}
