package com.strategy.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
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
    private Texture menubk = new Texture(Gdx.files.internal("core/assets/MainMenuScreenTextures/menu.png"));
    private Stage stage;

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
        batch.draw(menubk, 0, 0);
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

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
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

    }

    private void setCursorOverButton(Button button) {
        button.addListener(new InputListener() {
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
        button.addListener(new InputListener() {
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        });
    }

    private void setupStage() {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        VerticalGroup buttons = new VerticalGroup();
        stage.addActor(buttons);
        buttons.setPosition(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.55f);

        SpriteDrawable newGameSprite = new SpriteDrawable(new Sprite(
                new Texture(Gdx.files.internal("core/assets/MainMenuScreenTextures/newgame.png"))));
        ImageButton newGameButton = new ImageButton(newGameSprite);
        newGameButton.padBottom(Gdx.graphics.getHeight() * 0.05f);
        newGameButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                game.setScreen(new GameScreen(game));
                dispose();
                return false;
            }
        });
        setCursorOverButton(newGameButton);
        buttons.addActor(newGameButton);

        SpriteDrawable loadGameSprite = new SpriteDrawable(new Sprite(
                new Texture(Gdx.files.internal("core/assets/MainMenuScreenTextures/loadgame.png"))));
        ImageButton loadGameButton = new ImageButton(loadGameSprite);
        loadGameButton.padBottom(Gdx.graphics.getHeight() * 0.05f);
        loadGameButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                game.setScreen(new GameScreen(game));
//                dispose();
//                System.out.println("Pressed enter!");
                return false;
            }
        });
        setCursorOverButton(loadGameButton);
        buttons.addActor(loadGameButton);

        SpriteDrawable settingsSprite = new SpriteDrawable(new Sprite(
                new Texture(Gdx.files.internal("core/assets/MainMenuScreenTextures/settings.png"))));
        ImageButton settingsButton = new ImageButton(settingsSprite);
        settingsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                game.setScreen(new GameScreen(game));
//                dispose();
//                System.out.println("Pressed enter!");
                return false;
            }
        });
        setCursorOverButton(settingsButton);
        buttons.addActor(settingsButton);
    }
}
