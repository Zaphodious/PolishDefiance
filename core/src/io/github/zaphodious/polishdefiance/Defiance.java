package io.github.zaphodious.polishdefiance;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.zaphodious.polishdefiance.combat.scene.FightScene;
import io.github.zaphodious.polishdefiance.Tween.CameraTweenAccessor;
import io.github.zaphodious.polishdefiance.Tween.SpriteTweenAccessor;

public class Defiance extends ApplicationAdapter {

	FightScene scene;

    @Override
    public void create () {
        Tween.registerAccessor(Sprite.class, new SpriteTweenAccessor());
        Tween.registerAccessor(Camera.class, new CameraTweenAccessor());
        scene = new FightScene("upscaled.tmx");//"blargh.tmx");

        Gdx.input.setInputProcessor(scene.getMultiplexer());



    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        scene.render();

    }

   @Override
    public void resize(int width, int height) {
    super.resize(width, height);
    scene.getCamera().update();
        scene.resize(width,height);
    }


    @Override
    public void dispose() {
        super.dispose();
        scene.dispose();
    }
}
