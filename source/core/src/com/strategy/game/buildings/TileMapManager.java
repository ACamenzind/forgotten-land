package com.strategy.game.buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.strategy.game.*;
import com.strategy.game.screens.GameScreen;
import com.strategy.game.world.Resource;
import com.strategy.game.world.ResourceContainerBuilder;
import com.strategy.game.world.World;

import java.util.ArrayList;

/**
 * Handles the creation, placement and destruction of static entities (e.g. buildings)
 *
 */
public class TileMapManager implements Observable {
    private GameScreen gameScreen;
    private TiledMapTileLayer buildingsLayer; // the buildings layer
    private TiledMapTileLayer selectionLayer; // contains the selected building
    private TiledMapTileLayer influenceLayer;
    private MapEntity selectedEntity;
    private World world;
    private TiledMapTileLayer gridLayer;
    private SoundManager soundManager;
    private ArrayList<EventListener> listeners;
    private MapEntity lastDestroyed;

    public TileMapManager(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.world = gameScreen.getWorld();
        this.buildingsLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Buildings");
        this.selectionLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Selection");
        this.influenceLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Influence");
        this.selectionLayer.setOpacity(0.5f);
        this.influenceLayer.setOpacity(0.5f);
        this.gridLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Grid");
        this.gridLayer.setVisible(false);
        this.gridLayer.setOpacity(0.2f);
        this.soundManager = gameScreen.getGame().getSoundManager();
        this.listeners = new ArrayList<>();
//        this.addListener(this.gameScreen.getResourcesBar());
    }

    public MapEntity getSelectedEntity() {
        return selectedEntity;
    }

    /**
     * Toggles the selection
     * @param entity: the entity to be placed
     */
    public void setSelectedEntity(MapEntity entity) {
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

    public void removeSelectedEntity() {
        gameScreen.getSidebar().setEntity(null, false);
        selectedEntity = null;
        gridLayer.setVisible(false);
        influenceLayer.setVisible(false);
    }

    public boolean hasSelectedEntity() {
        return selectedEntity != null;
    }

    private Resource createNewResource(String type, ExtendedStaticTiledMapTile tile, int x, int y) {
        Resource res = new Resource(type, 100);
        res.setMainTextureSimple(tile.getTextureRegion().getTexture());
        res.setCoords(new Vector2(x, y));
        tile.setObject(res);
        return res;
    }

    /**
     * Searches the map file for tiles that are of type resource, and adds them to the resource list
     * to keep track of them.
     */
    public ArrayList<Resource> readResources() {
        TiledMapTileLayer layer = buildingsLayer;
        ArrayList<Resource> resources = new ArrayList<>();
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x,y);
                if (cell != null && cell.getTile() != null) {
                    ExtendedStaticTiledMapTile tile = (ExtendedStaticTiledMapTile) cell.getTile();
                    String type = tile.getProperties().get("type", String.class);
                    if (type != null) {
                        if (type.equals("wood")) {
                            Resource wood = createNewResource("Tree", tile, x, y);
                            resources.add(wood);
                        } else if (type.equals("rock")) {
                            Resource stone = createNewResource("Stone", tile, x, y);
                            resources.add(stone);
                        }
                    }
                }
            }
        }
        return resources;
    }

    public ResourceContainer getNearbyResources(Structure building, int startX, int endX, int startY, int endY) {
        boolean foundAResource = false;
        ResourceContainer resourcesAmount = new ResourceContainerBuilder().build();
        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i,j);

                if (cell != null && cell.getTile() != null) {
                    ExtendedStaticTiledMapTile tile = (ExtendedStaticTiledMapTile) cell.getTile();
                    MapEntity object = tile.getObject();
                    String property = tile.getProperties().get("type", String.class);
                    String resourceType = ((Collector) building).getResourceCollected();

                    if (property != null && property.equals(resourceType) && object != null && !foundAResource) {
                        ResourceContainer resourceProduction = building.getProductionsPerTurn();
                        int available = object.getLife();
                        int amount = 0;
                        if (resourceType.equals("wood")) {
                            amount = resourceProduction.getWood();
                        } else if (resourceType.equals("rock")) {
                            amount = resourceProduction.getRock();
                        }

                        if (available - amount > 0) {
                            object.removeLife(amount);
                        } else {
                            world.getBuilder().destroy(object);
                        }
                        foundAResource = true;
                        resourcesAmount.add(resourceProduction);
                    }
                }
            }
        }
        if (!foundAResource) {
            return null;
        }
        return resourcesAmount;
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
                boolean isTileValid = buildingsCell != null && buildingsCell.getTile() != null;
                if (isTileValid) {
                    TiledMapTile buildingTile = buildingsCell.getTile();
                    if (buildingTile instanceof ExtendedStaticTiledMapTile) {
                        if (((ExtendedStaticTiledMapTile) buildingTile).getBuildingsNearby() > 0) {
                            ExtendedStaticTiledMapTile influenceTile = new ExtendedStaticTiledMapTile(new TextureRegion(Assets.getTexture("influenceTile")));
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
     * Destroys the buildings in the given list. Used to collect buildings which should be
     * destroyed, and destroy them later.
     * @param buildingsToDestroy a list with buildings ready to be destroyed
     */
    public void destroyBuildings(ArrayList<Structure> buildingsToDestroy) {
        for (Structure building: buildingsToDestroy) {
            destroy(building);
        }
    }

    /**
     * Repairs a building. The cost depends on how much the building is damaged,
     * and the repair is only done if there are enough resources available.
     * @param building the building to repair
     */
    public void repairBuilding(Structure building) {
        int currentLife = building.getLife();
        int maxLife = building.getMaxLife();
        int amountToRepair = maxLife - currentLife;

        ResourceContainer cost = building.getCosts().multiply(amountToRepair/maxLife * 0.5f);
        ResourceContainer available = world.getResourceHandler().getTotalResources();

        if (available.subtract(cost).noNegativeResources()) {
            building.addLife(amountToRepair);
            world.getResourceHandler().removeFromTotal(cost);
            // Updates resources bar
            world.getGameScreen().getResourcesBar().update();
        }
    }

    /**
     * Removes the given building
     * @param entity the building to remove
     */
    public void destroy(MapEntity entity) {
        if (entity != null && world.getBuildings().size() > 1) {
            if (entity instanceof Resource) {
                world.getResources().remove(entity);
                updateListeners(Events.RESOURCE_DEPLETED);
            } else {
                world.getBuildings().remove(entity);
                ResourceContainer refund = ((Structure) entity).getCosts().multiply(0.5f);
                world.getResourceHandler().addToTotal(refund);
                world.getResourceHandler().removeAllWorkers((Structure) entity);
                // When destroying a building, you get half its costs back
                updateListeners(Events.BUILDING_DESTROYED);
            }

            Vector2 coords = entity.getCoords();

            // Remove the building tiles
            removeEntityTiles(entity, coords);

            int startX = (int) coords.x - entity.getInfluenceRadius();
            int startY = (int) coords.y - entity.getInfluenceRadius();

            int endX = (int) coords.x + entity.getInfluenceRadius() + (int) entity.collisionSize.x;
            int endY = (int) coords.y + entity.getInfluenceRadius() + (int) entity.collisionSize.y;

            // TODO: 12/05/2016 Add also for upper limits
            if (startX < 0) startX = 0;
            if (startY < 0) startY = 0;

            // Resets the influence area
            if (entity instanceof Structure) {
                resetInfluenceArea(startX, startY, endX, endY);
            }
            refreshInfluence(); // Redraws the influence area with the new data
        }
    }

    private void removeEntityTiles(MapEntity entity, Vector2 coords) {
        for (int i = (int) coords.x; i < (int) coords.x + entity.collisionSize.x; i++) {
            for (int j = (int) coords.y; j < (int) coords.y + entity.collisionSize.y; j++) {
                TiledMapTileLayer.Cell buildingsCell = buildingsLayer.getCell(i, j);
                ExtendedStaticTiledMapTile buildingTile = (ExtendedStaticTiledMapTile) buildingsCell.getTile();
                buildingTile.setTextureRegion(new TextureRegion(Assets.getTexture("emptyTile")));
                buildingTile.setObject(null);
                buildingTile.setObstacle(false);
                buildingsCell.setTile(buildingTile);
                buildingsLayer.setCell(i, j, buildingsCell);
            }
        }
    }

    private void resetInfluenceArea(int startX, int startY, int endX, int endY) {
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
            isSpaceFree = isSpaceFree(x, y);

            int endX = x + (int)selectedEntity.getCollisionSize().x;
            int endY = y + (int)selectedEntity.getCollisionSize().y;

            // Check whether it's in the area of influence of another building
            isInInfluenceRadius = isInInfluenceRadius(x, y, endX, endY);

            // Used for placing buildings at the start
            //TODO: do it in a different way possibly?
            if (forced) isInInfluenceRadius = true;

            if (!isSpaceFree) {
                updateListeners(Events.BUILDING_OVERLAP);
            }
            if (!isInInfluenceRadius) {
                updateListeners(Events.BUILDING_OUT_OF_INFLUENCE);
            }


            if (isSpaceFree && isInInfluenceRadius) {
                // If the placed entity is a building, remove its cost from the total resources if possible
                // Note: for now, all placeable entities are buildings, but in the future
                // we may add a map editor
                boolean placeable = world.getResourceHandler().canPlaceBuilding((Structure) selectedEntity);
                if (placeable) {
                    // Places the selected entity on the buildings layer, and add it to the list
                    selectedEntity.placeOnLayer(buildingsLayer, x, y);
                    this.world.getBuildings().add((Structure) selectedEntity);
                    this.world.getResourceHandler().removeFromTotal(((Structure) selectedEntity).getCosts());
                    if (selectedEntity instanceof Container)
                        this.world.getResourceHandler().addToMaximum(((Container) selectedEntity).getResourcesStored());
                    updateListeners(Events.BUILDING_PLACED);
                } else {
                    updateListeners(Events.BUILDING_NOT_ENOUGH_RESOURCES);
                }

                try {
                    // Makes a new instance of the proper subclass after placing it (for the next one)
                    Texture currentTex = selectedEntity.getMainTexture();
                    selectedEntity = selectedEntity.getClass().newInstance();
                    selectedEntity.setMainTexture(currentTex);

                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isInInfluenceRadius(int x, int y, int endX, int endY) {
        boolean result = false;
        for (int i = x; i < endX; i++) {
            for (int j = y; j < endY; j++) {
                TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i, j);
                boolean isExtended = cell != null && cell.getTile() instanceof ExtendedStaticTiledMapTile;
                if (isExtended) {
                    boolean hasBuildingsNearby =
                            ((ExtendedStaticTiledMapTile) cell.getTile()).getBuildingsNearby() > 0;
                    if (hasBuildingsNearby) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    private boolean isSpaceFree(int x, int y) {
        boolean result = true;
        for (int i = x; i < x + selectedEntity.getCollisionSize().x; i++) {
            for (int j = y; j < y + selectedEntity.getCollisionSize().y; j++) {
                TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i,j);
                boolean condition = cell != null && cell.getTile() != null;
                if (condition) {
                    TiledMapTile tile = cell.getTile();
                    boolean hasObject = tile instanceof ExtendedStaticTiledMapTile
                            && ((ExtendedStaticTiledMapTile) tile).getObject() != null;
                    if (hasObject) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Displays the given building's influence area.
     * @param building
     */
    public void showInfluenceArea(Structure building) {
        TiledMapTileLayer layer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Local influence");
        layer.setOpacity(0.5f);

        // Reset the layer
        for (int i = 0; i < layer.getWidth(); i++) {
            for (int j = 0; j < layer.getHeight(); j++) {
                layer.setCell(i,j, null);
            }
        }

        if (building != null) {
            int clickX = (int) building.getCoords().x;
            int clickY = (int) building.getCoords().y;
            int influenceRadius = building.getInfluenceRadius();
            Vector2 collisionSize = building.getCollisionSize();

            int startY = clickY - influenceRadius;
            int startX = clickX - influenceRadius;
            int endY = clickY + influenceRadius + (int) collisionSize.y;
            int endX = clickX + influenceRadius + (int) collisionSize.x;

            // TODO: 12/05/2016 Add also for upper limits
            if (startX < 0) startX = 0;
            if (startY < 0) startY = 0;

            for (int y = startY; y < endY; y++) {
                for (int x = startX; x < endX; x++) {
                    TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                    if (cell == null) {
                        cell = new TiledMapTileLayer.Cell();
                    }

                    TextureRegion texture;
                    TiledMapTile tile;
                    tile = cell.getTile();
                    if (layer.getName().equals("Local influence") && cell.getTile() != null) {
                        texture = cell.getTile().getTextureRegion();
                    } else if (layer.getName().equals("Local influence") && cell.getTile() == null) {
                        texture = new TextureRegion(Assets.getTexture("influenceTile"));
                    } else if (cell.getTile() != null) {
                        texture = cell.getTile().getTextureRegion();
                    } else {
                        texture = new TextureRegion(Assets.getTexture("emptyTile"));
                    }

                    // Creates a new tile if needed
                    if (!(tile instanceof ExtendedStaticTiledMapTile)) {
                        if (tile == null) {
                            tile = new ExtendedStaticTiledMapTile(texture);
                        } else {
                            tile = new ExtendedStaticTiledMapTile((StaticTiledMapTile) tile);
                        }
                    }

                    cell.setTile(tile);
                    layer.setCell(x, y, cell);
                }
            }
        }
    }

    /**
     * Changes the texture of the entity (if applicable).
     */
    public void rotate() {
        if (selectedEntity != null) {
            selectedEntity.changeTexture();
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

    @Override
    public void addListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    @Override
    public void removeListener(EventListener eventListener) {
        listeners.remove(eventListener);
    }

    @Override
    public void updateListeners(Events eventType) {
        for (EventListener listener : this.listeners) {
            listener.update(eventType);
        }
    }

    public MapEntity getLastDestroyed() {
        return lastDestroyed;
    }
}
