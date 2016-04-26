package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;


/**
 * Handles the game input
 */
public class GameInputProcessor implements InputProcessor{

    private TiledMapTileLayer.Cell oldcell;
    private GameScreen screen;
    private OrthographicCamera camera;
    private final int EDGE_THRESHOLD_WIDTH = 50;

    public GameInputProcessor(GameScreen screen) {
        this.screen = screen;
        this.camera = screen.getCamera();
        System.out.println(camera);
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

        // TODO: refactor
        Vector3 touch = new Vector3(screenX, screenY, 0);

        camera.unproject(touch);
        touch.mul(Utils.invIsoTransformMatrix());

        int pickedTileX = (int) (touch.x / Utils.TILE_SIZE);
        int pickedTileY = (int) (touch.y / Utils.TILE_SIZE);

        TiledMapTileLayer baseLayer = (TiledMapTileLayer) screen.getMap().getLayers().get(0);
        TiledMapTileLayer upperLayer = (TiledMapTileLayer) screen.getMap().getLayers().get(1);
        Texture tex = new Texture(Gdx.files.internal("core/assets/house1.png"));
        MapEntity building = new MapEntity(tex);

//        screen.getBuilder().selectEntity(building);
        screen.getBuilder().placeSelectedEntity(pickedTileX, pickedTileY);

        // Places a building on the upper layer
//        building.placeOnLayer(upperLayer, pickedTileX, pickedTileY);
//        building.setSelected(false);


//        upperLayer.setCell(pickedTileX, pickedTileY, new TiledMapTileLayer.Cell());
////        TiledMapTileLayer.Cell cell = upperLayer.getCell(pickedTileX, pickedTileY);
//        TiledMapTileLayer.Cell cell = upperLayer.getCell(pickedTileX, pickedTileY);
////        screen.getMap().getLayers().add();
////        StaticTiledMapTile
//
//        if (cell != null) {
////            System.out.println(cell.toString());
////            cell.getTile()
////            cell.setTile(null);
//            Texture texture = new Texture(Gdx.files.internal("core/assets/watchtower_lvl2-exp_full_size.png"));
//            TextureRegion region = new TextureRegion(texture);
//            StaticTiledMapTile newTile = new StaticTiledMapTile(region);
//            cell.setTile(newTile);
////;
//
//        }
        return true;
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
