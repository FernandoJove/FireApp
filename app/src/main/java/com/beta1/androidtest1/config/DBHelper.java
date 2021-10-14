package com.beta1.androidtest1.config;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.beta1.androidtest1.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "productos.db";
    public static final String TABLE_PRODUCTOS = "t_productos";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PRODUCTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "categoria TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PRODUCTOS);
        onCreate(sqLiteDatabase);

    }
    public List<Producto> mostrarProductos(){
        SQLiteDatabase bd= getReadableDatabase();
        Cursor cursor = bd.rawQuery( "SELECT * FROM productos", null);

        List<Producto> productos = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                productos.add(new Producto(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return productos;
    }
}
