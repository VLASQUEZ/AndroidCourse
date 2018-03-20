package com.olimpiait.seccion_7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AndresVelasquez on 12/02/18.
 */

public class CarsSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Cars(Id integer PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, color TEXT)";

    public CarsSQLiteHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Cars");
        db.execSQL(sqlCreate);
    }
}
