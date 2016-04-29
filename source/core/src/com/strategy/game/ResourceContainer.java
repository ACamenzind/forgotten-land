package com.strategy.game;

/**
 * Created by batta on 28/04/16.
 */
public class ResourceContainer {
    final int wood;
    final int food;
    final int rock;
    final int gold;
    final int people;

    public ResourceContainer(final int wood, final int food, final int rock, final int gold, final int people) {
        this.wood = wood;
        this.food = food;
        this.rock = rock;
        this.gold = gold;
        this.people = people;
    }

    /**
     * Returns a new ResourceContainer with the sum of the two.
     * @param rc
     * @return New ResourceContainer with the sum of the two.
     */
    public ResourceContainer add(final ResourceContainer rc) {
        final int wood = this.wood + rc.wood;
        final int food = this.food + rc.food;
        final int rock = this.rock + rc.rock;
        final int gold = this.gold + rc.gold;
        final int people = this.people + rc.people;

        return new ResourceContainer(wood, food, rock, gold, people);
    }
}
