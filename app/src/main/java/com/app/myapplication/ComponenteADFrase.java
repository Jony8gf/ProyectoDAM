package com.app.myapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ComponenteADFrase {

    static String dml = null;
    Connection conexion;

    /**
     * Método constructor vacio de la clase.
     *
     * */

    public ComponenteADFrase() throws ExcepcionFrase {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ExcepcionFrase exc = new ExcepcionFrase();
            exc.setMensajeUsuario("Error en el sistema, consulte con el administrador");
            exc.setMensajeAdministrador(ex.getMessage());
            throw exc;
        }
    }


    public void conectarBD() throws ExcepcionFrase {
        try {
            String url = "https://remotemysql.com/phpmyadmin/index.php";
            String puertoTrue = "3306";
            conexion = DriverManager.getConnection("jdbc:mysql://"+url+":443/rbEmS40PZ9", "rbEmS40PZ9", "sOKoEWyHIQ");

        } catch (SQLException ex) {
            ExcepcionFrase exc = new ExcepcionFrase();
            exc.setMensajeAdministrador(ex.getMessage());
            exc.setCodigoError(ex.getErrorCode());
            exc.setMensajeUsuario("Error en el sistema. Consulta con el administrador");

            throw exc;
        }
    }

    public void desconectarDB() throws ExcepcionFrase {
        try {
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionFrase exc = new ExcepcionFrase();
            exc.setMensajeAdministrador(ex.getMessage());
            exc.setCodigoError(ex.getErrorCode());
            exc.setMensajeUsuario("Error en el sistema. Consulta con el administrador");

            throw exc;
        }
    }



    //Selecionar todos los Usuarios
    public ArrayList<UsuarioFrase> leerEmpleados() throws ExcepcionFrase {
        ArrayList<UsuarioFrase> listaEmpleado = new ArrayList();
        String dql = "select * from Usuario";

        try {
            conectarBD();

            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql);
            while (resultado.next()) {
                UsuarioFrase usuario = new UsuarioFrase();
                usuario.setUsuarioId(resultado.getInt("usuario_id"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setCorreo(resultado.getString("correo"));
                listaEmpleado.add(usuario);
            }

            resultado.close();
            sentencia.close();

        } catch (SQLException ex) {

            ExcepcionFrase exc = new ExcepcionFrase();
            exc.setMensajeAdministrador(ex.getMessage());
            exc.setCodigoError(ex.getErrorCode());
            exc.setSentenciaSQL(dql);

            throw exc;
        }
        desconectarDB();

        return listaEmpleado;
    }

}
