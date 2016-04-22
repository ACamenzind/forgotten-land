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
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;

import java.util.Iterator;

/**
 * The main game screen, where the gameplay is rendered.
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
//    private OrthogonalTiledMapRenderer renderer;

    public GameScreen(StrategyGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.testPlayer = new Image(new Texture("core/assets/badlogic.jpg"));
//        stage.addActor(testPlayer);
        Gdx.input.setInputProcessor(stage);
//        stage.setKeyboardFocus(testPlayer);


    }

    @Override
    public void show() {
        this.map = new TmxMapLoader().load("core/assets/isometric_grass_and_water.tmx");
        this.renderer = new IsometricTiledMapRenderer(map);

//        Iterator<String> it = map.getProperties().getKeys();
//        Iterator<Object> it2 = map.getProperties().getValues();
//
//        while (it.hasNext() && it2.hasNext()) {
//            System.out.println(it.next());
//            System.out.println(it2.next());
//        }

//        System.out.println(map.getProperties().getKeys());
//        this.renderer = new OrthogonalTiledMapRenderer(map);
        this.camera = new OrthographicCamera(1280, 720);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(10,0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-10,0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.translate(0,10);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0,-10);
        }


        if (Gdx.input.isKeyPressed(Input.Keys.C)) {
            camera.zoom += 0.1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.V)) {
            camera.zoom -= 0.1;
        }


        camera.update();
//        stage.act(delta);

//        float pX = testPlayer.getX();
//        float pY = testPlayer.getY();
//
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            pY += 10;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//            pY -= 10;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            pX -= 10;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            pX += 10;
//        }
//
//
//        Vector2 pos = new Vector2(pX, pY);
//        Vector2 pos2 = Utils.isoToCartesian(pos);
////            Vector2 pos2 = Utils.cartesianToIso(pos);
//        testPlayer.setPosition(pos2.x, pos2.y);
//        System.out.println(pos2.toString());

//        stage.draw();

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

    }

}
