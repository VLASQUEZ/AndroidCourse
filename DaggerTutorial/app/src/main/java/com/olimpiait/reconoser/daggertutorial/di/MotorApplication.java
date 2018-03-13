package com.olimpiait.reconoser.daggertutorial.di;

import android.app.Application;


/**
 * Created by AndresVelasquez on 23/02/18.
 */

public class MotorApplication extends Application {
    private MotorComponent motorComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        motorComponent = DaggerMotorComponent.builder().motorModule(new MotorModule()).build();
    }

    public MotorComponent getMotorComponent(){
        return motorComponent;
    }
}
