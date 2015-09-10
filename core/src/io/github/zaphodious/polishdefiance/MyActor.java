package io.github.zaphodious.polishdefiance;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import io.github.zaphodious.polishdefiance.logic.map.Terrain;

public class MyActor extends Actor {

    Sprite sprite = Terrain.GRAVEL.getSprite();//new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));

    public MyActor() {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        addListener(new InputListener() {
                @Override public boolean keyDown(InputEvent event, int keycode) {
                    if (keycode == Input.Keys.RIGHT) {
                        MoveByAction mba = new MoveByAction();
                        mba.setAmount(100f,0f);
                        mba.setDuration(5f);

                        MyActor.this.addAction(mba);
                    }
                    return true;
                }
                    }
        );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }
}
