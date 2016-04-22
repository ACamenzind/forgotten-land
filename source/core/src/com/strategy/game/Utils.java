package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Utility class with useful constants and static methods
 */
public class Utils {
    public static Vector2 cartesianToIso(Vector2 coords) {
        return new Vector2(coords.x - coords.y,
                          (coords.x + coords.y) / 2.f);
    }

    public static Vector2 isoToCartesian(Vector2 coords) {
        coords.y = -coords.y;
        return new Vector2((2 * coords.y + coords.x) / 2.f,
                            (2 * coords.y - coords.x) / 2.f);
    }
}
