package com.vlasquez.games.jumdontdie.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.vlasquez.games.jumdontdie.MainGame;
import com.vlasquez.games.jumdontdie.entitites.PlayerEntity;

/**
 * @author <a href="avelasquez@clickdelivery.com">Andres Velasquez</a>
 * @since 23/04/18
 */
public class GameScreen extends BaseScreen {
  private Stage stage;
  private World world;

  private PlayerEntity playerEntity;

  public GameScreen(MainGame game) {
    super(game);

    stage = new Stage(new FitViewport(640, 380));
    world = new World(new Vector2(0, -10), true);
  }

  @Override public void show() {
    super.show();
    Texture texture = game.getAssetManager().get("player.png");
    playerEntity = new PlayerEntity(texture, world, new Vector2(1, 2));
    stage.addActor(playerEntity);
  }

  @Override public void hide() {
    super.hide();
    playerEntity.detach();
    playerEntity.remove();
  }

  @Override public void dispose() {
    super.dispose();
    world.dispose();
    stage.dispose();
  }

  @Override public void render(float delta) {
    Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 2f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    //actualiza los actores
    stage.act();
    //Actualiza el mundo
    world.step(delta, 6, 2);
    //Dibuja los actores
    stage.draw();
  }
}
