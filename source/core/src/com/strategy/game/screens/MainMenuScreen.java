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

        //stage.setDebugAll(true); // For debug purpose

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
//        buttons.setSize(Gdx.graphics.getWidth() * 0.25f, Gdx.graphics.getHeight() * 0.5f);
        stage.addActor(buttons);
        buttons.setPosition(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.3f);

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
        buttons.add(newGameButton);
        buttons.row();

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
        buttons.add(loadGameButton);
        buttons.row();

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
        buttons.add(settingsButton);

        //setupTest2();
    }

    private void setupTest2() {
        Table building = new Table();
        stage.addActor(building);
        building.setSize(Gdx.graphics.getWidth() * 0.15f, Gdx.graphics.getWidth() * 0.15f);
        building.setPosition(0, 0);

        BitmapFont font = new BitmapFont();
        Color color = new Color(0, 0, 0, 1);
        Label.LabelStyle style = new Label.LabelStyle(font, color);

        Image buildingImage = new Image(new Texture("core/assets/house1.png"));

        Table buildingStatus = new Table();
        Label buildingName = new Label("House", style);
        Label buildingLife = new Label("Life: 100/100", style);
        Label buildingWorkers = new Label("Workers: 4/5", style);

        buildingStatus.add(buildingName).left();
        buildingStatus.row();
        buildingStatus.add(buildingLife).left();
        buildingStatus.row();
        buildingStatus.add(buildingWorkers).left();

        Table firstRow = new Table();

        Cell buildingImageCell = firstRow.add(buildingImage);
        buildingImageCell.maxWidth(building.getWidth() / 2f);
        buildingImageCell.maxHeight(building.getHeight() / 2f);

        Cell buildingStatusCell = firstRow.add(buildingStatus);
        buildingStatusCell.maxHeight(firstRow.getHeight() / 2f);
        buildingStatusCell.left();

        Table buildingResources = new Table();

        Label resources = new Label("Resources:", style);
        Label resFood = new Label("Food", style);
        Label resGold = new Label("Gold", style);
        Label resWood = new Label("Wood", style);
        Label resRock = new Label("Rock", style);
        Label resPeople = new Label("People", style);

        Label cost = new Label("Cost:", style);
        Label costFood = new Label("0", style);
        Label costGold = new Label("0", style);
        Label costWood = new Label("50", style);
        Label costRock = new Label("50", style);
        Label costPeople = new Label("0", style);

        Label profit = new Label("Profit:", style);
        Label proFood = new Label("0", style);
        Label proGold = new Label("0", style);
        Label proWood = new Label("0", style);
        Label proRock = new Label("0", style);
        Label proPeople = new Label("10", style);

        buildingResources.add(resources);
        buildingResources.add(resFood);
        buildingResources.add(resGold);
        buildingResources.add(resWood);
        buildingResources.add(resRock);
        buildingResources.add(resPeople);

        buildingResources.row();

        buildingResources.add(cost);
        buildingResources.add(costFood);
        buildingResources.add(costGold);
        buildingResources.add(costWood);
        buildingResources.add(costRock);
        buildingResources.add(costPeople);

        buildingResources.row();

        buildingResources.add(profit);
        buildingResources.add(proFood);
        buildingResources.add(proGold);
        buildingResources.add(proWood);
        buildingResources.add(proRock);
        buildingResources.add(proPeople);

        Cell firstRowCell = building.add(firstRow);
        firstRowCell.expandX();
        firstRowCell.left();

        building.row();

        building.add(buildingResources);

        building.top();
        building.left();
    }
}
