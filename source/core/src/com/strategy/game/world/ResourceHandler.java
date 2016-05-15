package com.strategy.game.world;

import com.strategy.game.ResourceContainer;
import com.strategy.game.buildings.Building;
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
    }

    public ResourceContainer getTotalResources() {
        return totalResources;
    }

    public void incrementWoodIncrementer(int woodIncrementerIncrease) {
        woodIncrementer += woodIncrementerIncrease;
    }

    public void incrementGoldIncrementer(int goldIncrementerIncrease) {
        goldIncrementer += goldIncrementerIncrease;
    }

    public void incrementFoodIncrementer(int foodIncrementerIncrease) {
        foodIncrementer += foodIncrementerIncrease;
    }

    public void incrementRockIncrementer(int rockIncrementerIncrease) {
        rockIncrementer += rockIncrementerIncrease;
    }


    public void incrementWoodCounter(int woodCounterIncrease) {
        woodCounter += woodCounterIncrease;
    }

    public void incrementGoldCounter(int goldCounterIncrease) {
        goldCounter += goldCounterIncrease;
    }

    public void incrementFoodCounter (int foodCounterIncrease) {
        foodCounter += foodCounterIncrease;
    }

    public void incrementRockCounter (int rockCounterIncrease) {
        rockCounter += rockCounterIncrease;
    }


    public int getStartingIncrement() {
        return startingIncrement;
    }

    public int getWoodCounter() {
        return woodCounter;
    }

    public int getGoldCounter() {
        return goldCounter;
    }

    public int getFoodCounter() {
        return foodCounter;
    }

    public int getRockCounter() {
        return rockCounter;
    }

    public int getWoodIncrementer() {
        return woodIncrementer;
    }

    public int getGoldIncrementer() {
        return goldIncrementer;
    }

    public int getFoodIncrementer() {
        return foodIncrementer;
    }

    public int getRockIncrementer() {
        return rockIncrementer;
    }

    public void removeFromTotal(ResourceContainer amount) {
        totalResources = totalResources.subtract(amount);
    }

    /**
     * Updates the state of all the resources.
     * Goes through all the buildings and adds their production to the total,
     * as well as removing the maintenance costs.
     */
    public void update() {
        woodCounter += startingIncrement + woodIncrementer;
        goldCounter += startingIncrement + goldCounter;
        foodCounter += startingIncrement + foodIncrementer;
        rockCounter += startingIncrement + rockIncrementer;

        for (MapEntity building:
             world.getStaticEntities()) {
            ResourceContainer production = ((Building) building).getProductions();
            ResourceContainer maintenance = ((Building) building).getMaintenanceCosts();
            totalResources = totalResources.add(production);
            totalResources = totalResources.subtract(maintenance);
        }
    }


}
