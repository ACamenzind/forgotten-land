package com.strategy.game;

/**
 * Created by alex on 27/04/16.
 */
public class Castle extends MapEntity {
    public Castle() {
        super();
        this.mainTexture = Assets.castle;
        sliceTexture(mainTexture);
    }
}
