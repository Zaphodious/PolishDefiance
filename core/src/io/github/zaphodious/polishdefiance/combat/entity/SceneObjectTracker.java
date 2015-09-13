package io.github.zaphodious.polishdefiance.combat.entity;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.zaphodious.polishdefiance.ZaphUtil;
import io.github.zaphodious.polishdefiance.combat.scene.Direction;
import io.github.zaphodious.polishdefiance.combat.scene.FightScene;
import io.github.zaphodious.polishdefiance.combat.scene.NumberPair;
import io.github.zaphodious.polishdefiance.combat.scene.TiledMapPropertyParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by achyt_000 on 9/8/2015.
 */
public final class SceneObjectTracker {

    private FightScene fightScene;
    private TweenManager tweenManager;

    private Map<Class, List<SceneObject>> objectLists;
    private List<Texture> texturesToDisposeOf;

    public SceneObjectTracker(FightScene fightScene, TweenManager tweenManager) {
        this.fightScene = fightScene;
        List<CombatUnit> testList = ZaphUtil.newList();
        this.tweenManager = tweenManager;
        this.objectLists = ZaphUtil.newMap();

        this.texturesToDisposeOf = ZaphUtil.newList();
    }

    public void addNewObjectToScene(String name, Texture texture, SceneObjectType objectType, SnapType snapType, Vector2 startPosition) {
        try {
            addObjectToScene(objectType.newInstance(name, texture, startPosition, tweenManager, this, fightScene), objectType.getSceneObjectClass(), snapType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewObjectToScene(String name, Texture texture, SceneObjectType objectType, SnapType snapType, Vector3 startPosition) {
        addNewObjectToScene(name, texture, objectType, snapType, new Vector2(startPosition.x, startPosition.y));
    }

    public void addNewObjectToScene(String name, Texture texture, SceneObjectType objectType, SnapType snapType, Rectangle startPosition) {
        addNewObjectToScene(name, texture, objectType, snapType, new Vector2(startPosition.x, startPosition.y));
    }

    public <K extends SceneObject> void addObjectToScene(SceneObject sceneObject, Class<K> paramaterClass, SnapType snapType) throws Exception {
        if (snapType != SnapType.FREE_FLOAT) {
            Rectangle boundingRect = sceneObject.getBoundingRectangle();
            sceneObject.setPosition(snapType.adjustPosition(new Vector2(boundingRect.x, boundingRect.y), fightScene));
        }



        if (paramaterClass == sceneObject.getClass()) {

            if (!objectLists.containsKey(paramaterClass)) {
                objectLists.put(paramaterClass, new ArrayList<SceneObject>());
            }

            //System.out.println("Class matched!");
            objectLists.get(paramaterClass).add(sceneObject);
        } else {
            throw new ClassCastException("You tried to pass in a " + sceneObject.getClass() + ", but your type parameter was a " + paramaterClass);
        }
    }

    public void drawObjects(SpriteBatch batch) {
        /*for (Projectile projectile : projectiles) {
            if (!projectile.isKill()) {
                projectile.draw(batch);
            }
        }

        for (CombatUnit combatUnit : combatUnits) {
            if (!combatUnit.isKill()) {
                combatUnit.draw(batch);
            }
        }*/

        for (List<SceneObject> list : objectLists.values()){
            for (SceneObject sceneObject : list) {
                sceneObject.draw(batch);
            }
        }
    }

    public void processInteractions() {


        for (Projectile projectile : getObjectsOfType(Projectile.class)) {
            for (CombatUnit unit : getObjectsOfType(CombatUnit.class)) {
                if (doesProjectileHitUnit(projectile, unit)) {
                    unit.damage(projectile.getPower());
                    projectile.thisIsKillNow();
                }
            }
        }

        removeDeadSceneObjects();

    }

    private boolean doesProjectileHitUnit(Projectile projectile, CombatUnit unit) {
        boolean toReturn = false;

        if (fightScene.doTheseOverlap(projectile, unit)) {
            if (!projectile.isKill() && !unit.isKill()) {
                if (projectile.getUnitFaction() != unit.getUnitFaction()) {
                    TiledMapPropertyParser.PropBundle propBundle = fightScene.getPropertyParser().getTranslatedProps(fightScene.getCellAt(unit.getBoundingRectangle()));

                    if (fightScene.getRandom().nextFloat() > propBundle.getBulletDodgeChance()) {
                        toReturn = true;
                    } else {
                    }

                }
            }
        }

        return toReturn;
    }

    public void removeDeadSceneObjects() {
        List<SceneObject> toRemove = ZaphUtil.newList();

        for (List<SceneObject> list : objectLists.values()) {
            for (SceneObject sceneObject : list) {
                if (sceneObject.isKill()) {
                    toRemove.add(sceneObject);
                    texturesToDisposeOf.add(sceneObject.getTexture());
                }
            }
            list.removeAll(toRemove);
            toRemove.clear();
        }
    }

    public <K extends SceneObject> List<K> getObjectsOfType(Class<K> thisClass) {
        List<K> toReturn = ZaphUtil.newList();

        if (objectLists.containsKey(thisClass)) {
            for (SceneObject sceneObject : objectLists.get(thisClass)) {
                K returnObject = (K) sceneObject;
                toReturn.add(returnObject);
            }
        }


        return toReturn;
    }



    public void advanceRound() {
        for (CombatUnit combatUnit : this.getObjectsOfType(CombatUnit.class)) {
            if (combatUnit.getUnitFaction() == Faction.NAZI) {
                if (fightScene.getRandom().nextBoolean() == true) {
                    combatUnit.command(Command.ATTACK, Direction.WEST);
                } else {
                    combatUnit.command(Command.MOVE, Direction.WEST);
                }
            }
            combatUnit.advanceRound();
        }

        for (int i = 0; i < fightScene.getRandom().nextInt(10); i++) {
            Vector2 startPosition = new Vector2(fightScene.getWorldSize().getX()-fightScene.getTileDimentions().getX(), fightScene.getRandom().nextInt((int) fightScene.getWorldSize().getY()));
            this.addNewObjectToScene("Nazi!", new Texture("imp-laugh.png"),SceneObjectType.COMBAT_UNIT,SnapType.SNAP_TO_GRID,startPosition);
        }
    }

    public FightScene getFightScene() {
        return fightScene;
    }


    public void dispose() {
        for (List<SceneObject> list : objectLists.values()) {
            for (SceneObject sceneObject : list) {
                sceneObject.dispose();
            }
        }

        for (Texture texture : texturesToDisposeOf) {
            texture.dispose();
        }


    }

    public void printListsOfObjects() {
        for (Projectile projectile : getObjectsOfType(Projectile.class)) {
            System.out.println("Projectile " + projectile.getName() + " at " + projectile.getBoundingRectangle() + "," + projectile.getBoundingRectangle().y + " and isKill status is " + projectile.isKill());
        }

        for (CombatUnit combatUnit : getObjectsOfType(CombatUnit.class)) {
            System.out.println("CombatUnit " + combatUnit.getName() + " at " + combatUnit.getBoundingRectangle().x + "," + combatUnit.getBoundingRectangle().y + " and isKill status is " + combatUnit.isKill());
        }
    }

    public enum SceneObjectType {
        COMBAT_UNIT(CombatUnit.class) {
            @Override
            public SceneObject newInstance(String name, Texture texture, Vector2 startPosition, TweenManager tweenManager, SceneObjectTracker tracker, FightScene fightScene) {
                NumberPair<Integer> tileDims = fightScene.getTileDimentions();
                CombatUnit toAdd = new CombatUnit(name, texture,startPosition,tweenManager,tracker).setMoveAmounts(new Vector2(tileDims.getX()*fightScene.getRandom().nextInt(4), tileDims.getY()*fightScene.getRandom().nextInt(4)));


                toAdd.setSize(tileDims.getX(), tileDims.getY());
                toAdd.command(Command.ATTACK, Direction.EAST);
                return toAdd;
            }
        },
        PROJECTILE(Projectile.class) {
            @Override
            public SceneObject newInstance(String name, Texture texture, Vector2 startPosition, TweenManager tweenManager, SceneObjectTracker tracker, FightScene fightScene) {
                NumberPair<Integer>  tileDims = fightScene.getTileDimentions();
                startPosition.x += tileDims.getX();
                Projectile toAdd = new Projectile(name,fightScene,texture,startPosition,tweenManager,tracker,Faction.NAZI);
                toAdd.setSize(tileDims.getX(), tileDims.getY());

                return toAdd;
            }
        };

        Class objectClass;

        SceneObjectType(Class objectClass) {
            this.objectClass = objectClass;
        }

        public Class getSceneObjectClass() {
            return this.objectClass;
        }
        abstract public SceneObject newInstance(String name, Texture texture, Vector2 startPosition, TweenManager tweenManager, SceneObjectTracker tracker, FightScene fightScene);

    }

    public enum SnapType {
        SNAP_TO_GRID {
            @Override
            public Vector2 adjustPosition(Vector2 initialPosition, FightScene fightScene) {
                return fightScene.getTileLocation(initialPosition);
            }
        },
        FREE_FLOAT {
            @Override
            public Vector2 adjustPosition(Vector2 initialPosition, FightScene fightScene) {
                return initialPosition;
            }
        };

        abstract public Vector2 adjustPosition(Vector2 initialPosition, FightScene fightScene);

    }

}

