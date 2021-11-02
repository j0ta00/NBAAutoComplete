package com.example.juanjomz.nbaautocompleteandspiner;

public class Equipo{

    private String nombre;
    private int idFoto;


    public Equipo(String nombre, int idFoto) {
        this.nombre = nombre;
        this.idFoto = idFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }
}
