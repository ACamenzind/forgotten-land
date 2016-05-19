package com.strategy.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.strategy.game.screens.GameScreen;

/**
 * Utility class with useful constants and static methods
 */
public class Utils {
    public static final int TILE_SIZE = 128;
    public static final float DEFAULT_RATIO = 1.5f;
    public static final int DEFAULT_WIDTH = (int) (1280 * DEFAULT_RATIO);
    public static final int DEFAULT_HEIGHT = (int) (720 * DEFAULT_RATIO);
    public static final int BASE_CAMERA_SPEED = 10;
    public static final int RENDER_OFFSET = 20;
    // HDPI
    public static final boolean HDPI = true;
    // Without HDPI
    public static final int FONT_SIZE_SMALL = HDPI ? (int) (12 * DEFAULT_RATIO) * 2 : (int) (12 * DEFAULT_RATIO);
    public static final int FONT_SIZE_MEDIUM = HDPI ? (int) (14 * DEFAULT_RATIO) * 2 : (int) (14 * DEFAULT_RATIO);
    public static final int FONT_SIZE_BIG = HDPI ? (int) (20 * DEFAULT_RATIO) * 2 : (int) (20 * DEFAULT_RATIO);
    public static final int FONT_SIZE_HUGE = HDPI ? (int) (40 * DEFAULT_RATIO) * 2 : (int) (40 * DEFAULT_RATIO);
    // With HDPI
//    public static final int FONT_SIZE_SMALL = (int) (24 * DEFAULT_RATIO);
//    public static final int FONT_SIZE_MEDIUM = (int) (28 * DEFAULT_RATIO);
//    public static final int FONT_SIZE_BIG = (int) (40 * DEFAULT_RATIO);
//    public static final int FONT_SIZE_HUGE = (int) (80 * DEFAULT_RATIO);


    public static Matrix4 isoTransformMatrix() {
        // from http://www.alcove-games.com/advanced-tutorials/isometric-tile-picking/
        Matrix4 isoTransform = new Matrix4();
        isoTransform.idt();
        isoTransform.translate(0.f, 0.25f * Utils.TILE_SIZE, 0.0f);
        isoTransform.scale((float)(Math.sqrt(2.0) / 2.0), (float)(Math.sqrt(2.0) / 4.0), 1.0f);
        isoTransform.rotate(0.0f, 0.0f, 1.0f, -45.0f);
        return isoTransform;
    }

    // When multiplied with a vector, it trasforms it into isometric oordinates.
    public static Matrix4 invIsoTransformMatrix() {
        return Utils.isoTransformMatrix().inv();
    }

    public static Vector3 cartesianToIso(Vector3 coords, OrthographicCamera camera) {
        camera.unproject(coords);
        coords.mul(Utils.invIsoTransformMatrix());
        return new Vector3(coords.x / TILE_SIZE, coords.y / TILE_SIZE, 0);
    }

    public static void printTileInfo(Vector3 pickedTile, GameScreen screen) {
        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) (screen.getMap().getLayers().get(1)))
                .getCell((int) pickedTile.x, (int) pickedTile.y);
        if (cell != null) {
            ExtendedStaticTiledMapTile tile = (ExtendedStaticTiledMapTile) cell.getTile();
            System.out.println(tile.getObject().toString());
        }
        else {
            System.out.println("Empty");
        }
    }


    // doesn't work
    public static Vector2 isoToCartesian(Vector2 coords) {
        coords.y = -coords.y;
        return new Vector2((2 * coords.y + coords.x) / 2.f,
                            (2 * coords.y - coords.x) / 2.f);
    }


}
