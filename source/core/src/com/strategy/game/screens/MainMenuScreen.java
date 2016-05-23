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
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.StrategyGame;
import com.strategy.game.Utils;

/**
 * The main menu screen
 * TODO: implement UI using Scene2d.ui
 */
public class MainMenuScreen implements Screen{
    private final StrategyGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture menubk = new Texture(Gdx.files.internal("core/assets/textures/mainMenuScreen/menu.png"));
    private Stage stage;
    private Stage backStage;

    public MainMenuScreen(final StrategyGame game) {
        this.game = game;
        this.camera = new OrthographicCamera(Utils.DEFAULT_WIDTH, Utils.DEFAULT_HEIGHT);
        this.batch = game.getBatch();
        this.font = game.getFont();
        menubk.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
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

        backStage.act(delta);
        stage.act(delta);
        backStage.getViewport().apply();
        backStage.draw();
//        stage.setDebugAll(true); // For debug purpose
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
        Texture newGameButtonT = new Texture(Gdx.files.internal("core/assets/textures/mainMenuScreen/newgame.png"));
        newGameButtonT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        GameButton newGameButton = new GameButton(newGameButtonT);
        newGameButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                dispose();
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                return false;
            }
        });
        buttons.addActor(newGameButton);
        Assets.setSizeRelative(newGameButton, 1f, 0.25f);
        Assets.setPositionRelative(newGameButton, 0f, 0.8f, false, true);

        //LOAD GAME BUTTON
        Texture loadGameButtonT = new Texture(Gdx.files.internal("core/assets/textures/mainMenuScreen/loadgame.png"));
        loadGameButtonT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        GameButton loadGameButton = new GameButton(loadGameButtonT);
        loadGameButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
//                game.setScreen(new GameScreen(game));
//                dispose();
                return false;
            }
        });
        buttons.addActor(loadGameButton);
        Assets.setSizeRelative(loadGameButton, 1f, 0.25f);
        Assets.setPositionRelative(loadGameButton, 0f, 0.5f, false, true);

        // SETTINGS BUTTON
        Texture settingsButtonT = new Texture(Gdx.files.internal("core/assets/textures/mainMenuScreen/settings.png"));
        settingsButtonT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        GameButton settingsButton = new GameButton(settingsButtonT);
        settingsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
//                game.setScreen(new GameScreen(game));
//                dispose();
                return false;
            }
        });
        buttons.addActor(settingsButton);
        Assets.setSizeRelative(settingsButton, 1f, 0.25f);
        Assets.setPositionRelative(settingsButton, 0f, 0.2f, false, true);
    }

}
