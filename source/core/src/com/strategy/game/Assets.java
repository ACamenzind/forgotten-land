package com.strategy.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Loads the assets.
 */
public class Assets {
    public static TiledMap map;

    public static void load() {
        map = new TmxMapLoader().load("core/assets/isometric_grass_and_water.tmx");
    }
}
