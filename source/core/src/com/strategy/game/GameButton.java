package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Amedeo on 02/05/16.
 */
public class GameButton extends Button {

    public GameButton(String texturePath) {
        super(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(texturePath)))));

        this.addListener(new InputListener() {
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // TODO: change the five following lines with the commented one (that for some reason doesn't work).
                // The image "core/assets/cursor_hand2.png" has to have width and height powers of 2 (e.g. 16x16px)
                Pixmap cursorHandPixmap = new Pixmap(Gdx.files.internal("core/assets/cursor_hand2.png"));
                Cursor cursorHand = Gdx.graphics.newCursor(cursorHandPixmap, 0, 0);
                Gdx.graphics.setCursor(cursorHand);
                cursorHand.dispose();
//                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        });
        this.addListener(new InputListener() {
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        });
    }

}
