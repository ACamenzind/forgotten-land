package com.strategy.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

/**
 * Handles the creation and placement of static entities (e.g. buildings)
 *
 */
public class StaticEntityBuilder {
    private GameScreen gameScreen;
    private TiledMapTileLayer buildingsLayer; // the buildings layer
    private TiledMapTileLayer selectionLayer;
    private MapEntity selectedEntity;
    public StaticEntityBuilder(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.buildingsLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Buildings");
        this.selectionLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Selection");
    }

    public void selectEntity(MapEntity entity) {
        this.selectedEntity = entity;
        selectedEntity.setSelected(true);

    }
    public void placeSelectedEntity(int x, int y) {
        if (selectedEntity != null) {
            selectedEntity.placeOnLayer(buildingsLayer, x, y);
            selectedEntity.setSelected(false);
        }
    }

    public void renderSelection(int x, int y) {
        if (selectedEntity != null) {
            selectedEntity.placeOnLayer(selectionLayer, x, y);
        }
    }

    public void clear() {
        selectedEntity.resetTiles();
    }
}
