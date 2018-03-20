package com.example.andresvelasquez.seccion_9_fragments.activities;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.andresvelasquez.seccion_9_fragments.R;
import com.example.andresvelasquez.seccion_9_fragments.fragments.DataFragment;
import com.example.andresvelasquez.seccion_9_fragments.fragments.DetailsFragment;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void sendData(String text) {
        DetailsFragment fragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        fragment.setText(text);
    }
}
