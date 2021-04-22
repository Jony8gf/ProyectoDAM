package com.app.myapplication;

public class Frase {

    private Integer id;
    private String nombre;
    private String tipo;
    private String idioma;

    public Frase() {
    }

    public Frase(Integer id, String frase, String tipo, String idioma) {
        this.id = id;
        this.nombre = frase;
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

    @Override
    public String toString() {
        return "Frase{" +
                "id=" + id +
                ", frase='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idioma='" + idioma + '\'' +
                '}';
    }
}
