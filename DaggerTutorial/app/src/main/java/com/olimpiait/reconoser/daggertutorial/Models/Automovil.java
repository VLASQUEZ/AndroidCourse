package com.olimpiait.reconoser.daggertutorial.Models;

/**
 * Created by AndresVelasquez on 23/02/18.
 */

public class Automovil {
    private Motor motor;

    public Automovil(Motor motor) {
        this.motor = motor;
    }

    public String getMotor() {
        return "Carro con motor "+motor.getTipoMotor();
    }
}
