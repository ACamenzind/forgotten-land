package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Loads the assets.
 * TODO: maybe add an AssetManager for better performance
 */
public class Assets {
    public static TiledMap map;
    public static Texture house1;

    public static void load() {
//        map = new TmxMapLoader().load("core/assets/big_map.tmx");
        map = new TmxMapLoader().load("core/assets/test128.tmx");
        house1 = new Texture(Gdx.files.internal("core/assets/house1.png"));
    }

    public static void dispose() {
        map.dispose();
    }
}
