package com.example.andresvelasquez.seccion_7_realm.models;

import com.example.andresvelasquez.seccion_7_realm.app.MyApplication;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by andresvelasquez on 18/02/18.
 */

public class Board extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private String title;
    @Required
    private Date createdAt;


    //Relacion entre Note y Board
    private RealmList<Note> notes;

    //Constructor vacio requerido por Realm
    public Board() {
    }

    public Board(String title) {
        this.id = MyApplication.boardId.incrementAndGet();
        this.title = title;
        this.createdAt = new Date();
        this.notes = new RealmList<Note>();

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public RealmList<Note> getNotes() {
        return notes;
    }

}
