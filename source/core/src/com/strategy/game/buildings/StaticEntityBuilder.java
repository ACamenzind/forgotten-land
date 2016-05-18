package com.strategy.game.buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.strategy.game.Assets;
import com.strategy.game.ExtendedStaticTiledMapTile;
import com.strategy.game.Utils;
import com.strategy.game.screens.GameScreen;
import com.strategy.game.world.World;

/**
 * Handles the creation and placement of static entities (e.g. buildings)
 * // TODO: 14/05/2016 Add deletion
 */
public class StaticEntityBuilder {
    private GameScreen gameScreen;
    private TiledMapTileLayer buildingsLayer; // the buildings layer
    private TiledMapTileLayer selectionLayer; // contains the selected building
    private TiledMapTileLayer influenceLayer;
    private MapEntity selectedEntity;
    private World world;
    private TiledMapTileLayer gridLayer;


    public StaticEntityBuilder(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.world = gameScreen.getWorld();
        this.buildingsLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Buildings");
        this.selectionLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Selection");
        this.influenceLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Influence");
        this.selectionLayer.setOpacity(0.5f);
        this.influenceLayer.setOpacity(0.2f);
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
            influenceLayer.setVisible(true);
        }
        else {
            selectedEntity = null;
            gridLayer.setVisible(false);
            influenceLayer.setVisible(false);
        }
    }

    public void untoggleSelectEntity() {
        selectedEntity = null;
        gridLayer.setVisible(false);
        influenceLayer.setVisible(false);
    }

    /**
     * Iterates over all buildings and resources placed in the map
     * and destroys those that have negative health.
     */
    public void checkDeadBuildings() {

    }


    /**
     * Draws the influence area of all placed buildings.
     * (Checks whether each tile has any buildings nearby and if so, highlights the tile)
     */
    private void refreshInfluence() {
        for (int i = 0; i < influenceLayer.getWidth(); i++) {
            for (int j = 0; j < influenceLayer.getHeight(); j++) {
                TiledMapTileLayer.Cell influenceCell = new TiledMapTileLayer.Cell();
                TiledMapTileLayer.Cell buildingsCell = buildingsLayer.getCell(i,j);
                boolean condition = buildingsCell != null && buildingsCell.getTile() != null;
                if (condition) {
                    TiledMapTile buildingTile = buildingsCell.getTile();
                    if (buildingTile instanceof ExtendedStaticTiledMapTile) {
                        if (((ExtendedStaticTiledMapTile) buildingTile).getBuildingsNearby() > 0) {
                            ExtendedStaticTiledMapTile influenceTile = new ExtendedStaticTiledMapTile(new TextureRegion(Assets.redTile));
                            influenceCell.setTile(influenceTile);
                            influenceLayer.setCell(i, j, influenceCell);
                        } else {
                            influenceCell.setTile(null);
                            influenceLayer.setCell(i, j, influenceCell);
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the entity which has been clicked, if it's there.
     * @param x tile coordinate
     * @param y tile coordinate
     * @return The clicked entity.
     */
    public MapEntity getEntityAt(int x, int y) {
        TiledMapTileLayer.Cell buildingsCell = buildingsLayer.getCell(x,y);
        if (buildingsCell != null) {
            TiledMapTile tile = buildingsCell.getTile();
            if (tile instanceof ExtendedStaticTiledMapTile) {
                return ((ExtendedStaticTiledMapTile) tile).getObject();
            }
        }
        return null;
    }

    /**
     * Removes the given building
     * @param building the building to remove
     */
    public void destroy(Building building) {
        if (building != null) {
            world.getStaticEntities().remove(building);

            Vector2 coords = building.getCoords();

            // Remove the building tiles
            for (int i = (int) coords.x; i < (int) coords.x + building.collisionSize.x; i++) {
                for (int j = (int) coords.y; j < (int) coords.y + building.collisionSize.y; j++) {
                    TiledMapTileLayer.Cell buildingsCell = buildingsLayer.getCell(i, j);
                    ExtendedStaticTiledMapTile buildingTile = (ExtendedStaticTiledMapTile) buildingsCell.getTile();
                    buildingTile.setTextureRegion(new TextureRegion(Assets.emptyTile));
                    buildingTile.setObject(null);
                    buildingsCell.setTile(buildingTile);
                    buildingsLayer.setCell(i, j, buildingsCell);
                }
            }

            int startX = (int) coords.x - building.getInfluenceRadius();
            int startY = (int) coords.y - building.getInfluenceRadius();

            int endX = (int) coords.x + building.getInfluenceRadius() + (int) building.collisionSize.x;
            int endY = (int) coords.y + building.getInfluenceRadius() + (int) building.collisionSize.y;

            // TODO: 12/05/2016 Add also for upper limits
            if (startX < 0) startX = 0;
            if (startY < 0) startY = 0;

            // Resets the influence area
            for (int j = startY; j < endY; j++) {
                for (int i = startX; i < endX; i++) {
                    TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i, j);
                    if (cell != null) {
                        ExtendedStaticTiledMapTile tile = (ExtendedStaticTiledMapTile) cell.getTile();
                        int buildingsNearby = tile.getBuildingsNearby();
                        if (buildingsNearby > 0)
                            tile.decBuildingsNearby();
                        cell.setTile(tile);
                        buildingsLayer.setCell(i, j, cell);
                    }
                }
            }
            refreshInfluence(); // Redraws the influence area with the new data
        }
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

            // collision when:
            // - tile has obstacle property
            // - tile has object

            // Check if cells are occupied
            for (int i = x; i < x + selectedEntity.getCollisionSize().x; i++) {
                for (int j = y; j < y + selectedEntity.getCollisionSize().y; j++) {
                    TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i,j);
                    boolean condition = cell != null && cell.getTile() != null;
                    if (condition) {
                        TiledMapTile tile = cell.getTile();
                        boolean hasObject = tile instanceof ExtendedStaticTiledMapTile
                                && ((ExtendedStaticTiledMapTile) tile).getObject() != null;
                        boolean hasProperty = tile.getProperties().get("obstacle", Boolean.class) != null;
                        if (hasObject || hasProperty) {
                            isSpaceFree = false;
                            break;
                        }
                    }
                }
            }

//            int influenceRadius = selectedEntity.getInfluenceRadius();
            int startX = x - (int)selectedEntity.getCollisionSize().x;
            int startY = y - (int)selectedEntity.getCollisionSize().y;
            int endX = x + (int)selectedEntity.getCollisionSize().x;
            int endY = y + (int)selectedEntity.getCollisionSize().y;

            // Check whether it's in the area of influence of another building
            // TODO: 14/05/2016 Add exceptions for specific buildings (e.g. walls)
            for (int i = startX; i < endX; i++) {
                for (int j = startY; j < endY; j++) {
                    TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i, j);
                    boolean isExtended = cell != null
                            && cell.getTile() instanceof ExtendedStaticTiledMapTile;
                    if (isExtended) {
                        boolean hasBuildingsNearby =
                                ((ExtendedStaticTiledMapTile) cell.getTile()).getBuildingsNearby() > 0;
                        if (hasBuildingsNearby) {
                            isInInfluenceRadius = true;
                            break;
                        }
                    }
                }
            }

            // Used for placing buildings at the start
            //TODO: do it in a different way possibly?
            if (forced) isInInfluenceRadius = true;

            Sound sound = Assets.hit;

            if (isSpaceFree && isInInfluenceRadius) {
                long id = sound.play(0.5f);
                sound.setPitch(id, 0.75f);



                // If the placed entity is a building, remove its cost from the total resources if possible
                // Note: for now, all placeable entities are buildings, but in the future
                // we may add a map editor
                boolean placeable = world.getResourceHandler().canPlaceBuilding((Building) selectedEntity);
                if (selectedEntity instanceof Building && placeable) {
                    // Places the selected entity on the buildings layer, and add it to the list
                    selectedEntity.placeOnLayer(buildingsLayer, x, y);
                    this.world.getStaticEntities().add(selectedEntity);
                    this.world.getResourceHandler().removeFromTotal(((Building) selectedEntity).getCosts());
                }



                try {
                    // Makes a new instance of the proper subclass after placing it (for the next one)
                    // TODO: check if this creates other problems.
                    Texture currentTex = selectedEntity.getMainTexture();
                    selectedEntity = selectedEntity.getClass().newInstance();
                    selectedEntity.setMainTexture(currentTex);

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                // Updates resources bar after placing building
                world.getGameScreen().getResourcesBar().update();



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
            refreshInfluence(); // Refresh the influence area view
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
