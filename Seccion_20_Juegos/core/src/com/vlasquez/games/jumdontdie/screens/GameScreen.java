package com.vlasquez.games.jumdontdie.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.vlasquez.games.jumdontdie.MainGame;
import com.vlasquez.games.jumdontdie.entitites.FloorEntity;
import com.vlasquez.games.jumdontdie.entitites.PlayerEntity;
import com.vlasquez.games.jumdontdie.entitites.SpikeEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="avelasquez@clickdelivery.com">Andres Velasquez</a>
 * @since 23/04/18
 */
public class GameScreen extends BaseScreen {
  private Stage stage;
  private World world;

  private PlayerEntity playerEntity;
  private List<FloorEntity> floorList = new ArrayList<FloorEntity>();
  private List<SpikeEntity> spikeList = new ArrayList<SpikeEntity>();

  public GameScreen(MainGame game) {
    super(game);

    stage = new Stage(new FitViewport(640, 380));
    world = new World(new Vector2(0, -10), true);
    world.setContactListener(new ContactListener() {

      private boolean areCollided(Contact contact, Object userA, Object userB) {
        return (contact.getFixtureA().getUserData().equals(userA) && contact.getFixtureB()
            .getUserData()
            .equals(userB) ||
            contact.getFixtureA().getUserData().equals(userB) && contact.getFixtureB()
                .getUserData()
                .equals(userA));
      }

      @Override public void beginContact(Contact contact) {
        if (areCollided(contact, "player", "floor")) {
          playerEntity.setJumping(false);
          if (Gdx.input.isTouched()) {
            playerEntity.setMustJump(true);
          }
        }

        if (areCollided(contact, "player", "spike")) {
          playerEntity.setAlive(false);
        }
      }

      @Override public void endContact(Contact contact) {

      }

      @Override public void preSolve(Contact contact, Manifold oldManifold) {

      }

      @Override public void postSolve(Contact contact, ContactImpulse impulse) {

      }
    });
  }

  @Override public void show() {
    super.show();
    Texture playerTexture = game.getAssetManager().get("player.png");
    Texture floorTexture = game.getAssetManager().get("floor.png");
    Texture overFloorTexture = game.getAssetManager().get("overfloor.png");
    Texture spikeTexture = game.getAssetManager().get("spike.png");

    playerEntity = new PlayerEntity(playerTexture, world, new Vector2(1, 2));

    floorList.add(new FloorEntity(floorTexture, overFloorTexture, world, 0, 1000, 1));
    spikeList.add(new SpikeEntity(spikeTexture, world, 6, 1));

    for (FloorEntity floor : floorList) {
      stage.addActor(floor);
    }

    for (SpikeEntity spike : spikeList) {
      stage.addActor(spike);
    }
    stage.addActor(playerEntity);
  }

  @Override public void hide() {
    super.hide();
    playerEntity.detach();
    playerEntity.remove();
    for (FloorEntity floor : floorList) {
      floor.detach();
      floor.remove();
    }

    for (SpikeEntity spike : spikeList) {
      spike.detach();
      spike.remove();
    }
  }

  @Override public void dispose() {
    super.dispose();
    world.dispose();
    stage.dispose();
  }

  @Override public void render(float delta) {
    Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    //actualiza los actores
    stage.act();
    //Actualiza el mundo
    world.step(delta, 6, 2);
    //Dibuja los actores
    stage.draw();
  }
}
