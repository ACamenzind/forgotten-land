package com.strategy.game.world;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.ExtendedStaticTiledMapTile;
import com.strategy.game.ResourceContainer;
import com.strategy.game.buildings.Building;
import com.strategy.game.buildings.CollectorWood;
import com.strategy.game.buildings.MapEntity;
import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.Iterator;

/**
 * Created by francescosani on 27/04/16.
 */
public class ResourceHandler {
    private final int startingIncrement;

    private int woodCounter;
    private int goldCounter;
    private int foodCounter;
    private int rockCounter;

    private int woodIncrementer;
    private int goldIncrementer;
    private int foodIncrementer;
    private int rockIncrementer;

    private World world;

    private ResourceContainer totalResources;
    private ResourceContainer maximumResources;


    public ResourceHandler(World world, int startingIncrement, int woodCounter, int goldCounter, int foodCounter, int rockCounter, int people) {
        this.startingIncrement = startingIncrement;
        this.woodCounter = woodCounter;
        this.goldCounter = goldCounter;
        this.foodCounter = foodCounter;
        this.rockCounter = rockCounter;
        this.woodIncrementer = 0;
        this.goldIncrementer = 0;
        this.foodIncrementer = 0;
        this.rockIncrementer = 0;
        this.world = world;
        this.totalResources = new ResourceContainer(woodCounter, goldCounter, foodCounter, rockCounter, people);
        this.maximumResources = new ResourceContainer(100, 100, 100, 100, 10);
    }

    public ResourceContainer getTotalResources() {
        return totalResources;
    }

    public ResourceContainer getMaximumResources() {
        return maximumResources;
    }

    public void removeFromTotal(ResourceContainer amount) {
        totalResources = totalResources.subtract(amount);
    }

    /**
     * Checks whether there are enough resources to place a building
     * @param building the building that is being placed
     * @return true if the building is placeable
     */
    public boolean canPlaceBuilding(Building building) {
        ResourceContainer result = totalResources.subtract(building.getCosts());
        return result.noNegativeResources();
    }

    /**
     * Updates the state of all the resources.
     * Goes through all the buildings and adds their production to the total,
     * as well as removing the maintenance costs.
     */
    public void update() {

        for (MapEntity entity:
             world.getStaticEntities()) {
            if (entity instanceof CollectorWood) {
                TiledMapTileLayer buildingsLayer = (TiledMapTileLayer) world.getGameScreen().getMap().getLayers().get("Buildings");
                int treeCount = 0;
                Vector2 coords = entity.getCoords();

                int startX = (int)coords.x - entity.getInfluenceRadius();
                int endX = (int) (coords.x + entity.getCollisionSize().x + entity.getInfluenceRadius());

                int startY = (int)coords.y - entity.getInfluenceRadius();
                int endY = (int) (coords.x + entity.getCollisionSize().y + entity.getInfluenceRadius());

                // Goes through the tiles inside the building's influence area and removes resources from the first
                // resource (either rock or wood) it finds.
                boolean foundAResource = false;
                for (int i = startX; i < endX; i++) {
                    for (int j = startY; j < endY; j++) {
                        TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i,j);
                        if (cell != null && cell.getTile() != null) {
                            ExtendedStaticTiledMapTile tile = (ExtendedStaticTiledMapTile) cell.getTile();
                            MapEntity object = tile.getObject();
                            String property = tile.getProperties().get("type", String.class);
                            String resourceType = ((CollectorWood) entity).getResourceCollected();
                            if (property != null && property.equals(resourceType) && object != null && !foundAResource) {
                                int amount = ((CollectorWood) entity).getWorkers() * 10;
                                int available = object.getLife();
                                if (available - amount > 0) {
                                    object.removeLife(amount);
                                } else {
                                    System.out.println("Resource empty");
                                    world.getBuilder().destroy(object);
                                }
                                foundAResource = true;
                            }
                        }
                    }
                }
//                System.out.println("Looped");
//                System.out.println("num of trees: " + treeCount);
//                ((CollectorWood) building).setTreeCount(treeCount);
//                System.out.println("Per worker: " + ((CollectorWood) building).getProductionsPerWorker().toString());
//                System.out.println("Per turn: " + ((CollectorWood) building).getProductionsPerTurn().toString());

            }
//            ResourceContainer production = ((Building) building).getProductionsPerTurn();
//            ResourceContainer maintenance = ((Building) building).getMaintenanceCosts();
//            totalResources = totalResources.add(production);
//            totalResources = totalResources.subtract(maintenance);
        }
    }


}
