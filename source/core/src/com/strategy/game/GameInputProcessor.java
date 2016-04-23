package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

/**
 * Handles the game input
 */
public class GameInputProcessor implements InputProcessor{

    private GameScreen screen;
    private OrthographicCamera camera;

    public GameInputProcessor(GameScreen screen) {
        this.screen = screen;
        this.camera = screen.getCamera();
        System.out.println(camera);
    }

    // Used for continuous presses
    public void pollKeyboard() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) camera.translate(10,0);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) camera.translate(-10,0);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) camera.translate(0,10);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) camera.translate(0,-10);
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

        TiledMapTileLayer.Cell cell = baseLayer.getCell(pickedTileX, pickedTileY);

        if (cell != null) {
            System.out.println(cell.toString());
            cell.setTile(null);
        }
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
        camera.zoom += amount / 10.f;
        camera.update();
        return true;
    }
}
