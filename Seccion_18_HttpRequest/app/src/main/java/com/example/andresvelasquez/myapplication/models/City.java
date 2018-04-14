package com.example.andresvelasquez.myapplication.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    //Solamente Serializa este campo
    //SerializedName => Nombre del campo del JSON
    @SerializedName("name")
    @Expose
    private String name;
    private int id;
    @SerializedName("main")
    private Temperature temp;

    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City(String name, int id, Temperature temp,String country) {
        this.name = name;
        this.id = id;
        this.temp = temp;
        this.country = country;
    }

    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static Temperature parseJSON(String response){
        Gson gson = new GsonBuilder().create();
        Temperature temp = gson.fromJson(response,Temperature.class);
        return temp;
    }
}
