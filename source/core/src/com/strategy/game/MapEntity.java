package com.strategy.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

/**
 * A generic object to put on the map. Inherit from this to create new buildings.
 */
public class MapEntity implements Disposable{
    protected Vector2 collisionSize;
    protected Texture mainTexture;
    private ArrayList<ExtendedStaticTiledMapTile> tiles;
    private int x,y;
    protected Vector2 imgOffset;
    private TiledMapTileLayer layer;
    private ArrayList<TiledMapTileLayer.Cell> prevCells;

    public MapEntity() {
        this.mainTexture = null;
        this.tiles = new ArrayList<ExtendedStaticTiledMapTile>();
        this.prevCells = new ArrayList<TiledMapTileLayer.Cell>();
        this.collisionSize = new Vector2(0,0);
        this.imgOffset = new Vector2(0,0);
        sliceTexture(mainTexture);
    }

    public Vector2 getCoords() {
        return new Vector2(x,y);
    }

    public Vector2 getCollisionSize() {
        return collisionSize;
    }

    public ArrayList<ExtendedStaticTiledMapTile> getTiles() {
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
                ExtendedStaticTiledMapTile tile = new ExtendedStaticTiledMapTile(arr[0][i]);
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

        //TODO: refactor a bit

        // Occupy cells
        for (int i = x; i < x + collisionSize.x; i++) {
            for (int j = y; j < y + collisionSize.y; j++) {
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                TextureRegion tex = new TextureRegion(Assets.emptyTile);
                ExtendedStaticTiledMapTile tile = new ExtendedStaticTiledMapTile(tex);
                tile.setObject(this);
                tile.setObstacle(true);
                cell.setTile(tile);
                layer.setCell(i, j, cell);
            }
        }


        // Draw textures on the diagonal cells
        for (ExtendedStaticTiledMapTile tile :
                tiles) {
            prevCells.add(layer.getCell(x + offset - (int)imgOffset.x, y + offset - (int) imgOffset.y)); // save previous state
            TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
            tile.setObstacle(true);
            tile.setObject(this);
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
