package com.strategy.game.buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.strategy.game.Assets;
import com.strategy.game.ExtendedStaticTiledMapTile;
import com.strategy.game.Utils;
import com.strategy.game.screens.GameScreen;
import com.strategy.game.world.World;

/**
 * Handles the creation and placement of static entities (e.g. buildings)
 *
 */
public class StaticEntityBuilder {
    private GameScreen gameScreen;
    private TiledMapTileLayer buildingsLayer; // the buildings layer
    private TiledMapTileLayer selectionLayer; // contains the selected building
    private MapEntity selectedEntity;
    private World world;
    private TiledMapTileLayer gridLayer;


    public StaticEntityBuilder(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.world = gameScreen.getWorld();
        this.buildingsLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Buildings");
        this.selectionLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Selection");
        this.selectionLayer.setOpacity(0.5f);
        this.gridLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Grid");
        this.gridLayer.setVisible(false);
        this.gridLayer.setOpacity(0.2f);
    }

    public MapEntity getSelectedEntity() {
        return selectedEntity;
    }

    /**
     * Toggles the selection
     * @param entity: the entity to be placed
     */
    public void toggleSelectEntity(MapEntity entity) {
        if (entity != null) {
            selectedEntity = entity;
            gridLayer.setVisible(true);
        }
        else {
            selectedEntity = null;
            gridLayer.setVisible(false);
        }
    }

    public void untoggleSelectEntity() {
        selectedEntity = null;
        gridLayer.setVisible(false);
    }

    /**
     *  Places the selected building onto the buildings layer.
     * @param x : tile coordinate
     * @param y : tile coordinate
     */
    public void placeSelectedEntity(int x, int y, boolean forced) {
        if (selectedEntity != null) {
            boolean isSpaceFree = true;
            boolean isInInfluenceRadius = false;

            // Check if cells are occupied
            for (int i = x; i < x + selectedEntity.getCollisionSize().x; i++) {
                for (int j = y; j < y + selectedEntity.getCollisionSize().y; j++) {
                    TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i, j);
                    if (cell != null && cell.getTile() instanceof ExtendedStaticTiledMapTile) {
                        ExtendedStaticTiledMapTile tile = (ExtendedStaticTiledMapTile) cell.getTile();
                        if (tile != null && tile.getObject() != null) {
                            isSpaceFree = false;
                            break;
                        }
                    }
                }
            }

            int influenceRadius = selectedEntity.getInfluenceRadius();
            // Check whether it's in the area of influence of another building
            for (int i = x - (int)selectedEntity.getCollisionSize().x; i < x + (int)selectedEntity.getCollisionSize().x; i++) {
                for (int j = y - (int)selectedEntity.getCollisionSize().y; j < y + (int)selectedEntity.getCollisionSize().y; j++) {
                    TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i, j);
                    if (cell != null) {
                        if (cell.getTile() instanceof ExtendedStaticTiledMapTile) {
                            ExtendedStaticTiledMapTile tile = (ExtendedStaticTiledMapTile) cell.getTile();
                            if (tile != null && tile.getBuildingsNearby() > 0) {
                                System.out.println(tile.getBuildingsNearby());
                                isInInfluenceRadius = true;
                                break;
                            }
                        }
                    }
                }
            }

            if (forced) isInInfluenceRadius = true;


            Sound sound = Assets.hit;

            if (isSpaceFree && isInInfluenceRadius) {
                long id = sound.play(0.5f);
                sound.setPitch(id, 0.75f);

                try {
                    // Makes a new instance of the proper subclass
                    // TODO: check if this creates other problems.
                    Texture currentTex = selectedEntity.getMainTexture();
                    selectedEntity = selectedEntity.getClass().newInstance();
                    selectedEntity.setMainTexture(currentTex);

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                selectedEntity.placeOnLayer(buildingsLayer, x, y);
                this.world.getStaticEntities().add(selectedEntity);

                if (this.getSelectedEntity() instanceof House) //TODO: abstract a bit
                    world.getResourceHandler().incrementWoodCounter(-100);
                else if (this.getSelectedEntity() instanceof Castle)
                    world.getResourceHandler().incrementStoneCounter(-100);


                //remove resources

            } else {
                long id = sound.play(0.5f);
                sound.setPitch(id, 5f);
            }
        }
    }

    public void rotate() {
        if (selectedEntity != null) {
            selectedEntity.changeTexture();
        }
//        System.out.println(selectedEntity.getTiles().toString());
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
