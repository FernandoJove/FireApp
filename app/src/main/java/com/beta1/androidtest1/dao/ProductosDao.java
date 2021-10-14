package com.beta1.androidtest1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.beta1.androidtest1.config.DBHelper;
import com.beta1.androidtest1.model.Producto;

import java.util.ArrayList;
import java.util.List;


public class ProductosDao extends DBHelper{

    Context context;

    public ProductosDao(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProducto(String nombre, String categoria) {

        long id = 0;

        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("categoria", categoria);

            id = db.insert(TABLE_PRODUCTOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Producto> mostrarProductos() {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Producto> listaContactos = new ArrayList<>();
        Producto producto;
        Cursor cursorProducto;

        cursorProducto = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS, null);

        if (cursorProducto.moveToFirst()) {
            do {
                producto = new Producto();
                producto.setId(cursorProducto.getInt(0));
                producto.setNombre(cursorProducto.getString(1));
                producto.setCategoria(cursorProducto.getString(2));
                listaContactos.add(producto);
            } while (cursorProducto.moveToNext());
        }

        cursorProducto.close();

        return listaContactos;
    }

    public Producto verProducto(int id) {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Producto producto = null;
        Cursor cursorProducto;

        cursorProducto = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorProducto.moveToFirst()) {
            producto = new Producto();
            producto.setId(cursorProducto.getInt(0));
            producto.setNombre(cursorProducto.getString(1));
            producto.setCategoria(cursorProducto.getString(2));
        }

        cursorProducto.close();

        return producto;
    }

    public boolean editarProducto(int id, String nombre, String categoria) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PRODUCTOS + " SET nombre = '" + nombre + "', categoria = '" + categoria + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarProducto(int id) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_PRODUCTOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    /*private DBHelper helper;
    private SQLiteDatabase database;
    Context context;

    public ProductosDao(Context context) {
        super();
        helper = new DBHelper(context);
    }

    public dbProductos_(@Nullable Context context){
        super (context);
        this.context = context;
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }

    /*
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
*/
}
