package com.example.taller_3_material_desing;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Datos {

    private static String db = "Libros";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private static StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    private static ArrayList<Libro> libros = new ArrayList();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Libro p){
        databaseReference.child(db).child(p.getId()).setValue(p);
    }

    public static ArrayList<Libro> obtener(){
        return libros;
    }

    public static void setLibros(ArrayList<Libro> libros){
        libros = libros;
    }

    public static void eliminar(Libro p){
        databaseReference.child(db).child(p.getId()).removeValue();
        storageReference.child(p.getId()).delete();
    }
}
