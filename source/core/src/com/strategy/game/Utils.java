package com.strategy.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created on 22/04/2016.
 */
public class Utils {
    public static Vector2 cartesianToIso(Vector2 coords) {
        return new Vector2(coords.x - coords.y,
                          (coords.x + coords.y) / 2);
    }

    public static Vector2 isoToCartesian(Vector2 coords) {
        return new Vector2((2 * coords.y + coords.x) / 2,
                            (2 * coords.y - coords.x) / 2);
    }
}
