package io.github.zaphodious.polishdefiance.combat.entity;

import com.badlogic.gdx.graphics.Color;
import io.github.zaphodious.polishdefiance.combat.scene.Direction;

/**
 * Created by achyt_000 on 9/9/2015.
 */
public enum Faction {

    POLISH(Color.GOLD, Direction.EAST),
    NAZI(Color.RED, Direction.WEST);

    private Color associatedColor;
    private Direction defaultAttackDirection;

    Faction(Color associatedColor, Direction defaultAttackDirection) {
        this.associatedColor = associatedColor;
        this.defaultAttackDirection = defaultAttackDirection;
    }

    public Direction getDefaultAttackDirection() {
        return defaultAttackDirection;
    }

    public Color getAssociatedColor() {
        return associatedColor;
    }
}
