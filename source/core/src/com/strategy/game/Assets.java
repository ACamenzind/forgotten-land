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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Loads the assets.
 * TODO: maybe add an AssetManager for better performance
 */
public class Assets {

    public static TiledMap map;
    // BUILDINGS
    public static Texture house1, castle, leftwall, middlewall, rightwall, emptyTile, redTile, cows, warehouse, wheat, lumberjack, mine;
    // SIDEBAR BACKGROUNDS
    public static Texture sidebarBg, sidebarBgInfo, sidebarBgBuild, sidebarBgMenu, sidebarBuildInfoBg;
    // SIDEBAR MENU
    public static Texture sidebarMenuInfo, sidebarMenuBuild, sidebarMenuMenu, sidebarMenuInfoClicked,
            sidebarMenuBuildClicked, sidebarMenuMenuClicked;
    // SIDEBAR BUILD
    public static Texture sidebarBuildManufacturers, sidebarBuildWarehouses, sidebarBuildDecorations, sidebarBuildOther,
            sidebarBuildRotate, sidebarBuildBack, sidebarBuildCancel;
    // SIDEBAR GAME MENU
    public static Texture sidebarMenuNew, sidebarMenuLoad, sidebarMenuSave, sidebarMenuQuit, sidebarMenuCredits;
    // SIDEBAR BUILDING INFO
    public static Texture sidebarBuildInfoInfo, sidebarBuildInfoCost, sidebarBuildInfoProfit, sidebarBuildInfoCapacity,
            sidebarBuildInfoPlus, sidebarBuildInfoMinus, sidebarBuildInfoDestroy, sidebarBuildInfoRepair;
    // RESOURCES_BAR
    public static Texture resourcesBarBg, resourcesBarPause, resourcesBarResume;
    // RESOURCES
    public static Texture resourcesFood, resourcesWood, resourcesRock, resourcesGold, resourcesPeople;
    // SOUNDS
    public static Sound bgSound, hit;
    private static final String DEFAULT_FONT_PATH = "core/assets/fonts/san_francisco_regular.ttf";

    public static void load() {
//        map = new TmxMapLoader().load("core/assets/maps/big_map.tmx");
        //TODO: add credit for CC3.0 sprites
        map = new TmxMapLoader().load("core/assets/maps/default.tmx");
        // BUILDINGS
        house1 = new Texture(Gdx.files.internal("core/assets/textures/buildings/house1.png"));
        house1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        castle = new Texture(Gdx.files.internal("core/assets/textures/buildings/castle.png"));
        castle.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        cows = new Texture(Gdx.files.internal("core/assets/textures/buildings/cows.png"));
        cows.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        warehouse = new Texture(Gdx.files.internal("core/assets/textures/buildings/warehouse.png"));
        warehouse.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        wheat = new Texture(Gdx.files.internal("core/assets/textures/buildings/wheat.png"));
        wheat.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        lumberjack = new Texture(Gdx.files.internal("core/assets/textures/buildings/lumberjack.png"));
        lumberjack.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        mine = new Texture(Gdx.files.internal("core/assets/textures/buildings/mine.png"));
        mine.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        leftwall = new Texture(Gdx.files.internal("core/assets/textures/buildings/wall/Wall_L.png"));
        leftwall.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        middlewall = new Texture(Gdx.files.internal("core/assets/textures/buildings/wall/Wall_M.png"));
        middlewall.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        rightwall = new Texture(Gdx.files.internal("core/assets/textures/buildings/wall/Wall_R.png"));
        rightwall.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        emptyTile = new Texture(Gdx.files.internal("core/assets/textures/terrains/empty.png"));
        emptyTile.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        redTile = new Texture(Gdx.files.internal("core/assets/textures/terrains/red.png"));
        redTile.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        // SIDEBAR BACKGROUNDS
        sidebarBg = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_bg.png"));
        sidebarBg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBgInfo = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_bg_info.png"));
        sidebarBgInfo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBgBuild = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_bg_build.png"));
        sidebarBgBuild.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBgMenu = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_bg_menu.png"));
        sidebarBgMenu.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildInfoBg = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_building_info_bg.png"));
        sidebarBuildInfoBg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        // SIDEBAR MENU
        sidebarMenuInfo = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_menu_info.png"));
        sidebarMenuInfo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarMenuBuild = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_menu_build.png"));
        sidebarMenuBuild.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarMenuMenu = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_menu_menu.png"));
        sidebarMenuMenu.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarMenuInfoClicked = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_menu_info_clicked.png"));
        sidebarMenuInfoClicked.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarMenuBuildClicked = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_menu_build_clicked.png"));
        sidebarMenuBuildClicked.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarMenuMenuClicked = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_menu_menu_clicked.png"));
        sidebarMenuMenuClicked.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        // SIDEBAR BUILD
        sidebarBuildManufacturers = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_build_manufacturers.png"));
        sidebarBuildManufacturers.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildWarehouses = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_build_warehouses.png"));
        sidebarBuildWarehouses.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildDecorations = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_build_decorations.png"));
        sidebarBuildDecorations.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildOther = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_build_other.png"));
        sidebarBuildOther.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildRotate = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_build_rotate.png"));;
        sidebarBuildRotate.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildBack = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_build_back.png"));;
        sidebarBuildBack.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildCancel = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_build_cancel.png"));;
        sidebarBuildCancel.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        // SIDEBAR GAME MENU
        sidebarMenuNew = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_menu_new.png"));
        sidebarMenuNew.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarMenuLoad = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_menu_load.png"));
        sidebarMenuLoad.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarMenuSave = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_menu_save.png"));
        sidebarMenuSave.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarMenuQuit = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_menu_quit.png"));
        sidebarMenuQuit.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarMenuCredits = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_menu_credits.png"));;
        sidebarMenuCredits.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        // SIDEBAR BUILDING INFO
        sidebarBuildInfoInfo = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_buildinfo_info.png"));
        sidebarBuildInfoInfo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildInfoCost = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_buildinfo_cost.png"));
        sidebarBuildInfoCost.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildInfoProfit = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_buildinfo_profit.png"));
        sidebarBuildInfoProfit.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildInfoCapacity = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_buildinfo_capacity.png"));
        sidebarBuildInfoCapacity.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildInfoPlus = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_buildinfo_plus.png"));
        sidebarBuildInfoPlus.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildInfoMinus = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_buildinfo_minus.png"));
        sidebarBuildInfoMinus.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildInfoDestroy = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_buildinfo_destroy.png"));
        sidebarBuildInfoDestroy.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sidebarBuildInfoRepair = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/sidebar_buildinfo_repair.png"));
        sidebarBuildInfoRepair.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        // RESOURCES_BAR
        resourcesBarBg = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/resourcesbar_bg.png"));
        resourcesBarBg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        resourcesBarPause = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/resourcebar_pause.png"));
        resourcesBarPause.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        resourcesBarResume = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/resourcebar_resume.png"));
        resourcesBarResume.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        // RESOURCES
        resourcesFood = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/resources_food.png"));
        resourcesFood.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        resourcesWood = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/resources_wood.png"));
        resourcesWood.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        resourcesRock = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/resources_rock.png"));
        resourcesRock.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        resourcesGold = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/resources_gold.png"));
        resourcesGold.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        resourcesPeople = new Texture(Gdx.files.internal("core/assets/textures/gameScreen/resources_people.png"));
        resourcesPeople.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


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

    public static void setBackground(final Table table, Texture texture) {
        if (table != null)
            table.setBackground(new SpriteDrawable(new Sprite(texture)));
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
            if (centerX) offsetX = actor.getWidth() / 2f;
            float offsetY = 0;
            if (centerY) offsetY = actor.getHeight() / 2f;
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
     * @param font: The font on which the LabelStyle is based on.
     * @return The LabelStyle with the given font.
     */
    public static Label.LabelStyle makeLabelStyle(BitmapFont font) {
        return new Label.LabelStyle(font, font.getColor());
    }

    /**
     * Generates a Label, using parameters for font.
     * @param text: The text written in the Label.
     * @param font: The font that defines the LabelStyle.
     * @return The Label, with given text and font.
     */
    public static Label makeLabel(String text, BitmapFont font) {
        return new Label(text, new Label.LabelStyle(font, font.getColor()));
    }

    public static Image makeImage(Texture texture) {
        return new Image(new SpriteDrawable(new Sprite(texture)));
    }
}
