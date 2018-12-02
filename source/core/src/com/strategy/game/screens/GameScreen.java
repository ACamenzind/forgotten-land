package com.strategy.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.strategy.game.*;
import com.strategy.game.buildings.Castle;
import com.strategy.game.buildings.House;
import com.strategy.game.buildings.StaticEntityBuilder;
import com.strategy.game.screens.sidebar.Sidebar;
import com.strategy.game.world.World;


/**
 * The main game screen, where the gameplay is rendered.
 *
 */
public class GameScreen implements Screen, EventListener {

    private Stage stage;
    private final StrategyGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Image testPlayer;
    private TiledMap map;
//    private IsometricTiledMapRenderer renderer;
    private BetterRenderer renderer;
    private InputMultiplexer gameInputMultiplexer;
    private GameInputProcessor gameInputProcessor;
    private World world;
    private StaticEntityBuilder builder;

    private Sidebar sidebar;
    private ResourcesBar resourcesBar;

    private Table messages;
    private Image gamePaused;
    private ConsoleMessage consoleMessage;
    private MessageLog newGame;
    private MessageLog quitGame;
    private MessageLog gameOver;

    private Vector2 touchDownCoords;
    private Vector2 touchUpCoords;

    private ShapeRenderer shapeRenderer;
    private boolean isSelecting;


    public GameScreen(StrategyGame game) {
        this.game = game;

        this.batch = game.getBatch();
        this.font = game.getFont();
        this.shapeRenderer = new ShapeRenderer();

        Assets.loadMap();
        Assets.loadSounds();
        this.map = Assets.map;
        this.convertMap(map);


//        this.renderer = new IsometricTiledMapRenderer(map);
        this.renderer = new BetterRenderer(map);
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.gameInputMultiplexer = new InputMultiplexer();

        this.gameInputProcessor = new GameInputProcessor(this);
        this.world = new World(this);
        this.builder = new StaticEntityBuilder(this);
        world.setBuilder(builder);
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.sidebar = new Sidebar(stage, this);
        this.resourcesBar = new ResourcesBar(stage, world);
        this.builder.addListener(this.resourcesBar);
        this.builder.addListener(world);
        setMessages();

        // Looping background sound
        game.getSoundManager().playSound(SoundManager.SoundType.BACKGROUND);

        gameInputMultiplexer.addProcessor(stage);
        gameInputMultiplexer.addProcessor(gameInputProcessor);
        Gdx.input.setInputProcessor(gameInputMultiplexer);

        this.isSelecting = false;


        // Starting building
        // TODO: remove magic numbers (make it random?)
        builder.setSelectedEntity(new Castle());
        builder.placeSelectedEntity(25, 20, true);
        builder.removeSelectedEntity();
        camera.translate(25*128, 0);

    }

    public void setMessages() {
        this.messages = new Table();
        messages.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(messages);
        // Console message
        this.consoleMessage = new ConsoleMessage(stage);
        messages.addActor(consoleMessage);
        // Game Over
        this.gameOver = new MessageLog(this, MessageLog.MessageType.GAME_OVER);
        gameOver.setVisible(false);
        messages.addActor(gameOver);
        // New Game
        this.newGame = new MessageLog(this, MessageLog.MessageType.NEW_GAME);
        newGame.setVisible(false);
        messages.addActor(newGame);
        // Game Over
        this.quitGame = new MessageLog(this, MessageLog.MessageType.QUIT);
        quitGame.setVisible(false);
        messages.addActor(quitGame);
        // Game Paused
        this.gamePaused =  Assets.makeImage(Assets.pausedOverlay);
        messages.addActor(gamePaused);
        gamePaused.setSize(Gdx.graphics.getWidth() / 3f, Gdx.graphics.getWidth() * 0.25f / 3f);
        gamePaused.setPosition((Gdx.graphics.getWidth() - sidebar.getWidth() - gamePaused.getWidth()) / 2f, Gdx.graphics.getHeight() * 0.5f);
        gamePaused.setVisible(false);
    }

    public void toggleGameOver() {
        gameOver.setVisible(!gameOver.isVisible());
    }

    public void toggleNewGame() {
        newGame.setVisible(!newGame.isVisible());
    }

    public void toggleQuitGame() {
        quitGame.setVisible(!quitGame.isVisible());
    }

    public StrategyGame getGame() {
        return game;
    }

    /**
     * Converts all tiles to ExtendedStaticTiledMapTiles
     * @param map the tiled map to be converted
     */
    public void convertMap(TiledMap map) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Buildings");
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x,y);
                if (cell != null) {
                    ExtendedStaticTiledMapTile newTile = new ExtendedStaticTiledMapTile((StaticTiledMapTile) cell.getTile());
                    cell.setTile(newTile);
                }
            }
        }
    }

    public Stage getStage() {
        return stage;
    }

    public Table getMessages() {
        return messages;
    }

    public void setConsoleMessage(String text) {
        consoleMessage.setMessage(text);
    }

    public InputMultiplexer getGameInputMultiplexer() {
        return gameInputMultiplexer;
    }

    public boolean isSelecting() {
        return isSelecting;
    }

    public void setSelecting(boolean selecting) {
        isSelecting = selecting;
    }

    public void setTouchDownCoords(Vector2 touchDownCoords) {
        this.touchDownCoords = touchDownCoords;
    }

    public Vector2 getTouchDownCoords() {
        return touchDownCoords;
    }

    public void setTouchUpCoords(Vector2 touchUpCoords) {
        this.touchUpCoords = touchUpCoords;
    }

    public Vector2 getTouchUpCoords() {
        return touchUpCoords;
    }

    public World getWorld() {
        return world;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public TiledMap getMap() {
        return map;
    }

    public StaticEntityBuilder getBuilder() {
        return builder;
    }

    public Sidebar getSidebar() {
        return sidebar;
    }

    public ResourcesBar getResourcesBar() {
        return resourcesBar;
    }

    public void toggleGamePaused() {
        gamePaused.setVisible(!gamePaused.isVisible());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // OpenGL stuff
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Handle the input
        gameInputProcessor.pollKeyboard();
        gameInputProcessor.pollMouse();

        world.update(delta);

        builder.renderSelection(camera);

        camera.update();

        renderer.setView(camera);
        renderer.render();

        builder.clear();

        // Draw FPS counter on the top left
        batch.begin();
//        font.draw(batch, "FPS: "+ Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight());
        batch.end();

        consoleMessage.update(delta);

        // Draw stage
        stage.act(delta);
        sidebar.update();
//        stage.setDebugAll(true); // For debug purpose
        stage.getViewport().apply();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Updates the screen when the window is resized
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
//        sidebar.update(); //Apparently not necessary
//        resourcesBar.update(); //Apparently not necessary
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Assets.dispose();
        world.dispose();
        stage.dispose();
        game.getSoundManager().dispose();
    }

    @Override
    public void update(Events eventType) {
        switch (eventType) {
            case BUILDING_OVERLAP:
                setConsoleMessage("That place is already occupied!");
                break;
            case BUILDING_OUT_OF_INFLUENCE:
                setConsoleMessage("You must place the building inside the colored influence area!");
                break;
            case BUILDING_NOT_ENOUGH_RESOURCES:
                setConsoleMessage("Not enough resources to build this!");
                break;
        }
    }
}
