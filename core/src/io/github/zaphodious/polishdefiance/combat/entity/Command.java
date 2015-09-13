package io.github.zaphodious.polishdefiance.combat.entity;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.zaphodious.polishdefiance.Tween.SpriteTweenAccessor;
import io.github.zaphodious.polishdefiance.combat.scene.Direction;

/**
 * Created by achyt_000 on 9/12/2015.
 */
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
            direction.getOffset(new Vector2(shootFrom.x, shootFrom.y), tracker.getFightScene().getTileDimentions().getVector2());
            //shootFrom.x += unit.getWidth()+.1;
            //String name, Texture texture, Vector2 startPosition, TweenManager tweenManager, SceneObjectTracker tracker, FightScene fightScen
            Projectile projectile = (Projectile) SceneObjectTracker.SceneObjectType.PROJECTILE.newInstance("bullet", new Texture("gun_blast.png"), new Vector2(shootFrom.x,shootFrom.y), tweenManager, tracker, tracker.getFightScene());
            projectile.setDirection(unit.getUnitFaction().getDefaultAttackDirection());
            projectile.setFaction(unit.getUnitFaction());
            if (tracker.getFightScene().getPropertyParser().getTranslatedProps(tracker.getFightScene().getCellAt(unit.getBoundingRectangle())).isFireSuperbullets()) {
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
