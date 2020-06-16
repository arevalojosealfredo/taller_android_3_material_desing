package com.example.taller_3_material_desing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetalleLibro extends AppCompatActivity {

    private Libro p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_libro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView foto;
        TextView isbn, nombrelibro, autorlibro, paisautor, numeropaginas;
        Bundle bundle;
        Intent intent;

        String id, codigoisbn, nombredellibro, autordellibro;
        String paisdelautor, numerodepaginas;
        final int fot;

        StorageReference storageReference;

        foto = findViewById(R.id.imgFotoDetalle);
        isbn = findViewById(R.id.lblIsbnDetalle);
        nombrelibro = findViewById(R.id.lblNombreLibroDetalle);
        autorlibro = findViewById(R.id.lblAutorLibroDetalle);

        paisautor = findViewById(R.id.txtPaisAutor);
        numeropaginas = findViewById(R.id.txtNumeroPaginas);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        id = bundle.getString("foto");
        codigoisbn = bundle.getString("isbn");
        nombredellibro = bundle.getString("nombrelibro");
        autordellibro = bundle.getString("autorlibro");
        paisdelautor = bundle.getString("paisautor");
        numerodepaginas = bundle.getString("numeropaginas");

        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });

        //foto.setImageResource(fot);
        isbn.setText(codigoisbn);
        nombrelibro.setText(nombredellibro);
        autorlibro.setText(autordellibro);
        //paisautor.setText(paisdelautor);
        //numeropaginas.setText(numerodepaginas);

        p = new Libro(codigoisbn, nombredellibro, autordellibro, paisdelautor, numerodepaginas, 0, id);
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleLibro.this, MainActivity.class);
        startActivity(intent);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_eliminar);
        builder.setMessage(R.string.pregunta_mensaje_eliminar);
        positivo = getString(R.string.opcion_si);
        negativo = getString(R.string.opcion_no);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                p.eliminar();
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
