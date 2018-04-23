package com.vlasquez.games.jumdontdie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MainGameApplicationAdapter extends ApplicationAdapter {

	private Texture player;

	private SpriteBatch spriteBatch;
	/**
	 * Metodo de inicio del juego
	 */
	@Override
	public void create() {
		super.create();

		player = new Texture("player1.png");
		spriteBatch = new SpriteBatch();

		InputProcess process = new InputProcess();
		Gdx.input.setInputProcessor(process);
	}

	/**
	 * Metodo que se ejecuta cada vez que cambia el estado del juego
	 * se ejecuta 30 o 60 veces x segundo
	 */
	@Override
	public void render() {
		super.render();
		Gdx.gl.glClearColor(1,0,0.5f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Trae la anchura de la pantalla
		Gdx.graphics.getWidth();
		//Inicia los objetos de golpe
		spriteBatch.begin();
		spriteBatch.draw(player,0,0);

		spriteBatch.end();


	}

	/**
	 * Liberar recursos de la tarjeta grafica
	 */
	@Override
	public void dispose() {
		super.dispose();
		player.dispose();
		spriteBatch.dispose();
	}
}
