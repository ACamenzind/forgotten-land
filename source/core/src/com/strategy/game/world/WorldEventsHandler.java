package com.strategy.game.world;

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

    private void startFire() {
    }

    public void startPlague() {
        int halfPop = resourceHandler.getTotalResources().people / 2;
        for (int i = 0; i < halfPop; i++) {
            resourceHandler.removeOnePop();
        }
    }
}
