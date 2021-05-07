package com.app.myapplication;

public class Frase {

    public Integer id;
    public String nombre;
    public String tipo;
    public String idioma;

    public Frase() {
    }

    public Frase(Integer id, String nombre, String tipo, String idioma) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.idioma = idioma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
