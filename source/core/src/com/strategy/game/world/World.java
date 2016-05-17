package com.strategy.game.world;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.utils.Disposable;
import com.strategy.game.buildings.MapEntity;
import com.strategy.game.buildings.StaticEntityBuilder;
import com.strategy.game.screens.GameScreen;
//import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.*;

import java.util.ArrayList;

/**
 * Contains the world simulation.
 *
 */
public class World implements Disposable, Serializable {

    public static final int TICK_DURATION = 300;
    private transient Stage gameStage;
    private transient GameScreen gameScreen;
    private ArrayList<MapEntity> staticEntities;
    private ArrayList<MovableEntity> movableEntities;
    private transient TiledMap map;
    private StaticEntityBuilder builder;
    private ResourceHandler resourceHandler;
    private int updateCounter;
    private PopulationHandler populationHandler;
    private int tick;
    private boolean isRunning;

    private static String filePath = "saveGame.ser";


    public World(GameScreen gameScreen) {
        this.gameStage = new Stage();
        this.gameScreen = gameScreen;
        this.map = gameScreen.getMap();
        this.staticEntities = new ArrayList<MapEntity>();
        this.movableEntities = new ArrayList<MovableEntity>();
//        this.builder = new StaticEntityBuilder(this);
        this.resourceHandler = new ResourceHandler(this, 0, 1000, 1000, 1000, 1000, 5);
        this.updateCounter = 0;
        this.populationHandler = new PopulationHandler(5, 200, 20);
        this.tick = 0;
        this.isRunning = true;
    }

    public ArrayList<MapEntity> getStaticEntities() {
        return staticEntities;
    }

    public ArrayList<MovableEntity> getMovableEntities() {
        return movableEntities;
    }

    public StaticEntityBuilder getBuilder() {
        return builder;
    }

    public ResourceHandler getResourceHandler() {
        return resourceHandler;
    }

    public int getUpdateCounter() {
        return updateCounter;
    }

    public PopulationHandler getPopulationHandler() {
        return populationHandler;
    }

    public int getTick() {
        return tick;
    }

    public boolean getIsRunning() {
        return isRunning;
    }


    public void setStaticEntities(ArrayList<MapEntity> staticEntities) {
        this.staticEntities = staticEntities;
    }

    public void setMovableEntities(ArrayList<MovableEntity> movableEntities) {
        this.movableEntities = movableEntities;
    }

    public void setBuilder(StaticEntityBuilder builder) {
        this.builder = builder;
    }

    public void setResourceHandler(ResourceHandler resourceHandler) {
        this.resourceHandler = resourceHandler;
    }

    public void setUpdateCounter(int updateCounter) {
        this.updateCounter = updateCounter;
    }

    public void setPopulationHandler(PopulationHandler populationHandler) {
        this.populationHandler = populationHandler;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }


    /**
     * Toggles between running and pausing the game.
     */
    public void toggleRunning() {
        isRunning = !isRunning;
        if (isRunning) System.out.println("Game resumed!");
        else System.out.println("Game paused!");
    }

    /**
     * Searches the map file for tiles that are of type resource, and adds them to the resource list
     * to keep track of them.
     * TODO: implement
     */
    private void readResources() {

    }

    public void update(float delta) {
        if(updateCounter / TICK_DURATION >= 1 && isRunning) {
            resourceHandler.update();
            updateCounter = 0;
            tick++;

            System.out.println("--- Resources at turn " + tick + " ---");
            System.out.println(resourceHandler.getTotalResources().toString());

            //TODO: make that you have to press something to go to next turn?

////            int treeCount = 0;
//            TiledMapTileLayer buildingsLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Buildings");
//
//            // Increment wood per tick for each tree in the world
//            // TODO: make it so it only increments it if there is an appropriate building in range
//            // TODO: do the same for other resources
//            int treeCount = 0;
//            for (int i = 0; i < buildingsLayer.getWidth(); i++) {
//                for (int j = 0; j < buildingsLayer.getHeight(); j++) {
//                    TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i,j);
//                    if (cell != null) {
//                        String type = cell.getTile().getProperties().get("type", String.class);
//                        if (type != null && type.equals("wood")) {
//                            treeCount++;
//                        }
//                    }
//                }
//            }
//            System.out.println("num of trees: " + treeCount);
//            resourceHandler.incrementWoodCounter(treeCount);

        }
        updateCounter++;


    }


    @Override
    public void dispose() {
        gameStage.dispose();
    }


    public static void saveGame(World gameToBeSaved) {
        try {
            File fileInWhichToSaveIn = new File(filePath);
            FileOutputStream fos = new FileOutputStream(fileInWhichToSaveIn);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameToBeSaved);
            oos.flush();
            oos.close();
            fos.close();
        }
        catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static World loadSavedGame() {
        try {
            File fileToLoad = new File(filePath);
            FileInputStream fis = new FileInputStream(fileToLoad);
            ObjectInputStream ois  = new ObjectInputStream(fis);
            World gs = (World) ois.readObject();
            ois.close();
            fis.close();

            return gs;

        }
        catch (Exception ex) {
            ex.printStackTrace();
            return loadSavedGame();
        }
    }
}
