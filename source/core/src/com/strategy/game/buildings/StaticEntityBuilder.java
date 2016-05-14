package com.strategy.game.buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.strategy.game.Assets;
import com.strategy.game.ExtendedStaticTiledMapTile;
import com.strategy.game.Utils;
import com.strategy.game.screens.GameScreen;
import com.strategy.game.world.World;

/**
 * Handles the creation and placement of static entities (e.g. buildings)
 * // TODO: 14/05/2016 Add deletion
 */
public class StaticEntityBuilder {
    private GameScreen gameScreen;
    private TiledMapTileLayer buildingsLayer; // the buildings layer
    private TiledMapTileLayer selectionLayer; // contains the selected building
    private TiledMapTileLayer influenceLayer;
    private MapEntity selectedEntity;
    private World world;
    private TiledMapTileLayer gridLayer;


    public StaticEntityBuilder(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.world = gameScreen.getWorld();
        this.buildingsLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Buildings");
        this.selectionLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Selection");
        this.influenceLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Influence");
        this.selectionLayer.setOpacity(0.5f);
        this.influenceLayer.setOpacity(0.2f);
        this.gridLayer = (TiledMapTileLayer) gameScreen.getMap().getLayers().get("Grid");
        this.gridLayer.setVisible(false);
        this.gridLayer.setOpacity(0.2f);
    }

    public MapEntity getSelectedEntity() {
        return selectedEntity;
    }

    /**
     * Toggles the selection
     * @param entity: the entity to be placed
     */
    public void toggleSelectEntity(MapEntity entity) {
        if (entity != null) {
            selectedEntity = entity;
            gridLayer.setVisible(true);
            influenceLayer.setVisible(true);
        }
        else {
            selectedEntity = null;
            gridLayer.setVisible(false);
            influenceLayer.setVisible(false);
        }
    }

    public void untoggleSelectEntity() {
        selectedEntity = null;
        gridLayer.setVisible(false);
        influenceLayer.setVisible(false);
    }


    /**
     * Draws the influence area of all placed buildings.
     * (Checks whether each tile has any buildings nearby and if so, highlights the tile)
     */
    private void drawInfluence() {
        for (int i = 0; i < influenceLayer.getWidth(); i++) {
            for (int j = 0; j < influenceLayer.getHeight(); j++) {
                TiledMapTileLayer.Cell influenceCell = new TiledMapTileLayer.Cell();
                TiledMapTileLayer.Cell buildingsCell = buildingsLayer.getCell(i,j);
                boolean condition = buildingsCell != null && buildingsCell.getTile() != null;
                if (condition) {
                    TiledMapTile buildingTile = buildingsCell.getTile();
                    if (buildingTile instanceof ExtendedStaticTiledMapTile) {
                        if (((ExtendedStaticTiledMapTile) buildingTile).getBuildingsNearby() > 0) {
                            ExtendedStaticTiledMapTile influenceTile = new ExtendedStaticTiledMapTile(new TextureRegion(Assets.redTile));
                            influenceCell.setTile(influenceTile);
                            influenceLayer.setCell(i, j, influenceCell);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Places the selected building onto the buildings layer.
     * @param x : tile coordinate
     * @param y : tile coordinate
     */
    public void placeSelectedEntity(int x, int y, boolean forced) {
        if (selectedEntity != null) {
            boolean isSpaceFree = true;
            boolean isInInfluenceRadius = false;

            // collision when:
            // - tile has obstacle property
            // - tile has object

            // Check if cells are occupied
            for (int i = x; i < x + selectedEntity.getCollisionSize().x; i++) {
                for (int j = y; j < y + selectedEntity.getCollisionSize().y; j++) {
                    TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i,j);
                    boolean condition = cell != null && cell.getTile() != null;
                    if (condition) {
                        TiledMapTile tile = cell.getTile();
                        boolean hasObject = tile instanceof ExtendedStaticTiledMapTile
                                && ((ExtendedStaticTiledMapTile) tile).getObject() != null;
                        boolean hasProperty = tile.getProperties().get("obstacle", Boolean.class) != null;
                        if (hasObject || hasProperty) {
                            isSpaceFree = false;
                            break;
                        }
                    }
                }
            }

//            int influenceRadius = selectedEntity.getInfluenceRadius();
            int startX = x - (int)selectedEntity.getCollisionSize().x;
            int startY = y - (int)selectedEntity.getCollisionSize().y;
            int endX = x + (int)selectedEntity.getCollisionSize().x;
            int endY = y + (int)selectedEntity.getCollisionSize().y;

            // Check whether it's in the area of influence of another building
            // TODO: 14/05/2016 Add exceptions for specific buildings (e.g. walls)
            for (int i = startX; i < endX; i++) {
                for (int j = startY; j < endY; j++) {
                    TiledMapTileLayer.Cell cell = buildingsLayer.getCell(i, j);
                    boolean isExtended = cell != null
                            && cell.getTile() instanceof ExtendedStaticTiledMapTile;
                    if (isExtended) {
                        boolean hasBuildingsNearby =
                                ((ExtendedStaticTiledMapTile) cell.getTile()).getBuildingsNearby() > 0;
                        if (hasBuildingsNearby) {
                            isInInfluenceRadius = true;
                            break;
                        }
                    }
                }
            }

            // Used for placing buildings at the start
            //TODO: do it in a different way possibly?
            if (forced) isInInfluenceRadius = true;

            Sound sound = Assets.hit;

            if (isSpaceFree && isInInfluenceRadius) {
                long id = sound.play(0.5f);
                sound.setPitch(id, 0.75f);

                try {
                    // Makes a new instance of the proper subclass
                    // TODO: check if this creates other problems.
                    Texture currentTex = selectedEntity.getMainTexture();
                    selectedEntity = selectedEntity.getClass().newInstance();
                    selectedEntity.setMainTexture(currentTex);

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                selectedEntity.placeOnLayer(buildingsLayer, x, y);
                this.world.getStaticEntities().add(selectedEntity);

                if (this.getSelectedEntity() instanceof House) //TODO: abstract a bit
                    world.getResourceHandler().incrementWoodCounter(-100);
                else if (this.getSelectedEntity() instanceof Castle)
                    world.getResourceHandler().incrementRockCounter(-100);


                //remove resources

            } else {
                long id = sound.play(0.5f);
                sound.setPitch(id, 5f);
            }
        }
    }

    public void rotate() {
        if (selectedEntity != null) {
            selectedEntity.changeTexture();
        }
//        System.out.println(selectedEntity.getTiles().toString());
    }

    /**
     * Render the building being moved around with respect to the camera.
     * @param camera scene camera
     */
    public void renderSelection(OrthographicCamera camera) {
        int screenX = Gdx.input.getX();
        int screenY = Gdx.input.getY();




        Vector3 touch = new Vector3(screenX, screenY, 0);
        Vector3 pickedTile = Utils.cartesianToIso(touch, camera);
        if (selectedEntity != null) {
            drawInfluence(); // Refresh the influence area view
            selectedEntity.placeOnLayer(selectionLayer, (int)pickedTile.x, (int)pickedTile.y);
        }
    }

    /**
     * Resets the tiles to the previous state. Called after the render() method.
     */
    public void clear() {
        if (selectedEntity != null) {
            selectedEntity.resetTiles();
        }
    }
}
