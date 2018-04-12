package com.example.andresvelasquez.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.andresvelasquez.myapplication.R;
import com.example.andresvelasquez.myapplication.models.City;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jsonStr ="{\n " +
                "id:200," +
                "name:\'london\'" +
                "}";
        City city =null;
        try{
            JSONObject json = new JSONObject(jsonStr);
            String name = (String) json.getString("name");
            int id = (Integer) json.getInt("id");

            city =  new City(name,id);

        }catch(JSONException e){
            e.printStackTrace();
        }

        /**
         *  Serializacion Implementando GSON
         */

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        City city1 = gson.fromJson(jsonStr,City.class);
        Toast.makeText(this,"City name :"+city1.getName(),Toast.LENGTH_SHORT).show();
    }
}
