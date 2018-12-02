package com.strategy.game.screens.sidebar;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.strategy.game.Assets;
import com.strategy.game.ResourceContainer;
import com.strategy.game.Utils;

/**
 * Created by Amedeo on 21/05/16.
 */
public class ResourcesTable extends Table {

    private static final int RESOURCES_NUMBER = 5;
    private BitmapFont default_font;
    private Actor[][] table;

    public ResourcesTable(int columnsNumber, BitmapFont font, boolean black) {
        this.default_font = font;

        table = new Actor[RESOURCES_NUMBER + 1][columnsNumber + 1];

        if (black) {
            table[1][0] = Assets.makeImage(Assets.getTexture("resourcesFoodBk"));
            table[2][0] = Assets.makeImage(Assets.getTexture("resourcesWoodBk"));
            table[3][0] = Assets.makeImage(Assets.getTexture("resourcesRockBk"));
            table[4][0] = Assets.makeImage(Assets.getTexture("resourcesGoldBk"));
            table[5][0] = Assets.makeImage(Assets.getTexture("resourcesPeopleBk"));
        }
        else {
            table[1][0] = Assets.makeImage(Assets.getTexture("resourcesFood"));
            table[2][0] = Assets.makeImage(Assets.getTexture("resourcesWood"));
            table[3][0] = Assets.makeImage(Assets.getTexture("resourcesRock"));
            table[4][0] = Assets.makeImage(Assets.getTexture("resourcesGold"));
            table[5][0] = Assets.makeImage(Assets.getTexture("resourcesPeople"));
        }

        // This looks weird, but I need it to get the height of a Cell
        Label test = Assets.makeLabel("TEST", font);
        float size = test.getHeight();

        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table[y].length; x++) {
                if (x == 0) {
                    add(table[y][x]).size(size);
                }
                else {
                    table[y][x] = Assets.makeLabel("-", font);
                    add(table[y][x]).uniform().expand();
                }

            }
            row();
        }
        for (int y = 0; y < table.length; y++) {

        }
    }

    public void set(String[] titles, int[][] values) {
        for (int i = 0; i < titles.length; i++) {
            ((Label) table[0][i+1]).setText(titles[i]);
        }
        set(values);
    }

    public void set(int[][] values) {
        for (int x = 0; x < values.length; x++) {
            for (int y = 0; y < values[x].length; y++) {
                ((Label) table[y+1][x+1]).setText(Integer.toString(values[x][y]));
            }
        }
    }

    public void set(ResourceContainer[] resourceContainers) {
        for (int i = 0; i < resourceContainers.length; i++) {
            if (resourceContainers[i].getFood() < 0)
                ((Label) table[1][i+1]).setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_RED));
            else
                ((Label) table[1][i+1]).setStyle(Assets.makeLabelStyle(default_font));
            ((Label) table[1][i+1]).setText(Integer.toString(resourceContainers[i].getFood()));

            if (resourceContainers[i].getWood() < 0)
                ((Label) table[2][i+1]).setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_RED));
            else
                ((Label) table[2][i+1]).setStyle(Assets.makeLabelStyle(default_font));
            ((Label) table[2][i+1]).setText(Integer.toString(resourceContainers[i].getWood()));

            if (resourceContainers[i].getRock() < 0)
                ((Label) table[3][i+1]).setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_RED));
            else
                ((Label) table[3][i+1]).setStyle(Assets.makeLabelStyle(default_font));
            ((Label) table[3][i+1]).setText(Integer.toString(resourceContainers[i].getRock()));

            if (resourceContainers[i].getGold() < 0)
                ((Label) table[4][i+1]).setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_RED));
            else
                ((Label) table[4][i+1]).setStyle(Assets.makeLabelStyle(default_font));
            ((Label) table[4][i+1]).setText(Integer.toString(resourceContainers[i].getGold()));

            if (resourceContainers[i].getPeople() < 0)
                ((Label) table[5][i+1]).setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_RED));
            else
                ((Label) table[5][i+1]).setStyle(Assets.makeLabelStyle(default_font));
            ((Label) table[5][i+1]).setText(Integer.toString(resourceContainers[i].getPeople()));
        }
    }
}
