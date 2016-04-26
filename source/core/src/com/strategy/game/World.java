package com.strategy.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
//import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;

/**
 * Contains the world simulation.
 * TODO: implement
 */
public class World implements Disposable{

    private Stage gameStage;
    private GameScreen gameScreen;
    private ArrayList<MapEntity> staticEntities;
    private ArrayList<MovableEntity> movableEntities;
    private TiledMap map;
    private StaticEntityBuilder builder;


    public World(GameScreen gameScreen) {
        this.gameStage = new Stage();
        this.gameScreen = gameScreen;
        this.map = gameScreen.getMap();
        this.staticEntities = new ArrayList<MapEntity>();
        this.movableEntities = new ArrayList<MovableEntity>();
//        this.builder = new StaticEntityBuilder(this);
    }

    public void update(float delta) {

    }


    @Override
    public void dispose() {
        gameStage.dispose();
    }
}
