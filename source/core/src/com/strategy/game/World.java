package com.strategy.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

/**
 * Contains the world simulation.
 * TODO: implement
 */
public class World implements Disposable{

    private Stage gameStage;



    public World() {
        this.gameStage = new Stage();
    }


    @Override
    public void dispose() {
        gameStage.dispose();
    }
}
