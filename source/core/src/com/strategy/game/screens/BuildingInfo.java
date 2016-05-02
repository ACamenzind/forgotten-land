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
 * Class displying building info in the bottom-left corner of the screen.
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
        this.stage = stage;

        // FONT
        BitmapFont font = new BitmapFont();
        font.getData().setScale(0.65f);
        Color color = new Color(0, 0, 0, 1);
        Label.LabelStyle style = new Label.LabelStyle(font, color);


        // BUILDING DATA
        buildingData = new Table();
        this.stage.addActor(buildingData);
        buildingData.setSize(Gdx.graphics.getWidth() * 0.15f, Gdx.graphics.getWidth() * 0.15f);
        buildingData.setPosition(0, 0);

        SpriteDrawable buildingBg = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("core/assets/test.jpg"))));
        buildingData.setBackground(buildingBg);


        // BUILDING IMAGE
        buildingImage = new Image();//new Image(new Texture("core/assets/house1.png"));
        buildingData.addActor(buildingImage);
        Assets.setSizeRelative(buildingImage, 0.4f, 0.4f);
        Assets.setPositionRelative(buildingImage, 0.05f, 0.55f);


        // BUILDING DATA
        buildingStatus = new Group();
        buildingData.addActor(buildingStatus);
        Assets.setSizeRelative(buildingStatus, 0.5f, 0.5f);
        Assets.setPositionRelative(buildingStatus, 0.5f, 0.5f);

        buildingName = new Label("", style);
        buildingStatus.addActor(buildingName);
        Assets.setPositionRelative(buildingName, 0.1f, 0.75f, false, true);

        buildingLife = new Label("Life:", style);
        buildingStatus.addActor(buildingLife);
        Assets.setPositionRelative(buildingLife, 0.1f, 0.5f, false, true);

        buildingWorkers = new Label("Workers:", style);
        buildingStatus.addActor(buildingWorkers);
        Assets.setPositionRelative(buildingWorkers, 0.1f, 0.25f, false, true);


        // BUILDING RESOURCES
        buildingResources = new Group();
        buildingData.addActor(buildingResources);
        Assets.setPositionRelative(buildingResources, 0f, 0f);
        Assets.setSizeRelative(buildingResources, 1f, 0.5f);

        // ROW 1
        rowTop = new Group();
        buildingResources.addActor(rowTop);
        Assets.setPositionRelative(rowTop, 0f, 2f / 3f);
        Assets.setSizeRelative(rowTop, 1f, 1f / 3f);

        resources = new Label("Resources:", style);
//        rowTop.addActor(resources);
//        setPositionRelative(resources, COLUMN_TITLES, 0.5f, false, true);

        resFood = new Label("Food", style);
        rowTop.addActor(resFood);
        Assets.setPositionRelative(resFood, COLUMN_FOOD, 0.5f, true, true);

        resGold = new Label("Gold", style);
        rowTop.addActor(resGold);
        Assets.setPositionRelative(resGold, COLUMN_GOLD, 0.5f, true, true);

        resWood = new Label("Wood", style);
        rowTop.addActor(resWood);
        Assets.setPositionRelative(resWood, COLUMN_WOOD, 0.5f, true, true);

        resRock = new Label("Rock", style);
        rowTop.addActor(resRock);
        Assets.setPositionRelative(resRock, COLUMN_ROCK, 0.5f, true, true);

        resPeople = new Label("People", style);
        rowTop.addActor(resPeople);
        Assets.setPositionRelative(resPeople, COLUMN_PEOPLE, 0.5f, true, true);

        // ROW 2
        rowMiddle = new Group();
        buildingResources.addActor(rowMiddle);
        Assets.setPositionRelative(rowMiddle, 0f, 1f / 3f);
        Assets.setSizeRelative(rowMiddle, 1f, 1f / 3f);

        cost = new Label("Cost:", style);
        rowMiddle.addActor(cost);
        Assets.setPositionRelative(cost, COLUMN_TITLES, 0.5f, false, true);

        costFood = new Label("", style);
        rowMiddle.addActor(costFood);
        Assets.setPositionRelative(costFood, COLUMN_FOOD, 0.5f, true, true);

        costGold = new Label("", style);
        rowMiddle.addActor(costGold);
        Assets.setPositionRelative(costGold, COLUMN_GOLD, 0.5f, true, true);

        costWood = new Label("", style);
        rowMiddle.addActor(costWood);
        Assets.setPositionRelative(costWood, COLUMN_WOOD, 0.5f, true, true);

        costRock = new Label("", style);
        rowMiddle.addActor(costRock);
        Assets.setPositionRelative(costRock, COLUMN_ROCK, 0.5f, true, true);

        costPeople = new Label("", style);
        rowMiddle.addActor(costPeople);
        Assets.setPositionRelative(costPeople, COLUMN_PEOPLE, 0.5f, true, true);

        // ROW BOTTOM
        rowBottom = new Group();
        buildingResources.addActor(rowBottom);
        Assets.setPositionRelative(rowBottom, 0f, 0f);
        Assets.setSizeRelative(rowBottom, 1f, 1f / 3f);

        profit = new Label("Profit:", style);
        rowBottom.addActor(profit);
        Assets.setPositionRelative(profit, COLUMN_TITLES, 0.5f, false, true);

        proFood = new Label("", style);
        rowBottom.addActor(proFood);
        Assets.setPositionRelative(proFood, COLUMN_FOOD, 0.5f, true, true);

        proGold = new Label("", style);
        rowBottom.addActor(proGold);
        Assets.setPositionRelative(proGold, COLUMN_GOLD, 0.5f, true, true);

        proWood = new Label("", style);
        rowBottom.addActor(proWood);
        Assets.setPositionRelative(proWood, COLUMN_WOOD, 0.5f, true, true);

        proRock = new Label("", style);
        rowBottom.addActor(proRock);
        Assets.setPositionRelative(proRock, COLUMN_ROCK, 0.5f, true, true);

        proPeople = new Label("", style);
        rowBottom.addActor(proPeople);
        Assets.setPositionRelative(proPeople, COLUMN_PEOPLE, 0.5f, true, true);

        //TODO: move in MapEntity setClicked(boolean clicked);
        setBuilding(null);
    }

    public void setBuilding(MapEntity newBuilding) {
        building = newBuilding;

        //TODO: change demo values with building values

        buildingImage.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("core/assets/house1.png")))));

        buildingName.setText("House");
        buildingLife.setText("Life: " + "50 / 50");
        buildingWorkers.setText(("Workers: " + "4 / 5"));

        costFood.setText("0");
        costGold.setText("0");
        costWood.setText("50");
        costRock.setText("50");
        costPeople.setText("0");

        proFood.setText("0");
        proGold.setText("0");
        proWood.setText("0");
        proRock.setText("0");
        proPeople.setText("10");
    }

    public void update() {
        setBuilding(this.building);
    }

    public void changeBuildingWorkers(int n) {
        //TODO: implement
//        building.changeWorkers(n);
    }
}
