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
	private BitmapFont font;
	private SoundManager soundManager;

	private static StrategyGame instance = null;
	public static StrategyGame instance() {
		if (instance == null) {
			instance = new StrategyGame();
		}
		return instance;
	}

	private StrategyGame() {
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
		soundManager = SoundManager.instance();

	}

	public SoundManager getSoundManager() {
		return soundManager;
	}

	@Override
	public void render () {
		super.render();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
	}
}
