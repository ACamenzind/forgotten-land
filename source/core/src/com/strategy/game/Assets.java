package com.strategy.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Loads the assets.
 * TODO: maybe add an AssetManager for better performance
 */
public class Assets {
    public static TiledMap map;

    public static void load() {
        map = new TmxMapLoader().load("core/assets/big_map.tmx");
    }

    public static void dispose() {
        map.dispose();
    }
}
