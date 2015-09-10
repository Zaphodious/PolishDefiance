package io.github.zaphodious.polishdefiance.combat.entity;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.zaphodious.polishdefiance.combat.scene.Direction;
import io.github.zaphodious.polishdefiance.combat.scene.FightScene;

/**
 * Created by achyt_000 on 9/8/2015.
 */
public class Projectile extends SceneObject {

    FightScene fightScene;
    boolean isThisKill;
    Direction direction;


    Projectile(String name, FightScene fightScene, Texture texture, Vector2 startPosition, TweenManager tweenManager, SceneObjectTracker tracker, Faction faction) {
        super(name, texture, startPosition, tweenManager, tracker);
        this.setFaction(faction);
        this.fightScene = fightScene;
        //Tween.to((Sprite) this, SpriteTweenAccessor.POSITION_X, 4f).target(fightScene.getWorldSize().x).ease(Linear.INOUT).start(tweenManager);
        isThisKill = false;
        setDirection(Direction.EAST);
    }


    @Override
    public boolean isKill() {
        if (this.isThisOutOfBounds()) {
            isThisKill = true;
        }
        return isThisKill;
    }

    public void thisIsKillNow() {
        isThisKill = true;
    }

    public int getPower() {
        return 5;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        direction.rotateSprite(this, SceneObjectTracker.SnapType.SNAP_TO_GRID,tracker);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void fireOff() {
        direction.startProjectileTween(this,tracker,tweenManager);
    }
}
