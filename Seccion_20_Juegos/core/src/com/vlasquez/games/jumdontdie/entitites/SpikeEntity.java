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

/**
 * @author <a href="avelasquez@clickdelivery.com">Andres Velasquez</a>
 * @since 24/04/18
 */
public class SpikeEntity extends Actor {

  private Texture texture;

  private World world;

  private Body body;

  private Fixture fixture;

  public SpikeEntity(Texture texture, World world, float x, float y) {
    this.texture = texture;
    this.world = world;

    BodyDef def = new BodyDef();
    def.position.set(x, y + 0.5f);
    body = world.createBody(def);

    PolygonShape box = new PolygonShape();
    Vector2[] vertex = new Vector2[3];
    vertex[0] = new Vector2(-0.5f, -0.5f);
    vertex[1] = new Vector2(0.5f, -0.5f);
    vertex[2] = new Vector2(0, 0.5f);

    box.set(vertex);

    fixture = body.createFixture(box, 1);
    fixture.setUserData("spike");
    box.dispose();

    setPosition((x - 0.5f) * PIXELS_IN_METER, y * PIXELS_IN_METER);
    setSize(PIXELS_IN_METER, PIXELS_IN_METER);
  }

  @Override public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    batch.draw(texture, getX(), getY(), getWidth(), getHeight());
  }

  public void detach() {
    body.destroyFixture(fixture);
    world.destroyBody(body);
  }
}
