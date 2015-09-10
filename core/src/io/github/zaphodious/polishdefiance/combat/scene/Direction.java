package io.github.zaphodious.polishdefiance.combat.scene;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Linear;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.zaphodious.polishdefiance.combat.entity.Projectile;
import io.github.zaphodious.polishdefiance.combat.entity.SceneObjectTracker;
import io.github.zaphodious.polishdefiance.Tween.SpriteTweenAccessor;
import io.github.zaphodious.polishdefiance.ZaphUtil;

/**
 * Created by achyt_000 on 9/4/2015.
 */
public enum Direction {

    NORTH("North", 270f) {
        @Override
        public Vector2 getOffset(Vector2 start, Vector2 offsetAmount) {
            start.y += offsetAmount.y;
            return start;
        }

        @Override
        public void startProjectileTween(Projectile projectile, SceneObjectTracker tracker, TweenManager tweenManager) {
            Tween.to((Sprite) projectile, SpriteTweenAccessor.POSITION_Y, 1.5f).target(tracker.getFightScene().getWorldSize().y).ease(Linear.INOUT).start(tweenManager);
        }
    },
    SOUTH("South", 90f) {
        @Override
        public Vector2 getOffset(Vector2 start, Vector2 offsetAmount) {
            start.y -= offsetAmount.y;
            return start;
        }

        @Override
        public void startProjectileTween(Projectile projectile, SceneObjectTracker tracker, TweenManager tweenManager) {
            Tween.to((Sprite) projectile, SpriteTweenAccessor.POSITION_Y, 1.5f).target(0 - tracker.getFightScene().tileHeight).ease(Linear.INOUT).start(tweenManager);
        }
    },
    EAST("East", 0f) {
        @Override
        public Vector2 getOffset(Vector2 start, Vector2 offsetAmount) {
            start.x += offsetAmount.x;
            return start;
        }

        @Override
        public void startProjectileTween(Projectile projectile, SceneObjectTracker tracker, TweenManager tweenManager) {
            Tween.to((Sprite) projectile, SpriteTweenAccessor.POSITION_X, 1.5f).target(projectile.getBoundingRectangle().x + tracker.getFightScene().getWorldSize().x).ease(Linear.INOUT).start(tweenManager);
        }
    },
    WEST("West", 180f) {
        @Override
        public Vector2 getOffset(Vector2 start, Vector2 offsetAmount) {
            start.x -= offsetAmount.x;
            return start;
        }

        @Override
        public void startProjectileTween(Projectile projectile, SceneObjectTracker tracker, TweenManager tweenManager) {
            Tween.to((Sprite) projectile, SpriteTweenAccessor.POSITION_X, 1.5f).target((projectile.getBoundingRectangle().x - tracker.getFightScene().getWorldSize().x)).ease(Linear.INOUT).start(tweenManager);
        }

        @Override
        public void rotateSprite(Sprite sprite, SceneObjectTracker.SnapType snapType, SceneObjectTracker tracker) {
            super.rotateSprite(sprite, snapType, tracker);
            Vector2 position = ZaphUtil.getVectorFromRectangle(sprite.getBoundingRectangle());
            position.x -= tracker.getFightScene().getTileDims().x;
            position.y += tracker.getFightScene().getTileDims().y;
            sprite.setX(position.x);
            sprite.setY(position.y);
        }
    };

    String asText;
    float rotationAngle;

    Direction(String name, float rotationAngle) {
        this.rotationAngle = rotationAngle;
        this.asText = name;
    }

    public float getRotationAngle() {
        return this.rotationAngle;
    }

    public void rotateSprite(Sprite sprite, SceneObjectTracker.SnapType snapType, SceneObjectTracker tracker) {
        sprite.rotate(this.rotationAngle);

        snapType.adjustPosition(ZaphUtil.getVectorFromRectangle(sprite.getBoundingRectangle()),tracker.getFightScene());
    }

    public static Direction getForString(String string) {
        for (Direction direction : Direction.values()) {
            String caps = string.toUpperCase();
            if (direction.asText == caps) {
                return direction;
            }
        }
        return EAST;
    }


    public abstract Vector2 getOffset(Vector2 start, Vector2 offsetAmount);
    public abstract void startProjectileTween(Projectile projectile, SceneObjectTracker tracker, TweenManager tweenManager);

}
