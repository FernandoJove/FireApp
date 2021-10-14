package com.beta1.androidtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.beta1.androidtest1.adapter.ListaProductosAdapter;
import com.beta1.androidtest1.config.DBHelper;
import com.beta1.androidtest1.dao.ProductosDao;
import com.beta1.androidtest1.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class RecyclerProducts extends AppCompatActivity {

    RecyclerView listaProductos;
    ArrayList<Producto> listaArrayProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_products);

        listaProductos = findViewById(R.id.listaProductos);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));


        ProductosDao dbProductos = new ProductosDao(RecyclerProducts.this);

        listaArrayProductos = new ArrayList<>();
        ListaProductosAdapter  adapter=  new ListaProductosAdapter(dbProductos.mostrarProductos());

        listaProductos.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_productos, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void nuevoRegistro(){
        Intent intent = new Intent(this, Products.class);
        startActivity(intent);
    }


}