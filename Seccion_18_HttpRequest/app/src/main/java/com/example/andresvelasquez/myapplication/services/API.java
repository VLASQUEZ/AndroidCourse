package com.example.andresvelasquez.myapplication.services;

import com.example.andresvelasquez.myapplication.deserializer.Deserializer;
import com.example.andresvelasquez.myapplication.models.City;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String API_KEY = "cdfbc99145392451b32ada315bfc1e97";

    private static Retrofit retrofit = null;

    public static Retrofit getAPI(){
        if(retrofit == null){
          GsonBuilder gson = new GsonBuilder();
          gson.registerTypeAdapter(City.class, new Deserializer());

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson.create()))
                    .build();
        }
        return retrofit;
    }
}
