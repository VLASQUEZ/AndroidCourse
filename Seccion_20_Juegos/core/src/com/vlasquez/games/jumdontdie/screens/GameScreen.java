package com.vlasquez.games.jumdontdie.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.vlasquez.games.jumdontdie.MainGame;
import com.vlasquez.games.jumdontdie.entitites.FloorEntity;
import com.vlasquez.games.jumdontdie.entitites.PlayerEntity;
import com.vlasquez.games.jumdontdie.entitites.SpikeEntity;
import java.util.ArrayList;
import java.util.List;

import static com.vlasquez.games.jumdontdie.Constants.PIXELS_IN_METER;
import static com.vlasquez.games.jumdontdie.Constants.PLAYER_SPEED;

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
  private Sound jumpSound, dieSound;
  private Music bgMusic;

  public GameScreen(final MainGame game) {
    super(game);
    jumpSound = game.getAssetManager().get("audio/jump.ogg");
    dieSound = game.getAssetManager().get("audio/die.ogg");
    bgMusic = game.getAssetManager().get("audio/song.ogg");

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
            jumpSound.play();
            playerEntity.setMustJump(true);
          }
        }

        if (areCollided(contact, "player", "spike")) {
          if(playerEntity.isAlive()){
            playerEntity.setAlive(false);
            dieSound.play();
            bgMusic.stop();

            stage.addAction(
                Actions.sequence(
                    Actions.delay(1.5f),
                    Actions.run(new Runnable() {
                      @Override public void run() {
                        game.setScreen(game.gameOverScreen);
                      }
                    })
                ));
          }

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
    floorList.add(new FloorEntity(floorTexture, overFloorTexture, world, 12, 10, 2));
    floorList.add(new FloorEntity(floorTexture, overFloorTexture, world, 30, 10, 2));

    spikeList.add(new SpikeEntity(spikeTexture, world, 6, 1));
    spikeList.add(new SpikeEntity(spikeTexture, world, 18, 2));
    spikeList.add(new SpikeEntity(spikeTexture, world, 32, 2));
    spikeList.add(new SpikeEntity(spikeTexture, world, 50, 1));
    spikeList.add(new SpikeEntity(spikeTexture, world, 65, 1));
    stage.addActor(playerEntity);

    for (FloorEntity floor : floorList) {
      stage.addActor(floor);
    }

    for (SpikeEntity spike : spikeList) {
      stage.addActor(spike);
    }
    bgMusic.setVolume(0.75f);
    bgMusic.play();
  }

  @Override public void hide() {
    super.hide();
    bgMusic.stop();
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
    if (playerEntity.getX() > 150 && playerEntity.isAlive()) {
      stage.getCamera().translate(PLAYER_SPEED * delta * PIXELS_IN_METER, 0, 0);
    }
    if (Gdx.input.justTouched()) {
      jumpSound.play();
      playerEntity.jump();
    }
    //actualiza los actores
    stage.act();
    //Actualiza el mundo
    world.step(delta, 6, 2);
    //Dibuja los actores
    stage.draw();
  }
}
