package com.example.taller_3_material_desing;

public class Libro {
    private String isbn;
    private String nombrelibro;
    private String autorlibro;
    private String paisautor;
    private int numeropaginas;
    private int foto;

    public Libro (String isbn, String nombrelibro, String autorlibro, String paisautor, int numeropaginas, int foto){
        this.isbn = isbn;
        this.nombrelibro = nombrelibro;
        this.autorlibro = autorlibro;
        this.paisautor = paisautor;
        this.numeropaginas = numeropaginas;
        this.foto = foto;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNombrelibro() {
        return nombrelibro;
    }

    public void setNombrelibro(String nombrelibro) {
        this.nombrelibro = nombrelibro;
    }

    public String getAutorlibro() {
        return autorlibro;
    }

    public void setAutorlibro(String autorlibro) {
        this.autorlibro = autorlibro;
    }

    public String getPaisautor() {
        return paisautor;
    }

    public void setPaisautor(String paisautor) {
        this.paisautor = paisautor;
    }

    public int getNumeropaginas() {
        return numeropaginas;
    }

    public void setNumeropaginas(int numeropaginas) {
        this.numeropaginas = numeropaginas;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void guardar(){
        Datos.guardar(this);
    }
}
