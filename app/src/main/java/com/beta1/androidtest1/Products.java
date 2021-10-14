package com.beta1.androidtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.beta1.androidtest1.dao.ProductosDao;
import com.beta1.androidtest1.model.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Products extends AppCompatActivity {

    private EditText txtNombre, txtCategoria;
    private Button btn_salir_,btn_registrar_,btn_registrosproductos;
    private Producto productos;
    private ProductosDao productosDao;


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        productosDao = new ProductosDao(this);

        txtNombre = (EditText) findViewById(R.id.txt_name);
        txtCategoria  =(EditText) findViewById(R.id.txt_category);
        btn_registrar_ = (Button) findViewById(R.id.btn_registrar);
        btn_registrosproductos = (Button) findViewById(R.id.btn_registrosproductos);

        btn_salir_ = (Button) findViewById(R.id.btn_salir);



/*
        btn_registrar_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarproducto();
            }
        });


*/

        btn_registrar_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txtCategoria.getText().toString().equals("")) {

                    //llamas a la base de datos
                    ProductosDao dbProductos = new ProductosDao(Products.this);

                    long id = dbProductos.insertarProducto(txtNombre.getText().toString(), txtCategoria.getText().toString());

                    if (id > 0) {
                        Toast.makeText(Products.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();

                    } else {
                        Toast.makeText(Products.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Products.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });


        btn_salir_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(Products.this,ActivityAuth.class));
                finish();
            }
        });


        btn_registrosproductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Products.this,RecyclerProducts.class));
            }
        });



    }
    private void limpiar(){
        txtNombre.setText("");
        txtCategoria.setText("");
    }

    private void nuevoRegistro(){

    }

}