package com.strategy.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.screens.sidebar.Display;
import com.strategy.game.world.ResourceHandler;
import com.strategy.game.world.World;

/**
 * Created by Amedeo on 17/05/16.
 */
public class ResourcesBar extends Table implements Display {

    private Stage stage;
    private World world;
    private Table resources = new Table();

    private static final Label food = Assets.makeLabel("Food: ", 14, Color.BLACK);
    private static final Label wood = Assets.makeLabel(" | Wood: ", 14, Color.BLACK);
    private static final Label gold = Assets.makeLabel(" | Gold: ", 14, Color.BLACK);
    private static final Label rock = Assets.makeLabel(" | Minerals: ", 14, Color.BLACK);
    private static final Label people = Assets.makeLabel(" | People: ", 14, Color.BLACK);

    private Label foodCount = Assets.makeLabel("TEST", 14, Color.BLACK);
    private Label woodCount = Assets.makeLabel("", 14, Color.BLACK);
    private Label goldCount = Assets.makeLabel("", 14, Color.BLACK);
    private Label rockCount = Assets.makeLabel("", 14, Color.BLACK);
    private Label peopleCount = Assets.makeLabel("", 14, Color.BLACK);

    public ResourcesBar(Stage stage, World world) {
        this.stage = stage;
        this.stage.addActor(this);
        this.world = world;

        Assets.setBackground(this, "core/assets/textures/gameScreen/resourcesbar_bg.png");

        resources.add(food);
        resources.add(foodCount);
        resources.add(wood);
        resources.add(woodCount);
        resources.add(rock);
        resources.add(rockCount);
        resources.add(gold);
        resources.add(goldCount);
        resources.add(people);
        resources.add(peopleCount);

        addActor(resources);

        updatePosition();
    }

    @Override
    public void updatePosition() {
        setSize(stage.getWidth() * 0.85f, stage.getHeight() * 0.05f);
        setPosition(0, stage.getHeight() * 0.95f);

        Assets.setPositionRelative(resources, 0.5f, 0.5f, true, true);

        String newFoodCount = "" + world.getResourceHandler().getTotalResources().food;
        String newWoodCount = "" + world.getResourceHandler().getTotalResources().wood;
        String newRockCount = "" + world.getResourceHandler().getTotalResources().rock;
        String newGoldCount = "" + world.getResourceHandler().getTotalResources().gold;
        String newPeopleCount = "" + world.getResourceHandler().getTotalResources().people;

        ResourceHandler resourceHandler = world.getResourceHandler();

        if (resourceHandler.getTotalResources().food < resourceHandler.getMaximumResources().food)
            foodCount.setStyle(Assets.makeLabelStyle(14, Color.BLACK));
        else
            foodCount.setStyle(Assets.makeLabelStyle(14, Color.RED));
        foodCount.setText(newFoodCount);

        if (resourceHandler.getTotalResources().wood < resourceHandler.getMaximumResources().wood)
            woodCount.setStyle(Assets.makeLabelStyle(14, Color.BLACK));
        else
            woodCount.setStyle(Assets.makeLabelStyle(14, Color.RED));
        woodCount.setText(newWoodCount);

        if (resourceHandler.getTotalResources().rock < resourceHandler.getMaximumResources().rock)
            rockCount.setStyle(Assets.makeLabelStyle(14, Color.BLACK));
        else
            rockCount.setStyle(Assets.makeLabelStyle(14, Color.RED));
        rockCount.setText(newRockCount);

        if (resourceHandler.getTotalResources().gold < resourceHandler.getMaximumResources().gold)
            goldCount.setStyle(Assets.makeLabelStyle(14, Color.BLACK));
        else
            goldCount.setStyle(Assets.makeLabelStyle(14, Color.RED));
        goldCount.setText(newGoldCount);

        if (resourceHandler.getTotalResources().people < resourceHandler.getMaximumResources().people)
            peopleCount.setStyle(Assets.makeLabelStyle(14, Color.BLACK));
        else
            peopleCount.setStyle(Assets.makeLabelStyle(14, Color.RED));
        peopleCount.setText(newPeopleCount);
    }
}
