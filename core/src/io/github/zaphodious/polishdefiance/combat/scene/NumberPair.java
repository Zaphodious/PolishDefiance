package io.github.zaphodious.polishdefiance.combat.scene;

import com.badlogic.gdx.math.Vector2;

/**
 * This class is distinct from Vector2 in that it does not imply locational data.
 * @param <K> K extends Number, and both values must be of K
 */
public class NumberPair<K extends Number>{
    K x;
    K y;

    public NumberPair(K x, K y) {
        this.x = x;
        this.y = y;
    }

    public K getX() {
        return x;
    }

    public K getY() { return y; }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        return this.toString() == obj.toString();
    }

    @Override
    public String toString() {
        return this.getX() + "," + this.getY();
    }

    public <T extends Number> NumberPair<T> castThis(Class<T> classParam) {
        return new NumberPair<T>((T)this.getX(), (T)this.getY());
    }

    public static <T extends Number> NumberPair<T> getFromVector2(Vector2 vector2, Class<T> classParam) {
        return new NumberPair<T>((classParam.cast(vector2.x)) , classParam.cast(vector2.y));
    }
}
