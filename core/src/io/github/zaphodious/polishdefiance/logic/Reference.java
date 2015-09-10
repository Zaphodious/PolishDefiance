package io.github.zaphodious.polishdefiance.logic;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by achyt_000 on 8/24/2015.
 */
public class Reference {

    private static TextureAtlas TerrainAtlas;

    public static TextureAtlas getTerrainAtlas() {
        if (TerrainAtlas == null) {
            TerrainAtlas = new TextureAtlas("spritesheets/TerrainSprites.atlas");
        }

        return TerrainAtlas;
    }

}
