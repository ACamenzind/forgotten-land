package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


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
    private IsometricTiledMapRenderer renderer;
    private GameInputProcessor gameInputProcessor;
    private World world;



    public GameScreen(StrategyGame game) {
        this.game = game;
        this.world = new World(this);
        this.batch = game.getBatch();
        this.font = game.getFont();

        Assets.load();

        this.map = Assets.map;
        this.renderer = new IsometricTiledMapRenderer(map);
        this.camera = new OrthographicCamera(Utils.DEFAULT_WIDTH, Utils.DEFAULT_HEIGHT);
        this.gameInputProcessor = new GameInputProcessor(this);

        Gdx.input.setInputProcessor(gameInputProcessor);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public TiledMap getMap() {
        return map;
    }

    @Override
    public void show() {
        // Prints the map properties
//        Iterator<String> it = map.getProperties().getKeys();
//        Iterator<Object> it2 = map.getProperties().getValues();
//
//        while (it.hasNext() && it2.hasNext()) {
//            System.out.println(it.next());
//            System.out.println(it2.next());
//        }

//        System.out.println(map.getProperties().getKeys());
//        this.renderer = new OrthogonalTiledMapRenderer(map);

    }

    @Override
    public void render(float delta) {
        // OpenGL stuff
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Handle the input
        gameInputProcessor.pollKeyboard();
        gameInputProcessor.pollMouse();

        camera.update();
        renderer.setView(camera);
        renderer.render(); // Render the tilemap

        MapProperties prop = map.getProperties();

        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tileWidth = prop.get("tilewidth", Integer.class);
        int tileHeight = prop.get("tileheight", Integer.class);

        // Draw FPS counter on the top left
        batch.begin();
        font.draw(batch, "FPS: "+ Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight());
        batch.end();


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
    }
}
