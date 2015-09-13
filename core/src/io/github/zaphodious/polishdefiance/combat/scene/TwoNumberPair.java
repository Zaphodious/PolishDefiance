package io.github.zaphodious.polishdefiance.combat.scene;

import com.badlogic.gdx.math.Vector2;
import javafx.util.Pair;

/**
 * This class is distinct from Vector2 in that it does not imply locational data.
 * @param <K> K extends Number, and both values must be of K
 */
public class TwoNumberPair<K extends Number>{
    private Pair<K,K> backingPair;

    public TwoNumberPair(K x, K y) {
        backingPair = new Pair<K,K>(x,y);
    }

    public K getX() {
        return backingPair.getKey();
    }

    public K getY() {
        return backingPair.getValue();
    }

    @Override
    public int hashCode() {
        return backingPair.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return backingPair.equals(obj);
    }

    @Override
    public String toString() {
        return this.getX() + "," + this.getY();
    }

    public <T extends Number> TwoNumberPair<T> castThis(Class<T> classParam) {
        return new TwoNumberPair<T>((T)this.getX(), (T)this.getY());
    }

    public static <T extends Number> TwoNumberPair<T> getFromVector2(Vector2 vector2, Class<T> classParam) {
        return new TwoNumberPair<T>((classParam.cast(vector2.x)) , classParam.cast(vector2.y));
    }
}
