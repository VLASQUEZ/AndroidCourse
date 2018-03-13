package com.olimpiait.reconoser.daggertutorial.di;

import com.olimpiait.reconoser.daggertutorial.Models.Automovil;
import com.olimpiait.reconoser.daggertutorial.Models.Motor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by AndresVelasquez on 23/02/18.
 */

@Module
public class MotorModule {
    @Singleton
    @Named("diesel")
    @Provides
    public Motor providesMotorDiesel(){
        return new Motor("diesel");
    }
    @Named("gasolina")
    @Provides
    public Motor providesMotorGasolina(){
        return new Motor("gasolina");
    }
    @Provides
    public Automovil providesAutomovil(@Named("diesel") Motor motor){
        return new Automovil(motor);
    }
}
