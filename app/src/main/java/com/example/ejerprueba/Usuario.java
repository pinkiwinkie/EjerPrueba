package com.example.ejerprueba;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private int edad;
    private String actividad;

    public Usuario(String nombre, int edad, String actividad) {
        this.nombre = nombre;
        this.edad = edad;
        this.actividad = actividad;
    }

    @Override
    public String toString() {
        return nombre + " - " + edad + " - " + actividad;
    }
}