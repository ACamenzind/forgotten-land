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
import com.strategy.game.*;
import com.strategy.game.screens.sidebar.Sidebar;

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
        Assets.load();
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
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            if(NewUtils.HDPI) {
                Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth()/2 - 320, Gdx.graphics.getHeight()/2 - 180);
            } else {
                Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth() - 320, Gdx.graphics.getHeight() - 180);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            if (NewUtils.HDPI) {
                Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth()/2 + 320, Gdx.graphics.getHeight()/2 + 180);
            } else {
                Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth() + 320, Gdx.graphics.getHeight() + 180);
            }
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
        this.backStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Gdx.input.setInputProcessor(stage);
        Image background = new Image(menubk);

        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backStage.addActor(background);

        Table buttons = new Table();
        stage.addActor(buttons);
        buttons.setSize(Gdx.graphics.getWidth() * 0.25f, Gdx.graphics.getHeight() * 0.525f);
        buttons.setPosition(Gdx.graphics.getWidth() * 0.7f, Gdx.graphics.getHeight() * 0.05f);

        float buttonWidth = Gdx.graphics.getWidth() * 0.25f;
        float buttonHeight = Gdx.graphics.getHeight() * 0.125f;
        float buttonHeightRelative = buttonHeight / buttons.getHeight();
        float padding = (1f - buttonHeightRelative * 4f) / 3;

        // NEW GAME BUTTON
        GameButton newGameButton = new GameButton(Assets.mainMenuNewGame);
        newGameButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                dispose();
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                return false;
            }
        });
        buttons.addActor(newGameButton);
        newGameButton.setSize(buttonWidth, buttonHeight);
        Assets.setPositionRelative(newGameButton, 0f, 1f - buttonHeightRelative);

        // INSTRUCTIONS BUTTON
        GameButton instructionsButton = new GameButton(Assets.mainMenuInstructions);
        instructionsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(new Instructions(game));
                return false;
            }
        });
        buttons.addActor(instructionsButton);
        instructionsButton.setSize(buttonWidth, buttonHeight);
        Assets.setPositionRelative(instructionsButton, 0f, (buttonHeightRelative + padding) * 2);

        // SETTINGS BUTTON
        GameButton settingsButton = new GameButton(Assets.mainMenuSettings);
        settingsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(new Settings(game));
                return false;
            }
        });
        buttons.addActor(settingsButton);
        settingsButton.setSize(buttonWidth, buttonHeight);
        Assets.setPositionRelative(settingsButton, 0f, buttonHeightRelative + padding);

        // CREDITS BUTTON
        GameButton creditsButton = new GameButton(Assets.mainMenuCredits);
        creditsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(new FullScreen(game, Assets.screenCredits));
                return false;
            }
        });
        buttons.addActor(creditsButton);
        creditsButton.setSize(buttonWidth, buttonHeight);
//        Assets.setPositionRelative(creditsButton, 0f, 0f);
    }

    public StrategyGame getGame() {
        return game;
    }

    public Stage getStage() {
        return stage;
    }

}
