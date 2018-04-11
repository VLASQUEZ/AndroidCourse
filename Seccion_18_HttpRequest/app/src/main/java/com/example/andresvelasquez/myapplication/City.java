package com.example.andresvelasquez.myapplication;

import com.google.gson.annotations.Expose;

public class City {

    //Solamente Serializa este campo
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
