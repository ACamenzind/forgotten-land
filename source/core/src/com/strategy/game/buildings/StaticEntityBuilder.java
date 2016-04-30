package com.strategy.game.buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.strategy.game.Assets;
import com.strategy.game.Utils;
import com.strategy.game.screens.GameScreen;

/**
 * Handles the creation and placement of static entities (e.g. buildings)
 *
 */
public class StaticEntityBuilder {
    private GameScreen gameScreen;
    private TiledMapTileLayer buildingsLayer; // the buildings layer
    private TiledMapTileLayer selectionLayer; // contains the selected building
    private MapEntity selectedEntity;
    public StaticEntityBuilder(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.buildingsLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Buildings");
        this.selectionLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Selection");
        this.selectionLayer.setOpacity(0.5f);
    }

    public MapEntity getSelectedEntity() {
        return selectedEntity;
    }

    /**
     * Toggles the selection
     * @param entity: the entity to be placed
     */
    public void toggleSelectEntity(MapEntity entity) {
        if (selectedEntity == null)
            this.selectedEntity = entity;
        else
            this.selectedEntity = null;
    }

    /**
     *  Places the selected building onto the buildings layer.
     * @param x : tile coordinate
     * @param y : tile coordinate
     */
    public void placeSelectedEntity(int x, int y) {
        if (selectedEntity != null) {
            boolean isSpaceFree = true;

            // Check if cells are occupied
            for (int i = x; i < x + selectedEntity.getCollisionSize().x; i++) {
                for (int j = y; j < y + selectedEntity.getCollisionSize().y; j++) {
                    TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i, j);
                    if (cell != null) {
                        isSpaceFree = false;
                        break;
                    }
                }
            }
            Sound sound = Assets.hit;
            if (isSpaceFree) {
                long id = sound.play(0.5f);
                sound.setPitch(id, 0.75f);
//                selectedEntity = new Ma;
                try {
                    // Makes a new instance of the proper subclass
                    // TODO: check if this creates other problems.
                    selectedEntity = selectedEntity.getClass().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                selectedEntity.placeOnLayer(buildingsLayer, x, y);
            } else {
                long id = sound.play(0.5f);
                sound.setPitch(id, 5f);
            }
        }
    }

    /**
     * Render the building being moved around with respect to the camera.
     * @param camera scene camera
     */
    public void renderSelection(OrthographicCamera camera) {
        int screenX = Gdx.input.getX();
        int screenY = Gdx.input.getY();

        Vector3 touch = new Vector3(screenX, screenY, 0);
        Vector3 pickedTile = Utils.cartesianToIso(touch, camera);
        if (selectedEntity != null) {
            selectedEntity.placeOnLayer(selectionLayer, (int)pickedTile.x, (int)pickedTile.y);
        }
    }

    /**
     * Resets the tiles to the previous state. Called after the render() method.
     */
    public void clear() {
        if (selectedEntity != null) {
            selectedEntity.resetTiles();
        }
    }
}
