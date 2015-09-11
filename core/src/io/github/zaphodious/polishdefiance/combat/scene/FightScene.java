package io.github.zaphodious.polishdefiance.combat.scene;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Back;
import aurelienribon.tweenengine.equations.Bounce;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import io.github.zaphodious.polishdefiance.combat.entity.CombatUnit;
import io.github.zaphodious.polishdefiance.combat.entity.Faction;
import io.github.zaphodious.polishdefiance.combat.entity.SceneObjectTracker;
import io.github.zaphodious.polishdefiance.Tween.CameraTweenAccessor;
import javafx.util.Pair;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by achyt_000 on 8/28/2015.
 */
public class FightScene implements InputProcessor, GestureDetector.GestureListener, Disposable {

    OrthographicCamera camera;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    TilePropertyParser parser;

    SceneObjectTracker tracker;

    int mapHeightInTiles;
    int mapWidthInTiles;
    int tileWidth;
    int tileHeight;
    int tileOnScreenHeight;
    int tileOnScreenWidth;

    float differenceRatio;

    Vector2 worldSize;
    Vector2 graphicDims;
    Vector2 previousLocation;



    GestureDetector gestureDetector;
    FightUI fightUI;
    InputMultiplexer multiplexer = new InputMultiplexer();

    float menuBarHeight;
    //Vector2 menuAreaOnScreen;

    TweenManager tweenManager;

    SpriteBatch batch;
    BitmapFont font;
    String textToRender;
    String propsToRender;

    FreeTypeFontGenerator fontGenerator;
    FreeTypeFontGenerator.FreeTypeFontParameter fontParam;

    Vector3 totalScrollDelta;

    boolean isCombatMenuShowing;

    private Random rng;

    public FightScene(String mapFilePath) {
        tweenManager = new TweenManager();
        tracker = new SceneObjectTracker(this,tweenManager);
        //tiledMap = new TmxMapLoader().load("TestMap3.tmx");
        tiledMap = new TmxMapLoader().load(mapFilePath);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        textToRender = "Map Test. Tap any tile to see its properties.";
        propsToRender = "No tile has been selected yet.";






        //menuAreaOnScreen = new Vector2();

        totalScrollDelta = new Vector3();






        System.out.println("on screen width and height of buttons are " + tileOnScreenWidth + ", " +  tileOnScreenHeight);

        batch = new SpriteBatch();


        fightUI = new FightUI(this);

        /*
        So that the window can be resized easily on Desktop, we do all of the graphical
        set-up in the resize function.
         */
        this.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        /*CombatUnit firstUnit = new CombatUnit("helm", new Texture("brutal-helm.png"), tweenManager).setMoveAmounts(new Vector2(this.tileHeight, this.tileWidth));
        units.add(firstUnit);
        firstUnit.setSize(tileWidth, tileHeight);*/


        System.out.println("on screen width and height of buttons are " + tileOnScreenWidth + ", " + tileOnScreenHeight);


        gestureDetector = new GestureDetector(this);



        multiplexer.addProcessor(fightUI);
        multiplexer.addProcessor(gestureDetector);
        multiplexer.addProcessor(this);




        parser = new TilePropertyParser(tiledMap);



        isCombatMenuShowing = false;

        int i = 0;
        int j = 0;

            for (i = 0; i > mapHeightInTiles; i++) {
                for (j = 0; j > mapWidthInTiles; j++) {
                    System.out.println(parser.getTranslatedProps(i,j).getBulletDodgeChance());
                }
            }

    }

    public Vector2 getWorldSize() {
        return worldSize;
    }

    public boolean showCombatMenu() {
        if (isCombatMenuShowing) {
            System.out.println("menu put away");
            isCombatMenuShowing = false;
        } else {
            System.out.println("menu showed!");
            isCombatMenuShowing = true;
        }

        return isCombatMenuShowing;

    }



    public void resize(int width, int height) {
        /*
        first, get the pixels shown on screen.
         */
        graphicDims = new Vector2(width, height);

        /*
         then, get the properties from the map
         */
        MapProperties props = tiledMap.getProperties();
        mapHeightInTiles = props.get("height", Integer.class);
        mapWidthInTiles = props.get("width", Integer.class);
        tileWidth = props.get("tilewidth", Integer.class);
        tileHeight = props.get("tileheight", Integer.class);

        System.out.println("tile width = " + tileWidth + " and tile height = " + tileHeight);
        worldSize = new Vector2(mapWidthInTiles * tileWidth, (mapHeightInTiles * tileHeight) + menuBarHeight);
        System.out.println("menuBarHeight = " + menuBarHeight + "and total world height = " + worldSize.y);

        /*
        Make the camera, and set it so that it projects correctly for whatever aspect ratio
        happens to be passed in.
         */


        Vector2 origCamPos = (camera != null) ? new Vector2(camera.position.x,camera.position.y):new Vector2(-1,-1);

        camera = new OrthographicCamera();
        float aspectRatio = graphicDims.x/graphicDims.y;
        camera.setToOrtho(false, (worldSize.y * aspectRatio), worldSize.y);




        /*
        The differenceRatio is the ratio between the pixels shown on screen
        and the units that we're using to do our calculations.
         */
        differenceRatio = camera.viewportWidth / this.graphicDims.x;
        menuBarHeight = this.tileHeight;//(mapHeightInTiles * tileHeight)* ( ZaphUtil.GOLDEN_MEAN_REVERSE);//tileHeight * 3;
        System.out.println("Menu bar height after math with the ratio = " + menuBarHeight / differenceRatio);
        System.out.println("and window width = " + Gdx.graphics.getWidth());
        camera.position.y -= menuBarHeight/2;
        //menuAreaOnScreen = new Vector2(Gdx.graphics.getWidth(),menuBarHeight / differenceRatio);

        //camera.zoom = 100f;

        Tween.from(camera, CameraTweenAccessor.ZOOM, 1f).target(3F)
                .start(tweenManager).ease(Bounce.OUT);

        tileOnScreenHeight = (int) (tileHeight / differenceRatio);
        System.out.println("tileOnScreenHeight = " + tileOnScreenHeight);
        tileOnScreenWidth = (int) (tileWidth / differenceRatio);
        System.out.println("tileOnScreenWidth = " + tileOnScreenWidth);

        if (origCamPos.x != -1) {
            camera.position.x = origCamPos.x;
            camera.position.y = origCamPos.y;

        }







        /*
        font stuff. we get a generator, then we make a font using our current visual
        information.
         */


        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Xolonium-Regular.ttf"));
        fontParam = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParam.color = Color.WHITE;
        fontParam.borderColor = Color.BLACK;
        fontParam.borderWidth = .5f;
        fontParam.size = (int)(graphicDims.x / 32);
        if (font != null) {
            font.dispose();
        }
        font = fontGenerator.generateFont(fontParam);



        //fightUI.updateUIDims();
        //fightUI.updateCamera();
        fightUI.makeUI();

    }

    public void render() {
        //this.act();
        putCameraInBounds();
        tweenManager.update(Gdx.graphics.getDeltaTime());
        camera.update();


        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        Matrix4 originalMatrix = batch.getProjectionMatrix();
        batch.setProjectionMatrix(camera.combined);

        tracker.processInteractions();

        batch.begin();

        tracker.drawObjects(batch);

        batch.end();


        fightUI.getBatch().begin();
        /*font.draw(fightUI.getBatch(), textToRender, 0, graphicDims.y);
        font.draw(fightUI.getBatch(), propsToRender, 0, graphicDims.y - font.getXHeight() * 3);*/
        fightUI.getBatch().end();

        fightUI.act();
        fightUI.draw();
    }

    public void dispose() {
        tiledMap.dispose();
        batch.dispose();
        font.dispose();
        tracker.dispose();
        fightUI.dispose();

    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public TiledMap getMap() {
        return this.tiledMap;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if(keycode == Input.Keys.LEFT)
            camera.translate(-20,0);
        if(keycode == Input.Keys.RIGHT)
            camera.translate(20,0);
        if(keycode == Input.Keys.UP)
            camera.translate(0,20);
        if(keycode == Input.Keys.DOWN)
            camera.translate(0,-20);
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        //System.out.println("Touch at " + screenX + " " + screenY);
        previousLocation = new Vector2(screenX,screenY);
        Vector3 touchLocation = new Vector3(previousLocation, 0);
        Vector3 inWorldTouchLocation = camera.unproject(touchLocation);
        //testSprite.setPosition((inWorldTouchLocation.x)-((inWorldTouchLocation.x)%(tileWidth)),(inWorldTouchLocation.y)-((inWorldTouchLocation.y)%(tileHeight)));

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //return scrollScreen(screenX, screenY);
        return false;
    }

    private Vector2 getChange(int x, int y) {
        return new Vector2((previousLocation.x - x)*differenceRatio, (-(previousLocation.y - x)*differenceRatio));
    }

    private boolean scrollScreen(float deltaX, float deltaY) {
        return scrollScreen(deltaX,deltaY,false);
    }

    /**
     *
     * @param deltaX
     * @param deltaY
     * @return
     */
    private boolean scrollScreen(float deltaX, float deltaY, boolean tweenThis) {
/*
        *//*
        The change is multiplied by the ratio of the difference between the number of pixels
        used by the camera, and the camera's units. (differenceRatio, which is calculated in the
        constructor.)
         *//*
        Vector2 change = new Vector2((previousLocation.x - screenX)*differenceRatio, (-(previousLocation.y - screenY)*differenceRatio));

        //System.out.println("Change is " + change.x);\
        //Vector2 cameraPosition = new Vector2(camera.position.x,camera.position.y);
        //System.out.println("Camera position is " + cameraPosition);

        System.out.println("Scroll started. Change = " + change);

        //System.out.println("Ratio is " + differenceRatio);
        *//*
        If the camera's initial position is within the bounds of the map, allow the screen
        to scroll
        *//*

        this.pan(screenX,screenY,change.x,change.y);

        *//*
        Update the previousLocation, for the sake of the next drag event.
         *//*
        previousLocation = new Vector2(screenX, screenY);*/


        if (tweenThis) {
            Tween.to(camera,CameraTweenAccessor.POSITION_X,.5f).target(camera.position.x + deltaX).start(tweenManager);
            this.putCameraInBounds();
            return false;

        }
        if (deltaX < 0 && camera.position.x > camera.viewportWidth / 2) {

                camera.translate(deltaX, 0);


        } else if (deltaX > 0 && camera.viewportWidth/2 + camera.position.x < worldSize.x) {

                camera.translate(deltaX, 0);

        }

        //camera.translate(0, change.y);
        /*if (change.y < 0 && camera.position.y < camera.viewportHeight / 2) {
            camera.translate(0, change.y);
        } else if (change.y > 0 && camera.viewportHeight/2 + camera.position.y > worldSize.y) {
            camera.translate(0, change.y);
        }*/

        /*
        If the camera's position after scrolling is out of bounds,
        set it's x position to be in-bounds.
         */

        this.putCameraInBounds();
        return false;
    }

    private void putCameraInBounds() {

        if (camera.position.x == camera.viewportWidth / 2 || camera.viewportWidth/2 + camera.position.x == worldSize.x){
            return;
        }

        if (camera.position.x < camera.viewportWidth / 2){
            /*camera.position.set(
                    camera.viewportWidth / 2,
                    camera.position.y,
                    camera.position.z);*/
            Tween.to(camera,CameraTweenAccessor.POSITION_X,.3f).target(camera.viewportWidth / 2).start(tweenManager).ease(Back.OUT);

        } else if (camera.viewportWidth/2 + camera.position.x > worldSize.x){
            /*camera.position.set(
                    worldSize.x - (camera.viewportWidth / 2),
                    camera.position.y,
                    camera.position.z);*/
            Tween.to(camera,CameraTweenAccessor.POSITION_X,.3f).target(worldSize.x - (camera.viewportWidth / 2)).start(tweenManager).ease(Back.OUT);


        }


    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        this.scrollScreen(amount* mapWidthInTiles,0,true);
        return true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap (float x, float y, int count, int button) {


        /*
        Make it so that Scene2D (ui) inputs stop here.
         */





        System.out.println("shit happened yo!");
        Vector3 touchLocation = new Vector3(x, y, 0);
        Vector3 inWorldTouchLocation = camera.unproject(touchLocation);
        int tileX = (int) (inWorldTouchLocation.x / this.tileWidth);
        int tileY = (int) (inWorldTouchLocation.y / this.tileHeight);
        System.out.println("Touch happened at " + tileX + "," + tileY);

        try {
            System.out.println("bullet dodge chance" + parser.getTranslatedProps(tileX, tileY).bulletDodgeChance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (CombatUnit unit : tracker.getObjectsOfType(CombatUnit.class)) {
            if (unit.isIntersectedBy(new Vector2(inWorldTouchLocation.x,inWorldTouchLocation.y))) {
                System.out.println("we've got a hit!");

                System.out.println("the unit's command is " + unit.getCurrentCommand() + " and direction is " + unit.getCurrentCommand());

                fightUI.setFocusOnUnit(unit);

                fightUI.toggleButtonVisability();
                Rectangle boundingRect = unit.getBoundingRectangle();
                Vector3 buttonsPlacement = camera.project(new Vector3(boundingRect.x, boundingRect.y,0));

                fightUI.setPositionForButtons(new Vector2(buttonsPlacement.x, buttonsPlacement.y));
                return true;
            }
        }
        /*Vector2 tileTouchLocation = new Vector2(this.getTileLocation(inWorldTouchLocation));

       CombatUnit newUnit = new CombatUnit("helm", new Texture("brutal-helm.png"), tileTouchLocation, tweenManager).setMoveAmounts(new Vector2(this.tileHeight, this.tileWidth));
        newUnit.setSize(tileHeight, tileWidth);
        units.add(newUnit);*/

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            tracker.addNewObjectToScene("helm", new Texture("brutal-helm.png"), SceneObjectTracker.SceneObjectType.COMBAT_UNIT, SceneObjectTracker.SnapType.SNAP_TO_GRID, inWorldTouchLocation);
        } else {
            // String name, Texture texture, Vector2 startPosition, TweenManager tweenManager, SceneObjectTracker tracker, FightScene fightScene
            CombatUnit newUnit = (CombatUnit) SceneObjectTracker.SceneObjectType.COMBAT_UNIT.newInstance("helm", new Texture("brutal-helm.png"), new Vector2(inWorldTouchLocation.x, inWorldTouchLocation.y), tweenManager,tracker,this);
            newUnit.setFaction(Faction.POLISH);
            try {
                tracker.addObjectToScene(newUnit, SceneObjectTracker.SceneObjectType.COMBAT_UNIT.getSceneObjectClass(), SceneObjectTracker.SnapType.SNAP_TO_GRID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




        /*tileTouchLocation.x += tileWidth+1;

        Projectile newProjectile = new Projectile("Bullet",this, new Texture(Gdx.files.internal("gun_blast.png")),
                tileTouchLocation,
                        tweenManager);

        projectiles.add(newProjectile);*/


        ;


        Vector2 toDraw = fightUI.getViewport().unproject(new Vector2(x,y));
        float screenTilesWide = Gdx.graphics.getWidth() / tileOnScreenWidth ;
        float screenTilesHigh = Gdx.graphics.getHeight() / tileOnScreenHeight;
        //fightUI.readjustButtons(toDraw);//new Vector2( x - x % tileOnScreenWidth, y - y % tileOnScreenHeight));

        //Tween.to(units.get("helm"), SpriteTweenAccessor.POSITION_XY, .5f).target((inWorldTouchLocation.x) - ((inWorldTouchLocation.x) % (tileWidth)), (inWorldTouchLocation.y) - ((inWorldTouchLocation.y) % (tileHeight))).start(tweenManager);

        //testSprite.setPosition((inWorldTouchLocation.x) - ((inWorldTouchLocation.x) % (tileWidth)), (inWorldTouchLocation.y) - ((inWorldTouchLocation.y) % (tileHeight)));

        try {
        TiledMapTileLayer featureLayer = (TiledMapTileLayer) tiledMap.getLayers().get("features");//("features");
        TiledMapTileLayer baseLayer = (TiledMapTileLayer) tiledMap.getLayers().get("base");//("base");
        TiledMapTileLayer buildingLayer = (TiledMapTileLayer) tiledMap.getLayers().get("bunkers");//("buildings");
        System.out.println("Got the layer named " + featureLayer.getName());

        TiledMapTileLayer.Cell selectedCell = buildingLayer.getCell(tileX, tileY);

        if (selectedCell == null) {
            selectedCell = featureLayer.getCell(tileX, tileY);
        }

        if (selectedCell == null) {
            selectedCell = baseLayer.getCell(tileX, tileY);
        }


        System.out.println("The cell is  " + selectedCell.toString());


        MapProperties props = selectedCell.getTile().getProperties();

        Iterator<String> propertyList = props.getKeys();

        StringBuilder propTextBuilder = new StringBuilder();
        propTextBuilder.append("Selected Tile ID:" + selectedCell.getTile().getId() + "\n");
        while (propertyList.hasNext()) {
            String prop = propertyList.next();
            propTextBuilder.append(prop + ":" + props.get(prop) + "\n");
        }
        //System.out.println(propTextBuilder.toString());
        propsToRender = propTextBuilder.toString();
    } catch (NullPointerException e) {

    }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        //float amountToMoveX = camera.unproject(new Vector3(deltaX, 0, 0)).x;
        //System.out.println("deltaX = " + deltaX + " and amountToMoveX = " + amountToMoveX);
        deltaX *= -(this.differenceRatio);
        deltaY *= -(this.differenceRatio);
        this.scrollScreen(deltaX,deltaY);
        totalScrollDelta.x += deltaX;
        totalScrollDelta.z += 1;
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        Tween.to(camera,CameraTweenAccessor.POSITION_X,.3f).target(((totalScrollDelta.x*3)/totalScrollDelta.z) + camera.position.x).start(tweenManager).ease(Back.OUT);
        totalScrollDelta = new Vector3();
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        /*System.out.println("zoom detected. Current zoom:  " + camera.zoom + " while  initialDistance: " + initialDistance + " and distance:" + distance);

        float distanceDifference = distance - initialDistance;

        camera.zoom -= distanceDifference / 100000f;


        if (camera.zoom > 1f) { camera.zoom = 1f;}
        if (camera.zoom < .6f) { camera.zoom = .6f;}
        camera.update();*/

        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    public InputMultiplexer getMultiplexer() {
        return this.multiplexer;
    }

    public Vector2 getGraphicDimentions() {
        return graphicDims;
    }

    public void advanceRound() {
        tracker.advanceRound();
    }

    /**
     * Gets the location of the bottom-left corner that's been clicked on.
     * @param inWorldTouchLocation the location of the tap.
     * @return the location of the bottom-left corner that's been clicked on.
     */
    public Vector2 getTileLocation(Vector3 inWorldTouchLocation) {

        Vector2 toReturn = new Vector2((inWorldTouchLocation.x - ((inWorldTouchLocation.x) % (tileWidth))), (inWorldTouchLocation.y) - ((inWorldTouchLocation.y) % (tileHeight)));
        //System.out.println(toReturn);
        return toReturn;
    }

    public Vector2 getTileLocation(Rectangle boundingRectangle) {
        return getTileLocation(new Vector3(boundingRectangle.x, boundingRectangle.y,0));
    }

    public Vector2 getTileLocation(Vector2 inWorldTouchLocation) {
        return getTileLocation(new Vector3(inWorldTouchLocation, 0));
    }

    public Pair<Integer, Integer> getCellAt(Rectangle location) {
        int tileX = (int) (location.x / this.tileWidth);
        int tileY = (int) (location.y / this.tileHeight);

        return new Pair<Integer, Integer>(tileX,tileY);
    }

    public boolean doTheseOverlap(Sprite sprite1, Sprite sprite2) {
        return sprite1.getBoundingRectangle().overlaps(sprite2.getBoundingRectangle());
    }

    /*
    This will be used once I get around to making units check their surroundings
     */
    public boolean willTheseOverlap(Sprite sprite1, Direction direction, Vector2 moveAmounts, Sprite sprite2) {
        Rectangle newLocation = new Rectangle(sprite1.getBoundingRectangle());
        Vector2 calcResult = direction.getOffset(new Vector2(newLocation.x, newLocation.y), moveAmounts);
        newLocation.setPosition(calcResult);

        if (newLocation.overlaps(sprite2.getBoundingRectangle())) {
            return true;
        }
        return false;
    }

    public Vector2 getTileDims() {
        return new Vector2(tileHeight,tileWidth);
    }

    public TilePropertyParser getTilePropertyParser() {
        return parser;
    }

    public Random getRandom() {
        if (rng == null) {
            rng = new Random();
        }
        return rng;
    }
}
