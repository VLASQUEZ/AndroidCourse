package com.vlasquez.games.jumdontdie.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vlasquez.games.jumdontdie.MainGame;
import com.vlasquez.games.jumdontdie.screens.BaseScreen;

public class Scene2DScreen extends BaseScreen {

    private Stage stage ;
    private PlayerActor player;
    private SpineActor spineActor;
    Texture texturePlayer,textureSpine;
    TextureRegion spineRegion;

    public Scene2DScreen(MainGame game) {
        super(game);
        texturePlayer = new Texture("player1.png");
        textureSpine = new Texture("player1.png");
        spineRegion = new TextureRegion(textureSpine,0,64,128,64);


    }

    @Override
    public void show() {
        stage = new Stage();
        stage.setDebugAll(true);
        player = new PlayerActor(texturePlayer);
        spineActor = new SpineActor(spineRegion);
        stage.addActor(player);
        stage.addActor(spineActor);

        player.setPosition(20,100);
        spineActor.setPosition(400, 100);
        super.show();
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 2f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Dibuja los actores
        stage.draw();
        //actualiza los actores
        stage.act();

        checkCollisions();
    }

    @Override
    public void dispose() {
        texturePlayer.dispose();
    }

    private void checkCollisions(){
        if(player.isAlive() && (player.getX()+ player.getWidth()) > spineActor.getX()){
            System.out.print("se murio puto");
            player.setAlive(false);
        }
    }
}
