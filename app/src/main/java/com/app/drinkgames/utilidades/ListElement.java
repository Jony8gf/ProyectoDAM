package com.app.drinkgames.utilidades;

import java.io.Serializable;

public class ListElement implements Serializable {

    public int color;
    public String nombre;
    public String jugadores;
    public int typeGame;


    public ListElement(int color, String nombre, String jugadores, int typeGame) {
        this.color = color;
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.typeGame = typeGame;
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

    public int getTypeGame() {
        return typeGame;
    }

    public void setTypeGame(int typeGame) {
        this.typeGame = typeGame;
    }
}
