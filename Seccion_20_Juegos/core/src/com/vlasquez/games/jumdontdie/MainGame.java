package com.vlasquez.games.jumdontdie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.vlasquez.games.jumdontdie.screens.Box2DScreen;
import com.vlasquez.games.jumdontdie.screens.GameScreen;
import javax.xml.soap.Text;

public class MainGame extends Game {
    private AssetManager manager;

    @Override
    public void create() {
        manager = new AssetManager();
        manager.load("floor.png", Texture.class);
        manager.load("overfloor.png", Texture.class);
        manager.load("spike.png", Texture.class);
        manager.load("player.png", Texture.class);
        manager.finishLoading();
        setScreen(new GameScreen(this));

    }

    public AssetManager getAssetManager() {
        return manager;
    }
}
