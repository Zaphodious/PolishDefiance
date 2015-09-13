package io.github.zaphodious.polishdefiance.combat.entity;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.zaphodious.polishdefiance.combat.scene.NumberPair;

/**
 * Created by achyt_000 on 9/8/2015.
 */
public abstract class SceneObject extends Sprite {

    String name;

    TweenManager tweenManager;
    SceneObjectTracker tracker;
    boolean hasMoved;



    private Faction unitFaction;
    private Rectangle boundingRectangleCache; //should only be accesed through "getBoundingRectangle()"

    public SceneObject(String name, Texture texture, Vector2 startPosition, TweenManager tweenManager, SceneObjectTracker tracker) {
        super(texture);
        this.setPosition(startPosition);
        this.name = name;
        this.tweenManager = tweenManager;
        this.tracker = tracker;
        boolean hasMoved = true;
        this.setFaction(Faction.NAZI);
    }

    public void setPosition(Vector2 position) {
        this.setPosition(position.x, position.y);
    }

    @Override
    public void setPosition(float x, float y) {
        hasMoved = true;
        super.setPosition(x, y);
    }

    @Override
    public void setX(float x) {
        hasMoved = true;
        super.setX(x);
    }

    @Override
    public void setY(float y) {
        hasMoved = true;
        super.setY(y);
    }
/*
    *//**
     * Modified to cache the bounding rectangle when the unit has not been modified.
     * @return The bounding rectangle
     *//*
    @Override
    public Rectangle getBoundingRectangle() {
        if (hasMoved) {
            boundingRectangleCache = super.getBoundingRectangle();
        }
        hasMoved = false;
        return boundingRectangleCache;
    }*/

    public void setFaction(Faction faction) {
        this.unitFaction = faction;
        this.setColor(getUnitFaction().getAssociatedColor());
    }

    public boolean isIntersectedBy(Vector2 selection) {

        Rectangle boundingRect = this.getBoundingRectangle();

        System.out.println("Vector selection= " + selection + "and the bounding rectangle is " + boundingRect);

        if (selection.x < boundingRect.x) {
            return false;
        }

        if (selection.y < boundingRect.y) {
            return false;
        }

        if (selection.y > boundingRect.y + this.getHeight()) {
            return false;
        }

        if (selection.x > boundingRect.x + this.getWidth()) {
            return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = name;
    }

    public Faction getUnitFaction() {
        return unitFaction;
    }

    public void setUnitFaction(Faction unitFaction) {
        this.unitFaction = unitFaction;
    }

    public void dispose() {
        this.getTexture().dispose();
    }

    public boolean isThisOutOfBounds() {
        boolean toReturn = false;
        Rectangle thisBoundingRectangle = this.getBoundingRectangle();
        NumberPair<Integer> worldSize = tracker.getFightScene().getWorldSize();

        if (thisBoundingRectangle.x >= worldSize.getX()) {
            toReturn = true;
        }

        if (thisBoundingRectangle.y >= worldSize.getY()) {
            toReturn = true;
        }

        if (thisBoundingRectangle.x < 0) {
            toReturn = true;
        }

        if (thisBoundingRectangle.y < 0) {
            toReturn = true;
        }

        return toReturn;
    }

    public abstract boolean isKill();




}
