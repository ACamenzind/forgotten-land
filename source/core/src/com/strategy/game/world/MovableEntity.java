package com.strategy.game.world;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * This class represents an entity that can move (e.g. characters)
 */
public class MovableEntity extends Image {
    private String MovableEntityClass;
    private int maxLife;
    private int life;

    // Initialises an entity with a name and the maximum life
    public MovableEntity(String MovableEntityClass, int maxLife) {
        this.MovableEntityClass = MovableEntityClass;
        this.maxLife = maxLife;
        life = maxLife;
    }

    // Method ro reduce life
    public void reduceLife(int damage) {
        if(life - damage < 0) {
            life = 0;
        }
        else {
            life -= damage;
        }
        // Should implement a function to remove object.
    }

    // Method to increase life (e.g. when eating)
    public void increaseLife(int increase) {
        if(life + increase > maxLife) {
            life = maxLife;
        }
        else {
            life += increase;
        }
    }

    // Getter to know how much life the entity has
    public int getLife() {
        return life;
    }
}
