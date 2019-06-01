package com.example.appchoys;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdUsuarios extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ExPtChoys";
    private static final String TABLE_CREATE =
            "CREATE TABLE Usuarios" +
                    "(" +
                    "id TEXT PRIMARY KEY," +
                    "email TEXT," +
                    "first_name TEXT," +
                    "last_name TEXT," +
                    "avatar TEXT" +
                    ")";

    public BdUsuarios(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }



}
