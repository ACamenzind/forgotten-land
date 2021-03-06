package com.strategy.game.world;

import com.badlogic.gdx.math.Vector2;
import com.strategy.game.ResourceContainer;
import com.strategy.game.buildings.Structure;
import com.strategy.game.buildings.Collector;

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


    public ResourceHandler(World world, ResourceContainer initialResources) {
        this.world = world;
        this.totalResources = initialResources;
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
        return totalResources.getPeople() - totalWorkers;
    }

    public void setTotalWorkers(int totalWorkers) {
        this.totalWorkers = totalWorkers;
    }

    /**
     * Moves a worker to the given building.
     * @param building
     */
    public void addWorker(Structure building) {
        if (totalWorkers < totalResources.getPeople()) {
            building.changeWorker(1);
            totalWorkers++;
        }
    }

    /**
     * Removes a worker from the given building
     * @param building
     */
    public void removeWorker(Structure building) {
        if (totalWorkers > 0) {
            building.changeWorker(-1);
            totalWorkers--;
        }
    }

    /**
     * Removes all workers from the given building (used when destroying).
     * @param building
     */
    public void removeAllWorkers(Structure building) {
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
        int totalPop = totalResources.getPeople();
        if (totalPop > 0){
            removeFromTotal(new ResourceContainer(0, 0, 0, 0, 1));
            int availablePop = totalPop - totalWorkers;
            if (availablePop <= 0) {
                // Remove a worker from a radom building.
                ArrayList<Structure> buildings = world.getBuildings();
                boolean removed = false;
                Random random = new Random();
                while (!removed) {
                    int randIndex = random.nextInt(buildings.size());
                    Structure currentBuilding = buildings.get(randIndex);
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

        for (Structure building : world.getBuildings()) {
            totalCost = totalCost.add(building.getMaintenanceCosts());
            totalProduction = totalProduction.add(building.getProductionsPerTurn());
        }

        // Food consumption is handled separately
        totalCost.setFood(totalResources.getPeople());

        return totalProduction.subtract(totalCost);
    }

    /**
     * Checks whether there are enough resources to place a building
     * @param building the building that is being placed
     * @return true if the building is placeable
     */
    public boolean canPlaceBuilding(Structure building) {
        ResourceContainer result = totalResources.subtract(building.getCosts());

        return !result.hasNegativeResources(result);
    }


    /**
     * Limits the resource count
     */
    private void capResourceCount() {
        int wood = Math.min(totalResources.getWood(), maximumResources.getWood());
        int food = Math.min(totalResources.getFood(), maximumResources.getFood());
        int rock = Math.min(totalResources.getRock(), maximumResources.getRock());
        int gold = Math.min(totalResources.getGold(), maximumResources.getGold());
        int people = Math.min(totalResources.getPeople(), maximumResources.getPeople());

        totalResources = new ResourceContainer(wood, food, rock, gold, people);
    }

    /**
     * Handles what happens when resources go negative.
     */
    private void handleNegativeResourceCount() {
        int food = totalResources.getFood();
        // for every 10 food negative 1 person dies
        if (food <= -10) {
            int factor = - totalResources.getFood() / 10;
            for (int i = 0; i < factor; i++) {
                removeOnePop();
            }
        }

        // If the people count goes negative, the game is lost.
        int people = totalResources.getPeople();
        if (people <= 0) {
            world.handleGameLost();
        }

        // The other resources just don't go below zero.
        int wood = Math.max(totalResources.getWood(), 0);
        int rock = Math.max(totalResources.getRock(), 0);
        int gold = Math.max(totalResources.getGold(), 0);

        totalResources = new ResourceContainer(wood, food, rock, gold, people);
    }

    /**
     * Reduce 1 unit of food for each citizen per turn.
     */
    public void feedPopulation() {
        int wood = totalResources.getWood();
        int food = totalResources.getFood() - totalResources.getPeople();
        int rock = totalResources.getRock();
        int gold = totalResources.getGold();
        int people = totalResources.getPeople();
        totalResources = new ResourceContainer(wood, food, rock, gold, people);
    }


    /**
     * Updates the state of all the resources.
     * Goes through all the buildings and adds their production to the total,
     * as well as removing the maintenance costs.
     */
    public void update() {

        for (Structure building:
             world.getBuildings()) {
            ResourceContainer maintenance;

            // Resource collectors: lumberjack and mine
            if (building instanceof Collector) {
                Vector2 coords = building.getCoords();

                int startX = (int)coords.x - building.getInfluenceRadius();
                int endX = (int) (coords.x + building.getCollisionSize().x + building.getInfluenceRadius());

                int startY = (int)coords.y - building.getInfluenceRadius();
                int endY = (int) (coords.y + building.getCollisionSize().y + building.getInfluenceRadius());

                // Goes through the tiles inside the building's influence area and removes resources from the first
                // resource (either rock or wood) it finds.
                ResourceContainer foundResources = world.getBuilder().getNearbyResources(building, startX, endX, startY, endY);
                if (foundResources == null)
                    world.getGameScreen().setConsoleMessage("One of your buildings has no resources around it. Destroy it!");
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
     * @param type
     * @return returns true when totoalresources of that type is in bounds
     */
    public boolean isResourceInBounds(ResourceType type){
        int value = totalResources.get(type);
        return 0 <= value && value < getMaximumResources().get(type);
    }

    /**
     * Degrades the buildings if such conditions are met (i.e. lack of resources)
     */
    private void degradeBuildings() {
        ArrayList<Structure> toDestroy = new ArrayList<Structure>();
        for (Structure building: world.getBuildings()) {
            building.degrade();
            if (building.getLife() <= 0) {
                toDestroy.add(building);
            }
        }
        world.getBuilder().destroyBuildings(toDestroy);
    }
}
