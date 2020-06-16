package com.example.taller_3_material_desing;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorLibro.OnLibroClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab;
        RecyclerView lstLibros;
        final ArrayList<Libro> libros;
        LinearLayoutManager llm;
        final AdaptadorLibro adapter;

        DatabaseReference databaseReference;
        String db = "Libros";

        lstLibros = findViewById(R.id.lstLibros);
        libros = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorLibro(libros, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstLibros.setLayoutManager(llm);
        lstLibros.setAdapter(adapter);

        fab = findViewById(R.id.btnAgregar);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                libros.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Libro p = snapshot.getValue(Libro.class);
                        libros.add(p);
                    }
                }

                adapter.notifyDataSetChanged();
                Datos.setLibros(libros);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void agregar(View v){
        Intent intent;
        intent = new Intent(MainActivity.this, AgregarLibro.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onLibroClick(Libro p) {
        Intent intent;
        Bundle bundle;

        bundle = new Bundle();
        bundle.putString("isbn", p.getIsbn());
        bundle.putString("nombrelibro", p.getNombrelibro());
        bundle.putString("autorlibro", p.getAutorlibro());
        bundle.putInt("foto", p.getFoto());

        intent = new Intent(MainActivity.this, DetalleLibro.class);
        intent.putExtra("datos", bundle);
        startActivity(intent);
        finish();
    }
}
