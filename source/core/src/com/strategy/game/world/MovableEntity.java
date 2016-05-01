package com.strategy.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.strategy.game.Assets;
import com.strategy.game.Utils;
import com.strategy.game.screens.GameScreen;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

/**
 * This class represents an entity that can move (e.g. characters)
 */
public class MovableEntity extends Image {
    private String MovableEntityClass;
    private int maxLife;
    private int life;
    private Vector3 position;
    private Vector3 velocity;
    private GameScreen screen;


    // Initialises an entity with a name and the maximum life
    public MovableEntity(String MovableEntityClass, int maxLife, GameScreen screen) {
        super(Assets.dino);
        this.screen = screen;
        this.MovableEntityClass = MovableEntityClass;
        this.maxLife = maxLife;
        this.velocity = new Vector3(1,0,0);
        setOrigin(getWidth()/2, getHeight()/2);
        life = maxLife;
//        this.position = new Vector2(1000,500)
        this.position = new Vector3(1000,100,0);
        this.position.mul(Utils.isoTransformMatrix());
        this.setPosition(position.x, position.y);
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

    @Override
    public void act(float delta) {
        super.act(delta);

        // TODO: Is this really the best way?
//        position.mul(Utils.invIsoTransformMatrix());
//
//
//
////        position.add(new Vector3(1,1,0));
//        position.mul(Utils.isoTransformMatrix());
//
//        setPosition(position.x, position.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(this.getColor());
//        batch.setTransformMatrix(Utils.isoTransformMatrix());
//        batch.setProjectionMatrix(camera.combined);

        ((TextureRegionDrawable)getDrawable()).draw(batch, getX(),getY(),
                getOriginX(),getOriginY(),
                getWidth(),getHeight(),
                getScaleX(),getScaleY(),
                getRotation());

    }

    protected void computeMovement(float delta) {
        // polling instead of event handling to support concurrent inputs
        if (Gdx.input.isKeyPressed(Input.Keys.A))  position.x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D))  position.x += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.W))  position.y += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S))  position.y -= 1;
    }
}
