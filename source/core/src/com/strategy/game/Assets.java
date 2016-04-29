package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Loads the assets.
 * TODO: maybe add an AssetManager for better performance
 */
public class Assets {
    public static TiledMap map;
    public static Texture house1, castle, leftwall, middlewall, rightwall, emptyTile;
    public static Sound bgSound, hit;

    public static void load() {
//        map = new TmxMapLoader().load("core/assets/big_map.tmx");
        map = new TmxMapLoader().load("core/assets/test128.tmx");
        house1 = new Texture(Gdx.files.internal("core/assets/house1.png"));
        castle = new Texture(Gdx.files.internal("core/assets/castle.png"));
        leftwall = new Texture(Gdx.files.internal("core/assets/Wall/Wall_L.png"));
        middlewall = new Texture(Gdx.files.internal("core/assets/Wall/Wall_M.png"));
        rightwall = new Texture(Gdx.files.internal("core/assets/Wall/Wall_R.png"));
        emptyTile = new Texture(Gdx.files.internal("core/assets/badlogic.png"));

        // http://opengameart.org/content/outdoor-ambiance CC3.0
        bgSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/Outdoor_ambiance.mp3"));
        hit = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/hit.wav"));
    }

    public static void dispose() {
        map.dispose();
    }
}
