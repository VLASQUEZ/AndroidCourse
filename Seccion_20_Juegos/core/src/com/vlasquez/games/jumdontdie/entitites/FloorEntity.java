package com.vlasquez.games.jumdontdie.entitites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.vlasquez.games.jumdontdie.Constants.PIXELS_IN_METER;

public class FloorEntity extends Actor {
    private Texture texture;

    private World world;

    private Body body;

    private Fixture fixture;

    public FloorEntity(Texture texture, World world, Vector2 vector2) {
        this.texture = texture;
        this.world = world;

        BodyDef def = new BodyDef();
        def.position.set(x);
        def.type = BodyDef.BodyType.StaticBody;

        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(500,1);
        fixture = body.createFixture(floorShape,1);
        floorShape.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        setPosition((body.getPosition().x-0.5f) * PIXELS_IN_METER, (body.getPosition().y-0.5f) * PIXELS_IN_METER);
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());

    }
}
