package com.example.taller_3_material_desing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptadorLibro extends RecyclerView.Adapter<AdaptadorLibro.LibroViewHolder>{

    private ArrayList<Libro> libros;

    public AdaptadorLibro(ArrayList<Libro> libros){
        this.libros = libros;
    }

    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
        return new LibroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {
        Libro p = libros.get(position);
        holder.foto.setImageResource(p.getFoto());
        holder.isbn.setText(p.getIsbn());
        holder.nombrelibro.setText(p.getNombrelibro());
        holder.autorlibro.setText(p.getAutorlibro());
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    public static class LibroViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView isbn;
        private TextView nombrelibro;
        private TextView autorlibro;
        private View v;

        public LibroViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.imgFoto);
            isbn = v.findViewById(R.id.lblIsbn);
            nombrelibro = v.findViewById(R.id.lblNombreLibro);
            autorlibro = v.findViewById(R.id.lblAutorLibro);
        }
    }

}
