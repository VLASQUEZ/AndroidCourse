package com.vlasquez.games.jumdontdie.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.vlasquez.games.jumdontdie.MainGame;

/**
 * @author <a href="avelasquez@clickdelivery.com">Andres Velasquez</a>
 * @since 25/04/18
 */
public class MenuScreen extends BaseScreen {

  private Stage stage;

  private Skin skin;

  private Image gameover;

  private TextButton play;

  public MenuScreen(final MainGame game) {
    super(game);
    stage = new Stage(new FitViewport(640, 360));
    skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    gameover = new Image(game.getAssetManager().get("logo.png", Texture.class));
    play = new TextButton("Play", skin);
    gameover.setPosition(440 - gameover.getWidth() / 2, 320 - gameover.getHeight());
    play.setSize(200, 100);
    play.setPosition(220, 50);
    play.addCaptureListener(new ChangeListener() {
      @Override public void changed(ChangeEvent event, Actor actor) {
        game.setScreen(game.gameScreen);
      }
    });
    stage.addActor(play);
    stage.addActor(gameover);
  }

  @Override public void show() {
    super.show();
    Gdx.input.setInputProcessor(stage);
  }

  @Override public void dispose() {
    super.dispose();
    stage.dispose();
  }

  @Override public void hide() {
    super.hide();
    Gdx.input.setInputProcessor(null);
  }

  @Override public void render(float delta) {
    super.render(delta);
    Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act();
    stage.draw();
  }
}
