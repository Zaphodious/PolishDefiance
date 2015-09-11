package io.github.zaphodious.polishdefiance.combat.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import io.github.zaphodious.polishdefiance.combat.entity.CombatUnit;
import io.github.zaphodious.polishdefiance.ZaphUtil;

import java.util.Map;

/**
 * Created by achyt_000 on 9/2/2015.
 */
public class FightUI extends Stage {

    FightScene fightScene;

    boolean hasUIBeenMade;

    Map<String,Button>  buttons;

    boolean buttonsAreVisable;

    CombatUnit focusUnit;

    Button advanceTestButton;

    public FightUI(FightScene fightScene) {
        super();
        this.fightScene = fightScene;
        this.hasUIBeenMade = false;
        this.buttonsAreVisable = true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (super.touchDown(screenX, screenY, pointer, button)) {
            return true;
        } else if (this.areButtonsVisable()) {
            this.toggleButtonVisability();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keyCode) {
        return super.keyDown(keyCode);
    }

    @Override
    public boolean keyUp(int keyCode) {
        return super.keyUp(keyCode);
    }

    @Override
    public boolean keyTyped(char character) {
        return super.keyTyped(character);
    }

    @Override
    public void addActor(Actor actor) {
        System.out.println("actor added! " + actor.toString());
        super.addActor(actor);
    }

    public void updateCamera(Camera camera) {
        this.setViewport(new FillViewport(camera.viewportWidth, camera.viewportHeight, camera));
    }

    public void updateCamera() {
        this.updateCamera(fightScene.getCamera());
    }

    @Override
    public void draw() {
        Camera camera = getViewport().getCamera();
        camera.update();

        if (!getRoot().isVisible()) return;

        Batch batch = this.getBatch();
        if (batch != null) {
            //batch.setProjectionMatrix(camera.combined);
            batch.begin();
            getRoot().draw(batch, 1);
            batch.end();

        }
    }

    public void setFocusOnUnit(CombatUnit unit) {
        this.focusUnit = unit;
    }

    public void makeUI() {

        if (hasUIBeenMade) {
            return;
        }

        buttons = ZaphUtil.newMap();

        Skin skin = new Skin(Gdx.files.internal("uiSkin/uiskin.json"));
        buttons.put("up", new TextButton(" ", skin, "default"));
        buttons.put("down", new TextButton(" ", skin, "default"));
        buttons.put("left", new TextButton(" ", skin, "default"));
        buttons.put("right", new TextButton(" ", skin, "default"));
        buttons.put("attack", new TextButton("Atk", skin, "default"));
        buttons.get("up").addListener(new ClickListener() {
                                          @Override
                                          public void clicked(InputEvent event, float x, float y) {

                                              focusUnit.command(CombatUnit.Command.MOVE, Direction.NORTH);
                                              toggleButtonVisability();
                                          }
                                      }
        );
        buttons.get("down").addListener(new ClickListener() {
                                            @Override
                                            public void clicked(InputEvent event, float x, float y) {

                                                focusUnit.command(CombatUnit.Command.MOVE, Direction.SOUTH);
                                                toggleButtonVisability();
                                            }
                                        }
        );buttons.get("left").addListener(new ClickListener() {
                                              @Override
                                              public void clicked(InputEvent event, float x, float y) {

                                                  focusUnit.command(CombatUnit.Command.MOVE, Direction.WEST);
                                                  toggleButtonVisability();
                                              }
                                          }
        );buttons.get("right").addListener(new ClickListener() {
                                                                 @Override
                                                                 public void clicked(InputEvent event, float x, float y) {

                                                                     focusUnit.command(CombatUnit.Command.MOVE, Direction.EAST);
                                                                     toggleButtonVisability();
                                                                 }
                                                             }
        );buttons.get("attack").addListener(new ClickListener() {
                                                                           @Override
                                                                           public void clicked(InputEvent event, float x, float y) {

                                                                               focusUnit.command(CombatUnit.Command.ATTACK, Direction.EAST);
                                                                               toggleButtonVisability();
                                                                           }
                                                                       }
        );

        Vector2 startPosition = new Vector2(fightScene.tileOnScreenWidth * 3, fightScene.tileOnScreenHeight * 10);
        readjustButtons(startPosition);

        System.out.println("on screen width and height of buttons are " + fightScene.tileOnScreenWidth + ", " + fightScene.tileOnScreenHeight);

        for (Button button : buttons.values()) {
            this.addActor(button);
        }





        advanceTestButton = new TextButton("Advance Round", skin, "default");
        advanceTestButton.setPosition(0, 0);
        advanceTestButton.setWidth(fightScene.tileOnScreenWidth * 5);
        advanceTestButton.setHeight(fightScene.tileOnScreenHeight);
        advanceTestButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fightScene.advanceRound();
                //fightScene.tracker.printListsOfObjects();
            }
        });



        this.addActor(advanceTestButton);


        toggleButtonVisability();
        this.hasUIBeenMade = true;
    }

    public void setPositionForButtons(Vector2 startPosition) {
        buttons.get("up").setPosition(startPosition.x, startPosition.y + fightScene.tileOnScreenHeight);
        buttons.get("down").setPosition(startPosition.x, startPosition.y - fightScene.tileOnScreenHeight);
        buttons.get("left").setPosition(startPosition.x - fightScene.tileOnScreenWidth, startPosition.y);
        buttons.get("right").setPosition(startPosition.x + fightScene.tileOnScreenWidth, startPosition.y);
        buttons.get("attack").setPosition(startPosition.x, startPosition.y);

    }

    public void setPositionForButtons(Rectangle boundingRect) {
        setPositionForButtons(new Vector2(boundingRect.x,boundingRect.y));
    }

    public void readjustButtons(Vector2 startPosition) {
        setPositionForButtons(startPosition);

        for (Button button : buttons.values()) {
            button.setWidth((fightScene.tileOnScreenWidth));
            button.setHeight((fightScene.tileOnScreenHeight));

        }


    }



    public void toggleButtonVisability() {
        //buttonsAreVisable = (buttonsAreVisable) ? false:true;
        buttonsAreVisable = !buttonsAreVisable;
        for (Button button : buttons.values()) {
            button.setVisible(buttonsAreVisable);
        }
    }

    public boolean areButtonsVisable() {
        return buttonsAreVisable;
    }

    public void updateUIDims() {
        makeUI();
    }

}
