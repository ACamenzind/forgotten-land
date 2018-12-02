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

import java.util.HashMap;

/**
 * Loads the assets.
 * TODO: maybe add an AssetManager for better performance
 */
public final class Assets {
    private Assets(){}

    public static TiledMap map;
    // SOUNDS
    public static Sound bgSound, hit, demolition;
    private static final String DEFAULT_FONT_PATH = "core/assets/fonts/san_francisco_regular.ttf";
    // new Font
    private static final String ULTRALIGHT_FONT_PATH = "core/assets/fonts/san_francisco_ultralight.ttf";

    private static HashMap<String, Texture> textures = new HashMap<>();

    public static Texture getTexture(String name) {
        return textures.get(name);
    }

    private static void addTexture(String name, String path) {
        textures.put(name, createTexture(path));
    }

    public static void loadMap() {
        map = new TmxMapLoader().load("core/assets/maps/default.tmx");
    }

    public static void load() {
//        map = new TmxMapLoader().load("core/assets/maps/big_map.tmx");
        //TODO: add credit for CC3.0 sprites
//        map = new TmxMapLoader().load("core/assets/maps/default.tmx");

        // MAIN MENU
        addTexture("mainMenuNewGame", "core/assets/textures/mainMenuScreen/newgame.png");
        addTexture("mainMenuCredits", "core/assets/textures/mainMenuScreen/credits.png");
        addTexture("mainMenuSettings", "core/assets/textures/mainMenuScreen/settings.png");
        addTexture("mainMenuInstructions", "core/assets/textures/mainMenuScreen/instructions.png");
        // BUILDINGS
        addTexture("house1", "core/assets/textures/buildings/house1.png");
        addTexture("castle", "core/assets/textures/buildings/castle.png");
        addTexture("cows", "core/assets/textures/buildings/cows.png");
        addTexture("warehouse", "core/assets/textures/buildings/warehouse.png");
        addTexture("wheat", "core/assets/textures/buildings/wheat.png");
        addTexture("lumberjack", "core/assets/textures/buildings/lumberjack.png");
        addTexture("mine", "core/assets/textures/buildings/mine.png");
        addTexture("leftwall", "core/assets/textures/buildings/wall/Wall_L.png");
        addTexture("middlewall", "core/assets/textures/buildings/wall/Wall_M.png");
        addTexture("rightwall", "core/assets/textures/buildings/wall/Wall_R.png");
        addTexture("road", "core/assets/textures/terrains/road.png");

        addTexture("market", "core/assets/textures/buildings/market.png");

        addTexture("emptyTile", "core/assets/textures/terrains/empty.png");
        addTexture("influenceTile", "core/assets/textures/terrains/influence_tile.png");

        // SIDEBAR BACKGROUNDS
        addTexture("sidebarBg", "core/assets/textures/gameScreen/sidebar_bg.png");
        addTexture("sidebarBgInfo", "core/assets/textures/gameScreen/sidebar_bg_info.png");
        addTexture("sidebarBgBuild", "core/assets/textures/gameScreen/sidebar_bg_build.png");
        addTexture("sidebarBgMenu", "core/assets/textures/gameScreen/sidebar_bg_menu.png");
        addTexture("sidebarBuildInfoBg", "core/assets/textures/gameScreen/sidebar_building_info_bg.png");
        // SIDEBAR MENU
        addTexture("sidebarMenuInfo", "core/assets/textures/gameScreen/sidebar_menu_info.png");
        addTexture("sidebarMenuBuild", "core/assets/textures/gameScreen/sidebar_menu_build.png");
        addTexture("sidebarMenuMenu", "core/assets/textures/gameScreen/sidebar_menu_menu.png");
        addTexture("sidebarMenuInfoClicked", "core/assets/textures/gameScreen/sidebar_menu_info_clicked.png");
        addTexture("sidebarMenuBuildClicked", "core/assets/textures/gameScreen/sidebar_menu_build_clicked.png");
        addTexture("sidebarMenuMenuClicked", "core/assets/textures/gameScreen/sidebar_menu_menu_clicked.png");
        // SIDEBAR BUILD
        addTexture("sidebarBuildManufacturers", "core/assets/textures/gameScreen/sidebar_build_manufacturers.png");
        addTexture("sidebarBuildWarehouses", "core/assets/textures/gameScreen/sidebar_build_warehouses.png");
        addTexture("sidebarBuildDecorations", "core/assets/textures/gameScreen/sidebar_build_decorations.png");
        addTexture("sidebarBuildOther", "core/assets/textures/gameScreen/sidebar_build_other.png");
        addTexture("sidebarBuildRotate", "core/assets/textures/gameScreen/sidebar_build_rotate.png");
        addTexture("sidebarBuildBack", "core/assets/textures/gameScreen/sidebar_build_back.png");
        addTexture("sidebarBuildCancel", "core/assets/textures/gameScreen/sidebar_build_cancel.png");
        // SIDEBAR GAME MENU
        addTexture("sidebarMenuNew", "core/assets/textures/gameScreen/sidebar_menu_new.png");
        addTexture("sidebarMenuLoad", "core/assets/textures/gameScreen/sidebar_menu_load.png");
        addTexture("sidebarMenuSave", "core/assets/textures/gameScreen/sidebar_menu_save.png");
        addTexture("sidebarMenuQuit", "core/assets/textures/gameScreen/sidebar_menu_quit.png");
        addTexture("sidebarMenuCredits", "core/assets/textures/gameScreen/sidebar_menu_credits.png");
        addTexture("sidebarMenuSettings", "core/assets/textures/gameScreen/sidebar_menu_settings.png");
        addTexture("sidebarMenuInstructions", "core/assets/textures/gameScreen/sidebar_menu_instructions.png");
        // SIDEBAR BUILDING INFO
        addTexture("sidebarBuildInfoBg2", "core/assets/textures/gameScreen/sidebar_building_info_bg_bk.png");
        addTexture("sidebarBuildInfoInfo", "core/assets/textures/gameScreen/sidebar_buildinfo_info.png");
        addTexture("sidebarBuildInfoInfoLong", "core/assets/textures/gameScreen/sidebar_buildinfo_info_long.png");
        addTexture("sidebarBuildInfoCost", "core/assets/textures/gameScreen/sidebar_buildinfo_cost.png");
        addTexture("sidebarBuildInfoCostLong", "core/assets/textures/gameScreen/sidebar_buildinfo_cost_long.png");
        addTexture("sidebarBuildInfoProfit", "core/assets/textures/gameScreen/sidebar_buildinfo_profit.png");
        addTexture("sidebarBuildInfoCapacity", "core/assets/textures/gameScreen/sidebar_buildinfo_capacity.png");
        addTexture("sidebarBuildInfoPlus", "core/assets/textures/gameScreen/sidebar_buildinfo_plus.png");
        addTexture("sidebarBuildInfoMinus", "core/assets/textures/gameScreen/sidebar_buildinfo_minus.png");
        addTexture("sidebarBuildInfoDestroy", "core/assets/textures/gameScreen/sidebar_buildinfo_destroy.png");
        addTexture("sidebarBuildInfoRepair", "core/assets/textures/gameScreen/sidebar_buildinfo_repair.png");
        addTexture("sidebarBuildInfoCancel", "core/assets/textures/gameScreen/sidebar_bottom_cancel.png");
        // RESOURCES_BAR
        addTexture("resourcesBarBg", "core/assets/textures/gameScreen/resourcesbar_bg.png");
        addTexture("resourcesBarPause", "core/assets/textures/gameScreen/resourcebar_pause.png");
        addTexture("resourcesBarResume", "core/assets/textures/gameScreen/resourcebar_resume.png");
        addTexture("resourcesBarSpeedNormal", "core/assets/textures/gameScreen/resourcesbar_speed_normal.png");
        addTexture("resourcesBarSpeedFast", "core/assets/textures/gameScreen/resourcesbar_speed_fast.png");
        // RESOURCES
        addTexture("resourcesFood", "core/assets/textures/gameScreen/resources_food.png");
        addTexture("resourcesWood", "core/assets/textures/gameScreen/resources_wood.png");
        addTexture("resourcesRock", "core/assets/textures/gameScreen/resources_rock.png");
        addTexture("resourcesGold", "core/assets/textures/gameScreen/resources_gold.png");
        addTexture("resourcesPeople", "core/assets/textures/gameScreen/resources_people.png");
        addTexture("resourcesFoodBk", "core/assets/textures/gameScreen/resources_food_bk.png");
        addTexture("resourcesWoodBk", "core/assets/textures/gameScreen/resources_wood_bk.png");
        addTexture("resourcesRockBk", "core/assets/textures/gameScreen/resources_rock_bk.png");
        addTexture("resourcesGoldBk", "core/assets/textures/gameScreen/resources_gold_bk.png");
        addTexture("resourcesPeopleBk", "core/assets/textures/gameScreen/resources_people_bk.png");
        // OVERLAYS
        addTexture("pausedOverlay", "core/assets/textures/gameScreen/paused_overlay.png");
        addTexture("bottomBarBg", "core/assets/textures/gameScreen/bottombar_bg.png");
        // FULLSCREEN
        addTexture("screenCredits", "core/assets/textures/screen_credits.png");
        addTexture("screenInstructions", "core/assets/textures/screen_instructions.png");
        // MESSAGES
        addTexture("messageOk", "core/assets/textures/message_ok.png");
        addTexture("messageCancel", "core/assets/textures/message_cancel.png");
        // RESOURCES IMAGES
        addTexture("tree", "core/assets/resources/pine-half06.png");
        addTexture("stone", "core/assets/textures/buildings/rocks.png");
        // SETTINGS
        addTexture("settingsSliderBg", "core/assets/textures/settings/slider_bg.png");
        addTexture("settingsSliderKnob", "core/assets/textures/settings/slider_knob.png");
        addTexture("settingsRes720p", "core/assets/textures/settings/res_720p.png");
        addTexture("settingsRes1080p", "core/assets/textures/settings/res_1080p.png");
        addTexture("settingsResFull", "core/assets/textures/settings/res_full.png");
        addTexture("goBack", "core/assets/textures/settings/go_back.png");
        // INSTRUCTIONS
        addTexture("screenInstructions1", "core/assets/textures/instructions/instructions_01.png");
        addTexture("screenInstructions2", "core/assets/textures/instructions/instructions_02.png");
        addTexture("screenInstructions3", "core/assets/textures/instructions/instructions_03.png");
        addTexture("screenInstructions4", "core/assets/textures/instructions/instructions_04.png");
        addTexture("screenInstructions5", "core/assets/textures/instructions/instructions_05.png");
        addTexture("screenInstructionsBack", "core/assets/textures/instructions/instructions_back.png");
        addTexture("screenInstructionsNext", "core/assets/textures/instructions/instructions_next.png");
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
