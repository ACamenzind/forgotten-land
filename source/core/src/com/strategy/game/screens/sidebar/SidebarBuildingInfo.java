package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.strategy.game.Assets;
import com.strategy.game.GameButton;
import com.strategy.game.ResourceContainer;
import com.strategy.game.buildings.Building;

/**
 * Created by Amedeo on 12/05/16.
 */
public class SidebarBuildingInfo extends Table implements Display {

    public class ResourcesTable extends Table {

        private static final int RESOURCES_NUMBER = 5;

        private Label[][] table;
        private int tableHeight;
        private int tableWidth;

        public ResourcesTable(int columnsNumber) {
            tableWidth = columnsNumber + 1;
            tableHeight = RESOURCES_NUMBER + 1;

            table = new Label[RESOURCES_NUMBER + 1][columnsNumber + 1];
            table[1][0] = Assets.makeLabel("F", 12, Color.BLACK);
            table[2][0] = Assets.makeLabel("W", 12, Color.BLACK);
            table[3][0] = Assets.makeLabel("M", 12, Color.BLACK);
            table[4][0] = Assets.makeLabel("G", 12, Color.BLACK);
            table[5][0] = Assets.makeLabel("P", 12, Color.BLACK);

            for (int y = 0; y < table.length; y++) {
                for (int x = 0; x < table[y].length; x++) {
                    if (x == 0) {
                        add(table[y][x]);//.uniform();
                    }
                    else {
                        table[y][x] = Assets.makeLabel("", 12, Color.BLACK);
                        add(table[y][x]).uniform().expand();
                    }

                }
                row();
            }

            updatePosition();
        }

        public void set(String[] titles, int[][] values) {
            for (int i = 0; i < titles.length; i++) {
                table[0][i+1].setText(titles[i]);
            }
            set(values);
        }

        public void set(int[][] values) {
            for (int x = 0; x < values.length; x++) {
                for (int y = 0; y < values[x].length; y++) {
                    table[y+1][x+1].setText(Integer.toString(values[x][y]));
                }
            }
        }

        public void set(ResourceContainer[] resourceContainers) {
            for (int i = 0; i < resourceContainers.length; i++) {
                table[1][i+1].setText(Integer.toString(resourceContainers[i].food));
                table[2][i+1].setText(Integer.toString(resourceContainers[i].wood));
                table[3][i+1].setText(Integer.toString(resourceContainers[i].rock));
                table[4][i+1].setText(Integer.toString(resourceContainers[i].gold));
                table[5][i+1].setText(Integer.toString(resourceContainers[i].people));
            }
        }
    }

    // BUILDING
    Building building;

    // TABLES
    private Table tableInfo = new Table();
    private Table tableCost = new Table();
    private Table tableProfit = new Table();

    // NAME
    private Label name = Assets.makeLabel("Nothing", 14, Color.BLACK);
    private static final float NAME_POSITION_X = 0.075f;
    private static final float NAME_POSITION_Y = 0.85f;
    private static final Label COST = Assets.makeLabel(" - Cost", 14, Color.BLACK);
    //    private static final float COST_POSITION_X = 0.075f;
    private static final float COST_POSITION_Y = NAME_POSITION_Y;
    private static final Label PROFIT = Assets.makeLabel(" - Profit", 14, Color.BLACK);
    //    private static final float PROFIT_POSITION_X = 0.075f;
    private static final float PROFIT_POSITION_Y = NAME_POSITION_Y;

    // MENU
    private static final int MENU_ITEMS = 3;
    private static final float MENU_WIDTH = 1f;
    private static final float MENU_HEIGHT = 0.125f;
    private static final float MENU_BUTTON_WIDTH = MENU_WIDTH / MENU_ITEMS;
    private static final float MENU_BUTTON_HEIGHT = MENU_HEIGHT;
    private GameButton menuButtonInfo = new GameButton(Assets.sidebarBuildInfoInfo);
    private GameButton menuButtonCost = new GameButton(Assets.sidebarBuildInfoCost);
    private GameButton menuButtonProfit = new GameButton(Assets.sidebarBuildInfoProfit);

    //IMAGE
    private Image image = new Image();
    private static final float IMAGE_SIZE = 0.35f;
    private static final float IMAGE_POSITION_X = NAME_POSITION_X;
    private static final float IMAGE_POSITION_Y = NAME_POSITION_Y - IMAGE_SIZE - 0.05f;

    // LIFE
    private Label life = Assets.makeLabel("Life:\n1000 / 1000", 12, Color.BLACK);
    private static final float LIFE_POSITION_X = IMAGE_POSITION_X + IMAGE_SIZE + 0.05f;
    private static final float LIFE_POSITION_Y = IMAGE_POSITION_Y + IMAGE_SIZE / 2f;

    //WORKERS
    private Label workers = Assets.makeLabel("Workers:\n100 / 100", 12, Color.BLACK);
    private static final float WORKERS_POSITION_X = IMAGE_POSITION_X + IMAGE_SIZE + 0.05f;
    private static final float WORKERS_POSITION_Y = IMAGE_POSITION_Y;
    private GameButton workersButtonAdd = new GameButton(Assets.sidebarBuildInfoPlus);
    private GameButton workersButtonRemove = new GameButton(Assets.sidebarBuildInfoMinus);
    private static final float WORKERS_BUTTON_SIZE = 0.075f;
    private static final float WORKERS_BUTTON_POSITION_X = WORKERS_POSITION_X + 0.3f;
    private static final float WORKERS_BUTTON_POSITION_Y = IMAGE_POSITION_Y;

    //DESTROY
    private GameButton destroyButton = new GameButton(Assets.sidebarBuildInfoDestroy);
    private static final float DESTROY_WIDTH = IMAGE_SIZE;
    private static final float DESTROY_HEIGHT = 0.125f;
    private static final float DESTROY_POSITION_X = NAME_POSITION_X;
    private static final float DESTROY_POSITION_Y = IMAGE_POSITION_Y - DESTROY_HEIGHT - 0.05f;

    //RESOURCES_BALANCE
    private ResourcesTable resourcesBalance = new ResourcesTable(1);
    private static final float RESOURCES_BALANCE_WIDTH = 0.1f;
    private static final float RESOURCES_BALANCE_HEIGHT = 0.1f;
    private static final float RESOURCES_BALANCE_POSITION_X = 0.1f;
    private static final float RESOURCES_BALANCE_POSITION_Y = 0.1f;

    // COST
    private ResourcesTable resourcesCost = new ResourcesTable(2);
    private static final float RESOURCES_COST_WIDTH = 0.75f;
    private static final float RESOURCES_COST_HEIGHT = 0.6f;
    private static final float RESOURCES_COST_POSITION_X = 0.5f;
    private static final float RESOURCES_COST_POSITION_Y = 0.5f;

    // PROFIT
    private ResourcesTable resourcesProfit = new ResourcesTable(2);
    private static final float RESOURCES_PROFIT_WIDTH = 0.75f;
    private static final float RESOURCES_PROFIT_HEIGHT = 0.6f;
    private static final float RESOURCES_PROFIT_POSITION_X = 0.5f;
    private static final float RESOURCES_PROFIT_POSITION_Y = 0.5f;

    public SidebarBuildingInfo() {

        // GENERAL
        this.building = null;

        addActor(name);

        menuButtonInfo.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateMenu(1);
                return false;
            }
        });
        addActor(menuButtonInfo);
        menuButtonCost.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateMenu(2);
                return false;
            }
        });
        addActor(menuButtonCost);
        menuButtonProfit.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateMenu(3);
                return false;
            }
        });
        addActor(menuButtonProfit);

        // INFO
        tableInfo.addActor(life);
        tableInfo.addActor(workers);
        tableInfo.addActor(image);

        workersButtonAdd.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (building != null) {
                    building.changeWorker(1);
                    setBuilding(building);
                }
                return false;
            }
        });
        tableInfo.addActor(workersButtonAdd);
        workersButtonRemove.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (building != null) {
                    building.changeWorker(-1);
                    setBuilding(building);
                }
                return false;
            }
        });
        tableInfo.addActor(workersButtonRemove);

        tableInfo.addActor(destroyButton);

//        String[] resourcesBalanceTitles = { "Worth" };
//        int[][] resourcesBalanceValues = { { 1, 2, 3, 4, -1 } };
//        resourcesBalance.set(resourcesBalanceTitles, resourcesBalanceValues);
//        tableInfo.addActor(resourcesBalance);

        // COST
        tableCost.addActor(COST);
        String[] resourcesCostTitles = { "To build", "Per turn" };
        int[][] resourcesCostValues = { { 1, 2, 0, 0, 1 }, { 1, 2, 0, 0, 1 } };
        resourcesCost.set(resourcesCostTitles, resourcesCostValues);
        tableCost.addActor(resourcesCost);

        // PROFIT
        tableProfit.addActor(PROFIT);
        String[] resourcesProfitTitles = { "Per worker", "Per turn" };
        int[][] resourcesProfitValues = { { 1, 2, 0, 0, 1 }, { 1, 2, 0, 0, 1 } };
        resourcesProfit.set(resourcesProfitTitles, resourcesProfitValues);
        tableProfit.addActor(resourcesProfit);

        // TABLES
        addActor(tableInfo);
        addActor(tableCost);
        addActor(tableProfit);

        // INIT
        updateMenu(3);
    }


    @Override
    public void updatePosition() {

        // TABLES
        Assets.setSizeRelative(tableInfo, 1f, 1f);
        Assets.setSizeRelative(tableCost, 1f, 1f);
        Assets.setSizeRelative(tableProfit, 1f, 1f);

        // TITLE
        Assets.setPositionRelative(name, NAME_POSITION_X, NAME_POSITION_Y);
        Assets.setPositionRelative(COST, NAME_POSITION_X + name.getWidth() / getWidth(), COST_POSITION_Y);
        Assets.setPositionRelative(PROFIT, NAME_POSITION_X + name.getWidth() / getWidth(), PROFIT_POSITION_Y);

        // MENU
        Assets.setSizeRelative(menuButtonInfo, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        Assets.setSizeRelative(menuButtonCost, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        Assets.setSizeRelative(menuButtonProfit, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);

        Assets.setPositionRelative(menuButtonInfo, 0, 0);
        Assets.setPositionRelative(menuButtonCost, MENU_BUTTON_WIDTH, 0);
        Assets.setPositionRelative(menuButtonProfit, MENU_BUTTON_WIDTH * 2, 0);

        // INFO
        Assets.setSizeRelative(image, IMAGE_SIZE, IMAGE_SIZE);
        Assets.setPositionRelative(image, IMAGE_POSITION_X, IMAGE_POSITION_Y);

        Assets.setPositionRelative(life, LIFE_POSITION_X, LIFE_POSITION_Y);
        Assets.setPositionRelative(workers, WORKERS_POSITION_X, WORKERS_POSITION_Y);

        Assets.setSizeRelative(workersButtonAdd, WORKERS_BUTTON_SIZE, WORKERS_BUTTON_SIZE);
        Assets.setSizeRelative(workersButtonRemove, WORKERS_BUTTON_SIZE, WORKERS_BUTTON_SIZE);
        Assets.setPositionRelative(workersButtonAdd, WORKERS_BUTTON_POSITION_X, WORKERS_BUTTON_POSITION_Y);
        Assets.setPositionRelative(workersButtonRemove, WORKERS_BUTTON_POSITION_X + WORKERS_BUTTON_SIZE, WORKERS_BUTTON_POSITION_Y);

        Assets.setSizeRelative(destroyButton, DESTROY_WIDTH, DESTROY_HEIGHT);
        Assets.setPositionRelative(destroyButton, DESTROY_POSITION_X, DESTROY_POSITION_Y);

//        Assets.setSizeRelative(resourcesBalance, RESOURCES_BALANCE_WIDTH, RESOURCES_BALANCE_HEIGHT);
//        Assets.setPositionRelative(resourcesBalance, RESOURCES_BALANCE_POSITION_X, RESOURCES_BALANCE_POSITION_Y);

        // COSTS
        Assets.setSizeRelative(resourcesCost, RESOURCES_COST_WIDTH, RESOURCES_COST_HEIGHT);
        Assets.setPositionRelative(resourcesCost, RESOURCES_COST_POSITION_X, RESOURCES_COST_POSITION_Y, true, true);

        // PROFIT
        Assets.setSizeRelative(resourcesProfit, RESOURCES_PROFIT_WIDTH, RESOURCES_PROFIT_HEIGHT);
        Assets.setPositionRelative(resourcesProfit, RESOURCES_PROFIT_POSITION_X, RESOURCES_PROFIT_POSITION_Y, true, true);
    }

    public void updateMenu(int menu) {
        if (menu == 0) {
            tableInfo.setVisible(false);
            tableCost.setVisible(false);
            tableProfit.setVisible(false);
        }
        if (menu == 1) {
            tableInfo.setVisible(true);
            tableCost.setVisible(false);
            tableProfit.setVisible(false);
        }
        else if (menu == 2) {
            tableInfo.setVisible(false);
            tableCost.setVisible(true);
            tableProfit.setVisible(false);
        }
        else if (menu == 3) {
            tableInfo.setVisible(false);
            tableCost.setVisible(false);
            tableProfit.setVisible(true);
        }
        else {
            tableInfo.setVisible(false);
            tableCost.setVisible(false);
            tableProfit.setVisible(false);
        }
    }

    public void setBuilding(Building building) {
        this.building = building;
        if (building != null) {
            name.setText(building.getName());
            image.setDrawable(new SpriteDrawable(new Sprite(building.getMainTexture())));
            life.setText("Life: " + Integer.toString(building.getLife()) + " / " + Integer.toString(building.getMaxLife()));
            workers.setText("Workers: " + Integer.toString(building.getWorkers()) + " / " + Integer.toString(building.getMaxWorkers()));

            ResourceContainer[] resourceCostContainer = {building.getCosts(), building.getMaintenanceCosts()};
            resourcesCost.set(resourceCostContainer);

            ResourceContainer[] resourceProfitContainer = {building.getProductionsPerWorker(), building.getProductionsPerTurn()};
            resourcesProfit.set(resourceProfitContainer);


        }
    }
}
