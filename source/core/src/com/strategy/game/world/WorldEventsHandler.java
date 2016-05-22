package com.strategy.game.world;

/**
 * Handles the world events (e.g. diseases, attacks, ...)
 */
public class WorldEventsHandler {
    private World world;
    private ResourceHandler resourceHandler;

    public WorldEventsHandler(World world) {
        this.world = world;
        this.resourceHandler = world.getResourceHandler();
    }

    public void randomEvent() {

    }
}
