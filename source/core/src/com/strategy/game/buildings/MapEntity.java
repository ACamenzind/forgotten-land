package com.strategy.game.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.strategy.game.Assets;
import com.strategy.game.ExtendedStaticTiledMapTile;
import com.strategy.game.Utils;

import java.util.ArrayList;

/**
 * A generic object to put on the map. Inherit from this to create new buildings.
 */
public class MapEntity implements Disposable{
    protected Vector2 collisionSize; // The x and y length of the collision rectangle
    protected Texture mainTexture;
    private boolean isClicked;
    private ArrayList<ExtendedStaticTiledMapTile> tiles;
    private int x,y;
    protected Vector2 imgOffset; //TODO: useless?
    private TiledMapTileLayer layer;
    private ArrayList<TiledMapTileLayer.Cell> prevCells;
    protected ArrayList<Texture> textures;
    private int counter;

    private ExtendedStaticTiledMapTile[][] tiles2;
    private TiledMapTileLayer.Cell[][] prevCells2;


    public MapEntity() {
        this.mainTexture = null;
        this.tiles = new ArrayList<ExtendedStaticTiledMapTile>();
        this.prevCells = new ArrayList<TiledMapTileLayer.Cell>();
        this.collisionSize = new Vector2(3,3);
        this.imgOffset = new Vector2(0,0);
        this.isClicked = false;
        this.counter = 0;
        this.textures = new ArrayList<Texture>();
        this.tiles2 = new ExtendedStaticTiledMapTile[(int)collisionSize.x][(int)collisionSize.y];
        this.prevCells2 = new TiledMapTileLayer.Cell[(int)collisionSize.x][(int)collisionSize.y];
//        sliceTexture(mainTexture);
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
        //TODO: make pop-up window appear with details about the building.
    }

    public Vector2 getCoords() {
        return new Vector2(x,y);
    }

    public Texture getMainTexture() {
        return mainTexture;
    }

    public void setMainTexture(Texture mainTexture) {
        this.mainTexture = mainTexture;
        sliceTexture(mainTexture);
    }

    public void changeTexture() {
        if (textures.size() > 0) {
            counter = (counter + 1) % textures.size();
//            mainTexture = null;
            mainTexture = textures.get(counter);
            sliceTexture(mainTexture);
        }
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
        tiles = new ArrayList<ExtendedStaticTiledMapTile>(); // Reset tiles
        this.mainTexture = mainTexture;
        if (mainTexture != null) {
            TextureRegion tex = new TextureRegion(mainTexture);
            TextureRegion[][] arr = tex.split(Utils.TILE_SIZE, tex.getRegionHeight());

            int TILE_HEIGHT = 64;
            for (int y = 0; y < collisionSize.y; y++) {
                for (int x = 0; x < collisionSize.x; x++) {
                    float cY = (collisionSize.x - 1 + (y - x)) * (TILE_HEIGHT/2);
                    float cX = (x + y) * Utils.TILE_SIZE/2;
                    TextureRegion current = new TextureRegion(tex, (int)cX, 0, Utils.TILE_SIZE, tex.getRegionHeight() - (int)cY);
                    ExtendedStaticTiledMapTile tile = new ExtendedStaticTiledMapTile(current);
                    tiles2[x][y] = tile;
                }
            }


//            for (int i = 0; i < tex.getRegionWidth() / Utils.TILE_SIZE; i++) {
//                ExtendedStaticTiledMapTile tile = new ExtendedStaticTiledMapTile(arr[0][i]);
//                tiles.add(tile);
//            }
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

//        // Occupy cells
//        for (int i = x; i < x + collisionSize.x; i++) {
//            for (int j = y; j < y + collisionSize.y; j++) {
//                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
//                TextureRegion tex = new TextureRegion(Assets.emptyTile);
//                ExtendedStaticTiledMapTile tile = new ExtendedStaticTiledMapTile(tex);
//                tile.setObject(this);
//                tile.setObstacle(true);
//                cell.setTile(tile);
//                layer.setCell(i, j, cell);
//            }
//        }


        for (int y1 = 0; y1 < collisionSize.y; y1++) {
            for (int x1 = 0; x1 < collisionSize.x; x1++) {
                prevCells2[x1][y1] = layer.getCell(x + x1, y + y1);
//                System.out.println(tiles2.toString());
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                tiles2[x1][y1].setObstacle(true);
                tiles2[x1][y1].setObject(this);
                cell.setTile(tiles2[x1][y1]);
                layer.setCell(x + x1, y + y1, cell);
            }
        }

        // Draw textures on the diagonal cells
//        for (ExtendedStaticTiledMapTile tile :
//                tiles) {
//            prevCells.add(layer.getCell(x + offset, y + offset)); // save previous state
//            TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
//            tile.setObstacle(true);
//            tile.setObject(this);
//            cell.setTile(tile);
//            layer.setCell(x + offset, y + offset, cell);
//            offset++;
//        }
//        System.out.println("placed!");
    }

    /**
     * Resets the tiles to their previous state
     */
    public void resetTiles() {
        int offset = 0;

        for (int y1 = 0; y1 < collisionSize.y; y1++) {
            for (int x1 = 0; x1 < collisionSize.x; x1++) {
//                prevCells2[x1][y1] = layer.getCell(x + x1, y + y1);
////                System.out.println(tiles2.toString());
//                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
//                tiles2[x1][y1].setObstacle(true);
//                tiles2[x1][y1].setObject(this);
//                cell.setTile(tiles2[x1][y1]);
                layer.setCell(x + x1, y + y1, prevCells2[x1][y1]);
            }
        }
//        for (TiledMapTileLayer.Cell cell :
//                prevCells) {
//            layer.setCell(x + offset, y + offset, cell);
//            offset++;
//        }
    }



    @Override
    public void dispose() {
        mainTexture.dispose();
    }
}
