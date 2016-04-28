package com.strategy.game;

/**
 * Created by francescosani on 28/04/16.
 */
public class PopulationHandler {

    private int populationCounter;
    private int maxPopulationAllowed;
    private int currentPopulationAllowed;

    public PopulationHandler(int startingPopulation, int maxPopulationAllowed, int startingPopulationAllowed) {
        this.populationCounter = startingPopulation;
        this.maxPopulationAllowed = maxPopulationAllowed;
        this.currentPopulationAllowed = startingPopulationAllowed;
    }


    public void incrementPopulationCounter(int populationCounterIncrease) {
        this.populationCounter += populationCounterIncrease;
    }


    public int getPopulationCounter() {
        return populationCounter;
    }

    public int getCurrentPopulationAllowed() {
        return currentPopulationAllowed;
    }

    public int getMaxPopulationAllowed() {
        return maxPopulationAllowed;
    }
}
