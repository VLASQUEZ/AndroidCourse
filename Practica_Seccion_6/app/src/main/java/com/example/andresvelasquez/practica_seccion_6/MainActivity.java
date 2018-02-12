package com.example.andresvelasquez.practica_seccion_6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.example.andresvelasquez.practica_seccion_6.Adapter.MyAdapter;
import com.example.andresvelasquez.practica_seccion_6.Model.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    List<Fruit> fruits;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruits = getAllFruits();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new MyAdapter(this, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Fruit fruit, int position) {

            }
        },fruits);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    private List<Fruit> getAllFruits(){
        return new ArrayList<Fruit>(){{
            add(new Fruit("Fruta 1",R.drawable.ic_launcher_background,setFruitQuantity(),"Descripcion Fruta"));
            add(new Fruit("Fruta 2",R.drawable.ic_launcher_background,setFruitQuantity(),"Descripcion Fruta"));
            add(new Fruit("Fruta 3",R.drawable.ic_launcher_background,setFruitQuantity(),"Descripcion Fruta"));
            add(new Fruit("Fruta 4",R.drawable.ic_launcher_background,setFruitQuantity(),"Descripcion Fruta"));

        }};
    }
    private int setFruitQuantity(){
        Random r = new Random();
        int quantity = r.nextInt((10-1)+1)+1;
        return quantity;
    }
}
