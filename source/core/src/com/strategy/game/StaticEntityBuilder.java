package com.strategy.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

/**
 * Handles the creation and placement of static entities (e.g. buildings)
 * TODO: implement
 */
public class StaticEntityBuilder {
    private World world;
    private GameScreen gameScreen;
    private TiledMapTileLayer layer; // the buildings layer TODO: assign a name in tmx file
    private MapEntity selectedEntity;
    public StaticEntityBuilder(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.layer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get(1);
    }

    public void selectEntity(MapEntity entity) {
        this.selectedEntity = entity;
    }
    public void placeSelectedEntity(int x, int y) {
        selectedEntity.placeOnLayer(layer, x, y);
    }
}
