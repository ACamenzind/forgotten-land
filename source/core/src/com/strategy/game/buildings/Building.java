package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.strategy.game.ResourceContainer;
import com.strategy.game.buildings.MapEntity;
import com.strategy.game.world.World;

/**
 * A building with its resources. Inherit from this to create new ones.
 */
public abstract class Building extends MapEntity {
    private final String name;
    private final ResourceContainer costs;
//    private final int maxLife;
    private final int maxWorkers;
    protected ResourceContainer productions;
    private final ResourceContainer maintenanceCosts;
//    private int life;
    protected int workers;
    private BuildingType type;

    public enum BuildingType { MANUFACTURER, WAREHOUSE, DECORATION, OTHER }

    public Building(final String name,
                    final ResourceContainer costs,
                    final ResourceContainer productions,
                    final ResourceContainer maintenanceCosts,
                    final int maxLife,
                    final int maxWorkers,
                    final int influenceRadius,
                    final Texture mainTexture,
                    final Vector2 collisionSize,
                    final BuildingType type) {
        this.name = name;
        this.costs = costs;
        this.productions = productions;
        this.maintenanceCosts = maintenanceCosts;
        this.maxLife = maxLife;
        this.life = maxLife;
        this.maxWorkers = maxWorkers;
        this.influenceRadius = influenceRadius;
        this.mainTexture = mainTexture;
        this.collisionSize = collisionSize;
        this.type = type;
        sliceTexture(mainTexture);
    }

    public String getName() {
        return name;
    }

    public ResourceContainer getCosts() {
        return costs;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }

    public ResourceContainer getProductions() {
        return productions;
    }

    public ResourceContainer getProductionsPerWorker() {
        return productions;
    }

    public ResourceContainer getProductionsPerWorker(World world) {
        return productions;
    }

    public ResourceContainer getProductionsPerTurn() {
        return productions.multiply(workers);
    }

    public ResourceContainer getMaintenanceCosts() {
        return maintenanceCosts;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void addLife(int amount) {
        this.life += amount;
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public BuildingType getType() {
        return type;
    }

    public void degrade() {
        this.life--;
    }

    /**
     * Collect returns the collected resources for one single turn.
     * @return Collected resources per turn.
     */
    public ResourceContainer work() {
        final int wood = productions.wood * workers;
        final int food = productions.food * workers;
        final int rock = productions.rock * workers;
        final int gold = productions.gold * workers;
        final int people = productions.people * workers;

        return new ResourceContainer(wood, food, rock, gold, people);
    }

    /**
     * Change the actual workers in the building. Can be negative to remove instead of add.
     * @param n
     */
    public void changeWorker(int n) {
        final int newWorkers = this.workers + n;

        if (newWorkers <= this.maxWorkers && newWorkers >= 0)
            this.workers = newWorkers;
    }
}
