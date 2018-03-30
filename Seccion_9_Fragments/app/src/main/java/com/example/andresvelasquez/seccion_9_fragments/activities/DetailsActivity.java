package com.example.andresvelasquez.seccion_9_fragments.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andresvelasquez.seccion_9_fragments.R;
import com.example.andresvelasquez.seccion_9_fragments.fragments.DetailsFragment;

public class DetailsActivity extends AppCompatActivity {
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if(getIntent().getExtras() != null){
            text = getIntent().getStringExtra("text");

        }
        DetailsFragment fragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        fragment.setText(text);
    }
}
