package com.strategy.game;

/**
 * Created by francescosani on 27/04/16.
 */
public class ResourceHandler {
    private int startingIncrement;

    private int woodCounter;
    private int goldCounter;
    private int foodCounter;

    private int woodIncrementer;
    private int goldIncrementer;
    private int foodIncrementer;

    public ResourceHandler(int startingIncrement, int woodCounter, int goldCounter, int foodCounter) {
        this.startingIncrement = startingIncrement;
        this.woodCounter = woodCounter;
        this.goldCounter = goldCounter;
        this.foodCounter = foodCounter;
        this.woodIncrementer = 0;
        this.goldIncrementer = 0;
        this.foodIncrementer = 0;
    }


    public void incrementWoodIncrementer(int woodIncrementerIncrements) {
        woodIncrementer =+ woodIncrementerIncrements;
    }

    public void incrementGoldIncrementer(int goldIncrementerIncrements) {
        goldIncrementer =+ goldIncrementerIncrements;
    }

    public void incrementFoodIncrementer(int foodIncrementerIncrements) {
        foodIncrementer =+ foodIncrementerIncrements;
    }

    public void update() {
        woodCounter =+ startingIncrement + woodIncrementer;
        goldCounter =+ startingIncrement + goldCounter;
        foodCounter =+ startingIncrement + foodIncrementer;

    }


}
