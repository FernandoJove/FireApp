package com.beta1.androidtest1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.beta1.androidtest1.config.DBHelper;
import com.beta1.androidtest1.model.Producto;

import java.util.ArrayList;
import java.util.List;


public class ProductosDao {
    private DBHelper helper;
    private SQLiteDatabase database;

    public ProductosDao(Context context) {
        helper = new DBHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }
    private Producto crearProducto(Cursor cursor){
        Producto producto = new Producto(
                cursor.getInt(cursor.getColumnIndex(DBHelper.Productos.ID_)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Productos.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Productos.CATEGORIA))
        );

        return producto;
    }

    //Listar Productos
    public List<Producto> listarProductos(){
        Cursor cursor = getDatabase().query(DBHelper.Productos.TABLE,DBHelper.Productos.COLUMNAS, null, null, null, null, null);
        List<Producto> lista = new ArrayList<Producto>();
        while(cursor.moveToNext()){
            Producto modelo = createProducto(cursor);
            lista.add(modelo);
        }
        return lista;

    }

    //Create producto
    private Producto createProducto(Cursor cursor){

        Producto productos_ = new Producto(
                cursor.getInt(cursor.getColumnIndex(DBHelper.Productos.ID_)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Productos.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Productos.CATEGORIA))
        );
        return productos_;
    }

    public long modificarUsuario(Producto producto){
        ContentValues values = new ContentValues();
        values.put(DBHelper.Productos.NOMBRE, producto.getNombre());
        values.put(DBHelper.Productos.CATEGORIA, producto.getCategoria());
        if(producto.getId() != null){
            return database.update(DBHelper.Productos.TABLE, values,
                    "_id = ?", new String[]{producto.getId().toString()});
        }
        return getDatabase().insert(DBHelper.Productos.TABLE,null,values);
    }

    public Producto buscarProductoPorID(int id){
        Cursor cursor = getDatabase().query(DBHelper.Productos.TABLE,
                DBHelper.Productos.COLUMNAS, "id_ = ?", new String[]{ Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Producto model = createProducto(cursor);
            cursor.close();
            return model;
        }
        return null;
    }



    public void cerrarDB(){
        helper.close();
        database = null;
    }

}
