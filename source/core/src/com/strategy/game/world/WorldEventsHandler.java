package com.strategy.game.world;

import com.strategy.game.buildings.Building;

import java.util.ArrayList;

/**
 * Handles the world events (e.g. diseases, attacks, ...)
 */
public class WorldEventsHandler {
    private World world;
    private ResourceHandler resourceHandler;

    public enum WorldEvent {
        PLAGUE, FIRE;

        public static WorldEvent getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    public WorldEventsHandler(World world) {
        this.world = world;
        this.resourceHandler = world.getResourceHandler();
    }

    public void randomEvent() {
        WorldEvent event = WorldEvent.getRandom();
        switch (event) {
            case PLAGUE:
                startPlague();
                break;
            case FIRE:
                startFire();
                break;
        }
    }

    /**
     * Fire random event: all buildings get damaged (50%).
     */
    private void startFire() {
        System.out.println("A fire suddenly damaged all your buildings!");
        ArrayList<Building> buildings = world.getBuildings();
        for (Building building: buildings) {
            int halfLife = building.getLife() / 2;
            building.setLife(halfLife);
        }
    }

    /**
     * Plague random event: half of the population dies suddenly.
     */
    public void startPlague() {
        System.out.println("A plague has spread throughout your village! Half the population is now dead!");
        int halfPop = resourceHandler.getTotalResources().people / 2;
        resourceHandler.removePop(halfPop);
    }
}
