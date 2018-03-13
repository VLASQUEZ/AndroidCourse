package com.olimpiait.reconoser.daggertutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.olimpiait.reconoser.daggertutorial.Models.Automovil;
import com.olimpiait.reconoser.daggertutorial.Models.Motor;
import com.olimpiait.reconoser.daggertutorial.di.MotorApplication;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends AppCompatActivity {
    @Named("gasolina")
    @Inject
    Motor motor;
    @Inject
    Automovil automovil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MotorApplication)getApplication()).getMotorComponent().inject(this);
        Toast.makeText(this,motor.getTipoMotor(),Toast.LENGTH_SHORT).show();
        Toast.makeText(this,automovil.getMotor(),Toast.LENGTH_SHORT).show();
    }
}
