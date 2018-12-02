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
public final class Assets {
    private Assets(){}

    public static TiledMap map;
    // MAIN MENU
    public static Texture mainMenuNewGame, mainMenuCredits, mainMenuSettings, mainMenuInstructions;
    // BUILDINGS
    public static Texture house1, castle, leftwall, middlewall, rightwall, road, emptyTile, influenceTile, cows, warehouse, wheat, lumberjack, mine, market;
    // SIDEBAR BACKGROUNDS
    public static Texture sidebarBg, sidebarBgInfo, sidebarBgBuild, sidebarBgMenu, sidebarBuildInfoBg;
    // SIDEBAR MENU
    public static Texture sidebarMenuInfo, sidebarMenuBuild, sidebarMenuMenu, sidebarMenuInfoClicked,
            sidebarMenuBuildClicked, sidebarMenuMenuClicked;
    // SIDEBAR BUILD
    public static Texture sidebarBuildManufacturers, sidebarBuildWarehouses, sidebarBuildDecorations, sidebarBuildOther,
            sidebarBuildRotate, sidebarBuildBack, sidebarBuildCancel;
    // SIDEBAR GAME MENU
    public static Texture sidebarMenuNew, sidebarMenuLoad, sidebarMenuSave, sidebarMenuQuit, sidebarMenuCredits,
            sidebarMenuSettings, sidebarMenuInstructions;
    // SIDEBAR BUILDING INFO
    public static Texture sidebarBuildInfoInfo, sidebarBuildInfoBg2, sidebarBuildInfoInfoLong, sidebarBuildInfoCost,
            sidebarBuildInfoCostLong, sidebarBuildInfoProfit, sidebarBuildInfoCapacity, sidebarBuildInfoPlus,
            sidebarBuildInfoMinus, sidebarBuildInfoDestroy, sidebarBuildInfoRepair, sidebarBuildInfoCancel;
    // RESOURCES_BAR
    public static Texture resourcesBarBg, resourcesBarPause, resourcesBarResume, resourcesBarSpeedNormal,
            resourcesBarSpeedFast;
    // OVERLAYS
    public static Texture pausedOverlay, bottomBarBg;
    // RESOURCES
    public static Texture resourcesFood, resourcesWood, resourcesRock, resourcesGold, resourcesPeople, resourcesFoodBk,
            resourcesWoodBk, resourcesRockBk, resourcesGoldBk, resourcesPeopleBk;
    // FULLSCREEN
    public static Texture screenCredits, screenInstructions;
    // MESSAGES
    public static Texture messageOk, messageCancel;
    // RESOURCES IMAGES
    public static Texture tree, stone;
    // SETTINGS
    public static Texture settingsSliderBg, settingsSliderKnob, settingsRes720p,
            settingsRes1080p, settingsResFull, goBack;
    // INSTRUCTIONS
    public static Texture screenInstructions1, screenInstructions2, screenInstructions3, screenInstructions4,
            screenInstructions5, screenInstructionsBack, screenInstructionsNext;
    // SOUNDS
    public static Sound bgSound, hit, demolition;
    private static final String DEFAULT_FONT_PATH = "core/assets/fonts/san_francisco_regular.ttf";
    // new Font
    private static final String ULTRALIGHT_FONT_PATH = "core/assets/fonts/san_francisco_ultralight.ttf";


    public static void loadMap() {
        map = new TmxMapLoader().load("core/assets/maps/default.tmx");
    }

    public static void load() {
//        map = new TmxMapLoader().load("core/assets/maps/big_map.tmx");
        //TODO: add credit for CC3.0 sprites
//        map = new TmxMapLoader().load("core/assets/maps/default.tmx");

        // MAIN MENU
        mainMenuNewGame = createTexture("core/assets/textures/mainMenuScreen/newgame.png");
        mainMenuCredits = createTexture("core/assets/textures/mainMenuScreen/credits.png");
        mainMenuSettings = createTexture("core/assets/textures/mainMenuScreen/settings.png");
        mainMenuInstructions = createTexture("core/assets/textures/mainMenuScreen/instructions.png");
        // BUILDINGS
        house1 = createTexture("core/assets/textures/buildings/house1.png");
        castle = createTexture("core/assets/textures/buildings/castle.png");
        cows = createTexture("core/assets/textures/buildings/cows.png");
        warehouse = createTexture("core/assets/textures/buildings/warehouse.png");
        wheat = createTexture("core/assets/textures/buildings/wheat.png");
        lumberjack = createTexture("core/assets/textures/buildings/lumberjack.png");
        mine = createTexture("core/assets/textures/buildings/mine.png");
        leftwall = createTexture("core/assets/textures/buildings/wall/Wall_L.png");
        middlewall = createTexture("core/assets/textures/buildings/wall/Wall_M.png");
        rightwall = createTexture("core/assets/textures/buildings/wall/Wall_R.png");
        road = createTexture("core/assets/textures/terrains/road.png");

        market = createTexture("core/assets/textures/buildings/market.png");

        emptyTile = createTexture("core/assets/textures/terrains/empty.png");
        influenceTile = createTexture("core/assets/textures/terrains/influence_tile.png");

        // SIDEBAR BACKGROUNDS
        sidebarBg = createTexture("core/assets/textures/gameScreen/sidebar_bg.png");
        sidebarBgInfo = createTexture("core/assets/textures/gameScreen/sidebar_bg_info.png");
        sidebarBgBuild = createTexture("core/assets/textures/gameScreen/sidebar_bg_build.png");
        sidebarBgMenu = createTexture("core/assets/textures/gameScreen/sidebar_bg_menu.png");
        sidebarBuildInfoBg = createTexture("core/assets/textures/gameScreen/sidebar_building_info_bg.png");
        // SIDEBAR MENU
        sidebarMenuInfo = createTexture("core/assets/textures/gameScreen/sidebar_menu_info.png");
        sidebarMenuBuild = createTexture("core/assets/textures/gameScreen/sidebar_menu_build.png");
        sidebarMenuMenu = createTexture("core/assets/textures/gameScreen/sidebar_menu_menu.png");
        sidebarMenuInfoClicked = createTexture("core/assets/textures/gameScreen/sidebar_menu_info_clicked.png");
        sidebarMenuBuildClicked = createTexture("core/assets/textures/gameScreen/sidebar_menu_build_clicked.png");
        sidebarMenuMenuClicked = createTexture("core/assets/textures/gameScreen/sidebar_menu_menu_clicked.png");
        // SIDEBAR BUILD
        sidebarBuildManufacturers = createTexture("core/assets/textures/gameScreen/sidebar_build_manufacturers.png");
        sidebarBuildWarehouses = createTexture("core/assets/textures/gameScreen/sidebar_build_warehouses.png");
        sidebarBuildDecorations = createTexture("core/assets/textures/gameScreen/sidebar_build_decorations.png");
        sidebarBuildOther = createTexture("core/assets/textures/gameScreen/sidebar_build_other.png");
        sidebarBuildRotate = createTexture("core/assets/textures/gameScreen/sidebar_build_rotate.png");
        sidebarBuildBack = createTexture("core/assets/textures/gameScreen/sidebar_build_back.png");
        sidebarBuildCancel = createTexture("core/assets/textures/gameScreen/sidebar_build_cancel.png");
        // SIDEBAR GAME MENU
        sidebarMenuNew = createTexture("core/assets/textures/gameScreen/sidebar_menu_new.png");
        sidebarMenuLoad = createTexture("core/assets/textures/gameScreen/sidebar_menu_load.png");
        sidebarMenuSave = createTexture("core/assets/textures/gameScreen/sidebar_menu_save.png");
        sidebarMenuQuit = createTexture("core/assets/textures/gameScreen/sidebar_menu_quit.png");
        sidebarMenuCredits = createTexture("core/assets/textures/gameScreen/sidebar_menu_credits.png");
        sidebarMenuSettings = createTexture("core/assets/textures/gameScreen/sidebar_menu_settings.png");
        sidebarMenuInstructions = createTexture("core/assets/textures/gameScreen/sidebar_menu_instructions.png");
        // SIDEBAR BUILDING INFO
        sidebarBuildInfoBg2 = createTexture("core/assets/textures/gameScreen/sidebar_building_info_bg_bk.png");
        sidebarBuildInfoInfo = createTexture("core/assets/textures/gameScreen/sidebar_buildinfo_info.png");
        sidebarBuildInfoInfoLong = createTexture("core/assets/textures/gameScreen/sidebar_buildinfo_info_long.png");
        sidebarBuildInfoCost = createTexture("core/assets/textures/gameScreen/sidebar_buildinfo_cost.png");
        sidebarBuildInfoCostLong= createTexture("core/assets/textures/gameScreen/sidebar_buildinfo_cost_long.png");
        sidebarBuildInfoProfit = createTexture("core/assets/textures/gameScreen/sidebar_buildinfo_profit.png");
        sidebarBuildInfoCapacity = createTexture("core/assets/textures/gameScreen/sidebar_buildinfo_capacity.png");
        sidebarBuildInfoPlus = createTexture("core/assets/textures/gameScreen/sidebar_buildinfo_plus.png");
        sidebarBuildInfoMinus = createTexture("core/assets/textures/gameScreen/sidebar_buildinfo_minus.png");
        sidebarBuildInfoDestroy = createTexture("core/assets/textures/gameScreen/sidebar_buildinfo_destroy.png");
        sidebarBuildInfoRepair = createTexture("core/assets/textures/gameScreen/sidebar_buildinfo_repair.png");
        sidebarBuildInfoCancel = createTexture("core/assets/textures/gameScreen/sidebar_bottom_cancel.png");
        // RESOURCES_BAR
        resourcesBarBg = createTexture("core/assets/textures/gameScreen/resourcesbar_bg.png");
        resourcesBarPause = createTexture("core/assets/textures/gameScreen/resourcebar_pause.png");
        resourcesBarResume = createTexture("core/assets/textures/gameScreen/resourcebar_resume.png");
        resourcesBarSpeedNormal = createTexture("core/assets/textures/gameScreen/resourcesbar_speed_normal.png");
        resourcesBarSpeedFast = createTexture("core/assets/textures/gameScreen/resourcesbar_speed_fast.png");
        // RESOURCES
        resourcesFood = createTexture("core/assets/textures/gameScreen/resources_food.png");
        resourcesWood = createTexture("core/assets/textures/gameScreen/resources_wood.png");
        resourcesRock = createTexture("core/assets/textures/gameScreen/resources_rock.png");
        resourcesGold = createTexture("core/assets/textures/gameScreen/resources_gold.png");
        resourcesPeople = createTexture("core/assets/textures/gameScreen/resources_people.png");
        resourcesFoodBk = createTexture("core/assets/textures/gameScreen/resources_food_bk.png");
        resourcesWoodBk = createTexture("core/assets/textures/gameScreen/resources_wood_bk.png");
        resourcesRockBk = createTexture("core/assets/textures/gameScreen/resources_rock_bk.png");
        resourcesGoldBk = createTexture("core/assets/textures/gameScreen/resources_gold_bk.png");
        resourcesPeopleBk = createTexture("core/assets/textures/gameScreen/resources_people_bk.png");
        // OVERLAYS
        pausedOverlay = createTexture("core/assets/textures/gameScreen/paused_overlay.png");
        bottomBarBg = createTexture("core/assets/textures/gameScreen/bottombar_bg.png");
        // FULLSCREEN
        screenCredits = createTexture("core/assets/textures/screen_credits.png");
        screenInstructions = createTexture("core/assets/textures/screen_instructions.png");
        // MESSAGES
        messageOk = createTexture("core/assets/textures/message_ok.png");
        messageCancel = createTexture("core/assets/textures/message_cancel.png");
        // RESOURCES IMAGES
        tree = createTexture("core/assets/resources/pine-half06.png");
        stone = createTexture("core/assets/textures/buildings/rocks.png");
        // SETTINGS
        settingsSliderBg = createTexture("core/assets/textures/settings/slider_bg.png");
        settingsSliderKnob = createTexture("core/assets/textures/settings/slider_knob.png");
        settingsRes720p = createTexture("core/assets/textures/settings/res_720p.png");
        settingsRes1080p = createTexture("core/assets/textures/settings/res_1080p.png");
        settingsResFull = createTexture("core/assets/textures/settings/res_full.png");
        goBack = createTexture("core/assets/textures/settings/go_back.png");
        // INSTRUCTIONS
        screenInstructions1 = createTexture("core/assets/textures/instructions/instructions_01.png");
        screenInstructions2 = createTexture("core/assets/textures/instructions/instructions_02.png");
        screenInstructions3 = createTexture("core/assets/textures/instructions/instructions_03.png");
        screenInstructions4 = createTexture("core/assets/textures/instructions/instructions_04.png");
        screenInstructions5 = createTexture("core/assets/textures/instructions/instructions_05.png");
        screenInstructionsBack = createTexture("core/assets/textures/instructions/instructions_back.png");
        screenInstructionsNext = createTexture("core/assets/textures/instructions/instructions_next.png");
        // http://opengameart.org/content/outdoor-ambiance CC3.0
        bgSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/Outdoor_ambiance.mp3"));
        hit = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/hit.wav"));
        demolition = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/demolition.wav"));
    }

    private static Texture createTexture(String path) {
        Texture texture = new Texture(Gdx.files.internal(path));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return texture;
    }

    public static void loadSounds() {
        bgSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/Outdoor_ambiance.mp3"));
        hit = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/hit.wav"));
        demolition = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/demolition.wav"));
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
            SpriteDrawable buildingBg = new SpriteDrawable(new Sprite(createTexture(backgroundPath)));
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

    public static BitmapFont makeFontUltraLight(int fontSize, final Color fontColor) {
        return makeFont(ULTRALIGHT_FONT_PATH, fontSize, fontColor);
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
