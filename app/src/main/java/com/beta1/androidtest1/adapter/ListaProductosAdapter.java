package com.beta1.androidtest1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beta1.androidtest1.ListProductos;
import com.beta1.androidtest1.R;
import com.beta1.androidtest1.model.Producto;

import java.util.ArrayList;

public class ListaProductosAdapter  extends RecyclerView.Adapter<ListaProductosAdapter.ProductoViewHolder>{


    ArrayList<Producto> listaProductos;
    public ListaProductosAdapter(ArrayList<Producto> listaProductos){
    this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ListaProductosAdapter.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto,null,false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaProductosAdapter.ProductoViewHolder holder, int position) {
        holder.viewProducto.setText(listaProductos.get(position).getNombre());
        holder.viewCategoria.setText(listaProductos.get(position).getCategoria());
    }

    @Override
    public int getItemCount() {

        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView viewProducto, viewCategoria;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewProducto = itemView.findViewById(R.id.viewProducto);
            viewCategoria = itemView.findViewById(R.id.viewCategoria);
        }
    }


}
