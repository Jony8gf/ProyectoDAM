package com.app.myapplication;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketEleccion  extends Thread {

    public String eleccion;

    public SocketEleccion (String eleccion) {
        this.eleccion = eleccion;
    }

    /**
     * Método que implementa el comportamiento del hilo
     */
    @Override
    public void run() {
        try {
            System.out.println("\t Cliente.Consola " + eleccion + " - Se abre un socket en el cliente, y dicho socket establece "
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

            //ObjectOutputStream ods = new ObjectOutputStream(socketCliente.getOutputStream());
            //ods.writeObject(num);

            //ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //oos.writeObject(eleccion);

            OutputStream os = socketCliente.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(eleccion);

            //ods.close();
            //oos.close();
            os.close();
            dos.close();

        } catch (IOException  ex) {
            System.out.println(ex.getMessage());
        }
    }
}