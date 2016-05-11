package com.strategy.game.world;

import java.io.Serializable;

/**
 * Created by francescosani on 27/04/16.
 */
public class ResourceHandler implements Serializable{
    private final int startingIncrement;

    private int woodCounter;
    private int goldCounter;
    private int foodCounter;
    private int stoneCounter;

    private int woodIncrementer;
    private int goldIncrementer;
    private int foodIncrementer;
    private int stoneIncrementer;

    public ResourceHandler(int startingIncrement, int woodCounter, int goldCounter, int foodCounter, int stoneCounter) {
        this.startingIncrement = startingIncrement;
        this.woodCounter = woodCounter;
        this.goldCounter = goldCounter;
        this.foodCounter = foodCounter;
        this.stoneCounter = stoneCounter;
        this.woodIncrementer = 0;
        this.goldIncrementer = 0;
        this.foodIncrementer = 0;
        this.stoneIncrementer = 0;
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

    public void incrementStoneIncrementer(int stoneIncrementerIncrease) {
        stoneIncrementer += stoneIncrementerIncrease;
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

    public void incrementStoneCounter (int stoneCounterIncrease) {
        stoneCounter += stoneCounterIncrease;
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

    public int getStoneCounter() {
        return stoneCounter;
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

    public int getStoneIncrementer() {
        return stoneIncrementer;
    }



    public void update() {
        woodCounter += startingIncrement + woodIncrementer;
        goldCounter += startingIncrement + goldCounter;
        foodCounter += startingIncrement + foodIncrementer;
        stoneCounter += startingIncrement + stoneIncrementer;

    }


}
