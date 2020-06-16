package com.example.taller_3_material_desing;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Datos {

    private static String db = "Libros";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private static ArrayList<Libro> libros = new ArrayList();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Libro p){

        //libros.add(p);

        databaseReference.child(db).child(p.getId()).setValue(p);

    }

    public static ArrayList<Libro> obtener(){
        return libros;
    }

    public static void eliminar(Libro p){
        //libros.remove(p);

        for (int i = 0; i < libros.size(); i++){
            if (libros.get(i).getIsbn().equals(p.getIsbn())){
                libros.remove(i);
                break;
            }
        }

    }
}
