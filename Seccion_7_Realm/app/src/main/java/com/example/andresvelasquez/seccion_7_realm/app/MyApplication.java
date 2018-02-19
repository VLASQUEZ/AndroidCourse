package com.example.andresvelasquez.seccion_7_realm.app;

import android.app.Application;

import com.example.andresvelasquez.seccion_7_realm.models.Board;
import com.example.andresvelasquez.seccion_7_realm.models.Note;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by andresvelasquez on 18/02/18.
 */

public class MyApplication extends Application {
    public static AtomicInteger boardId = new AtomicInteger();
    public static AtomicInteger noteId = new AtomicInteger();
    @Override
    public void onCreate() {
        setupRealm();
        super.onCreate();
        Realm realm = Realm.getDefaultInstance();
        boardId = getIdByTable(realm, Board.class);
        noteId = getIdByTable(realm, Note.class);

        //
        realm.close();

    }

    private void setupRealm(){
        RealmConfiguration configuration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass ){
        RealmResults<T> result = realm.where(anyClass).findAll();
        //If con patron ternario
        return (result.size() > 0) ? new AtomicInteger(result.max("id").intValue()) : new AtomicInteger();
    }
}
