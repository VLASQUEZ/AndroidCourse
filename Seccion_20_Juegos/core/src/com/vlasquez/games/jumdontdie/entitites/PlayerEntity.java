package com.vlasquez.games.jumdontdie.entitites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.vlasquez.games.jumdontdie.Constants.IMPULSE_JUMP;
import static com.vlasquez.games.jumdontdie.Constants.PIXELS_IN_METER;
import static com.vlasquez.games.jumdontdie.Constants.PLAYER_SPEED;

/**
 * @author <a href="avelasquez@clickdelivery.com">Andres Velasquez</a>
 * @since 23/04/18
 */
public class PlayerEntity extends Actor {
  private Texture texture;

  private World world;

  private Body body;

  private Fixture fixture;

  private boolean isAlive = true;
  private boolean jumping = false;

  private boolean mustJump = false;

  public PlayerEntity(Texture texture, World world, Vector2 position) {
    this.texture = texture;
    this.world = world;

    BodyDef def = new BodyDef();
    def.position.set(position);
    def.type = BodyDef.BodyType.DynamicBody;
    body = world.createBody(def);

    PolygonShape box = new PolygonShape();
    box.setAsBox(0.5f, 0.5f);
    fixture = body.createFixture(box, 3);
    fixture.setUserData("player");
    box.dispose();

    setSize(PIXELS_IN_METER, PIXELS_IN_METER);
  }

  @Override public void draw(Batch batch, float parentAlpha) {
    setPosition((body.getPosition().x - 0.5f) * PIXELS_IN_METER,
        (body.getPosition().y - 0.5f) * PIXELS_IN_METER);
    batch.draw(texture, getX(), getY(), getWidth(), getHeight());
  }

  public void detach() {
    body.destroyFixture(fixture);
    world.destroyBody(body);
  }

  @Override public void act(float delta) {
    super.act(delta);

    //Iniciar un salto cuando toque la pantalla
    if (mustJump) {
      mustJump = false;
      jump();
    }

    //Hacer avanzar al jugador
    if (isAlive) {
      float speedY = body.getLinearVelocity().y;
      body.setLinearVelocity(PLAYER_SPEED, speedY);
    }
    if (jumping) {
      body.applyForceToCenter(0, -IMPULSE_JUMP * 1.15f, true);
    }
  }

  public void jump() {
    if (!jumping && isAlive) {
      jumping = true;
      Vector2 pos = body.getPosition();
      body.applyLinearImpulse(0, IMPULSE_JUMP, pos.x, pos.y, true);
    }
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  public boolean isJumping() {
    return jumping;
  }

  public void setJumping(boolean jumping) {
    this.jumping = jumping;
  }

  public boolean isMustJump() {
    return mustJump;
  }

  public void setMustJump(boolean mustJump) {
    this.mustJump = mustJump;
  }
}
