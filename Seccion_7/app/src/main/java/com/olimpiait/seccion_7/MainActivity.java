package com.olimpiait.seccion_7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAgregar,btnEliminar;
    private CarsSQLiteHelper carsHelper;
    private SQLiteDatabase db;

    private ListView listView;
    private MyAdapter myAdapter;

    private List<Car> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        cars = new ArrayList<Car>();

        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
                update();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAll();
                update();
            }
        });

        carsHelper = new CarsSQLiteHelper(this,"DBTest1", null,1);
        db = carsHelper.getWritableDatabase();

        myAdapter = new MyAdapter(cars,R.layout.itemdb,this);
        listView.setAdapter(myAdapter);

        update();
    }

    private List<Car> getAllCars(){
        Cursor cursor = db.rawQuery("select * from Cars",null);
        List<Car> list = new ArrayList<Car>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex("Id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String color = cursor.getString(cursor.getColumnIndex("color"));

                list.add(new Car(id,name,color));
                cursor.moveToNext();
            }
        }

        return list;
    }
    private void create(){
        if(db != null){
            //Crear el registro a insertar
            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("name","Seat");
            nuevoRegistro.put("color","Black");
            //Se insertan los registros en la base de datos
            db.insert("Cars",null,nuevoRegistro);

        }
    }
    private void removeAll(){
        db.delete("Cars","",null);

    }
    private void update(){
        cars.clear();

        cars.addAll(getAllCars());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
