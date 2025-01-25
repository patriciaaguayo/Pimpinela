/**
 * Clase Servidor que implementa un servidor TCP utilizando sockets.
 * Permite la comunicación con un cliente para intercambiar mensajes
 * y responder de acuerdo con los mensajes recibidos.
 */
package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Patricia Aguayo Escudero
 */

public class Servidor {

    /**
     * Punto de entrada principal para el servidor.
     * Este método inicializa un servidor TCP en el puerto especificado,
     * acepta conexiones entrantes y responde a los mensajes enviados por los clientes.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        ServerSocket servidor = null;
        Socket conexion = null;
        DataInputStream leer;
        DataOutputStream escribir;
        int PUERTO = 5000;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("\n Servidor iniciado");

            while (true) {

                // Espera una conexión de cliente.
                conexion = servidor.accept();
                System.out.println("\n CLIENTE CONECTADO");

                leer = new DataInputStream(conexion.getInputStream());
                escribir = new DataOutputStream(conexion.getOutputStream());

                boolean continuar = true;

                while (continuar) {
                    try {
                        String mensaje = leer.readUTF();

                        if (mensaje == null) {
                            continuar = false;
                            break;
                        }

                        // Procesa el mensaje recibido.
                        switch (mensaje) {
                            case "¿Quién es?":
                                System.out.println("\n El cliente dice: " + mensaje);
                                escribir.writeUTF("Soy yo");
                                break;

                            case "¿Qué vienes a buscar?":
                                System.out.println("\n El cliente dice: " + mensaje);
                                escribir.writeUTF("A ti");
                                break;

                            case "Ya es tarde":
                                System.out.println("\n El cliente dice: " + mensaje);
                                escribir.writeUTF("¿Por qué?");
                                break;

                            case "Porque ahora soy yo la que quiere estar sin ti":
                                System.out.println("\n El cliente dice: " + mensaje);
                                escribir.writeUTF("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta");
                                break;

                            case "Salir":
                                continuar = false;
                                break;

                            default:
                                escribir.writeUTF("Error");
                                break;
                        }
                    } catch (IOException e) {
                        System.out.println("\n CLIENTE DESCONECTADO.");
                        continuar = false;
                    }
                }

                conexion.close();
            }

        } catch (IOException ex) {
            System.out.println("\n Error del servidor: " + ex.getMessage());
        }
    }
}