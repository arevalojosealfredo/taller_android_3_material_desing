package com.example.taller_3_material_desing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Random;

public class AgregarLibro extends AppCompatActivity {

    private ArrayList<Integer> fotos;
    private EditText isbn, nombrelibro, autorlibro, paisautor, numeropaginas;

    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_libro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isbn = findViewById(R.id.txtIsbn);
        nombrelibro = findViewById(R.id.txtNombreLibro);
        autorlibro = findViewById(R.id.txtAutorLibro);
        paisautor = findViewById(R.id.txtPaisAutor);
        numeropaginas = findViewById(R.id.txtNumeroPaginas);

        fotos = new ArrayList<>();
        fotos.add(R.drawable.images1);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);
        fotos.add(R.drawable.images4);
        fotos.add(R.drawable.images5);
        fotos.add(R.drawable.images6);
        fotos.add(R.drawable.images7);
        fotos.add(R.drawable.images8);

        storageReference = FirebaseStorage.getInstance().getReference();

    }

    public void guardar(View v){
        String codigoisbn, nombredellibro, autordellibro, paisdelautor, numerodepaginas, id;
        int foto;
        Libro libro;

        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        codigoisbn = isbn.getText().toString();
        nombredellibro = nombrelibro.getText().toString();
        autordellibro = autorlibro.getText().toString();
        paisdelautor = paisautor.getText().toString();
        numerodepaginas = numeropaginas.getText().toString();

        foto = foto_aleatoria();

        id = Datos.getId();

        libro = new Libro(codigoisbn, nombredellibro, autordellibro, paisdelautor, numerodepaginas, foto, id);
        libro.guardar();
        subir_foto(id, foto);

        limpiar();

        imp.hideSoftInputFromWindow(isbn.getWindowToken(), 0);
        Snackbar.make(v, getString(R.string.mensaje_guardar), Snackbar.LENGTH_LONG).show();
    }

    public void  subir_foto(String id, int foto){
        StorageReference child = storageReference.child(id);
        Uri uri = Uri.parse("android.resource://"+R.class.getPackage().getName()+"/"+foto);
        UploadTask uploadTask = child.putFile(uri);
    }

    public void limpiar(View v){
        limpiar();
    }

    public int foto_aleatoria(){
        int foto_seleccionada;
        Random r = new Random();
        foto_seleccionada = r.nextInt(this.fotos.size());
        return fotos.get(foto_seleccionada);
    }

    public void limpiar(){
        isbn.setText("");
        nombrelibro.setText("");
        autorlibro.setText("");
        paisautor.setText("");
        numeropaginas.setText("");

        isbn.requestFocus();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarLibro.this, MainActivity.class);
        startActivity(i);
    }

}
