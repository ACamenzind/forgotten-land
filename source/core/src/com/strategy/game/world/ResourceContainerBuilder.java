package com.strategy.game.world;

import com.strategy.game.ResourceContainer;

public class ResourceContainerBuilder {
    private int wood = 0;
    private int food = 0;
    private int rock = 0;
    private int gold = 0;
    private int people = 0;

    public ResourceContainerBuilder() {
    }

    public ResourceContainerBuilder wood(int amount) {
        this.wood = amount;
        return this;
    }
    public ResourceContainerBuilder food(int amount) {
        this.food = amount;
        return this;
    }
    public ResourceContainerBuilder rock(int amount) {
        this.rock = amount;
        return this;
    }
    public ResourceContainerBuilder gold(int amount) {
        this.gold = amount;
        return this;
    }
    public ResourceContainerBuilder people(int amount) {
        this.people = amount;
        return this;
    }

    public ResourceContainer build() {
        return new ResourceContainer(this.wood, this.food, this.rock, this.gold, this.people);
    }
}
