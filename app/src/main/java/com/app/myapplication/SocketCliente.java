package com.app.myapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketCliente extends Thread {

        String nombre;

        public SocketCliente(String nombre) {
            this.nombre = nombre;
        }

        /**
         * Método que implementa el comportamiento del hilo
         */
        @Override
        public void run() {
            try {
                System.out.println("\t Cliente.Consola " + nombre + " - Se abre un socket en el cliente, y dicho socket establece "
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
                System.out.println("\t Cliente.Consola " + nombre + " - El cliente construye el mensaje y lo envía al servidor");
                OutputStream os = socketCliente.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF("SOY " + nombre + " ... HOLA SERVIDOR");
                System.out.println("\t Cliente.Consola " + nombre + " - El cliente ha recibido un mensaje del servidor y procede a mostrarlo por consola");
                InputStream is = socketCliente.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                System.out.println("\t Cliente.Consola " + nombre + " - Mensaje recibido del Servidor: " + dis.readUTF());
                is.close();
                dis.close();
                os.close();
                dos.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }


}
