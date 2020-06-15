package com.example.taller_3_material_desing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleLibro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_libro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView foto;
        TextView isbn, nombrelibro, autorlibro;
        Bundle bundle;
        Intent intent;

        foto = findViewById(R.id.imgFotoDetalle);
        isbn = findViewById(R.id.lblIsbnDetalle);
        nombrelibro = findViewById(R.id.lblNombreLibroDetalle);
        autorlibro = findViewById(R.id.lblAutorLibroDetalle);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        foto.setImageResource(bundle.getInt("foto"));
        isbn.setText(bundle.getString("isbn"));
        nombrelibro.setText(bundle.getString("nombrelibro"));
        autorlibro.setText(bundle.getString("autorlibro"));
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleLibro.this, MainActivity.class);
        startActivity(intent);
    }
}
