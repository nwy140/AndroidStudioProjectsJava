package com.example.silentwolfstudios.startup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 17109182 on 14/3/2018.
 */

public class Database extends SQLiteOpenHelper {

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){ // OnCreate is called when you use your database
        String sql = "create table POINTS(ID INTEGER PRIMARY KEY, X INTEGER NOT NULL, Y INTEGER NOT NULL)";
        db.execSQL(sql); //Create Empty Database Sqlite
    }
}
