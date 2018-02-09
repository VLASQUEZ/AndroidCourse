package com.olimpiait.seccion_6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    List<Movie> movies;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = getAllMovies();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);


        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(String name, int position) {
                //Toast.makeText(MainActivity.this,name + " - "+ position, Toast.LENGTH_SHORT).show();
                //deleteName(position);
            }
        });

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                //this.addName(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }



    private List<Movie> getAllMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Iron man",R.drawable.iron_man));
            add(new Movie("Thor",R.drawable.thor));
            add(new Movie("Spider-man Homecoming",R.drawable.spider_man));
            add(new Movie("Capitan America",R.drawable.capitan_america));

        }};
    }
    /*private void addName(int position){
        movies.add(position,"New Name"+ (++counter));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);

    }
    private void deleteName(int position){
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);

    }*/
}
