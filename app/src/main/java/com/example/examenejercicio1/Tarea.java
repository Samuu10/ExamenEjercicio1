package com.example.examenejercicio1;

//Clase Tarea que representa una tarea con un nombre y un estado de si está hecha o no
public class Tarea {

    //Atributos
    private String nombre;
    private boolean hecha;

    //Constructor
    public Tarea(String nombre) {
        this.nombre = nombre;
        this.hecha = false;
    }

    //Getters & Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean isHecha() {
        return hecha;
    }
    public void setHecha(boolean hecha) {
        this.hecha = hecha;
    }
}