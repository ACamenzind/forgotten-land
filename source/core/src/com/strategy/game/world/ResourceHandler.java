package com.strategy.game.world;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.ResourceContainer;
import com.strategy.game.buildings.Building;
import com.strategy.game.buildings.CollectorWood;
import com.strategy.game.buildings.MapEntity;

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

        for (MapEntity building:
             world.getStaticEntities()) {
            if (building instanceof CollectorWood) {
                TiledMapTileLayer buildingsLayer = (TiledMapTileLayer) world.getGameScreen().getMap().getLayers().get("Buildings");
                int treeCount = 0;
                Vector2 coords = building.getCoords();

                int startX = (int)coords.x;
                int endX = (int) (startX + building.getCollisionSize().x + building.getInfluenceRadius());

                int startY = (int)coords.y;
                int endY = (int) (startY + building.getCollisionSize().y + building.getInfluenceRadius());

                for (int i = startX; i < endX; i++) {
                    for (int j = startY; j < endY; j++) {
                        TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i,j);
                        if (cell != null) {
                            String type = cell.getTile().getProperties().get("type", String.class);
                            if (type != null && type.equals("wood")) {
                                treeCount++;
                            }
                        }
                    }
                }
//                System.out.println("num of trees: " + treeCount);
                ((CollectorWood) building).setTreeCount(treeCount);
//                System.out.println("Per worker: " + ((CollectorWood) building).getProductionsPerWorker().toString());
//                System.out.println("Per turn: " + ((CollectorWood) building).getProductionsPerTurn().toString());

            }
            ResourceContainer production = ((Building) building).getProductionsPerTurn();
            ResourceContainer maintenance = ((Building) building).getMaintenanceCosts();
            totalResources = totalResources.add(production);
            totalResources = totalResources.subtract(maintenance);
        }
    }


}
