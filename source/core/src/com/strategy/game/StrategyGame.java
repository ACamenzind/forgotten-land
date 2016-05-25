package com.strategy.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strategy.game.screens.MainMenuScreen;

/*
	The main game object, extends the Game class that easily allows
	multiple screens (e.g. main menu, game screen and so on).
 */

public class StrategyGame extends Game {
	private SpriteBatch batch;
	Texture img;
	private BitmapFont font;
	private SoundManager soundManager;


	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
		soundManager = new SoundManager();
//		img = new Texture("core/assets/badlogic.jpg");

	}

	public SoundManager getSoundManager() {
		return soundManager;
	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
		super.render();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
	}
}
