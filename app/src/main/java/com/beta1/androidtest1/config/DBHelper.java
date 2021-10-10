package com.beta1.androidtest1.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME  = "DB2020";
    private static final int DB_VERSION = 1;
    public DBHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table id_(id_ integer primary key autoincrement, "
                +"nombre text not null, categoria not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Productos {
        public static final String TABLE = "productos";
        public static final String ID_ = "id_";
        public static final String NOMBRE = "nombre";
        public static final String CATEGORIA = "categoria";


        public static final String[] COLUMNAS = new String[]{ID_,NOMBRE,CATEGORIA};

    }
}
