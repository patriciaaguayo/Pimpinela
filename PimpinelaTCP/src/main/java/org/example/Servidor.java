package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

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