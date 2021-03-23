package com.app.myapplication;

import java.io.Serializable;

public class ListElement implements Serializable {

    public int color;
    public String nombre;
    public String jugadores;


    public ListElement(int color, String nombre, String jugadores) {
        this.color = color;
        this.nombre = nombre;
        this.jugadores = jugadores;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getJugadores() {
        return jugadores;
    }

    public void setJugadores(String jugadores) {
        this.jugadores = jugadores;
    }

}
