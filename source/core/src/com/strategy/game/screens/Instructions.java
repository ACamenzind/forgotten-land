package com.strategy.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amedeo on 29/05/16.
 */
public class Instructions extends FullScreen {

    static private List<Texture> pages = new ArrayList<>();
    static{
        pages.add(Assets.getTexture("screenInstructions1"));
        pages.add(Assets.getTexture("screenInstructions2"));
        pages.add(Assets.getTexture("screenInstructions3"));
        pages.add(Assets.getTexture("screenInstructions4"));
        pages.add(Assets.getTexture("screenInstructions5"));
    }

    private static final float BUTTON_DIAMETER = 0.025f;


    private GameButton back;
    private GameButton next;
    private int currentPage = 0;

    public Instructions(final Stage screen,Runnable onClose) {
        super(screen, Assets.getTexture("screenInstructions1"), onClose);
        init();
    }

    public Instructions(final Stage stage) {
        super(stage, Assets.getTexture("screenInstructions1"),()->{});
        init();

    }

    private void navigateBack(){
        currentPage=Math.max(0,currentPage-1);
        updatePage();
    }

    private void navigateForward(){
        currentPage=Math.min(pages.size()-1,currentPage+1);
        updatePage();
    }

    private void updatePage(){
        Assets.setBackground(this,pages.get(currentPage));

        //next button
        if( pages.size()-1  == currentPage)
            next.setVisible(false);
        else
            next.setVisible(true);

        //back button
        if( 0  == currentPage )
            back.setVisible(false);
        else
            back.setVisible(true);
    }

    private void init(){
        back = new GameButton(Assets.getTexture("screenInstructionsBack"));
        next = new GameButton(Assets.getTexture("screenInstructionsNext"));

        back.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                navigateBack();
                return false;
            }
        });

        next.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                navigateForward();
                return false;
            }
        });

        placeButton(back,0.945f, 0.005f);
        placeButton(next,0.97f, 0.005f );
        updatePage();
    }

    private void placeButton(GameButton button, float x, float y){
        addActor(button);
        Assets.setSizeRelative(next, BUTTON_DIAMETER, BUTTON_DIAMETER * getWidth() / getHeight());
        Assets.setPositionRelative(next, x, y);

    }
}
