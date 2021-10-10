package com.beta1.androidtest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.beta1.androidtest1.dao.ProductosDao;
import com.beta1.androidtest1.model.Producto;

public class Products extends AppCompatActivity {

    private EditText txt_nombre, txt_categoria;
    private Producto productos;
    private ProductosDao productosDao;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        productosDao = new ProductosDao(this);

        txt_nombre = (EditText) findViewById(R.id.txt_name);
        txt_categoria = (EditText) findViewById(R.id.txt_category);

        id = getIntent().getIntExtra("PRODUCTO_ID",0);
        if(id > 0){
            Producto model = productosDao.buscarProductoPorID(id);
            txt_nombre.setText(model.getNombre());
            txt_categoria.setText(model.getCategoria());
            setTitle("Actualizar Usuario");
        }
    }

    @Override
    protected void onDestroy() {
        productosDao.cerrarDB();
        super.onDestroy();
    }

    public void registrarproducto() {
        String nombre = txt_nombre.getText().toString();
        String categoria = txt_categoria.getText().toString();

        if(nombre == null && nombre.isEmpty() && categoria== null && categoria.isEmpty() ){
            txt_nombre.setError("Nombre y categoria vacias");
        }if(nombre == null && nombre.isEmpty()){
            txt_nombre.setError("Nombre vacio, Por favor complete un campo");
        }if(categoria== null && categoria.isEmpty()){
            txt_categoria.setError("Categoria vacia, Por favor complete un campo");
        }else{
            productos = new Producto();
            productos.setNombre(nombre);
            productos.setCategoria(categoria);

            long resultado = productosDao.modificarUsuario(productos);
            if(resultado!= -1){
                Toast.makeText(this,"Modificado",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, ListProductos.class));
            }else{
                Toast.makeText(this,"Guardado",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, ListProductos.class));
            }
            finish();
        }

    }

}