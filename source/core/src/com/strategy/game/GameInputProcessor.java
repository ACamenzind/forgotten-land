package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.strategy.game.buildings.*;
import com.strategy.game.screens.GameScreen;


/**
 * Handles the game input
 */
public class GameInputProcessor implements InputProcessor{
    private GameScreen screen;
    private OrthographicCamera camera;
    private final int EDGE_THRESHOLD_WIDTH = 50;
    private Vector2 touchDownCoords;

    public GameInputProcessor(GameScreen screen) {
        this.screen = screen;
        this.camera = screen.getCamera();
    }

    // Used for continuous presses
    public void pollKeyboard() {
        // Moves the camera in the specified direction
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(camera.zoom*Utils.BASE_CAMERA_SPEED,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(camera.zoom*(-Utils.BASE_CAMERA_SPEED),0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0,camera.zoom*Utils.BASE_CAMERA_SPEED);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0,camera.zoom*(-Utils.BASE_CAMERA_SPEED));
        }
    }

    public void pollMouse() {
        // y-axis starts bottom-left by default
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        // Moves the camera accordingly if the cursor is near an edge.
        // The speed also depends on the current zoom.
        if ((mouseX > camera.viewportWidth - EDGE_THRESHOLD_WIDTH) &&
                (mouseY > (camera.viewportHeight / 4)) &&
                (mouseY < (camera.viewportHeight* 3/4)))
            camera.translate(camera.zoom*Utils.BASE_CAMERA_SPEED, 0);

        if ((mouseX < EDGE_THRESHOLD_WIDTH) &&
                (mouseY > (camera.viewportHeight / 4)) &&
                (mouseY < (camera.viewportHeight * 3/4)))
            camera.translate(camera.zoom*(-Utils.BASE_CAMERA_SPEED), 0);

        if ((mouseY > camera.viewportHeight - EDGE_THRESHOLD_WIDTH) &&
                (mouseX > (camera.viewportWidth / 4)) &&
                (mouseX < (camera.viewportWidth * 3/4)))
            camera.translate(0, camera.zoom*Utils.BASE_CAMERA_SPEED);

        if ((mouseY < EDGE_THRESHOLD_WIDTH) &&
                (mouseX > (camera.viewportWidth / 4)) &&
                (mouseX < (camera.viewportWidth * 3/4)))
            camera.translate(0, camera.zoom*(-Utils.BASE_CAMERA_SPEED));
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.NUM_1:
                screen.getBuilder().toggleSelectEntity(new House());
                break;
            case Input.Keys.NUM_2:
                screen.getBuilder().toggleSelectEntity(new Castle());
                break;
            case Input.Keys.NUM_3:
                screen.getBuilder().toggleSelectEntity(new Wall());
                break;
            case Input.Keys.R:
                screen.getBuilder().rotate();
                break;
        }
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

        Vector3 touch = new Vector3(screenX, screenY, 0);

        // Set coords used for selection box
        screen.setTouchDownCoords(new Vector2(screenX, screenY));
        screen.setSelecting(true);


        Vector3 pickedTile = Utils.cartesianToIso(touch, camera);
        //TODO: better to handle coords inside the builder?
        if (screen.getBuilder().getSelectedEntity() != null) {
            screen.getBuilder().placeSelectedEntity((int) pickedTile.x, (int) pickedTile.y);
        }
        else if (screen.getBuilder().getSelectedEntity() == null)  {
            MoveToAction action = new MoveToAction();
//            screenY = Gdx.graphics.getHeight() - screenY;
//            camera.unproject(touch);
//            Vector3 pos = new Vector3(screenX, screenY, 0);
            touch.mul(Utils.isoTransformMatrix());
            Vector3 unitCoords = new Vector3(screen.getTestUnit().getX(), screen.getTestUnit().getY(), 0);
//            unitCoords.mul(Utils.isoTransformMatrix());
            float dx = touch.x - unitCoords.x;
            float dy = touch.y - unitCoords.y;
            Vector3 delta = new Vector3(dx, dy, 0);
            action.setPosition(touch.x, touch.y);
            action.setInterpolation(Interpolation.pow2);

            action.setDuration(delta.len()/500.f);

            float width = screen.getTestUnit().getWidth();

            if (dx > 0 && width > 0) screen.getTestUnit().setWidth(-screen.getTestUnit().getWidth());
            if (dx < 0 && width < 0) screen.getTestUnit().setWidth(-screen.getTestUnit().getWidth());
//            action.set
            screen.getTestUnit().addAction(action);
            //TODO: get tile info and show window
            // for debugging purposes
//            TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) (screen.getMap().getLayers().get(1)))
//                    .getCell((int) pickedTile.x, (int) pickedTile.y);
//            if (cell != null) {
//                ExtendedStaticTiledMapTile tile = (ExtendedStaticTiledMapTile) cell.getTile();
//                MapEntity selected = tile.getObject();
//                selected.setClicked(true);
//                System.out.println("set clicked!");
//
//
//
//                MapObject obj = new RectangleMapObject(selected.getCoords().x * Utils.TILE_SIZE,
//                        selected.getCoords().y * Utils.TILE_SIZE ,
//                        selected.getCollisionSize().x * Utils.TILE_SIZE,
//                        selected.getCollisionSize().y * Utils.TILE_SIZE);
//                obj.setVisible(true);
//
//
//                MapLayer objLayer = screen.getMap().getLayers().get(3);
//
//                obj.setColor(new Color(Color.RED));
////                obj.setName("HELLO WORLD ------------------------------------");
//                obj.setOpacity(1.f);
////                objLayer.getObjects().add(obj);
//
//            }
//            else {
//                System.out.println("Empty");
//            }

//            Utils.printTileInfo(pickedTile, screen);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        screen.setTouchUpCoords(new Vector2(screenX, screenY));
        screen.setSelecting(false);
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
        // TODO: add limits
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        if(camera.zoom > 0.2 || amount > 0) {
            camera.zoom += amount / 10.f;
        }
        // Not working properly
        //camera.translate(mouseX-1280, mouseY-720);
        camera.update();
        return true;
    }
}
