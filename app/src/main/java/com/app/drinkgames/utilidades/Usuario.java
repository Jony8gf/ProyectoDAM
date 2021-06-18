package com.app.drinkgames.utilidades;

public class Usuario {

    String uid;
    String nombre;
    String correo;
    Integer cervezas;
    String ads;
    Integer avatar;


    public Usuario() {
    }

    public Usuario(String uid, String nombre, String correo, Integer cervezas, String ads, Integer avatar) {
        this.uid = uid;
        this.nombre = nombre;
        this.correo = correo;
        this.cervezas = cervezas;
        this.ads = ads;
        this.avatar = avatar;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getCervezas() {
        return cervezas;
    }

    public void setCervezas(Integer cervezas) {
        this.cervezas = cervezas;
    }

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "uid='" + uid + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", cervezas=" + cervezas +
                ", ads='" + ads + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
