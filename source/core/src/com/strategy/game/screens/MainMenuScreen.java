package com.strategy.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.strategy.game.StrategyGame;
import com.strategy.game.Utils;

/**
 * The main menu screen
 * TODO: implement UI using Scene2d.ui
 */
public class MainMenuScreen implements Screen{

    public class MenuButton extends ImageButton {

        public MenuButton(String texturePath, boolean paddingBottom) {
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

            if (paddingBottom)
                this.padBottom(Gdx.graphics.getHeight() * 0.025f);
        }

    }

    private final StrategyGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture menubk = new Texture(Gdx.files.internal("core/assets/MainMenuScreenTextures/menu.png"));
    private Stage stage;
    private Stage backStage;

    public MainMenuScreen(final StrategyGame game) {
        this.game = game;
        this.camera = new OrthographicCamera(Utils.DEFAULT_WIDTH, Utils.DEFAULT_HEIGHT);
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera.setToOrtho(false, 2*Utils.DEFAULT_WIDTH, 2*Utils.DEFAULT_HEIGHT);

        setupStage();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
//        batch.draw(menubk, 0, 0);
        font.draw(batch, "Welcome to the game!",
                Utils.DEFAULT_WIDTH/2,
                Utils.DEFAULT_HEIGHT/2);
        font.draw(batch, "fps:"+Gdx.graphics.getFramesPerSecond(),
                20, 30);

        font.draw(batch, "Press ENTER to start the game.",
                Utils.DEFAULT_WIDTH/2,
                Utils.DEFAULT_HEIGHT/2+30);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameScreen(game));
            dispose();
            System.out.println("Pressed enter!");
        }

//        stage.setDebugAll(true); // For debug purpose

        backStage.act(delta);
        stage.act(delta);
        backStage.getViewport().apply();
        backStage.draw();
        stage.getViewport().apply();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        backStage.getViewport().update(width, height, true);
        stage.getViewport().update(width, height, true);
//        camera.viewportWidth = width;
//        camera.viewportHeight = height;
//        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void setupStage() {
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.backStage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Group group = new Group();

        Gdx.input.setInputProcessor(stage);
        Image background = new Image(menubk);

//        group.addActor(background);
//        backStage.addActor(background);

        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backStage.addActor(background);

        Table buttons = new Table();
        stage.addActor(buttons);
        buttons.setSize(Gdx.graphics.getWidth() * 0.25f, Gdx.graphics.getHeight() * 0.5f);
        buttons.setPosition(Gdx.graphics.getWidth() * 0.7f, Gdx.graphics.getHeight() * 0.05f);

        // NEW GAME BUTTON
        MenuButton newGameButton = new MenuButton("core/assets/MainMenuScreenTextures/newgame.png", true);
        newGameButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                game.setScreen(new GameScreen(game));
                dispose();
                return false;
            }
        });
        buttons.addActor(newGameButton);
        setSizeRelative(newGameButton, 1f, 0.33f);
        setPositionRelative(newGameButton, 0f, 0.8f, false, true);

        //LOAD GAME BUTTON
        MenuButton loadGameButton = new MenuButton("core/assets/MainMenuScreenTextures/loadgame.png", true);
        loadGameButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
//                game.setScreen(new GameScreen(game));
//                dispose();
                return false;
            }
        });
        buttons.addActor(loadGameButton);
        setSizeRelative(loadGameButton, 1f, 0.33f);
        setPositionRelative(loadGameButton, 0f, 0.5f, false, true);

        // SETTINGS BUTTON
        MenuButton settingsButton = new MenuButton("core/assets/MainMenuScreenTextures/settings.png", false);
        settingsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
//                game.setScreen(new GameScreen(game));
//                dispose();
                return false;
            }
        });
        buttons.addActor(settingsButton);
        setSizeRelative(settingsButton, 1f, 0.33f);
        setPositionRelative(settingsButton, 0f, 0.2f, false, true);

//        setupTest();
    }

    private void setupTest() {
        Table building = new Table();
        stage.addActor(building);
        building.setSize(Gdx.graphics.getWidth() * 0.15f, Gdx.graphics.getWidth() * 0.15f);
        building.setPosition(0, 0);

        SpriteDrawable buildingBg = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("core/assets/test.jpg"))));
        building.setBackground(buildingBg);

        BitmapFont font = new BitmapFont();
        Color color = new Color(0, 0, 0, 1);
        Label.LabelStyle style = new Label.LabelStyle(font, color);


        // BUILDING IMAGE
        Image buildingImage = new Image(new Texture("core/assets/house1.png"));
        building.addActor(buildingImage);
        setSizeRelative(buildingImage, 0.4f, 0.4f);
        setPositionRelative(buildingImage, 0.05f, 0.55f);


        // BUILDING DATA
        Group buildingData = new Group();
        building.addActor(buildingData);
        setSizeRelative(buildingData, 0.5f, 0.5f);
        setPositionRelative(buildingData, 0.5f, 0.5f);

        Label buildingName = new Label("House", style);
        buildingData.addActor(buildingName);
        setPositionRelative(buildingName, 0.1f, 0.75f, false, true);

        Label buildingLife = new Label("Life: 50 / 50", style);
        buildingData.addActor(buildingLife);
        setPositionRelative(buildingLife, 0.1f, 0.5f, false, true);

        Label buildingWorkers = new Label("Workers: 4 / 5", style);
        buildingData.addActor(buildingWorkers);
        setPositionRelative(buildingWorkers, 0.1f, 0.25f, false, true);


        // BUILDING RESOURCES
        Group buildingResources = new Group();
        building.addActor(buildingResources);
        setPositionRelative(buildingResources, 0f, 0f);
        setSizeRelative(buildingResources, 1f, 0.5f);

        float margin = 0.05f;
        float COL1 = margin;
        float COL2 = margin + 1f / 6f;
        float COL3 = margin + 2f / 6f;
        float COL4 = margin + 3f / 6f;
        float COL5 = margin + 4f / 6f;
        float COL6 = margin + 5f / 6f;

        // ROW 1
        Group rowTop = new Group();
        buildingResources.addActor(rowTop);
        setPositionRelative(rowTop, 0f, 2f / 3f);
        setSizeRelative(rowTop, 1f, 1f / 3f);

//        Label resources = new Label("Resources:", style);
//        rowTop.addActor(resources);
//        setPositionRelative(resources, COL1, 0.5f, false, true);

        Label resFood = new Label("Food", style);
        rowTop.addActor(resFood);
        setPositionRelative(resFood, COL2, 0.5f, true, true);

        Label resGold = new Label("Gold", style);
        rowTop.addActor(resGold);
        setPositionRelative(resGold, COL3, 0.5f, true, true);

        Label resWood = new Label("Wood", style);
        rowTop.addActor(resWood);
        setPositionRelative(resWood, COL4, 0.5f, true, true);

        Label resRock = new Label("Rock", style);
        rowTop.addActor(resRock);
        setPositionRelative(resRock, COL5, 0.5f, true, true);

        Label resPeople = new Label("People", style);
        rowTop.addActor(resPeople);
        setPositionRelative(resPeople, COL6, 0.5f, true, true);

        // ROW 2
        Group rowMiddle = new Group();
        buildingResources.addActor(rowMiddle);
        setPositionRelative(rowMiddle, 0f, 1f / 3f);
        setSizeRelative(rowMiddle, 1f, 1f / 3f);

        Label cost = new Label("Cost:", style);
        rowMiddle.addActor(cost);
        setPositionRelative(cost, COL1, 0.5f, false, true);

        Label costFood = new Label("0", style);
        rowMiddle.addActor(costFood);
        setPositionRelative(costFood, COL2, 0.5f, true, true);

        Label costGold = new Label("0", style);
        rowMiddle.addActor(costGold);
        setPositionRelative(costGold, COL3, 0.5f, true, true);

        Label costWood = new Label("50", style);
        rowMiddle.addActor(costWood);
        setPositionRelative(costWood, COL4, 0.5f, true, true);

        Label costRock = new Label("50", style);
        rowMiddle.addActor(costRock);
        setPositionRelative(costRock, COL5, 0.5f, true, true);

        Label costPeople = new Label("0", style);
        rowMiddle.addActor(costPeople);
        setPositionRelative(costPeople, COL6, 0.5f, true, true);

        // ROW BOTTOM
        Group rowBottom = new Group();
        buildingResources.addActor(rowBottom);
        setPositionRelative(rowBottom, 0f, 0f);
        setSizeRelative(rowBottom, 1f, 1f / 3f);

        Label profit = new Label("Profit:", style);
        rowBottom.addActor(profit);
        setPositionRelative(profit, COL1, 0.5f, false, true);

        Label proFood = new Label("0", style);
        rowBottom.addActor(proFood);
        setPositionRelative(proFood, COL2, 0.5f, true, true);

        Label proGold = new Label("0", style);
        rowBottom.addActor(proGold);
        setPositionRelative(proGold, COL3, 0.5f, true, true);

        Label proWood = new Label("0", style);
        rowBottom.addActor(proWood);
        setPositionRelative(proWood, COL4, 0.5f, true, true);

        Label proRock = new Label("0", style);
        rowBottom.addActor(proRock);
        setPositionRelative(proRock, COL5, 0.5f, true, true);

        Label proPeople = new Label("10", style);
        rowBottom.addActor(proPeople);
        setPositionRelative(proPeople, COL6, 0.5f, true, true);


    }

    /**
     * Sets the position of the bottom-left corner of an Actor according to the position of its parent.
     * @param actor: The Actor which position is modified
     * @param x: The position relative and proportional to the parent on the x axis. E.g.: 0.5f will set the position of
     *         actor in the middle of its parent.
     * @param y: The position relative and proportional to the parent on the y axis. E.g.: 0.5f will set the position of
     *         actor in the middle of its parent.
     */
    private void setPositionRelative(final Actor actor, float x, float y) {
        if (actor.hasParent()) {
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
    private void setPositionRelative(final Actor actor, float x, float y, boolean centerX, boolean centerY) {
        if (actor.hasParent()) {
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
    private void setSizeRelative(final Actor actor, float width, float height) {
        if (actor.hasParent()) {
            final Actor parent = actor.getParent();
            actor.setSize(parent.getWidth() * width, parent.getHeight() * height);
        }
    }
}
