package com.example.taller_3_material_desing;

import java.util.ArrayList;

public class Datos {
    private static ArrayList<Libro> libros = new ArrayList();
    public static void guardar(Libro p){
        libros.add(p);
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
