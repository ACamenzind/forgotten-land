package com.strategy.game.screens.sidebar;

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

    private Label[][] table;

    public ResourcesTable(int columnsNumber) {
        table = new Label[RESOURCES_NUMBER + 1][columnsNumber + 1];
        table[1][0] = Assets.makeLabel("Food", Utils.FONT_SMALL_BLACK);
        table[2][0] = Assets.makeLabel("Wood", Utils.FONT_SMALL_BLACK);
        table[3][0] = Assets.makeLabel("Minerals", Utils.FONT_SMALL_BLACK);
        table[4][0] = Assets.makeLabel("Gold", Utils.FONT_SMALL_BLACK);
        table[5][0] = Assets.makeLabel("People", Utils.FONT_SMALL_BLACK);

        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table[y].length; x++) {
                if (x == 0) {
                    add(table[y][x]);
                }
                else {
                    table[y][x] = Assets.makeLabel("", Utils.FONT_SMALL_BLACK);
                    add(table[y][x]).uniform().expand();
                }

            }
            row();
        }
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
            if (resourceContainers[i].food < 0)
                table[1][i+1].setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_RED));
            else
                table[1][i+1].setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_BLACK));
            table[1][i+1].setText(Integer.toString(resourceContainers[i].food));

            if (resourceContainers[i].wood < 0)
                table[2][i+1].setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_RED));
            else
                table[2][i+1].setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_BLACK));
            table[2][i+1].setText(Integer.toString(resourceContainers[i].wood));

            if (resourceContainers[i].rock < 0)
                table[3][i+1].setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_RED));
            else
                table[3][i+1].setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_BLACK));
            table[3][i+1].setText(Integer.toString(resourceContainers[i].rock));

            if (resourceContainers[i].gold < 0)
                table[4][i+1].setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_RED));
            else
                table[4][i+1].setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_BLACK));
            table[4][i+1].setText(Integer.toString(resourceContainers[i].gold));

            if (resourceContainers[i].people < 0)
                table[5][i+1].setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_RED));
            else
                table[5][i+1].setStyle(Assets.makeLabelStyle(Utils.FONT_SMALL_BLACK));
            table[5][i+1].setText(Integer.toString(resourceContainers[i].people));
        }
    }
}
