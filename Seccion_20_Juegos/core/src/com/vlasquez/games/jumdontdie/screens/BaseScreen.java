package com.vlasquez.games.jumdontdie.screens;

import com.badlogic.gdx.Screen;
import com.vlasquez.games.jumdontdie.MainGame;

public abstract class BaseScreen implements Screen {
    public MainGame game;
    public BaseScreen(MainGame game){
        this.game = game;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
