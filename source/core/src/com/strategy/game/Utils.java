package com.strategy.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.strategy.game.desktop.DesktopLauncher;
import com.strategy.game.screens.GameScreen;
import sun.security.krb5.internal.crypto.Des;

import java.awt.*;

/**
 * Utility class with useful constants and static methods
 */
public class Utils {
    // SIZE OPTIONS
    public static final int TILE_SIZE = 128;
    public static final float DEFAULT_RATIO = 1f; // can be removed
    public static int BASE_CAMERA_SPEED = 10;
    public static final int RENDER_OFFSET = 5;
    // FONTS
    public static final int FONT_SIZE_SMALL = DesktopLauncher.HDPI ? (int) (12 * DEFAULT_RATIO) * 2 : (int) (12 * DEFAULT_RATIO);
    public static final int FONT_SIZE_MEDIUM = DesktopLauncher.HDPI ? (int) (14 * DEFAULT_RATIO) * 2 : (int) (14 * DEFAULT_RATIO);
    public static final int FONT_SIZE_BIG = DesktopLauncher.HDPI ? (int) (29 * DEFAULT_RATIO) * 2 : (int) (29 * DEFAULT_RATIO);
    public static final int FONT_SIZE_HUGE = DesktopLauncher.HDPI ? (int) (40 * DEFAULT_RATIO) * 2 : (int) (40 * DEFAULT_RATIO);
    public static BitmapFont FONT_SMALL_BLACK = Assets.makeFont(FONT_SIZE_SMALL, Color.BLACK);
    public static BitmapFont FONT_SMALL_WHITE = Assets.makeFont(FONT_SIZE_SMALL, Color.LIGHT_GRAY);
    public static BitmapFont FONT_SMALL_RED = Assets.makeFont(FONT_SIZE_SMALL, Color.RED);
    public static BitmapFont FONT_MEDIUM_BLACK = Assets.makeFont(FONT_SIZE_MEDIUM, Color.BLACK);
    public static BitmapFont FONT_MEDIUM_WHITE = Assets.makeFont(FONT_SIZE_MEDIUM, Color.LIGHT_GRAY);
    public static BitmapFont FONT_MEDIUM_RED = Assets.makeFont(FONT_SIZE_MEDIUM, Color.RED);
    public static BitmapFont FONT_BIG_BLACK = Assets.makeFont(FONT_SIZE_BIG, Color.BLACK);
    public static BitmapFont FONT_BIG_WHITE = Assets.makeFontUltraLight(FONT_SIZE_BIG, Color.LIGHT_GRAY); // Ultralight
    public static BitmapFont FONT_HUGE_RED = Assets.makeFont(FONT_SIZE_HUGE, Color.RED);
    public static BitmapFont FONT_HUGE_WHITE = Assets.makeFont(FONT_SIZE_HUGE, Color.LIGHT_GRAY);


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
