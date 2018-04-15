package com.vlasquez.games.jumdontdie.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerActor extends Actor {
    private Texture texturePlayer;
    private boolean isAlive;

    public PlayerActor(Texture texture) {
        this.texturePlayer = texture;
        this.isAlive = true;
        setSize(texturePlayer.getWidth(),texturePlayer.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texturePlayer,getX(),getY());
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
