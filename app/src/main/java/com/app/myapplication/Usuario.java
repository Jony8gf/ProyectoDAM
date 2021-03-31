package com.app.myapplication;

public class Usuario {

    private String uid;
    private String correo;
    private String nombre;
    private boolean ads;
    private int cervezas;

    public Usuario() {
    }

    public Usuario(String uid, String correo, String nombre, boolean ads, int cervezas) {
        this.uid = uid;
        this.correo = correo;
        this.nombre = nombre;
        this.ads = ads;
        this.cervezas = cervezas;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAds() {
        return ads;
    }

    public void setAds(boolean ads) {
        this.ads = ads;
    }

    public int getCervezas() {
        return cervezas;
    }

    public void setCervezas(int cervezas) {
        this.cervezas = cervezas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "uid='" + uid + '\'' +
                ", correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ads=" + ads +
                ", cervezas=" + cervezas +
                '}';
    }
}
