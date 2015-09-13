package io.github.zaphodious.polishdefiance.combat.scene;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Disposable;
import io.github.zaphodious.polishdefiance.ZaphUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by achyt_000 on 9/9/2015.
 */
public class TiledMapPropertyParser implements Disposable {

    TiledMap tiledMap;

    TiledMapTileLayer featureLayer;
    TiledMapTileLayer baseLayer;
    TiledMapTileLayer bunkerLayer;
    TiledMapTileLayer.Cell selectedCell;
    MapProperties props;

    Map<NumberPair<Integer>, PropBundle> bundleCache;

    public TiledMapPropertyParser(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
        this.explodeMap();
        setTileForInspection(1, 1);
        bundleCache = ZaphUtil.newMap();
    }

    public MapProperties getMapProperties() {
        return tiledMap.getProperties();
    }

    private void explodeMap() {
        featureLayer = (TiledMapTileLayer) tiledMap.getLayers().get(LayerNames.FEATURES_LAYER);
        baseLayer = (TiledMapTileLayer) tiledMap.getLayers().get(LayerNames.BASE_LAYER);
        bunkerLayer = (TiledMapTileLayer) tiledMap.getLayers().get(LayerNames.BUNKER_LAYER);
    }

    public TiledMap getMap() {
        return this.tiledMap;
    }

    public NumberPair<Integer> getTileDimentions() {
        return new NumberPair<Integer>(tiledMap.getProperties().get("tileheight", Integer.class),tiledMap.getProperties().get("tilewidth", Integer.class));
    }

    public NumberPair<Integer> getMapDimentionsInTiles() {
        return new NumberPair<Integer>(tiledMap.getProperties().get("height", Integer.class),tiledMap.getProperties().get("width", Integer.class));
    }

    private void setTileForInspection(int tileX, int tileY) {


        selectedCell = bunkerLayer.getCell(tileX, tileY);

        if (selectedCell == null) {
            selectedCell = featureLayer.getCell(tileX, tileY);
        }

        if (selectedCell == null) {
            selectedCell = baseLayer.getCell(tileX, tileY);
            if (selectedCell == null) {
                System.out.println("yep, it's here");
            }
        }


        try {
            props = selectedCell.getTile().getProperties();
        } catch (Exception e) {
            System.out.println("The tile that was accessed unsuccessfully is " + tileX + "," + tileY);
            e.printStackTrace();
            setTileForInspection(1,1);
        }

    }

    public PropBundle getTranslatedProps(NumberPair<Integer> location) {
        return getTranslatedProps(location.getX(), location.getY());
    }

    public PropBundle getTranslatedProps(int tileX, int tileY) {

        NumberPair<Integer> tileNumber = new NumberPair<Integer>(tileX,tileY);
        if (bundleCache.containsKey(tileNumber)) {
            return bundleCache.get(tileNumber);
        } else {
            setTileForInspection(tileX,tileY);
            PropBundle toReturn = new PropBundle();
            populatePropBundle(toReturn);
            return toReturn;
        }

    }

    private void populatePropBundle(PropBundle propBundle) {
        String bulletDodgeString = props.get(PropertyNames.BULLET_DODGE_CHANCE, String.class);
        String hidesUnitString = props.get(PropertyNames.HIDES_UNITS, String.class);
        String movementSlowAmountString = props.get(PropertyNames.MOVEMENT_SLOW_AMOUNT, String.class);
        String firesSuperBulletsString = props.get(PropertyNames.FIRE_SUPER_BULLETS, String.class);

        propBundle.bulletDodgeChance = Float.valueOf(bulletDodgeString);
        propBundle.hidesUnits = Boolean.valueOf(hidesUnitString);
        propBundle.movementSlowAmount = Integer.valueOf(movementSlowAmountString);
        propBundle.movementSlowDirection = parseDirections();
        propBundle.fireSuperbullets = Boolean.valueOf(firesSuperBulletsString);
    }

    private List<Direction> parseDirections(){
        List<Direction> toReturn = ZaphUtil.newList();

        String initialProperty = (String) props.get(PropertyNames.MOVEMENT_SLOW_DIRECTION);

        List<String> explodedProperty = Arrays.asList(initialProperty.split(","));

        for (String string : explodedProperty) {
            toReturn.add(Direction.getForString(string));
        }

        return toReturn;
    }

    @Override
    public void dispose() {
        tiledMap.dispose();
    }


    private static class PropertyNames {
        private static final String BULLET_DODGE_CHANCE = "BulletDodgeChance";
        private static final String HIDES_UNITS = "HidesUnits";
        private static final String MOVEMENT_SLOW_AMOUNT = "MovementSlowAmount";
        private static final String MOVEMENT_SLOW_DIRECTION = "MovementSlowDirection";
        private static final String FIRE_SUPER_BULLETS = "fireSuperBullets";
    }

    private static class LayerNames {
        private static final String FEATURES_LAYER = "features";
        private static final String BASE_LAYER = "base";

        private static final String BUNKER_LAYER = "bunkers";
    }

   /* private final class TileNumber {
        int tileX;
        int tileY;

        public TileNumber(int tileX, int tileY) {
            this.tileX = tileX;
            this.tileY = tileY;
        }

        public int getTileX() {
            return tileX;
        }

        public int getTileY() {
            return tileY;
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof TileNumber)) {
                return false;
            }
            return toString().equals(obj.toString());
        }

        @Override
        public String toString() {
            return "The tileX is " + tileX + " while the TileY is " + tileY;
        }
    }*/

    public class PropBundle {
        float bulletDodgeChance;
        boolean hidesUnits;
        int movementSlowAmount;
        List<Direction> movementSlowDirection;
        boolean fireSuperbullets;

        private PropBundle() {
        }

        public float getBulletDodgeChance() {
            return bulletDodgeChance;
        }

        public boolean isHidesUnits() {
            return hidesUnits;
        }

        public int getMovementSlowAmount() {
            return movementSlowAmount;
        }

        public List<Direction> getMovementSlowDirection() {
            return movementSlowDirection;
        }

        public boolean isFireSuperbullets() {
            return fireSuperbullets;
        }
    }
}

