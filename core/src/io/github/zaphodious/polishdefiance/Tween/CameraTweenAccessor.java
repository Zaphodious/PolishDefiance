package io.github.zaphodious.polishdefiance.Tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by achyt_000 on 9/2/2015.
 */
public class CameraTweenAccessor implements TweenAccessor<OrthographicCamera> {



    public static final int POSITION_X = 1;
    public static final int POSITION_Y = 2;
    public static final int POSITION_XY = 3;
    public static final int ZOOM = 4;

    @Override
    public int getValues(OrthographicCamera target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case POSITION_X: returnValues[0] = target.position.x; return 1;
            case POSITION_Y: returnValues[0] = target.position.y; return 1;
            case POSITION_XY:
                returnValues[0] = target.position.x;
                returnValues[1] = target.position.y;
                return 2;
            case ZOOM: returnValues[0] = target.zoom;
                return 1;
            default: assert false; return -1;
        }
    }


    @Override
    public void setValues(OrthographicCamera target, int tweenType, float[] newValues) {
        Vector3 newPosition = target.position;
        switch (tweenType) {
            case POSITION_X: newPosition.x= (newValues[0]); break;
            case POSITION_Y: newPosition.y= (newValues[0]); break;
            case POSITION_XY:
                newPosition.x= (newValues[0]);
                newPosition.y= (newValues[0]);
                break;
            case ZOOM: target.zoom = newValues[0];
            default: assert false; break;
        }
    }
}
