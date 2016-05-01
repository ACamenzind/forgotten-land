package com.strategy.game.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.strategy.game.buildings.MapEntity;
import com.strategy.game.buildings.StaticEntityBuilder;
import com.strategy.game.screens.GameScreen;
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
    private ResourceHandler resourceHandler;
    private int updateCounter;
    private PopulationHandler populationHandler;
    private int tick;


    public World(GameScreen gameScreen) {
        this.gameStage = new Stage();
        this.gameScreen = gameScreen;
        this.map = gameScreen.getMap();
        this.staticEntities = new ArrayList<MapEntity>();
        this.movableEntities = new ArrayList<MovableEntity>();
//        this.builder = new StaticEntityBuilder(this);
        this.resourceHandler = new ResourceHandler(10, 100, 100, 100, 100);
        this.updateCounter = 0;
        this.populationHandler = new PopulationHandler(5, 200, 20);
        this.tick = 0;
    }

    public ArrayList<MapEntity> getStaticEntities() {
        return staticEntities;
    }

    public StaticEntityBuilder getBuilder() {
        return builder;
    }

    public ResourceHandler getResourceHandler() {
        return resourceHandler;
    }

    public PopulationHandler getPopulationHandler() {
        return populationHandler;
    }

    public void update(float delta) {
        if(updateCounter / 300 >= 1) {
            resourceHandler.update();
            updateCounter = 0;
            tick++;

            System.out.println("--- Resources at turn " + tick + " ---");
            System.out.println("Food: " + resourceHandler.getFoodCounter());
            System.out.println("Gold: " + resourceHandler.getGoldCounter());
            System.out.println("Stone: " + resourceHandler.getStoneCounter());
            System.out.println("Wood: " + resourceHandler.getWoodCounter());
            System.out.println("-----------------");
            //TODO: make that you have to press something to go to next turn?
        }
        updateCounter++;


    }


    @Override
    public void dispose() {
        gameStage.dispose();
    }
}
