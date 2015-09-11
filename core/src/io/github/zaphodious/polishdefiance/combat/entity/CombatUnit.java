package io.github.zaphodious.polishdefiance.combat.entity;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.zaphodious.polishdefiance.combat.scene.Direction;
import io.github.zaphodious.polishdefiance.Tween.SpriteTweenAccessor;

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



    public enum Command {

        MOVE {
            @Override
            public void instructUnit(CombatUnit unit, Direction direction, TweenManager tweenManager, SceneObjectTracker tracker) {
                Vector2 result = direction.getOffset(new Vector2(unit.getX(), unit.getY()),unit.moveAmounts);

                Tween.to(unit, SpriteTweenAccessor.POSITION_XY, .5f).target(result.x, result.y).start(tweenManager);
            }
        },
        ATTACK{
            @Override
            public void instructUnit(CombatUnit unit, Direction direction, TweenManager tweenManager, SceneObjectTracker tracker) {
                Rectangle shootFrom = new Rectangle(unit.getBoundingRectangle());
                direction.getOffset(new Vector2(shootFrom.x, shootFrom.y), tracker.getFightScene().getTileDims());
                //shootFrom.x += unit.getWidth()+.1;
                //String name, Texture texture, Vector2 startPosition, TweenManager tweenManager, SceneObjectTracker tracker, FightScene fightScen
                Projectile projectile = (Projectile) SceneObjectTracker.SceneObjectType.PROJECTILE.newInstance("bullet", new Texture("gun_blast.png"), new Vector2(shootFrom.x,shootFrom.y), tweenManager, tracker, tracker.getFightScene());
                projectile.setDirection(unit.getUnitFaction().getDefaultAttackDirection());
                projectile.setFaction(unit.getUnitFaction());
                if (tracker.getFightScene().getTilePropertyParser().getTranslatedProps(tracker.getFightScene().getCellAt(unit.getBoundingRectangle())).isFireSuperbullets()) {
                    projectile.setPower(20);
                    projectile.scale(2f);
                }
                projectile.fireOff();
                try {
                    tracker.addObjectToScene(projectile, SceneObjectTracker.SceneObjectType.PROJECTILE.getSceneObjectClass(), SceneObjectTracker.SnapType.FREE_FLOAT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },
        DEFEND{
            @Override
            public void instructUnit(CombatUnit unit, Direction direction, TweenManager tweenManager, SceneObjectTracker tracker) {

            }
        },
        FLEE{
            @Override
            public void instructUnit(CombatUnit unit, Direction direction, TweenManager tweenManager, SceneObjectTracker tracker) {

            }
        };

        public abstract void instructUnit(CombatUnit unit, Direction direction, TweenManager tweenManager, SceneObjectTracker tracker);

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
