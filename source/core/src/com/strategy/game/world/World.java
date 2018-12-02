package com.strategy.game.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.strategy.game.ResourceContainer;
import com.strategy.game.buildings.Structure;
import com.strategy.game.buildings.TileMapManager;
import com.strategy.game.screens.GameScreen;
//import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.Random;

/**
 * Contains the world simulation.
 *
 */
public class World implements Disposable {

    private int tickDuration;
    private Stage gameStage;
    private GameScreen gameScreen;
    private ArrayList<Structure> buildings; // Resources and Buildings
    private ArrayList<Resource> resources;
    private TiledMap map;
    private TileMapManager builder;
    private ResourceHandler resourceHandler;
    private int updateCounter;
    private int eventsCounter;
    private WorldEventsHandler worldEventsHandler;
    private int tick;
    private boolean isRunning;
    private static final int DEFAULT_TICK_DURATION = 300;
    private static final int FAST_TICK_DURATION = 10;
    private static final int WORLD_EVENT_FREQUENCY = 50; // After how many ticks we have a chance for a random event

    public enum GameSpeed {
        NORMAL, FAST
    }

    public World(GameScreen gameScreen) {
        ResourceContainer initialResources = new ResourceContainerBuilder()
                .wood(300).gold(300).food(300).rock(300).people(5).build();
        this.gameStage = new Stage();
        this.gameScreen = gameScreen;
        this.map = gameScreen.getMap();
        this.buildings = new ArrayList<Structure>();
        this.resources = new ArrayList<Resource>();
        this.resourceHandler = new ResourceHandler(this, initialResources);
        this.updateCounter = 0;
        this.tick = 0;
        this.isRunning = true;
        this.tickDuration = DEFAULT_TICK_DURATION;
        this.worldEventsHandler = new WorldEventsHandler(this);
        this.eventsCounter = 0;
    }

    public WorldEventsHandler getWorldEventsHandler() {
        return worldEventsHandler;
    }

    public void setBuilder(TileMapManager builder) {
        this.builder = builder;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public ArrayList<Structure> getBuildings() {
        return buildings;
    }

    public TileMapManager getBuilder() {
        return builder;
    }

    public ResourceHandler getResourceHandler() {
        return resourceHandler;
    }

    /**
     * Sets the simulation speed (i.e. the tick duration).
     * @param speed the requested speed (either normal or fast).
     */
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
     * Changes the simulation speed.
     */
    public void toggleGameSpeed() {
        if (tickDuration == DEFAULT_TICK_DURATION) {
            tickDuration = FAST_TICK_DURATION;
        }
        else {
            tickDuration = DEFAULT_TICK_DURATION;
        }
    }

    /**
     * Toggles between running and pausing the game.
     */
    public void toggleRunning() {
        isRunning = !isRunning;
    }

    /**
     * What happens when the game-lost conditions are satisfied (i.e. people <= 0 for now).
     */
    public void handleGameLost() {
        isRunning = false;
        gameScreen.toggleGameOver();
    }

    /**
     * Searches the map file for tiles that are of type resource, and adds them to the resource list
     * to keep track of them.
     * TODO: refactor
     */
    public void readResources() {
        this.resources = this.builder.readResources();
    }

    /**
     * The main world simulation loop, called every frame.
     * @param delta time since last frame (used for animations)
     */
    public void update(float delta) {
        if(updateCounter / tickDuration >= 1 && isRunning) {
            resourceHandler.update();
            updateCounter = 0;
            tick++;

            if (tick % WORLD_EVENT_FREQUENCY == 0) {
                // Roll dice
                int rand = new Random().nextInt(100);
                if (rand > 50) { // 50% probability of having the plague
                    worldEventsHandler.randomEvent();
                }
            }
            gameScreen.getResourcesBar().update();
        }
        updateCounter++;
    }


    @Override
    public void dispose() {
        gameStage.dispose();
    }
}
