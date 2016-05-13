package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Loads the assets.
 * TODO: maybe add an AssetManager for better performance
 */
public class Assets {

    public static TiledMap map;
    public static Texture house1, castle, leftwall, middlewall, rightwall, emptyTile, redTile;
    public static Sound bgSound, hit;
    private static final String DEFAULT_FONT_PATH = "core/assets/fonts/times_new_roman.ttf";

    public static void load() {
//        map = new TmxMapLoader().load("core/assets/maps/big_map.tmx");
        //TODO: add credit for CC3.0 sprites
        map = new TmxMapLoader().load("core/assets/maps/default.tmx");
        house1 = new Texture(Gdx.files.internal("core/assets/textures/buildings/house1.png"));
        castle = new Texture(Gdx.files.internal("core/assets/textures/buildings/castle.png"));
        leftwall = new Texture(Gdx.files.internal("core/assets/textures/buildings/wall/Wall_L.png"));
        middlewall = new Texture(Gdx.files.internal("core/assets/textures/buildings/wall/Wall_M.png"));
        rightwall = new Texture(Gdx.files.internal("core/assets/textures/buildings/wall/Wall_R.png"));
        emptyTile = new Texture(Gdx.files.internal("core/assets/textures/terrains/empty.png"));
        redTile = new Texture(Gdx.files.internal("core/assets/textures/terrains/red.png"));

        // http://opengameart.org/content/outdoor-ambiance CC3.0
        bgSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/Outdoor_ambiance.mp3"));
        hit = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/hit.wav"));
    }

    public static void dispose() {
        map.dispose();
    }

    /**
     * Sets the background of a Table. It avoids to write each time SpriteDrawable, Sprite, and Texture.
     * @param table: The Table to set the background image.
     * @param backgroundPath: The path to the background image.
     */
    public static void setBackground(final Table table, String backgroundPath) {
        if (table != null) {
            SpriteDrawable buildingBg = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(backgroundPath))));
            table.setBackground(buildingBg);
        }
    }

    /**
     * Sets the position of the bottom-left corner of an Actor according to the position of its parent.
     * @param actor: The Actor which position is modified
     * @param x: The position relative and proportional to the parent on the x axis. E.g.: 0.5f will set the position of
     *         actor in the middle of its parent.
     * @param y: The position relative and proportional to the parent on the y axis. E.g.: 0.5f will set the position of
     *         actor in the middle of its parent.
     */
    public static void setPositionRelative(final Actor actor, float x, float y) {
        if (actor != null && actor.hasParent()) {
            final Actor parent = actor.getParent();
            actor.setPosition(parent.getWidth() * x, parent.getHeight() * y);
        }
    }

    /**
     * Same as setPositionRelative(final Actor actor, float x, float y), but leaving the possibility of positioning the
     * actor respect to its center.
     * @param actor: The Actor which position is modified
     * @param x: The position relative and proportional to the parent on the x axis. E.g.: 0.5f will set the position of
     *         actor in the middle of its parent.
     * @param y: The position relative and proportional to the parent on the y axis. E.g.: 0.5f will set the position of
     *         actor in the middle of its parent.
     * @param centerX: If true, the actor will be positioned on its x-center and not the bottom-left corner.
     * @param centerY: If true, the actor will be positioned on its y-center and not the bottom-left corner.
     */
    public static void setPositionRelative(final Actor actor, float x, float y, boolean centerX, boolean centerY) {
        if (actor != null && actor.hasParent()) {
            final Actor parent = actor.getParent();
            float offsetX = 0;
            if (centerX) offsetX = actor.getWidth() / 2;
            float offsetY = 0;
            if (centerY) offsetY = actor.getHeight() / 2;
            actor.setPosition(parent.getWidth() * x - offsetX, parent.getHeight() * y - offsetY);
        }
    }

    /**
     * Sets the size of an Actor proportional to the one of its parent.
     * @param actor: The Actor which size is modified.
     * @param width: The proportion of the width of the parent. E.g.: width = 0.5f, the new width of actor will be half
     *             the width of its parent.
     * @param height: The proportion of the height of the parent. E.g.: height = 0.5f, the new height of actor will be
     *             half the height of its parent.
     */
    public static void setSizeRelative(final Actor actor, float width, float height) {
        if (actor != null && actor.hasParent()) {
            final Actor parent = actor.getParent();
            actor.setSize(parent.getWidth() * width, parent.getHeight() * height);
        }
    }

    /**
     * Generates a font, using FreeTypeFont, keeping better resolutions.
     * @param fontPath: The path of the .ttf font file.
     * @param fontSize: The size of the font.
     * @param fontColor: The color of the font.
     * @return The BitmapFont generated with FreeFontType.
     */
    public static BitmapFont makeFont(final String fontPath, int fontSize, final Color fontColor) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = fontSize;
        fontParameter.color = fontColor;
        BitmapFont font = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();
        return font;
    }

    public static BitmapFont makeFont(int fontSize, final Color fontColor) {
        return makeFont(DEFAULT_FONT_PATH, fontSize, fontColor);
    }

    /**
     * Generates a LabelStyle, using parameters for font.
     * @param fontPath: The path of the .ttf font file.
     * @param fontSize: The size of the font.
     * @param fontColor: The color of the font.
     * @return The LabelStyle with the given font.
     */
    public static Label.LabelStyle makeLabelStyle(final String fontPath, int fontSize, final Color fontColor) {
        return new Label.LabelStyle(makeFont(fontPath, fontSize, fontColor), fontColor);
    }

    public static Label.LabelStyle makeLabelStyle(int fontSize, final Color fontColor) {
        return makeLabelStyle(DEFAULT_FONT_PATH, fontSize, fontColor);
    }

    /**
     * Generates a Label, using parameters for font.
     * @param text: The text written in the Label.
     * @param fontPath: The path of the .ttf font file.
     * @param fontSize: The size of the font.
     * @param fontColor: The color of the font.
     * @return The Label, with given text and font.
     */
    public static Label makeLabel(final String text, final String fontPath, int fontSize, final Color fontColor) {
        return new Label(text, makeLabelStyle(fontPath, fontSize, fontColor));
    }

    public static Label makeLabel(final String text, int fontSize, final Color fontColor) {
        return makeLabel(text, DEFAULT_FONT_PATH, fontSize, fontColor);
    }
}
