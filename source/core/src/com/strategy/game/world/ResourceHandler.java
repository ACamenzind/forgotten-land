package com.strategy.game.world;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.ExtendedStaticTiledMapTile;
import com.strategy.game.ResourceContainer;
import com.strategy.game.buildings.Building;
import com.strategy.game.buildings.Collector;
import com.strategy.game.buildings.CollectorWood;
import com.strategy.game.buildings.MapEntity;


/**
 * Created by francescosani on 27/04/16.
 */
public class ResourceHandler {
    private World world;

    private ResourceContainer totalResources;
    private ResourceContainer maximumResources;


    public ResourceHandler(World world, int woodCounter, int goldCounter, int foodCounter, int rockCounter, int people) {
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
            ResourceContainer maintenance;

            // Resource collectors: lumberjack and mine
            if (entity instanceof Collector) {
                TiledMapTileLayer buildingsLayer = (TiledMapTileLayer) world.getGameScreen().getMap().getLayers().get("Buildings");
                Vector2 coords = entity.getCoords();

                int startX = (int)coords.x - entity.getInfluenceRadius();
                int endX = (int) (coords.x + entity.getCollisionSize().x + entity.getInfluenceRadius());

                int startY = (int)coords.y - entity.getInfluenceRadius();
                int endY = (int) (coords.x + entity.getCollisionSize().y + entity.getInfluenceRadius());

                // Goes through the tiles inside the building's influence area and removes resources from the first
                // resource (either rock or wood) it finds.
                // TODO: 18/05/16 Refactor
                boolean foundAResource = false;
                for (int i = startX; i < endX; i++) {
                    for (int j = startY; j < endY; j++) {
                        TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i,j);

                        if (cell != null && cell.getTile() != null) {
                            ExtendedStaticTiledMapTile tile = (ExtendedStaticTiledMapTile) cell.getTile();
                            MapEntity object = tile.getObject();
                            String property = tile.getProperties().get("type", String.class);
                            String resourceType = ((Collector) entity).getResourceCollected();

                            if (property != null && property.equals(resourceType) && object != null && !foundAResource) {
                                ResourceContainer resourceProduction = ((Collector) entity).getProductionsPerTurn();
                                int available = object.getLife();
                                int amount = 0;
                                if (resourceType.equals("wood")) {
                                    amount = resourceProduction.wood;
                                } else if (resourceType.equals("rock")) {
                                    amount = resourceProduction.rock;
                                }

                                if (available - amount > 0) {
                                    object.removeLife(amount);
                                } else {
                                    System.out.println("Resource empty");
                                    world.getBuilder().destroy(object);
                                }
                                foundAResource = true;
                                totalResources = totalResources.add(resourceProduction);
                            }
                        }
                    }
                }
            }
//            else if (entity instanceof Building) {
//                // Other buildings, which only have maintenance
            maintenance = ((Building) entity).getMaintenanceCosts();
//            }
            totalResources = totalResources.subtract(maintenance);
        }
    }


}
