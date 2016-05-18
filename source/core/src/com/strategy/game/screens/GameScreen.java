package com.strategy.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.strategy.game.*;
import com.strategy.game.buildings.House;
import com.strategy.game.buildings.StaticEntityBuilder;
import com.strategy.game.screens.sidebar.Sidebar;
import com.strategy.game.world.World;


/**
 * The main game screen, where the gameplay is rendered.
 *  TODO: Make another input handler for the UI, and use multiplexing.
    TODO: Maybe create a new class to handle game logic and hold the game state, with Scene2D
 */
public class GameScreen implements Screen {

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


    private Vector2 touchDownCoords;
    private Vector2 touchUpCoords;

    private ShapeRenderer shapeRenderer;
    private boolean isSelecting;


    public GameScreen(StrategyGame game) {
        this.game = game;

        this.batch = game.getBatch();
        this.font = game.getFont();
        this.shapeRenderer = new ShapeRenderer();
        Assets.load();

        this.map = Assets.map;
        this.convertMap(map);


//        this.renderer = new IsometricTiledMapRenderer(map);
        this.renderer = new BetterRenderer(map);
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.gameInputMultiplexer = new InputMultiplexer();
        this.gameInputProcessor = new GameInputProcessor(this);
        this.world = new World(this);
        this.builder = new StaticEntityBuilder(this);
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.sidebar = new Sidebar(stage, this);
        this.resourcesBar = new ResourcesBar(stage, world);



        // Looping background sound
        Sound sound = Assets.bgSound;
        long id = sound.play(0.3f);
        sound.setLooping(id, true);

        gameInputMultiplexer.addProcessor(stage);
        gameInputMultiplexer.addProcessor(gameInputProcessor);
        Gdx.input.setInputProcessor(gameInputMultiplexer);

        this.isSelecting = false;


        // Starting building
        // TODO: remove magic numbers (make it random?)
        builder.toggleSelectEntity(new House());
        builder.placeSelectedEntity(25, 20, true);
        builder.untoggleSelectEntity();
        camera.translate(25*128, 0);

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

        // Draw selection box, bugged when resizing the screen
        // Doesn't really do anything at the moment
//        if (isSelecting) {
//            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//            float x = touchDownCoords.x;
//            float y = Gdx.graphics.getHeight() - touchDownCoords.y;
//            float dx = Gdx.input.getX() - x;
//            float dy = (Gdx.graphics.getHeight() - Gdx.input.getY()) - y;
//            Rectangle rect = new Rectangle(x, y, dx, dy);
//            shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
//            shapeRenderer.end();
//        }


//        System.out.println(deltaMouse.toString());



//        MapProperties prop = map.getProperties();
//
//        int mapWidth = prop.get("width", Integer.class);
//        int mapHeight = prop.get("height", Integer.class);
//        int tileWidth = prop.get("tilewidth", Integer.class);
//        int tileHeight = prop.get("tileheight", Integer.class);

        // Draw FPS counter on the top left
        batch.begin();
        font.draw(batch, "FPS: "+ Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight());
        batch.end();

        // Draw stage
        stage.act(delta);
//        sidebar.update();
        stage.setDebugAll(true); // For debug purpose
        stage.getViewport().apply();
        stage.draw();




//        batch.setProjectionMatrix(camera.combined);
//
//
//        batch.begin();
//
//        for (int col=0; col < mapWidth; col++) {
//            for (int row = 0; row < mapHeight; row++) {
//                Vector3 screen_coords = new Vector3(row*64, col*64, 0);
//                screen_coords.mul(Utils.isoTransformMatrix());
//
//                font.draw(batch, "(" +  row +":"+ col +")", screen_coords.x, screen_coords.y);
//            }
//        }
//        font.draw(batch, "tile", 10, 20);
//        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Updates the screen when the window is resized
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
        sidebar.update();
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
    }
}
