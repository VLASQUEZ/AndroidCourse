package com.vlasquez.games.jumdontdie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.vlasquez.games.jumdontdie.screens.GameOverScreen;
import com.vlasquez.games.jumdontdie.screens.GameScreen;
import com.vlasquez.games.jumdontdie.screens.LoadingScreen;
import com.vlasquez.games.jumdontdie.screens.MenuScreen;

public class MainGame extends Game {
  private AssetManager manager;
  public GameScreen gameScreen;
  public GameOverScreen gameOverScreen;
  public MenuScreen menuScreen;
  public LoadingScreen loadingScreen;

  @Override
  public void create() {
    manager = new AssetManager();
    manager.load("floor.png", Texture.class);
    manager.load("overfloor.png", Texture.class);
    manager.load("spike.png", Texture.class);
    manager.load("player.png", Texture.class);
    manager.load("gameover.png", Texture.class);
    manager.load("logo.png", Texture.class);
    manager.load("audio/die.ogg", Sound.class);
    manager.load("audio/jump.ogg", Sound.class);
    manager.load("audio/song.ogg", Music.class);

    loadingScreen = new LoadingScreen(this);
    setScreen(loadingScreen);
  }

  public void finishLoading() {
    gameOverScreen = new GameOverScreen(this);
    gameScreen = new GameScreen(this);
    menuScreen = new MenuScreen(this);
    setScreen(menuScreen);
  }

  public AssetManager getAssetManager() {
    return manager;
  }
}
