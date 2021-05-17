package com.app.myapplication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import herramientas.Usuario;

public class SocketEleccion extends Thread {

    public Usuario usuario;

    public SocketEleccion(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Método que implementa el comportamiento del hilo
     */
    @Override
    public void run() {
        try {
            System.out.println("\t Cliente.Consola " + usuario + " - Se abre un socket en el cliente, y dicho socket establece "
                    + "una conexión con el socket del servidor situado en el puerto 30500 de la 127.0.0.1");
            String equipoServidor = "192.168.1.135";
            int puertoServidor = 30500;
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);
            gestionarComunicacion(socketCliente);

            socketCliente.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Método que implementa el envío del mensaje al servidor, la recepción del mensaje de dicho
     * servidor y la muestra del mensaje recibido por consola
     * @param socketCliente Socket que se usa para realizar la comunicación con el servidor
     */
    public void gestionarComunicacion (Socket socketCliente) {

        try {

            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            usuario = (Usuario) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}