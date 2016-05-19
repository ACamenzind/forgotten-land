package com.strategy.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.Utils;
import com.strategy.game.world.ResourceHandler;
import com.strategy.game.world.World;

/**
 * Created by Amedeo on 17/05/16.
 */
public class ResourcesBar extends Table implements Display {

    private Stage stage;
    private World world;
    private Table resources = new Table();

    private static final float CELL_SIZE = 75; //TODO: make it proportional

    private static final Label food = Assets.makeLabel("Food: ", Utils.FONT_MEDIUM_BLACK);
    private static final Label wood = Assets.makeLabel("Wood: ", Utils.FONT_MEDIUM_BLACK);
    private static final Label gold = Assets.makeLabel("Gold: ", Utils.FONT_MEDIUM_BLACK);
    private static final Label rock = Assets.makeLabel("Minerals: ", Utils.FONT_MEDIUM_BLACK);
    private static final Label people = Assets.makeLabel("People: ", Utils.FONT_MEDIUM_BLACK);

    private Label foodCount = Assets.makeLabel("", Utils.FONT_MEDIUM_BLACK);
    private Label woodCount = Assets.makeLabel("", Utils.FONT_MEDIUM_BLACK);
    private Label goldCount = Assets.makeLabel("", Utils.FONT_MEDIUM_BLACK);
    private Label rockCount = Assets.makeLabel("", Utils.FONT_MEDIUM_BLACK);
    private Label peopleCount = Assets.makeLabel("", Utils.FONT_MEDIUM_BLACK);

    private GameButton pause = new GameButton(Assets.resourcesBarPause, Assets.resourcesBarResume, Assets.resourcesBarResume);

    public ResourcesBar(final Stage stage, final World world) {
        this.stage = stage;
        this.stage.addActor(this);
        this.world = world;

        Assets.setBackground(this, Assets.resourcesBarBackground);

        resources.add(food);
        Cell foodCountCell = resources.add(foodCount).width(cell_size());
        resources.add(wood);
        Cell woodCountCell = resources.add(woodCount).width(cell_size());
        resources.add(rock);
        Cell rockCountCell = resources.add(rockCount).width(cell_size());
        resources.add(gold);
        Cell goldCountCell = resources.add(goldCount).width(cell_size());
        resources.add(people);
        resources.add(peopleCount);

        addActor(resources);

        pause.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                world.toggleRunning();
                world.getGameScreen().toggleGamePaused();
                return false;
            }
        });
        addActor(pause);

        update();
        updatePosition();
        foodCountCell.width(cell_size());
        woodCountCell.width(cell_size());
        rockCountCell.width(cell_size());
        goldCountCell.width(cell_size());
    }

    @Override
    public void update() {
//        setSize(stage.getWidth() * 0.85f, stage.getHeight() * 0.05f);
//        setPosition(0, stage.getHeight() * 0.95f);
//
//        Assets.setPositionRelative(resources, 0.5f, 0.5f, true, true);

        String newFoodCount = "" + world.getResourceHandler().getTotalResources().food;
        String newWoodCount = "" + world.getResourceHandler().getTotalResources().wood;
        String newRockCount = "" + world.getResourceHandler().getTotalResources().rock;
        String newGoldCount = "" + world.getResourceHandler().getTotalResources().gold;
        String newPeopleCount = "" + world.getResourceHandler().getTotalResources().people;

        ResourceHandler resourceHandler = world.getResourceHandler();

        if (resourceHandler.getTotalResources().food < resourceHandler.getMaximumResources().food)
            foodCount.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_BLACK));
        else
            foodCount.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_RED));
        foodCount.setText(newFoodCount);

        if (resourceHandler.getTotalResources().wood < resourceHandler.getMaximumResources().wood)
            woodCount.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_BLACK));
        else
            woodCount.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_RED));
        woodCount.setText(newWoodCount);

        if (resourceHandler.getTotalResources().rock < resourceHandler.getMaximumResources().rock)
            rockCount.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_BLACK));
        else
            rockCount.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_RED));
        rockCount.setText(newRockCount);

        if (resourceHandler.getTotalResources().gold < resourceHandler.getMaximumResources().gold)
            goldCount.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_BLACK));
        else
            goldCount.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_RED));
        goldCount.setText(newGoldCount);

        if (resourceHandler.getTotalResources().people < resourceHandler.getMaximumResources().people)
            peopleCount.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_BLACK));
        else
            peopleCount.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_RED));
        peopleCount.setText(newPeopleCount);

//        Assets.setSizeRelative(pause, 0.075f, 0.6f);
//        Assets.setPositionRelative(pause, 0.0125f, 0.5f, false, true);
//        updatePosition();
    }

    @Override
    public void updatePosition() {
        setSize(stage.getWidth() * 0.85f, stage.getHeight() * 0.05f);
        setPosition(0, stage.getHeight() * 0.95f);

        Assets.setPositionRelative(resources, 0.5f, 0.5f, true, true);
        Assets.setSizeRelative(pause, 0.075f, 0.6f);
        Assets.setPositionRelative(pause, 0.0125f, 0.5f, false, true);
    }

    private float cell_size() {
        return getWidth() * 0.05f;
    }
}
