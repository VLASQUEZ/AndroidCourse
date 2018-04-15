package com.vlasquez.games.jumdontdie;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class InputProcess extends InputAdapter {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        System.out.println("Se ha pulsado con el dedo "+pointer+" en la posicion "+ screenX +" , "+screenY);
        return true;
    }
}
