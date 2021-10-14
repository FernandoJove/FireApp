package com.beta1.androidtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.beta1.androidtest1.adapter.ListaProductosAdapter;
import com.beta1.androidtest1.dao.ProductosDao;
import com.beta1.androidtest1.model.Producto;

import java.util.ArrayList;

public class ListProductos extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_productos);

    }


}