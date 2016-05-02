package com.strategy.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.strategy.game.Assets;
import com.strategy.game.buildings.MapEntity;

/**
 * Class displaying building info in the bottom-left corner of the screen.
 *
 * Created by Amedeo on 01/05/16.
 */
public class BuildingInfo {
    private Stage stage;

    private MapEntity building;

    private final Table buildingData;

    private Image buildingImage;

    private final Group buildingStatus;
    private Label buildingName;
    private Label buildingLife;
    private Label buildingWorkers;

    private final Group buildingResources;
    private final Group rowTop;
    private final Label resources, resFood, resWood, resGold, resRock, resPeople;
    private final Group rowMiddle;
    private Label cost, costFood, costWood, costGold, costRock, costPeople;
    private final Group rowBottom;
    private Label profit, proFood, proWood, proGold, proRock, proPeople;

    // Positions on x-axis for the resources cost/profit table
    private static final float MARGIN = 0.05f;
    private static final float COLUMN_TITLES = MARGIN;
    private static final float COLUMN_FOOD = MARGIN + 1f / 6f;
    private static final float COLUMN_GOLD = MARGIN + 2f / 6f;
    private static final float COLUMN_WOOD = MARGIN + 3f / 6f;
    private static final float COLUMN_ROCK = MARGIN + 4f / 6f;
    private static final float COLUMN_PEOPLE = MARGIN + 5f / 6f;

    public BuildingInfo(Stage stage) {
        this(stage, null);
    }

    public BuildingInfo(Stage stage, MapEntity building) {
        this.stage = stage;

        // FONT
        BitmapFont font = Assets.fontGenerator("core/assets/fonts/times_new_roman.ttf", 12, Color.BLACK);
        Label.LabelStyle style = new Label.LabelStyle(font, font.getColor());


        // BUILDING DATA
        buildingData = new Table();
        this.stage.addActor(buildingData);

        SpriteDrawable buildingBg = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("core/assets/test.jpg"))));
        buildingData.setBackground(buildingBg);


        // BUILDING IMAGE
        buildingImage = new Image();
        buildingData.addActor(buildingImage);


        // BUILDING DATA
        buildingStatus = new Group();
        buildingData.addActor(buildingStatus);

        buildingName = new Label("", style);
        buildingStatus.addActor(buildingName);

        buildingLife = new Label("Life:", style);
        buildingStatus.addActor(buildingLife);

        buildingWorkers = new Label("Workers:", style);
        buildingStatus.addActor(buildingWorkers);


        // BUILDING RESOURCES
        buildingResources = new Group();
        buildingData.addActor(buildingResources);

        // ROW 1
        rowTop = new Group();
        buildingResources.addActor(rowTop);

        resources = new Label("Resources:", style);
//        rowTop.addActor(resources);

        resFood = new Label("F", style);
        rowTop.addActor(resFood);

        resGold = new Label("G", style);
        rowTop.addActor(resGold);

        resWood = new Label("W", style);
        rowTop.addActor(resWood);;

        resRock = new Label("R", style);
        rowTop.addActor(resRock);

        resPeople = new Label("P", style);
        rowTop.addActor(resPeople);

        // ROW 2
        rowMiddle = new Group();
        buildingResources.addActor(rowMiddle);

        cost = new Label("Cost:", style);
        rowMiddle.addActor(cost);

        costFood = new Label("", style);
        rowMiddle.addActor(costFood);

        costGold = new Label("", style);
        rowMiddle.addActor(costGold);

        costWood = new Label("", style);
        rowMiddle.addActor(costWood);

        costRock = new Label("", style);
        rowMiddle.addActor(costRock);

        costPeople = new Label("", style);
        rowMiddle.addActor(costPeople);

        // ROW BOTTOM
        rowBottom = new Group();
        buildingResources.addActor(rowBottom);

        profit = new Label("Profit:", style);
        rowBottom.addActor(profit);

        proFood = new Label("", style);
        rowBottom.addActor(proFood);

        proGold = new Label("", style);
        rowBottom.addActor(proGold);

        proWood = new Label("", style);
        rowBottom.addActor(proWood);

        proRock = new Label("", style);
        rowBottom.addActor(proRock);

        proPeople = new Label("", style);
        rowBottom.addActor(proPeople);

        updatePosition();
        setBuilding(building);
    }


    /**
     * Sets the building for the class and all Labels with the newBuilding values.
     * @param newBuilding: the newBuilding from which values are taken. If null, "empty" values are used instead.
     */
    public void setBuilding(MapEntity newBuilding) {
        building = newBuilding;

        if (building != null) {
            //TODO: change demo values with building values
            buildingImage.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("core/assets/house1.png")))));

            costFood.setText("0");
            costGold.setText("0");
            costWood.setText("50");
            costRock.setText("50");
            costPeople.setText("0");
        }
        else {
            buildingImage.setDrawable(null);

            costFood.setText("-");
            costGold.setText("-");
            costWood.setText("-");
            costRock.setText("-");
            costPeople.setText("-");
        }

        updateBuildingData();
    }

    /**
     * Updates the life, workers and profit counters of the building. If building is null, "empty" values are used.
     */
    public void updateBuildingData() {

        if (building != null) {
            //TODO: change demo values with building values
            buildingName.setText("House");
            buildingLife.setText("Life: " + "50 / 50");
            buildingWorkers.setText(("Workers: " + "4 / 5"));

            proFood.setText("0");
            proGold.setText("0");
            proWood.setText("0");
            proRock.setText("0");
            proPeople.setText("10");
        }
        else {
            buildingName.setText("-");
            buildingLife.setText("Life: - / -");
            buildingWorkers.setText(("Workers: - / -"));

            proFood.setText("-");
            proGold.setText("-");
            proWood.setText("-");
            proRock.setText("-");
            proPeople.setText("-");
        }
    }

    /**
     * Updates the position of all Actors in BuildingInfo. Also called in constructor to first place the Actors.
     */
    public void updatePosition() {
        // BUILDING DATA
        buildingData.setSize(Gdx.graphics.getWidth() * 0.15f, Gdx.graphics.getWidth() * 0.15f);
        buildingData.setPosition(0, 0);


        // BUILDING IMAGE
        Assets.setSizeRelative(buildingImage, 0.4f, 0.4f);
        Assets.setPositionRelative(buildingImage, 0.05f, 0.55f);


        // BUILDING DATA
        Assets.setSizeRelative(buildingStatus, 0.5f, 0.5f);
        Assets.setPositionRelative(buildingStatus, 0.5f, 0.5f);
        Assets.setPositionRelative(buildingName, 0.1f, 0.75f, false, true);
        Assets.setPositionRelative(buildingLife, 0.1f, 0.5f, false, true);
        Assets.setPositionRelative(buildingWorkers, 0.1f, 0.25f, false, true);


        // BUILDING RESOURCES
        Assets.setPositionRelative(buildingResources, 0f, 0f);
        Assets.setSizeRelative(buildingResources, 1f, 0.5f);

        // ROW 1
        Assets.setPositionRelative(rowTop, 0f, 2f / 3f);
        Assets.setSizeRelative(rowTop, 1f, 1f / 3f);
//        Assets.setPositionRelative(resources, COLUMN_TITLES, 0.5f, false, true);
        Assets.setPositionRelative(resFood, COLUMN_FOOD, 0.5f, true, true);
        Assets.setPositionRelative(resGold, COLUMN_GOLD, 0.5f, true, true);
        Assets.setPositionRelative(resWood, COLUMN_WOOD, 0.5f, true, true);
        Assets.setPositionRelative(resRock, COLUMN_ROCK, 0.5f, true, true);
        Assets.setPositionRelative(resPeople, COLUMN_PEOPLE, 0.5f, true, true);

        // ROW 2
        Assets.setPositionRelative(rowMiddle, 0f, 1f / 3f);
        Assets.setSizeRelative(rowMiddle, 1f, 1f / 3f);
        Assets.setPositionRelative(cost, COLUMN_TITLES, 0.5f, false, true);
        Assets.setPositionRelative(costFood, COLUMN_FOOD, 0.5f, true, true);
        Assets.setPositionRelative(costGold, COLUMN_GOLD, 0.5f, true, true);
        Assets.setPositionRelative(costWood, COLUMN_WOOD, 0.5f, true, true);
        Assets.setPositionRelative(costRock, COLUMN_ROCK, 0.5f, true, true);
        Assets.setPositionRelative(costPeople, COLUMN_PEOPLE, 0.5f, true, true);

        // ROW BOTTOM
        Assets.setPositionRelative(rowBottom, 0f, 0f);
        Assets.setSizeRelative(rowBottom, 1f, 1f / 3f);
        Assets.setPositionRelative(profit, COLUMN_TITLES, 0.5f, false, true);
        Assets.setPositionRelative(proFood, COLUMN_FOOD, 0.5f, true, true);
        Assets.setPositionRelative(proGold, COLUMN_GOLD, 0.5f, true, true);
        Assets.setPositionRelative(proWood, COLUMN_WOOD, 0.5f, true, true);
        Assets.setPositionRelative(proRock, COLUMN_ROCK, 0.5f, true, true);
        Assets.setPositionRelative(proPeople, COLUMN_PEOPLE, 0.5f, true, true);
    }
}
