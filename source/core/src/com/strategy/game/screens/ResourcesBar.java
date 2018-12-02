package com.strategy.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.ResourceContainer;
import com.strategy.game.Utils;
import com.strategy.game.*;
import com.strategy.game.world.ResourceHandler;
import com.strategy.game.world.ResourceType;
import com.strategy.game.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Amedeo on 17/05/16.
 */
public class ResourcesBar extends Table implements Display, EventListener {

    private Stage stage;
    private ResourceHandler resourceHandler;
    private Table resourcesTable = new Table();

    Map<ResourceType, Label > resourceLabels;

    private GameButton pause = new GameButton(Assets.getTexture("resourcesBarPause"), Assets.getTexture("resourcesBarResume"), Assets.getTexture("resourcesBarResume"));
    private GameButton speed = new GameButton(Assets.getTexture("resourcesBarSpeedNormal"), Assets.getTexture("resourcesBarSpeedFast"), Assets.getTexture("resourcesBarSpeedFast"));

    public ResourcesBar(final Stage stage, final World world) {
        this.stage = stage;
        this.stage.addActor(this);
        resourceHandler = world.getResourceHandler();

        resourceLabels = new HashMap<>();

        Assets.setBackground(this, Assets.getTexture("resourcesBarBg"));

        pause.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                world.toggleRunning();
                world.getGameScreen().toggleGamePaused();
                return false;
            }
        });
        addActor(pause);

        speed.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                world.toggleGameSpeed();
                return false;
            }
        });
        addActor(speed);

        setUpTable();


    }

    private void setUpTable(){
       Map<ResourceType, Image> typeToImage = new HashMap<>();

       typeToImage.put(ResourceType.FOOD,  Assets.makeImage(Assets.getTexture("resourcesFood")));
       typeToImage.put(ResourceType.WOOD,  Assets.makeImage(Assets.getTexture("resourcesWood")));
       typeToImage.put(ResourceType.ROCK,  Assets.makeImage(Assets.getTexture("resourcesRock")));
       typeToImage.put(ResourceType.GOLD,  Assets.makeImage(Assets.getTexture("resourcesGold")));
       typeToImage.put(ResourceType.PEOPLE,  Assets.makeImage(Assets.getTexture("resourcesPeople")));

        //we need to resize the cells after all of them are in the table
        List<Cell> images = new ArrayList<>();
        List<Cell> labels = new ArrayList<>();

        for(ResourceType type: ResourceType.values()){
            Label label = Assets.makeLabel("", Utils.FONT_MEDIUM_WHITE);

            resourceLabels.put(type, label);

            Cell imageCell = resourcesTable.add(typeToImage.get(type));
            Cell resourceCell = resourcesTable.add(label);

            images.add(imageCell);
            labels.add(resourceCell);
        }
        addActor(resourcesTable);

        update();
        updatePosition();

        for(Cell c: images){
            c.width(getHeight() * 0.425f).padRight(cell_size() / 10f);
        }
        for(Cell c: labels){
            c.width(cell_size());
        }


    }

    @Override
    public void update() {

        for(ResourceType type: ResourceType.values()){
            Label label = resourceLabels.get(type);
            if(resourceHandler.isResourceInBounds(type)){
                label.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_WHITE));
            }else{
                label.setStyle(Assets.makeLabelStyle(Utils.FONT_MEDIUM_RED));
            }
            label.setText(resourceHandler.getTotalResources().get(type)+"");
        }

    }

    @Override
    public void updatePosition() {
        setSize(stage.getWidth() * 0.85f, stage.getHeight() * 0.05f);
        setPosition(0, stage.getHeight() * 0.95f);

        Assets.setPositionRelative(resourcesTable, 0.5f, 0.5f, true, true);
        Assets.setSizeRelative(pause, 0.064f, 1f);
        Assets.setPositionRelative(pause, 0.0125f, 0.5f, false, true);
        Assets.setSizeRelative(speed, 0.064f, 1f);
        Assets.setPositionRelative(speed, 0.09f, 0.5f, false, true);
    }

    private float cell_size() {
        return getWidth() * 0.05f;
    }

    @Override
    public void update(Events eventType) {
        switch (eventType) {
            case BUILDING_PLACED:
            case BUILDING_DESTROYED:
                update();
        }
    }
}

