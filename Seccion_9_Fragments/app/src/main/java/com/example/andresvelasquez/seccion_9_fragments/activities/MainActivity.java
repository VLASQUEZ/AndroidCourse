package com.example.andresvelasquez.seccion_9_fragments.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.andresvelasquez.seccion_9_fragments.R;
import com.example.andresvelasquez.seccion_9_fragments.fragments.DataFragment;
import com.example.andresvelasquez.seccion_9_fragments.fragments.DetailsFragment;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener {

    private boolean isMultiPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMultiPanel();
    }

    @Override
    public void sendData(String text) {

        if(isMultiPanel){
            DetailsFragment fragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
            fragment.setText(text);
        }
        else{
            Intent i = new Intent(this,DetailsActivity.class);
            i.putExtra("text",text);
            startActivity(i);
        }

    }

    private void setMultiPanel(){
        this.isMultiPanel = (getSupportFragmentManager().findFragmentById(R.id.detailsFragment)!=null);
    }
}
