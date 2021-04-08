package com.app.myapplication;

public class ExcepcionFrase extends Exception{

    private String mensajeUsuario;
    private String mensajeAdministrador;
    private Integer codigoError;
    private String sentenciaSQL;

    /**
     * Método constructor vacio de la clase
     */
    public ExcepcionFrase() {
    }

    /**
     * Método constructor con todos los atributos de la clase
     *
     * @param mensajeUsuario Atributo de la clase ExcepcionFrase que contiene
     * el mensaje de usuario de la excepcion
     * @param mensajeAdministrador Atributo de la clase ExcepcionFrase que
     * contiene el mensaje de administrador de la excepcion
     * @param codigoError Atributo de la clase ExcepcionFrase que contiene el
     * codigo de error de la excepcion
     * @param sentenciaSQL Atributo de la clase ExcepcionFrase que contiene
     * la sentencia SQL de la excepcion
     */
    public ExcepcionFrase(String mensajeUsuario, String mensajeAdministrador, Integer codigoError, String sentenciaSQL) {
        this.mensajeUsuario = mensajeUsuario;
        this.mensajeAdministrador = mensajeAdministrador;
        this.codigoError = codigoError;
        this.sentenciaSQL = sentenciaSQL;
    }

    /**
     * Método GET de la clase ExcepcionFrase utilizado para retornar el
     * mensaje de usuario de la excepcion
     *
     * @return Valor del atributo mensajeUsuario
     */
    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    /**
     * Método SET de la clase ExcepcionFrase utilizado para recoger el
     * mensaje de usuario de la excepcion
     *
     * @param mensajeUsuario Atributo de la clase ExcepcionFrase que contiene
     * el mensaje de usuario de la excepcion
     */
    public void setMensajeUsuario(String mensajeUsuario) {
        this.mensajeUsuario = mensajeUsuario;
    }

    /**
     * Método GET de la clase ExcepcionFrase utilizado para retornar el
     * mensaje de administrador de la excepcion
     *
     * @return Valor del atributo mensajeAdministrador
     */
    public String getMensajeAdministrador() {
        return mensajeAdministrador;
    }

    /**
     * Método SET de la clase ExcepcionFrase utilizado para recoger el
     * mensaje de administrador de la excepcion
     *
     * @param mensajeAdministrador Atributo de la clase ExcepcionFrase que
     * contiene el mensaje de administrador de la excepcion
     */
    public void setMensajeAdministrador(String mensajeAdministrador) {
        this.mensajeAdministrador = mensajeAdministrador;
    }

    /**
     * Método GET de la clase ExcepcionFrase utilizado para retornar el
     * codigo de error de la excepcion
     *
     * @return Valor del atributo codigoError
     */
    public Integer getCodigoError() {
        return codigoError;
    }

    /**
     * Método SET de la clase ExcepcionFrase utilizado para recoger el codigo
     * de error de la excepcion
     *
     * @param codigoError Atributo de la clase ExcepcionFrase que contiene el
     * codigo de error de la excepcion
     */
    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    /**
     * Método GET de la clase ExcepcionFrase utilizado para retornar la
     * sentencia de la excepcion
     *
     * @return Valor del atributo codigoError
     */
    public String getSentenciaSQL() {
        return sentenciaSQL;
    }

    /**
     * Método SET de la clase ExcepcionFrase utilizado para recoger la
     * sentencia de la excepcion
     *
     * @param sentenciaSQL Atributo de la clase ExcepcionFrase que contiene
     * la sentencia de la excepcion
     */
    public void setSentenciaSQL(String sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    /**
     * Método toString de la clase ExcepcionFrase utilizado para mostrar la
     * información del objeto ExcepcionFrase
     *
     * @return Cadena con la informcion del objeto venta
     */
    @Override
    public String toString() {
        return "ExcepcionFrase{" + "mensajeUsuario=" + mensajeUsuario + ", mensajeAdministrador=" + mensajeAdministrador + ", codigoError=" + codigoError + ", sentenciaSQL=" + sentenciaSQL + '}';
    }
}
