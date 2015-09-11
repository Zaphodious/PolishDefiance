package io.github.zaphodious.polishdefiance.combat.scene;

import com.badlogic.gdx.graphics.Texture;
import com.sun.media.jfxmediaimpl.MediaDisposer;
import io.github.zaphodious.polishdefiance.ZaphUtil;

import java.util.Map;

/**
 * Created by achyt_000 on 9/9/2015.
 */
public class TextureFactory implements MediaDisposer.Disposable{
    Map<String, Texture> cachedTextures;


    public TextureFactory() {
        cachedTextures = ZaphUtil.newMap();
    }

    public Texture getTexture(String resourceLocation) {
        if (cachedTextures.containsKey(resourceLocation)) {
            return cachedTextures.get(resourceLocation);
        } else {
            Texture newTexture = new Texture(resourceLocation);
            cachedTextures.put(resourceLocation,newTexture);
            return newTexture;
        }
    }


    @Override
    public void dispose() {
        for (Texture texture : cachedTextures.values()) {
            texture.dispose();
        }

        cachedTextures.clear();
    }
}
