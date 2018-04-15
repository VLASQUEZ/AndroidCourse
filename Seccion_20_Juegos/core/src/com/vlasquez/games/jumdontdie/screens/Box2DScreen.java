package com.vlasquez.games.jumdontdie.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
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

    private Body playerBody,floorBody;
    private Fixture playerFixture,floorFixture;
    @Override
    public void show() {
        super.show();

        world = new World(new Vector2(0,-10),true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(7.11f,4);
        camera.translate(0,1);

        playerBody = world.createBody(createPlayerBodyDef());
        floorBody = world.createBody(createFloorBodyDef());

        PolygonShape playerShape = new PolygonShape();
        playerShape.setAsBox(0.5f,0.5f);
        playerFixture = playerBody.createFixture(playerShape,1);
        playerShape.dispose();

        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(500,1);
        floorFixture = floorBody.createFixture(floorShape,1);
        floorShape.dispose();

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
        playerBody.destroyFixture(playerFixture);
        floorBody.destroyFixture(floorFixture);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);
    }

    private BodyDef createPlayerBodyDef(){
        BodyDef def = new BodyDef();
        def.position.set(0,10);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }
    private BodyDef createFloorBodyDef(){
        BodyDef def = new BodyDef();
        def.position.set(0,-1);
        def.type = BodyDef.BodyType.StaticBody;
        return def;
    }
}
