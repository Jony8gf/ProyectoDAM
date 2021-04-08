package com.app.myapplication;

import android.content.Intent;

public class UsuarioFrase {

    private Integer usuarioId;
    private String correo;
    private String nombre;

    public UsuarioFrase() {
    }

    public UsuarioFrase(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UsuarioFrase(Integer usuarioId, String correo, String nombre) {
        this.usuarioId = usuarioId;
        this.correo = correo;
        this.nombre = nombre;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
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

    @Override
    public String toString() {
        return "UsuarioFrase{" +
                "usuarioId=" + usuarioId +
                ", correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
