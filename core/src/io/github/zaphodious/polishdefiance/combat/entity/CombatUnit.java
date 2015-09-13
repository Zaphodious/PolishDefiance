package io.github.zaphodious.polishdefiance.combat.entity;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.zaphodious.polishdefiance.combat.scene.Direction;

/**
 * Created by achyt_000 on 9/4/2015.
 */
public class CombatUnit extends SceneObject {


    Vector2 moveAmounts;

    Command currentCommand;
    Direction currentDirection;

    int health;

    CombatUnit(String name, Texture texture, Vector2 startPosition, TweenManager tweenManager, SceneObjectTracker tracker) {
        super(name, texture, startPosition, tweenManager, tracker);
        this.moveAmounts = new Vector2(1,1);
        currentCommand = Command.MOVE;
        currentDirection = Direction.EAST;
        health = 10;
    }

    public CombatUnit(String name, Texture texture, TweenManager tweenManager, SceneObjectTracker tracker) {
        this(name,texture,new Vector2(),tweenManager,tracker);
    }





    public void setMoveHeight(float moveHeight) {
        this.moveAmounts.y = moveHeight;
    }

    public void setMoveWidth(float moveWidth) {
        this.moveAmounts.x = moveWidth;
    }

    public CombatUnit setMoveAmounts(Vector2 newMoveAmounts) {
        this.moveAmounts = newMoveAmounts;
        return this;
    }

    public void command(Command command, Direction direction) {
        this.currentCommand = command;
        this.currentDirection = direction;

    }

    public Command getCurrentCommand() {
        return this.currentCommand;
    }

    public void advanceRound() {
        this.currentCommand.instructUnit(this, currentDirection, tweenManager, tracker);
    }





    public int getHealth() {
        return health;
    }

    public void damage(int damage) {
        this.health -= damage;
    }

    public boolean isKill() {
        return this.health <= 0;
    }
}
