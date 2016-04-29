package com.strategy.game;

import com.strategy.game.buildings.MapEntity;

/**
 * Created by batta on 28/04/16.
 */
public class Building extends MapEntity {
    private final String name;
    private final ResourceContainer costs;
    private final int maxLife;
    private final int maxWorkers;
    private final ResourceContainer productions;
    private int life;
    private int workers;

    public Building(final String name, final ResourceContainer costs, final ResourceContainer productions, final int maxLife, final int maxWorkers) {
        this.name = name;
        this.costs = costs;
        this.productions = productions;
        this.maxLife = maxLife;
        this.life = maxLife;
        this.maxWorkers = maxWorkers;
    }

    /**
     * Collect returns the collected resources for one single turn.
     * @return Collected resources per turn.
     */
    public ResourceContainer collect() {
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
