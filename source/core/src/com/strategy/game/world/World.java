package com.strategy.game.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.strategy.game.ExtendedStaticTiledMapTile;
import com.strategy.game.buildings.Building;
import com.strategy.game.buildings.MapEntity;
import com.strategy.game.buildings.StaticEntityBuilder;
import com.strategy.game.screens.GameScreen;
//import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;

/**
 * Contains the world simulation.
 *
 */
public class World implements Disposable{

    private int tickDuration;
    private Stage gameStage;
    private GameScreen gameScreen;
    private ArrayList<Building> staticEntities; // Resources and Buildings
    private ArrayList<Resource> resources;
    private ArrayList<MovableEntity> movableEntities;
    private TiledMap map;
    private StaticEntityBuilder builder;
    private ResourceHandler resourceHandler;
    private int updateCounter;
    private PopulationHandler populationHandler;
    private int tick;
    private boolean isRunning;
    private static final int DEFAULT_TICK_DURATION = 300;
    private static final int FAST_TICK_DURATION = 50;

    public enum GameSpeed {
        NORMAL, FAST
    }


    public World(GameScreen gameScreen) {
        this.gameStage = new Stage();
        this.gameScreen = gameScreen;
        this.map = gameScreen.getMap();
        this.staticEntities = new ArrayList<Building>();
        this.resources = new ArrayList<Resource>();
        this.movableEntities = new ArrayList<MovableEntity>();
        this.resourceHandler = new ResourceHandler(this, 1000, 1000, 1000, 1000, 5);
        this.updateCounter = 0;
        this.populationHandler = new PopulationHandler(5, 200, 20);
        this.tick = 0;
        this.isRunning = true;
        this.tickDuration = DEFAULT_TICK_DURATION;
        readResources();
    }

    public void setBuilder(StaticEntityBuilder builder) {
        this.builder = builder;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public ArrayList<Building> getStaticEntities() {
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

    public void setGameSpeed(GameSpeed speed) {
        switch (speed){
            case NORMAL:
                tickDuration = DEFAULT_TICK_DURATION;
                break;
            case FAST:
                tickDuration = FAST_TICK_DURATION;
                break;
        }
    }

    /**
     * Toggles between running and pausing the game.
     */
    public void toggleRunning() {
        isRunning = !isRunning;
    }

    /**
     * Searches the map file for tiles that are of type resource, and adds them to the resource list
     * to keep track of them.
     * TODO: refactor
     */
    private void readResources() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Buildings");
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x,y);
                if (cell != null && cell.getTile() != null) {
                    ExtendedStaticTiledMapTile tile = (ExtendedStaticTiledMapTile) cell.getTile();
                    String type = tile.getProperties().get("type", String.class);
                    if (type != null) {
                        if (type.equals("wood")) {
                            Resource wood = new Resource("wood", 100);
                            wood.setCoords(new Vector2(x, y));
                            resources.add(wood);
                            tile.setObject(wood);
                        } else if (type.equals("rock")) {
                            Resource rock = new Resource("rock", 100);
                            rock.setCoords(new Vector2(x, y));
                            resources.add(rock);
                            tile.setObject(rock);
                        }
                    }
                }
            }
        }
    }

    public void update(float delta) {
        if(updateCounter / tickDuration >= 1 && isRunning) {
            resourceHandler.update();
            updateCounter = 0;
            tick++;
            builder.checkDeadEntities();

            gameScreen.getResourcesBar().update();
        }
        updateCounter++;


    }


    @Override
    public void dispose() {
        gameStage.dispose();
    }
}
