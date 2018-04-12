package com.example.andresvelasquez.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    //Solamente Serializa este campo
    //SerializedName => Nombre del campo del JSON
    @SerializedName("name")
    @Expose
    private String name;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
