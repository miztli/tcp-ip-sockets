/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.examples;

import java.net.*;
import java.io.*;

public class KnockKnockServer {

    public KnockKnockServer() {
    }
    
    
    //public static void main(String[] args) throws IOException {
    public void createServer(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java KnockKnockServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        System.out.println("port number: " + portNumber);
        
        System.out.println("Creating server...");
        System.out.println("Waiting for client to connect...");
        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            System.out.println("Created socket at: "+serverSocket.getInetAddress().getHostName() +" "+serverSocket.getInetAddress().getHostAddress()+":"+serverSocket.getLocalPort());
            String inputLine, outputLine;
            
            // Initiate conversation with client
            System.out.println("Beginning communication with channel");
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            System.out.println("Reading message from client...");
            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
