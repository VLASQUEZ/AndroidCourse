package com.vlasquez.games.jumdontdie.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpineActor extends Actor {

    private TextureRegion spineTexture;

    public SpineActor(TextureRegion spineTexture) {
        this.spineTexture = spineTexture;
        setSize(spineTexture.getRegionWidth(),spineTexture.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(spineTexture,getX(),getY());

    }

    @Override
    public void act(float delta) {
        setX(getX() -250 * delta);
    }
}

