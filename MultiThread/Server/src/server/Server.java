/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Windows
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try {
            int PORT = 6789;
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server started..." + server);
            while (true) {
                Socket socketForClient = server.accept();
                System.out.println("Client " + socketForClient + " connects");
                ClientHandler clientInstance = new ClientHandler(socketForClient);
                Thread t = new Thread(clientInstance);
                t.start();
            }
        } catch (IOException e) {
            System.err.println("Error:" + e);
        }
    }
}
