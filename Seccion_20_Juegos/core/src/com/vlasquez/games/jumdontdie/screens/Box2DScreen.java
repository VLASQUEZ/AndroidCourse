package com.vlasquez.games.jumdontdie.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vlasquez.games.jumdontdie.MainGame;

public class Box2DScreen extends BaseScreen {

    public Box2DScreen(MainGame game) {
        super(game);
    }
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    private Body playerBody,floorBody,spinBody;
    private Fixture playerFixture,floorFixture,spinFixture;
    private boolean shouldJump, playerJumping, playerAlive = true;
    @Override
    public void show() {
        super.show();

        world = new World(new Vector2(0,-10),true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(16,9);
        camera.translate(0,1);
        world.setContactListener(new ContactListener() {
            @Override public void beginContact(Contact contact) {
                Fixture fixA = contact.getFixtureA();
                Fixture fixb = contact.getFixtureB();

                if((fixA.getUserData().equals("player") && fixb.getUserData().equals("floor")) ||
                    (fixA.getUserData().equals("floor") && fixb.getUserData().equals("player"))){
                    if(Gdx.input.isTouched()){
                        shouldJump = true;
                    }
                    playerJumping = false;
                }
                if((fixA.getUserData().equals("player") && fixb.getUserData().equals("spin")) ||
                    (fixA.getUserData().equals("spin") && fixb.getUserData().equals("player"))){
                    playerAlive = false;
                }

            }

            @Override public void endContact(Contact contact) {

                Fixture fixA = contact.getFixtureA();
                Fixture fixb = contact.getFixtureB();

                if(fixA == playerFixture && fixb == floorFixture){
                    playerJumping = true;
                }
                if(fixA == floorFixture && fixb == playerFixture){
                    playerJumping = true;
                }
            }

            @Override public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
        playerBody = world.createBody(createPlayerBodyDef());
        floorBody = world.createBody(createFloorBodyDef());
        spinBody = world.createBody(createSpinBodyDef(4f));


        PolygonShape playerShape = new PolygonShape();
        playerShape.setAsBox(0.5f,0.5f);
        playerFixture = playerBody.createFixture(playerShape,3);
        playerShape.dispose();

        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(500,1);
        floorFixture = floorBody.createFixture(floorShape,1);
        floorShape.dispose();

        spinFixture = createSpinFixture(spinBody);

        playerFixture.setUserData("player");
        floorFixture.setUserData("floor");
        spinFixture.setUserData("spin");

    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        world.dispose();
        renderer.dispose();
        world.destroyBody(playerBody);
        world.destroyBody(floorBody);
        world.destroyBody(spinBody);
        playerBody.destroyFixture(playerFixture);
        floorBody.destroyFixture(floorFixture);
        spinBody.destroyFixture(spinFixture);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(shouldJump){
            shouldJump = false;
            jump();
        }
        if(Gdx.input.justTouched() && !playerJumping){
            shouldJump = true;
        }
        if(playerAlive){
            float speedY = playerBody.getLinearVelocity().y;
            playerBody.setLinearVelocity(8,speedY);
        }
        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);
    }

    private BodyDef createPlayerBodyDef(){
        BodyDef def = new BodyDef();
        def.position.set(0,0);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    private BodyDef createFloorBodyDef(){
        BodyDef def = new BodyDef();
        def.position.set(0,-1);
        def.type = BodyDef.BodyType.StaticBody;
        return def;
    }

    private BodyDef createSpinBodyDef(float x){
        BodyDef def = new BodyDef();
        def.position.set(x,0.5f);
        def.type = BodyDef.BodyType.StaticBody;
        return def;
    }

    private Fixture createSpinFixture(Body spinBody){
        Vector2[] vertex = new Vector2[3];
        vertex[0] = new Vector2(-0.5f,-0.5f);
        vertex[1] = new Vector2(0.5f,-0.5f);
        vertex[2] = new Vector2(0,0.5f);
        PolygonShape shape = new PolygonShape();
        shape.set(vertex);
        Fixture fix = spinBody.createFixture(shape,1);
        shape.dispose();
        return fix;
    }

    private void jump(){
        Vector2 pos = playerBody.getPosition();
        playerBody.applyLinearImpulse(0,20,pos.x,pos.y,true);
    }
}
