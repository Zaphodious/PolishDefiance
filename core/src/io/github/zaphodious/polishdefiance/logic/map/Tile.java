package io.github.zaphodious.polishdefiance.logic.map;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by achyt_000 on 8/23/2015.
 */
public interface Tile {

    public Sprite getSprite();

    public int getMovementCost();

}
