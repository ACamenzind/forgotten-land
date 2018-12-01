package com.strategy.game;

/**
 * Data structure that contains all the types of resources
 */
public class ResourceContainer {
    private int wood;
    private int food;
    private int rock;
    private int gold;
    private int people;

    public int getWood() {
        return wood;
    }

    public int getFood() {
        return food;
    }

    public int getRock() {
        return rock;
    }

    public int getGold() {
        return gold;
    }

    public int getPeople() {
        return people;
    }

    public ResourceContainer(int wood, int food, int rock, int gold, int people) {
        this.wood = wood;
        this.food = food;
        this.rock = rock;
        this.gold = gold;
        this.people = people;
    }

    public ResourceContainer() {
        this.wood = 0;
        this.food = 0;
        this.rock = 0;
        this.gold = 0;
        this.people = 0;
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

    public ResourceContainer multiply(float n) {
        final int wood = (int)(this.wood * n);
        final int food = (int)(this.food * n);
        final int rock = (int)(this.rock * n);
        final int gold = (int)(this.gold * n);
        final int people = (int)(this.people * n);

        return new ResourceContainer(wood, food, rock, gold, people);
    }

    /**
     * Returns true if none of the resources are negative.
     * @return
     */
    public boolean noNegativeResources() {
        return wood > 0 && rock > 0 && gold > 0;
    }

    /**
     * Checks if any of the resources is zero
     * @return
     */
    public boolean hasZeroResources() {
        int result = wood * food * rock * gold;
        return result == 0;
    }

    @Override
    public String toString() {
        return "W: " + wood + " F: " + food + " R: " + rock + " G: " + gold + " P: " + people;
    }

    public boolean hasNegativeResources(ResourceContainer rc) {
        return rc.wood < 0 || rc.rock < 0 || rc.gold < 0;
    }
}
