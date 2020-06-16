package com.example.taller_3_material_desing;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptadorLibro extends RecyclerView.Adapter<AdaptadorLibro.LibroViewHolder>{

    private ArrayList<Libro> libros;
    private OnLibroClickListener clickListener;

    public AdaptadorLibro(ArrayList<Libro> libros, OnLibroClickListener clickListener){
        this.libros = libros;
        this.clickListener = clickListener;
    }

    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
        return new LibroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final LibroViewHolder holder, int position) {
        final Libro p = libros.get(position);

        StorageReference storageReference;
        storageReference = FirebaseStorage.getInstance().getReference();

        storageReference.child(p.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.foto);
            }
        });


        holder.isbn.setText(p.getIsbn());
        holder.nombrelibro.setText(p.getNombrelibro());
        holder.autorlibro.setText(p.getAutorlibro());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onLibroClick(p);
            }
        });
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

    public interface OnLibroClickListener{
        void onLibroClick(Libro p);
    }

}
