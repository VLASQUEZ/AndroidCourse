package com.olimpiait.reconoser.daggertutorial.di;

import com.olimpiait.reconoser.daggertutorial.MainActivity;

import dagger.Component;

/**
 * Created by AndresVelasquez on 23/02/18.
 */
@PerActivity
@Component(modules = {MotorModule.class})
public interface MotorComponent {
    void inject(MainActivity mainActivity);
}
