package io.github.zaphodious.polishdefiance.logic.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by achyt_000 on 8/24/2015.
 */
public enum Terrain implements Tile{
    GRASS(new Sprite(getTerrainAtlas().findRegion("light_field")),1),
    GRAVEL(new Sprite(getTerrainAtlas().findRegion("light_gravel")),3);


    Sprite terrainSprite;
    int moveCost;

    Terrain(Sprite sprite, int cost){
        this.terrainSprite = sprite;
        this.moveCost = cost;
    }

    @Override
    public Sprite getSprite() {
        return this.terrainSprite;
    }

    @Override
    public int getMovementCost() {
        return this.moveCost;
    }


    private static TextureAtlas TerrainAtlas;

    private static TextureAtlas getTerrainAtlas() {
        if (TerrainAtlas == null) {
            TerrainAtlas = new TextureAtlas("spritesheets/TerrainSprites.atlas");
        }

        return TerrainAtlas;
    }
}
