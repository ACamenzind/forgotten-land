package com.strategy.game;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * This class represents a person or an animal.
 */
public class MovableEntity extends Image {
    private String MovableEntityClass;
    private int maxLife;
    private int life;

    public MovableEntity(String MovableEntityClass, int life) {
        this.MovableEntityClass = MovableEntityClass;
        this.life = life;
    }

    public void reduceLife(int damage) {
        if(life - damage < 0) {
            life = 0;
        }
        else {
            life -= damage;
        }
        // Should implement a function to remove object.
    }

    public void increaseLife(int increase) {
        if(life + increase > maxLife) {
            life = maxLife;
        }
        else {
            life += increase;
        }
    }

    public int getLife() {
        return life;
    }
}
