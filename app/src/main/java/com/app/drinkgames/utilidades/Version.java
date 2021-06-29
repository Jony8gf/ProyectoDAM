package com.app.drinkgames.utilidades;

public class Version {

    String nombre;
    String version;

    public Version() {
    }

    public Version(String nombre, String version) {
        this.nombre = nombre;
        this.version = version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Version{" +
                "nombre='" + nombre + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
