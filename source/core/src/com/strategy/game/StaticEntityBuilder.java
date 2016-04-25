package com.strategy.game;

/**
 * Handles the creation and placement of static entities (e.g. building)
 * TODO: implement
 */
public class StaticEntityBuilder {
    private World world;
    private MapEntity selectedEntity;
    public StaticEntityBuilder(World world) {
        this.world = world;
    }

    public void selectEntity(MapEntity entity) {
        this.selectedEntity = entity;
    }
}
