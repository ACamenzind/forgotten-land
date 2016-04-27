package com.strategy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

/**
 * A generic object to put on the map. Inherit from this to create new buildings.
 */
public class MapEntity implements Disposable{
    private int collisionSize;
    protected Texture mainTexture;
    protected Texture selectionTexture; // the transparent version
    private ArrayList<StaticTiledMapTile> tiles;
    private int x,y;
    private TiledMapTileLayer layer;
    private ArrayList<TiledMapTileLayer.Cell> prevCells;

    public MapEntity() {
        this.mainTexture = null;
        this.tiles = new ArrayList<StaticTiledMapTile>();
        this.prevCells = new ArrayList<TiledMapTileLayer.Cell>();
        sliceTexture(mainTexture);
        //TODO: handle secondary texture
    }


    public ArrayList<StaticTiledMapTile> getTiles() {
        return tiles;
    }

    /**
     * Splits the entity's mainTexture into vertical slices of width TILE_SIZE
     */
    protected void sliceTexture(Texture mainTexture) {
        if (mainTexture != null) {
            TextureRegion tex = new TextureRegion(mainTexture);
            TextureRegion[][] arr = tex.split(Utils.TILE_SIZE, tex.getRegionHeight());

            for (int i = 0; i < tex.getRegionWidth() / Utils.TILE_SIZE; i++) {
                StaticTiledMapTile tile = new StaticTiledMapTile(arr[0][i]);
                tiles.add(tile);
            }
        }
    }

    /**
     * Places the Entity onto the given layer at the specified coordinates
     * @param layer the layer onto which to put the entity
     * @param x tile coordinate
     * @param y tile coordinate
     */
    public void placeOnLayer(TiledMapTileLayer layer, int x, int y) {
        int offset = 0;
        this.layer = layer;
        this.x = x;
        this.y = y;

        for (StaticTiledMapTile tile :
                tiles) {
            prevCells.add(layer.getCell(x + offset, y + offset)); // save previous state
            TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
            cell.setTile(tile);
            layer.setCell(x + offset, y + offset, cell);
            offset++;
        }
    }

    /**
     * Resets the tiles to their previous state
     */
    public void resetTiles() {
        int offset = 0;
        for (TiledMapTileLayer.Cell cell :
                prevCells) {
            layer.setCell(x + offset, y + offset, cell);
            offset++;
        }
    }

    @Override
    public void dispose() {
        mainTexture.dispose();
    }
}
