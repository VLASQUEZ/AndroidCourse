package com.example.andresvelasquez.seccion_12_maps.activities;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.andresvelasquez.seccion_12_maps.R;
import com.example.andresvelasquez.seccion_12_maps.fragments.BlankFragment;
import com.example.andresvelasquez.seccion_12_maps.fragments.MapFragment;


public class MainActivity extends AppCompatActivity {
    Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            currentFragment = new BlankFragment();
            changeFragment(currentFragment);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_map:
                currentFragment = new MapFragment();
                break;
            case R.id.menu_welcome:
                currentFragment = new BlankFragment();
                break;
        }
        changeFragment(currentFragment);
        return super.onOptionsItemSelected(item);
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();

    }
}
