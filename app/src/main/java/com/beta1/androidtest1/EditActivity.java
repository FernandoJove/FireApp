package com.beta1.androidtest1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.beta1.androidtest1.dao.ProductosDao;
import com.beta1.androidtest1.model.Producto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity {

    EditText txtProducto, txtCategoria;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    Producto producto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        txtProducto = findViewById(R.id.txtProducto);
        txtCategoria = findViewById(R.id.txtCategoria);
        btnGuarda = findViewById(R.id.btnGuarda);

        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);

        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final ProductosDao dbProductos = new ProductosDao(EditActivity.this);
        producto = dbProductos.verProducto(id);

        if(producto != null){
            txtProducto.setText(producto.getNombre());
            txtCategoria.setText(producto.getCategoria());
            btnGuarda.setVisibility(View.INVISIBLE);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditActivity.this, Products.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbProductos.eliminarProducto(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }
    private void lista(){
        Intent intent = new Intent(this, RecyclerProducts.class);
        startActivity(intent);
    }
}