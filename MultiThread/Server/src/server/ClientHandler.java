/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Windows
 */
public class ClientHandler implements Runnable {

    private final Socket socketInstance;
    private BufferedReader inputFromClient;
    private DataOutputStream outToClient;
    private String clientMsg;

    public ClientHandler(Socket s) {
        socketInstance = s;
    }

    @Override
    public void run() {
        try {
            inputFromClient = new BufferedReader(new InputStreamReader(socketInstance.getInputStream()));
            outToClient = new DataOutputStream(socketInstance.getOutputStream());
            while (true) {
                clientMsg = inputFromClient.readLine();
                System.out.println("Client " + socketInstance + " says: " + clientMsg);
                if (clientMsg.equalsIgnoreCase("exit")) {
                    socketInstance.close();
                    break;
                } else {
                    String modifiedMsg = clientMsg.toUpperCase();
                    outToClient.writeBytes(modifiedMsg + '\n');
                }
            }
            inputFromClient.close();
            outToClient.close();
        } catch (IOException e) {
            System.err.println("Error:" + e);
        }
    }

}
