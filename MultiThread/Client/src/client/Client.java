/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Windows
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        String sentence;
        String modifiedSentence;
        //create input stream attached to standard input
        BufferedReader inFromUser
                = new BufferedReader(new InputStreamReader(System.in));
        //create client socket, connect to server
        Socket clientSocket = new Socket("localhost", 6789);
        //create output stream attached to server
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        //create output stream attached to server
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while (true) {
            //read line from standart input
            System.out.println("Enter your Sentence:");
            sentence = inFromUser.readLine();
            //send line to server
            outToServer.writeBytes(sentence + '\n');
            if (sentence.equalsIgnoreCase("exit")) {
                clientSocket.close();
                break;
            } else {
            //read line from server
                modifiedSentence = inFromServer.readLine();
            //display modified line to standard output
                System.out.println("Server says: " + modifiedSentence);
            }
        }
        inFromServer.close();
        inFromServer.close();
        outToServer.close();
    }
}
