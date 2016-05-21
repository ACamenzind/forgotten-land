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
import com.strategy.game.Utils;
import com.strategy.game.buildings.Building;
import com.strategy.game.buildings.Container;
import com.strategy.game.screens.Display;
import com.strategy.game.world.ResourceHandler;

/**
 * Created by Amedeo on 12/05/16.
 */
public class SidebarBuildingInfo extends Table implements Display {

//    public class ResourcesTable extends Table {
//
//        private static final int RESOURCES_NUMBER = 5;
//
//        private Label[][] table;
//        private int tableHeight;
//        private int tableWidth;
//
//        public ResourcesTable(int columnsNumber) {
//            tableWidth = columnsNumber + 1;
//            tableHeight = RESOURCES_NUMBER + 1;
//
//            table = new Label[RESOURCES_NUMBER + 1][columnsNumber + 1];
//            table[1][0] = Assets.makeLabel("Food", Utils.FONT_SMALL_BLACK);
//            table[2][0] = Assets.makeLabel("Wood", Utils.FONT_SMALL_BLACK);
//            table[3][0] = Assets.makeLabel("Minerals", Utils.FONT_SMALL_BLACK);
//            table[4][0] = Assets.makeLabel("Gold", Utils.FONT_SMALL_BLACK);
//            table[5][0] = Assets.makeLabel("People", Utils.FONT_SMALL_BLACK);
//
//            for (int y = 0; y < table.length; y++) {
//                for (int x = 0; x < table[y].length; x++) {
//                    if (x == 0) {
//                        add(table[y][x]);
//                    }
//                    else {
//                        table[y][x] = Assets.makeLabel("", Utils.FONT_SMALL_BLACK);
//                        add(table[y][x]).uniform().expand();
//                    }
//
//                }
//                row();
//            }
//        }
//
//        public void set(String[] titles, int[][] values) {
//            for (int i = 0; i < titles.length; i++) {
//                table[0][i+1].setText(titles[i]);
//            }
//            set(values);
//        }
//
//        public void set(int[][] values) {
//            for (int x = 0; x < values.length; x++) {
//                for (int y = 0; y < values[x].length; y++) {
//                    table[y+1][x+1].setText(Integer.toString(values[x][y]));
//                }
//            }
//        }
//
//        public void set(ResourceContainer[] resourceContainers) {
//            for (int i = 0; i < resourceContainers.length; i++) {
//                table[1][i+1].setText(Integer.toString(resourceContainers[i].food));
//                table[2][i+1].setText(Integer.toString(resourceContainers[i].wood));
//                table[3][i+1].setText(Integer.toString(resourceContainers[i].rock));
//                table[4][i+1].setText(Integer.toString(resourceContainers[i].gold));
//                table[5][i+1].setText(Integer.toString(resourceContainers[i].people));
//            }
//        }
//    }

    public enum Menu { NONE, INFO, COST, PROFIT, CAPACITY }

    // BUILDING
    private Building building;
    private boolean preview = false;

    // TABLES
    private Table tableInfo = new Table();
    private Table tableCost = new Table();
    private Table tableProfit = new Table();
    private Table tableCapacity = new Table();

    // NAME
    private Label name = Assets.makeLabel("", Utils.FONT_MEDIUM_BLACK);
    private Label nameCost = Assets.makeLabel(" - Cost", Utils.FONT_MEDIUM_BLACK);
    private Label nameProfit = Assets.makeLabel(" - Profit", Utils.FONT_MEDIUM_BLACK);
    private Label nameCapacity = Assets.makeLabel(" - Capacity", Utils.FONT_MEDIUM_BLACK);
    private static final float NAME_POSITION_X = 0.075f;
    private static final float NAME_POSITION_Y = 0.85f;

    // MENU x3
    private static final int MENU3_ITEMS = 3;
    private static final float MENU3_WIDTH = 1f;
    private static final float MENU3_HEIGHT = 0.125f;
    private static final float MENU3_BUTTON_WIDTH = MENU3_WIDTH / MENU3_ITEMS;
    private static final float MENU3_BUTTON_HEIGHT = 1f;
    private GameButton menu3ButtonInfo = new GameButton(Assets.sidebarBuildInfoInfo);
    private GameButton menu3ButtonCost = new GameButton(Assets.sidebarBuildInfoCost);
    private GameButton menu3ButtonProfit = new GameButton(Assets.sidebarBuildInfoProfit);
    private GameButton menu3ButtonCapacity = new GameButton(Assets.sidebarBuildInfoCapacity);
    private Table menu3 = new Table();

    // MENU x2
    private static final int MENU2_ITEMS = 2;
    private static final float MENU2_WIDTH = 1f;
    private static final float MENU2_HEIGHT = 0.125f;
    private static final float MENU2_BUTTON_WIDTH = MENU2_WIDTH / MENU2_ITEMS;
    private static final float MENU2_BUTTON_HEIGHT = 1f;
    private GameButton menu2ButtonInfo = new GameButton(Assets.sidebarBuildInfoInfo);
    private GameButton menu2ButtonCost = new GameButton(Assets.sidebarBuildInfoCost);
    private Table menu2 = new Table();

    // IMAGE
    private Image image = new Image();
    private static final float IMAGE_SIZE = 0.35f;
    private static final float IMAGE_POSITION_X = NAME_POSITION_X;
    private static final float IMAGE_POSITION_Y = NAME_POSITION_Y - IMAGE_SIZE - 0.05f;

    // LIFE
    private Label life = Assets.makeLabel("Life:\n1000 / 1000", Utils.FONT_SMALL_BLACK);
    private static final float LIFE_POSITION_X = IMAGE_POSITION_X + IMAGE_SIZE + 0.05f;
    private static final float LIFE_POSITION_Y = IMAGE_POSITION_Y + IMAGE_SIZE / 2f;

    // WORKERS
    private Label workers = Assets.makeLabel("Workers:\n100 / 100", Utils.FONT_SMALL_BLACK);
    private static final float WORKERS_POSITION_X = IMAGE_POSITION_X + IMAGE_SIZE + 0.05f;
    private static final float WORKERS_POSITION_Y = IMAGE_POSITION_Y;
    private GameButton workersButtonAdd = new GameButton(Assets.sidebarBuildInfoPlus);
    private GameButton workersButtonRemove = new GameButton(Assets.sidebarBuildInfoMinus);
    private static final float WORKERS_BUTTON_SIZE = 0.075f;
    private static final float WORKERS_BUTTON_POSITION_X = WORKERS_POSITION_X + 0.3f;
    private static final float WORKERS_BUTTON_POSITION_Y = IMAGE_POSITION_Y;

    // DESTROY
    private GameButton destroyButton = new GameButton(Assets.sidebarBuildInfoDestroy);
    private static final float DESTROY_WIDTH = IMAGE_SIZE / 2f - 0.025f;
    private static final float DESTROY_HEIGHT = 0.1f;
    private static final float DESTROY_POSITION_X = NAME_POSITION_X;
    private static final float DESTROY_POSITION_Y = IMAGE_POSITION_Y - DESTROY_HEIGHT - 0.05f;

    // REPAIR
    private GameButton repairButton = new GameButton(Assets.sidebarBuildInfoRepair);
    private static final float REPAIR_WIDTH = DESTROY_WIDTH;
    private static final float REPAIR_HEIGHT = 0.1f;
    private static final float REPAIR_POSITION_X = NAME_POSITION_X + DESTROY_WIDTH + 0.05f;
    private static final float REPAIR_POSITION_Y = DESTROY_POSITION_Y;

    // RESOURCES_BALANCE
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

    // CAPACITY
    private ResourcesTable resourcesCapacity = new ResourcesTable(1);
    private static final float RESOURCES_CAPACITY_WIDTH = 0.6f;
    private static final float RESOURCES_CAPACITY_HEIGHT = 0.6f;
    private static final float RESOURCES_CAPACITY_POSITION_X = 0.5f;
    private static final float RESOURCES_CAPACITY_POSITION_Y = 0.5f;

    public SidebarBuildingInfo() {

        // GENERAL
        this.building = null;

        tableInfo.addActor(name);

        //MENU3
        menu3ButtonInfo.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateMenu(Menu.INFO);
                return false;
            }
        });
        menu3.addActor(menu3ButtonInfo);
        menu3ButtonCost.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateMenu(Menu.COST);
                return false;
            }
        });
        menu3.addActor(menu3ButtonCost);
        menu3ButtonProfit.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateMenu(Menu.PROFIT);
                return false;
            }
        });
        menu3.addActor(menu3ButtonProfit);
        menu3ButtonCapacity.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateMenu(Menu.CAPACITY);
                return false;
            }
        });
        menu3.addActor(menu3ButtonCapacity);


        // MENU2
        menu2ButtonInfo.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateMenu(Menu.INFO);
                return false;
            }
        });
        menu2.addActor(menu2ButtonInfo);
        menu2ButtonCost.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateMenu(Menu.COST);
                return false;
            }
        });
        menu2.addActor(menu2ButtonCost);

        // INFO
        tableInfo.addActor(life);
        tableInfo.addActor(workers);
        tableInfo.addActor(image);

        workersButtonAdd.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (building != null) {
                    Sidebar sidebar = (Sidebar) getParent();
                    ResourceHandler handler = sidebar.getScreen().getWorld().getResourceHandler();
                    handler.addWorker(building);
                    sidebar.getScreen().getResourcesBar().update();
                    sidebar.update();
                }
                return false;
            }
        });
        tableInfo.addActor(workersButtonAdd);
        workersButtonRemove.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (building != null) {
                    Sidebar sidebar = (Sidebar) getParent();
                    ResourceHandler handler = sidebar.getScreen().getWorld().getResourceHandler();
                    handler.removeWorker(building);
                    sidebar.getScreen().getResourcesBar().update();
                    sidebar.update();
                }
                return false;
            }
        });
        tableInfo.addActor(workersButtonRemove);

        destroyButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //TODO: Fix clicked problem
                if (building != null && hasParent()) {
                    Sidebar sidebar = (Sidebar) getParent();
                    sidebar.getScreen().getBuilder().destroy(building);
                    updateMenu(Menu.NONE);
                }
                return false;
            }
        });
        tableInfo.addActor(destroyButton);

        repairButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (building != null && hasParent()) {
                    Sidebar sidebar = (Sidebar) getParent();
                    sidebar.getScreen().getBuilder().repairBuilding(building);
                    updateMenu(Menu.INFO);
                }
                return false;
            }
        });
        tableInfo.addActor(repairButton);

//        String[] resourcesBalanceTitles = { "Worth" };
//        int[][] resourcesBalanceValues = { { 1, 2, 3, 4, -1 } };
//        resourcesBalance.set(resourcesBalanceTitles, resourcesBalanceValues);
//        tableInfo.addActor(resourcesBalance);

        // COST
        tableCost.addActor(nameCost);
        String[] resourcesCostTitles = { "To build", "Per turn" };
        int[][] resourcesCostValues = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
        resourcesCost.set(resourcesCostTitles, resourcesCostValues);
        tableCost.addActor(resourcesCost);

        // PROFIT
        tableProfit.addActor(nameProfit);
        String[] resourcesProfitTitles = { "Per worker", "Per turn" };
        int[][] resourcesProfitValues = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
        resourcesProfit.set(resourcesProfitTitles, resourcesProfitValues);
        tableProfit.addActor(resourcesProfit);

        // CAPACITY
        tableCapacity.addActor(nameCapacity);
        String[] resourcesCapacityTitles = { "Total" };
        int[][] resourcesCapacityValues = { { 0, 0, 0, 0, 0 } };
        resourcesCapacity.set(resourcesCapacityTitles, resourcesCapacityValues);
        tableCapacity.addActor(resourcesCapacity);

        // TABLES
        addActor(menu3);
        addActor(menu2);
        addActor(tableInfo);
        addActor(tableCost);
        addActor(tableProfit);
        addActor(tableCapacity);

        // INIT
        update();
        updatePosition();
        updateMenu(Menu.NONE);
    }

    @Override
    public void update() {
        if (building != null) {
            if (preview)
                life.setText(("Life:\n" + building.getMaxLife() + " / " + building.getMaxLife()));
            else
                life.setText("Life:\n" + Integer.toString(building.getLife()) + " / " + Integer.toString(building.getMaxLife()));

            workers.setText("Workers:\n" + Integer.toString(building.getWorkers()) + " / " + Integer.toString(building.getMaxWorkers()));

            ResourceContainer[] resourceCostContainer = {building.getCosts(), building.getMaintenanceCosts()};
            resourcesCost.set(resourceCostContainer);

            ResourceContainer[] resourceProfitContainer = {building.getProductionsPerWorker(), building.getProductionsPerTurn()};
            resourcesProfit.set(resourceProfitContainer);

            ResourceContainer[] resourceCapacityContainer = { new ResourceContainer(0, 0, 0, 0, 0) };
            if (building instanceof Container) {
                Container container = (Container) building;
                resourceCapacityContainer[0] = container.getResourcesStored();
            }
            resourcesCapacity.set(resourceCapacityContainer);
        }
    }

    @Override
    public void updatePosition() {

        // TABLES
        Assets.setSizeRelative(menu3, MENU3_WIDTH, MENU3_HEIGHT);
        Assets.setSizeRelative(menu2, MENU2_WIDTH, MENU2_HEIGHT);
        Assets.setSizeRelative(tableInfo, 1f, 1f);
        Assets.setSizeRelative(tableCost, 1f, 1f);
        Assets.setSizeRelative(tableProfit, 1f, 1f);
        Assets.setSizeRelative(tableCapacity, 1f, 1f);

        // TITLE
        Assets.setPositionRelative(name, NAME_POSITION_X, NAME_POSITION_Y);
        Assets.setPositionRelative(nameCost, NAME_POSITION_X, NAME_POSITION_Y);
        Assets.setPositionRelative(nameProfit, NAME_POSITION_X, NAME_POSITION_Y);
        Assets.setPositionRelative(nameCapacity, NAME_POSITION_X, NAME_POSITION_Y);

        // MENU3
        Assets.setSizeRelative(menu3ButtonInfo, MENU3_BUTTON_WIDTH, MENU3_BUTTON_HEIGHT);
        Assets.setSizeRelative(menu3ButtonCost, MENU3_BUTTON_WIDTH, MENU3_BUTTON_HEIGHT);
        Assets.setSizeRelative(menu3ButtonProfit, MENU3_BUTTON_WIDTH, MENU3_BUTTON_HEIGHT);
        Assets.setSizeRelative(menu3ButtonCapacity, MENU3_BUTTON_WIDTH, MENU3_BUTTON_HEIGHT);

        Assets.setPositionRelative(menu3ButtonInfo, 0, 0);
        Assets.setPositionRelative(menu3ButtonCost, MENU3_BUTTON_WIDTH, 0);
        Assets.setPositionRelative(menu3ButtonProfit, MENU3_BUTTON_WIDTH * 2, 0);
        Assets.setPositionRelative(menu3ButtonCapacity, MENU3_BUTTON_WIDTH * 2, 0);

        // MENU2
        Assets.setSizeRelative(menu2ButtonInfo, MENU2_BUTTON_WIDTH, MENU2_BUTTON_HEIGHT);
        Assets.setSizeRelative(menu2ButtonCost, MENU2_BUTTON_WIDTH, MENU2_BUTTON_HEIGHT);

        Assets.setPositionRelative(menu2ButtonInfo, 0, 0);
        Assets.setPositionRelative(menu2ButtonCost, MENU2_BUTTON_WIDTH, 0);

        // INFO
        Assets.setSizeRelative(image, IMAGE_SIZE, IMAGE_SIZE);
        Assets.setPositionRelative(image, IMAGE_POSITION_X, IMAGE_POSITION_Y);

        Assets.setPositionRelative(life, LIFE_POSITION_X, LIFE_POSITION_Y);
        Assets.setPositionRelative(workers, WORKERS_POSITION_X, WORKERS_POSITION_Y);

        Assets.setSizeRelative(workersButtonAdd, WORKERS_BUTTON_SIZE, WORKERS_BUTTON_SIZE);
        Assets.setSizeRelative(workersButtonRemove, WORKERS_BUTTON_SIZE, WORKERS_BUTTON_SIZE);
        Assets.setPositionRelative(workersButtonRemove, WORKERS_BUTTON_POSITION_X, WORKERS_BUTTON_POSITION_Y);
        Assets.setPositionRelative(workersButtonAdd, WORKERS_BUTTON_POSITION_X + WORKERS_BUTTON_SIZE, WORKERS_BUTTON_POSITION_Y);

        Assets.setSizeRelative(destroyButton, DESTROY_WIDTH, DESTROY_HEIGHT);
        Assets.setPositionRelative(destroyButton, DESTROY_POSITION_X, DESTROY_POSITION_Y);

        Assets.setSizeRelative(repairButton, REPAIR_WIDTH, REPAIR_HEIGHT);
        Assets.setPositionRelative(repairButton, REPAIR_POSITION_X, REPAIR_POSITION_Y);

//        Assets.setSizeRelative(resourcesBalance, RESOURCES_BALANCE_WIDTH, RESOURCES_BALANCE_HEIGHT);
//        Assets.setPositionRelative(resourcesBalance, RESOURCES_BALANCE_POSITION_X, RESOURCES_BALANCE_POSITION_Y);

        // COSTS
        Assets.setSizeRelative(resourcesCost, RESOURCES_COST_WIDTH, RESOURCES_COST_HEIGHT);
        Assets.setPositionRelative(resourcesCost, RESOURCES_COST_POSITION_X, RESOURCES_COST_POSITION_Y, true, true);

        // PROFIT
        Assets.setSizeRelative(resourcesProfit, RESOURCES_PROFIT_WIDTH, RESOURCES_PROFIT_HEIGHT);
        Assets.setPositionRelative(resourcesProfit, RESOURCES_PROFIT_POSITION_X, RESOURCES_PROFIT_POSITION_Y, true, true);

        // CAPACITY
        Assets.setSizeRelative(resourcesCapacity, RESOURCES_CAPACITY_WIDTH, RESOURCES_CAPACITY_HEIGHT);
        Assets.setPositionRelative(resourcesCapacity, RESOURCES_CAPACITY_POSITION_X, RESOURCES_CAPACITY_POSITION_Y, true, true);
    }

    public void updateMenu(Menu menu) {
        if (menu == Menu.NONE || building == null) {
            this.menu2.setVisible(false);
            this.menu3.setVisible(false);
            tableInfo.setVisible(false);
            tableCost.setVisible(false);
            tableProfit.setVisible(false);
            tableCapacity.setVisible(false);
        }
        else {
            if (menu == Menu.INFO) {
                tableInfo.setVisible(true);
                tableCost.setVisible(false);
                tableProfit.setVisible(false);
                tableCapacity.setVisible(false);
            }
            else if (menu == Menu.COST) {
                tableInfo.setVisible(false);
                tableCost.setVisible(true);
                tableProfit.setVisible(false);
                tableCapacity.setVisible(false);
            }
            else if (menu == Menu.PROFIT) {
                tableInfo.setVisible(false);
                tableCost.setVisible(false);
                tableProfit.setVisible(true);
                tableCapacity.setVisible(false);
            }
            else if (menu == Menu.CAPACITY) {
                tableInfo.setVisible(false);
                tableCost.setVisible(false);
                tableProfit.setVisible(false);
                tableCapacity.setVisible(true);
            }

            boolean manufacturer = building.getType() == Building.BuildingType.MANUFACTURER;
            workers.setVisible(manufacturer);
            workersButtonAdd.setVisible(manufacturer && !preview);
            workersButtonRemove.setVisible(manufacturer && !preview);

            boolean warehouse = building.getType() == Building.BuildingType.WAREHOUSE;

            menu3.setVisible(manufacturer || warehouse);
            menu2.setVisible(!(manufacturer || warehouse));

            menu3ButtonProfit.setVisible(!warehouse || manufacturer);
            menu3ButtonCapacity.setVisible(warehouse || !manufacturer);

            destroyButton.setVisible(!preview);
            repairButton.setVisible(!preview);
        }
    }

    public void setBuilding(Building building) {
        setBuilding(building, false);
    }

    public void setBuilding(Building building, boolean preview) {
        this.building = building;
        this.preview = preview;
        if (building != null) {
            name.setText(building.getName());
            nameCost.setText(building.getName() + " - Cost");
            nameProfit.setText(building.getName() + " - Profit");
            nameCapacity.setText(building.getName() + " - Capacity");
            image.setDrawable(new SpriteDrawable(new Sprite(building.getMainTexture())));
            life.setText("Life:\n" + building.getLife() + " / " + building.getMaxLife());
            workers.setText("Workers:\n" + building.getWorkers() + " / " + building.getMaxWorkers());

            ResourceContainer[] resourceCostContainer = {building.getCosts(), building.getMaintenanceCosts()};
            resourcesCost.set(resourceCostContainer);

            ResourceContainer[] resourceProfitContainer = {building.getProductionsPerWorker(), building.getProductionsPerTurn()};
            resourcesProfit.set(resourceProfitContainer);

            ResourceContainer[] resourceCapacityContainer = { new ResourceContainer(0, 0, 0, 0, 0) };
            if (building instanceof Container) {
                Container container = (Container) building;
                resourceCapacityContainer[0] = container.getResourcesStored();
            }
            resourcesCapacity.set(resourceCapacityContainer);

            updateMenu(Menu.INFO);
        }
    }
}
