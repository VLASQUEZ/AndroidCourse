package com.vlasquez.games.jumdontdie;

import com.badlogic.gdx.Game;
import com.vlasquez.games.jumdontdie.screens.Box2DScreen;
import com.vlasquez.games.jumdontdie.screens.MainGameScreen;

public class MainGame extends Game {
    @Override
    public void create() {
        setScreen(new Box2DScreen(this));
    }
}
