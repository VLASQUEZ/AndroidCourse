package com.example.andresvelasquez.seccion_7_realm.models;

import com.example.andresvelasquez.seccion_7_realm.app.MyApplication;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by andresvelasquez on 18/02/18.
 */

public class Note extends RealmObject{

    @PrimaryKey
    private int id;

    @Required
    private String description;
    @Required
    private Date createdAt;

    public Note() {
    }

    public Note(String description) {
        this.id = MyApplication.noteId.incrementAndGet();
        this.description = description;
        this.createdAt = new Date();
    }

    public int getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
