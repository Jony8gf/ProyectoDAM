package com.app.myapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketCliente extends Thread {

        Usuario usuario;

        public SocketCliente(Usuario usuario) {
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

                ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
                oos.writeObject(usuario);

                /*
                System.out.println("\t Cliente.Consola " + usuario + " - El cliente ha recibido un objeto del servidor");
                ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
                usuario = (Usuario) ois.readObject();
                System.out.println("\t Cliente.Consola " + usuario + " - Objeto recibido del Servidor: " + usuario.toString());
                ois.close();

                 */
                oos.close();
            } catch (IOException  ex) {
                System.out.println(ex.getMessage());
            }
        }
}
