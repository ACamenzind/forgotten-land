package com.strategy.game.world;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.ExtendedStaticTiledMapTile;
import com.strategy.game.ResourceContainer;
import com.strategy.game.buildings.Building;
import com.strategy.game.buildings.Collector;
import com.strategy.game.buildings.MapEntity;

import java.util.ArrayList;
import java.util.Random;


/**
 * Handles all the resources in the world.
 */
public class ResourceHandler {
    private World world;

    private ResourceContainer totalResources;
    private ResourceContainer maximumResources;
    private int totalWorkers;


    public ResourceHandler(World world, int woodCounter, int goldCounter, int foodCounter, int rockCounter, int people) {
        this.world = world;
        this.totalResources = new ResourceContainer(woodCounter, goldCounter, foodCounter, rockCounter, people);
        this.maximumResources = new ResourceContainer(300, 300, 300, 300, 10);
        this.totalWorkers = 0;
    }

    public ResourceContainer getTotalResources() {
        return totalResources;
    }

    public ResourceContainer getMaximumResources() {
        return maximumResources;
    }

    public int getTotalWorkers() {
        return totalWorkers;
    }

    public int getUnemployed() {
        return totalResources.people - totalWorkers;
    }

    public void setTotalWorkers(int totalWorkers) {
        this.totalWorkers = totalWorkers;
    }

    /**
     * Moves a worker to the given building.
     * @param building
     */
    public void addWorker(Building building) {
        if (totalWorkers < totalResources.people) {
            building.changeWorker(1);
            totalWorkers++;
        }
    }

    /**
     * Removes a worker from the given building
     * @param building
     */
    public void removeWorker(Building building) {
        if (totalWorkers > 0) {
            building.changeWorker(-1);
            totalWorkers--;
        }
    }

    /**
     * Removes all workers from the given building (used when destroying).
     * @param building
     */
    public void removeAllWorkers(Building building) {
        int workers = building.getWorkers();
        building.changeWorker(-workers);
        totalWorkers -= workers;
    }

    /**
     * Removes the specified amount of people, also removing
     * a random worker accordingly.
     *
     */
    public void removeOnePop() {
        int totalPop = totalResources.people;
        if (totalPop > 0){
            removeFromTotal(new ResourceContainer(0, 0, 0, 0, 1));
            int availablePop = totalPop - totalWorkers;
            if (availablePop <= 0) {
                // Remove a worker from a radom building.
                ArrayList<Building> buildings = world.getBuildings();
                boolean removed = false;
                Random random = new Random();
                while (!removed) {
                    int randIndex = random.nextInt(buildings.size());
                    Building currentBuilding = buildings.get(randIndex);
                    if (currentBuilding != null && currentBuilding.getWorkers() > 0) {
                        currentBuilding.changeWorker(-1);
                        totalWorkers--;
                        removed = true;
                    }
                }
            }
        }
    }

    /**
     * Remove a certain amount of people from the game
     * @param amount the number of people to kill
     */
    public void removePop(int amount) {
        for (int i = 0; i < amount; i++) {
            removeOnePop();
        }
    }

    /**
     * Removes from the total count of resources
     * @param amount
     */
    public void removeFromTotal(ResourceContainer amount) {
        totalResources = totalResources.subtract(amount);
    }

    /**
     * Adds to the total amount of resources
     * @param amount the amount to add
     */
    public void addToTotal(ResourceContainer amount) {
        totalResources = totalResources.add(amount);
    }

    /**
     * Adds to the maximum amount of resources (used for containers)
     * @param amount
     */
    public void addToMaximum(ResourceContainer amount) {
        maximumResources = maximumResources.add(amount);
    }


    /**
     * Returns the amount of resources that will be added/consumed the next tick.
     * To do that it sums up all the maintenance costs as well as all the productions.
     * @return
     */
    public ResourceContainer getVariance() {
        ResourceContainer totalCost = new ResourceContainer();
        ResourceContainer totalProduction = new ResourceContainer();

        for (Building building : world.getBuildings()) {
            totalCost = totalCost.add(building.getMaintenanceCosts());
            totalProduction = totalProduction.add(building.getProductionsPerTurn());
        }

        // Food consumption is handled separately
        totalCost.food = totalResources.people;

        return totalProduction.subtract(totalCost);
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
     * Limits the resource count
     */
    private void capResourceCount() {
        int wood = Math.min(totalResources.wood, maximumResources.wood);
        int food = Math.min(totalResources.food, maximumResources.food);
        int rock = Math.min(totalResources.rock, maximumResources.rock);
        int gold = Math.min(totalResources.gold, maximumResources.gold);
        int people = Math.min(totalResources.people, maximumResources.people);

        totalResources = new ResourceContainer(wood, food, rock, gold, people);
    }

    /**
     * Handles what happens when resources go negative.
     */
    private void handleNegativeResourceCount() {
        int food = totalResources.food;
        // for every 10 food negative 1 person dies
        if (food <= -10) {
            int factor = - totalResources.food / 10;
            for (int i = 0; i < factor; i++) {
                removeOnePop();
            }
        }

        // If the people count goes negative, the game is lost.
        int people = totalResources.people;
        if (people <= 0) {
            world.handleGameLost();
        }

        // The other resources just don't go below zero.
        int wood = Math.max(totalResources.wood, 0);
        int rock = Math.max(totalResources.rock, 0);
        int gold = Math.max(totalResources.gold, 0);

        totalResources = new ResourceContainer(wood, food, rock, gold, people);
    }

    /**
     * Reduce 1 unit of food for each citizen per turn.
     */
    public void feedPopulation() {
        int wood = totalResources.wood;
        int food = totalResources.food - totalResources.people;
        int rock = totalResources.rock;
        int gold = totalResources.gold;
        int people = totalResources.people;
        totalResources = new ResourceContainer(wood, food, rock, gold, people);
    }


    /**
     * Updates the state of all the resources.
     * Goes through all the buildings and adds their production to the total,
     * as well as removing the maintenance costs.
     */
    public void update() {

        for (Building building:
             world.getBuildings()) {
            ResourceContainer maintenance;

            // Resource collectors: lumberjack and mine
            if (building instanceof Collector) {

                TiledMapTileLayer buildingsLayer = (TiledMapTileLayer) world.getGameScreen().getMap().getLayers().get("Buildings");
                Vector2 coords = building.getCoords();

                int startX = (int)coords.x - building.getInfluenceRadius();
                int endX = (int) (coords.x + building.getCollisionSize().x + building.getInfluenceRadius());

                int startY = (int)coords.y - building.getInfluenceRadius();
                int endY = (int) (coords.y + building.getCollisionSize().y + building.getInfluenceRadius());

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
                            String resourceType = ((Collector) building).getResourceCollected();

                            if (property != null && property.equals(resourceType) && object != null && !foundAResource) {
                                ResourceContainer resourceProduction = building.getProductionsPerTurn();
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
                if (!foundAResource) System.out.println("No resources found, move the building!");
            } else { // normal buildings
                ResourceContainer production = building.getProductionsPerTurn();
                totalResources = totalResources.add(production);
            }
            maintenance = building.getMaintenanceCosts();
            totalResources = totalResources.subtract(maintenance);

        }

        feedPopulation();
        capResourceCount();
        handleNegativeResourceCount();
//        System.out.println(getVariance().toString());

        if (totalResources.hasZeroResources())
            degradeBuildings();

    }

    /**
     * Degrades the buildings if such conditions are met (i.e. lack of resources)
     */
    private void degradeBuildings() {
        ArrayList<Building> toDestroy = new ArrayList<Building>();
        for (Building building: world.getBuildings()) {
            building.degrade();
            if (building.getLife() <= 0) {
                toDestroy.add(building);
            }
        }
        world.getBuilder().destroyBuildings(toDestroy);
    }
}
