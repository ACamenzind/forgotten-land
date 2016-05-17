package com.strategy.game;

import java.io.Serializable;

/**
 * Created by batta on 28/04/16.
 */
public class ResourceContainer implements Serializable{
    public int wood;
    public int food;
    public int rock;
    public int gold;
    public int people;

    public ResourceContainer(int wood, int food, int rock, int gold, int people) {
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

    /**
     * Returns a new ResourceContainer with the sum of the two.
     * @param rc
     * @return New ResourceContainer with the sum of the two.
     */
    public ResourceContainer subtract(final ResourceContainer rc) {
        final int wood = this.wood - rc.wood;
        final int food = this.food - rc.food;
        final int rock = this.rock - rc.rock;
        final int gold = this.gold - rc.gold;
        final int people = this.people - rc.people;

        return new ResourceContainer(wood, food, rock, gold, people);
    }

    public ResourceContainer multiply(int n) {
        final int wood = this.wood * n;
        final int food = this.food * n;
        final int rock = this.rock * n;
        final int gold = this.gold * n;
        final int people = this.people * n;

        return new ResourceContainer(wood, food, rock, gold, people);
    }

    public boolean noNegativeResources() {
        return wood >= 0 && food >= 0 && rock >= 0 && gold >= 0;
    }

    @Override
    public String toString() {
        return "W: " + wood + " F: " + food + " R: " + rock + " G: " + gold + " P: " + people;
    }
}
