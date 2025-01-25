package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        String HOST = "127.0.0.1";
        int PUERTO = 5000;
        DataInputStream leer;
        DataOutputStream escribir;

        try {

            Socket conexion = new Socket(HOST, PUERTO);

            leer = new DataInputStream(conexion.getInputStream());
            escribir = new DataOutputStream(conexion.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            String mensajeC;
            String mensajeS;

            do {
                System.out.print("\n Ingrese un mensaje (escriba 'SALIR' para cerrar): ");
                mensajeC = scanner.nextLine();
                escribir.writeUTF(mensajeC);

                if (mensajeC.equalsIgnoreCase("SALIR")) {
                    System.out.println("\n Cerrando conexión...");
                    break;
                }

                try {
                    mensajeS = leer.readUTF();
                    System.out.println("\n El servidor dice: " + mensajeS);

                } catch (IOException e) {
                    System.out.println("\n La conexión con el servidor se cerró.");
                    break;
                }

            } while (true);

            System.out.println("\n CONEXIÓN CERRADA.");
            conexion.close();

        } catch (IOException e) {
            System.out.println("\n Error en el cliente: " + e.getMessage());
        }
    }
}


/*

Manera de hacerlo sin tener el usuario que escribir los mensajes

            // MENSAJE 1

            escribir.writeUTF("¿Quién es?");
            String mensaje = leer.readUTF();
            System.out.println("\n El servidor dice: " + mensaje);

            // MENSAJE 2

            escribir.writeUTF("¿Qué vienes a buscar?");
            String mensaje2 = leer.readUTF();
            System.out.println("\n El servidor dice: " + mensaje2);

            // MENSAJE 3

            escribir.writeUTF("Ya es tarde");
            String mensaje3 = leer.readUTF();
            System.out.println("\n El servidor dice: " + mensaje3);

            // MENSAJE 4

            escribir.writeUTF("Porque ahora soy yo la que quiere estar sin ti");
            String mensaje4 = leer.readUTF();
            System.out.println("\n El servidor dice: " + mensaje4);
         */