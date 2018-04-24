package com.vlasquez.games.jumdontdie.entitites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.vlasquez.games.jumdontdie.Constants.PIXELS_IN_METER;

public class FloorEntity extends Actor {
  private Texture texture;
  private Texture overFloor;
  private World world;

  private Body body;

  private Fixture fixture;

  public FloorEntity(Texture texture, Texture overFloor, World world, float x, float width,
      float y) {
    this.texture = texture;
    this.world = world;
    this.overFloor = overFloor;

    BodyDef def = new BodyDef();
    def.position.set(x + width / 2, y - 0.5f);
    body = world.createBody(def);

    PolygonShape floorShape = new PolygonShape();
    floorShape.setAsBox(width / 2, 0.5f);
    fixture = body.createFixture(floorShape, 1);
    fixture.setUserData("floor");
    floorShape.dispose();

    setSize(width * PIXELS_IN_METER, PIXELS_IN_METER);
    setPosition((x - width / 2) * PIXELS_IN_METER, (y - 1) * PIXELS_IN_METER);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    batch.draw(overFloor, getX(), getY() + 0.9f * getHeight(), getWidth(), 0.1f * getHeight());
  }

  public void detach() {
    body.destroyFixture(fixture);
    world.destroyBody(body);
  }
}
