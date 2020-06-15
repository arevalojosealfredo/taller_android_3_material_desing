package com.example.taller_3_material_desing;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
        ArrayList<Libro> libros;
        LinearLayoutManager llm;
        AdaptadorLibro adapter;

        lstLibros = findViewById(R.id.lstLibros);
        libros = Datos.obtener();
        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorLibro(libros, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstLibros.setLayoutManager(llm);
        lstLibros.setAdapter(adapter);

        fab = findViewById(R.id.btnAgregar);
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
