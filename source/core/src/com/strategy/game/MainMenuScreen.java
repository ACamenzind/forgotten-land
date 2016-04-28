package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * The main menu screen
 * TODO: implement UI using Scene2d.ui
 */
public class MainMenuScreen implements Screen{

    private final StrategyGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture menubk = new Texture(Gdx.files.internal("core/assets/Buttons/menu.png"));
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

    private void setupStage() {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        VerticalGroup buttons = new VerticalGroup();
        stage.addActor(buttons);
        buttons.setPosition(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.55f);

        SpriteDrawable newGameSprite = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("core/assets/Buttons/newgame.png"))));
        ImageButton newGameButton = new ImageButton(newGameSprite);
        newGameButton.padBottom(Gdx.graphics.getHeight() * 0.05f);
        newGameButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                dispose();
                System.out.println("Pressed enter!");
                return false;
            }
        });
        buttons.addActor(newGameButton);

        SpriteDrawable loadGameSprite = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("core/assets/Buttons/loadgame.png"))));
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
        buttons.addActor(loadGameButton);

        SpriteDrawable settingsSprite = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("core/assets/Buttons/settings.png"))));
        ImageButton settingsButton = new ImageButton(settingsSprite);
        settingsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                game.setScreen(new GameScreen(game));
//                dispose();
//                System.out.println("Pressed enter!");
                return false;
            }
        });
        buttons.addActor(settingsButton);
    }
}
