package com.strategy.game;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * This class represents a Warrior.
 */
public class Warrior extends Image {
    private String warriorClass;
    private int maxLife;
    private int life;

    public Warrior(String warriorClass, int life) {
        this.warriorClass = warriorClass;
        // Should implement a function to remove object.
    }

    public void reduceLife(int damage) {
        if(life - damage < 0) {
            life = 0;
        }
        else {
            life -= damage;
        }
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
