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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


/**
 * The main game screen, where the gameplay is rendered.
 */
public class GameScreen implements Screen, InputProcessor {

    private final int TILE_SIZE = 64;


    private Stage stage;
    private final StrategyGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Image testPlayer;
    private TiledMap map;
    private IsometricTiledMapRenderer renderer;

    private Matrix4 isoTransform;
    private Matrix4 invIsoTransform;

//    private OrthogonalTiledMapRenderer renderer;

    public GameScreen(StrategyGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.testPlayer = new Image(new Texture("core/assets/badlogic.jpg"));
//        stage.addActor(testPlayer);
        Gdx.input.setInputProcessor(this);
//        stage.setKeyboardFocus(testPlayer);

        // from http://www.alcove-games.com/advanced-tutorials/isometric-tile-picking/
        isoTransform = new Matrix4();
        isoTransform.idt();
        isoTransform.translate(0.f, 0.25f * TILE_SIZE, 0.0f);
        isoTransform.scale((float)(Math.sqrt(2.0) / 2.0), (float)(Math.sqrt(2.0) / 4.0), 1.0f);
        isoTransform.rotate(0.0f, 0.0f, 1.0f, -45.0f);

        invIsoTransform = new Matrix4(isoTransform);
        invIsoTransform.inv();
        Assets.load();

    }

    @Override
    public void show() {
//        this.map = new TmxMapLoader().load("core/assets/isometric_grass_and_water.tmx");
        this.map = Assets.map;
        this.renderer = new IsometricTiledMapRenderer(map);
        this.camera = new OrthographicCamera(StrategyGame.DEFAULT_WIDTH, StrategyGame.DEFAULT_WIDTH);

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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        if (Gdx.input.isKeyPressed(Input.Keys.D)) camera.translate(10,0);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) camera.translate(-10,0);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) camera.translate(0,10);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) camera.translate(0,-10);


        camera.update();
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
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
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        // Makes a cell disappear
        Vector3 touch = new Vector3(screenX, screenY, 0);

        camera.unproject(touch);
        touch.mul(invIsoTransform);

        int pickedTileX = (int) (touch.x / TILE_SIZE);
        int pickedTileY = (int) (touch.y / TILE_SIZE);
        System.out.println(pickedTileX + " : " + pickedTileY);

        TiledMapTileLayer baseLayer = (TiledMapTileLayer) map.getLayers().get(0);

        TiledMapTileLayer.Cell cell = baseLayer.getCell(pickedTileX, pickedTileY);
//        baseLayer.getCell()
        if (cell != null) {
            System.out.println(cell.toString());
            cell.setTile(null);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {

        camera.zoom += amount / 10.f;
        camera.update();

        return false;
    }

}
