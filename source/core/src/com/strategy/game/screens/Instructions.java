package com.strategy.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;

/**
 * Created by Amedeo on 29/05/16.
 */
public class Instructions extends FullScreen {

    enum Page { ONE, TWO, THREE, FOUR, FIVE }

    private GameButton back = new GameButton(Assets.screenInstructionsBack);
    private GameButton next = new GameButton(Assets.screenInstructionsNext);
    private Page currentPage = Page.ONE;

    public Instructions(final GameScreen screen) {
        super(screen, Assets.screenInstructions1);

        final Table instructions = this;

        back.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (currentPage == Page.TWO) {
                    currentPage = Page.ONE;
                    Assets.setBackground(instructions, Assets.screenInstructions1);
                    back.setVisible(false);
                }
                else if (currentPage == Page.THREE) {
                    currentPage = Page.TWO;
                    Assets.setBackground(instructions, Assets.screenInstructions2);
                }
                else if (currentPage == Page.FOUR) {
                    currentPage = Page.THREE;
                    Assets.setBackground(instructions, Assets.screenInstructions3);
                }
                else if (currentPage == Page.FIVE) {
                    currentPage = Page.FOUR;
                    Assets.setBackground(instructions, Assets.screenInstructions2);
                    next.setVisible(true);
                }
                return false;
            }
        });
        addActor(back);
        Assets.setSizeRelative(back, 0.025f, 0.025f * getWidth() / getHeight());
        Assets.setPositionRelative(back, 0.945f, 0.005f);
        back.setVisible(false);

        next.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (currentPage == Page.ONE) {
                    currentPage = Page.TWO;
                    Assets.setBackground(instructions, Assets.screenInstructions2);
                    back.setVisible(true);
                }
                else if (currentPage == Page.TWO) {
                    currentPage = Page.THREE;
                    Assets.setBackground(instructions, Assets.screenInstructions3);
                }
                else if (currentPage == Page.THREE) {
                    currentPage = Page.FOUR;
                    Assets.setBackground(instructions, Assets.screenInstructions4);
                }
                else if (currentPage == Page.FOUR) {
                    currentPage = Page.FIVE;
                    Assets.setBackground(instructions, Assets.screenInstructions5);
                    next.setVisible(false);
                }
                return false;
            }
        });
        addActor(next);
        Assets.setSizeRelative(next, 0.025f, 0.025f * getWidth() / getHeight());
        Assets.setPositionRelative(next, 0.97f, 0.005f);
    }

    public Instructions(Game game) {
        super(game, Assets.screenInstructions1);

        final Table instructions = this;

        back.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (currentPage == Page.TWO) {
                    currentPage = Page.ONE;
                    Assets.setBackground(instructions, Assets.screenInstructions1);
                    back.setVisible(false);
                }
                else if (currentPage == Page.THREE) {
                    currentPage = Page.TWO;
                    Assets.setBackground(instructions, Assets.screenInstructions2);
                }
                else if (currentPage == Page.FOUR) {
                    currentPage = Page.THREE;
                    Assets.setBackground(instructions, Assets.screenInstructions3);
                }
                else if (currentPage == Page.FIVE) {
                    currentPage = Page.FOUR;
                    Assets.setBackground(instructions, Assets.screenInstructions2);
                    next.setVisible(true);
                }
                return false;
            }
        });
        addActor(back);
        Assets.setSizeRelative(back, 0.025f, 0.025f * getWidth() / getHeight());
        Assets.setPositionRelative(back, 0.945f, 0.005f);
        back.setVisible(false);

        next.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (currentPage == Page.ONE) {
                    currentPage = Page.TWO;
                    Assets.setBackground(instructions, Assets.screenInstructions2);
                    back.setVisible(true);
                }
                else if (currentPage == Page.TWO) {
                    currentPage = Page.THREE;
                    Assets.setBackground(instructions, Assets.screenInstructions3);
                }
                else if (currentPage == Page.THREE) {
                    currentPage = Page.FOUR;
                    Assets.setBackground(instructions, Assets.screenInstructions4);
                }
                else if (currentPage == Page.FOUR) {
                    currentPage = Page.FIVE;
                    Assets.setBackground(instructions, Assets.screenInstructions5);
                    next.setVisible(false);
                }
                return false;
            }
        });
        addActor(next);
        Assets.setSizeRelative(next, 0.025f, 0.025f * getWidth() / getHeight());
        Assets.setPositionRelative(next, 0.97f, 0.005f);
    }
}
