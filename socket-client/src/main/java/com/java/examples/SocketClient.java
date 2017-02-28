/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.examples;

/**
 *
 * @author miztli
 */
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class SocketClient {

    public SocketClient() {
    }
    
    public static void main(String[] args) throws IOException {

        System.out.println("Initiating socket client");

        if (args.length != 2) {
            System.err.println(
                "Usage: Please insert arguments: <host name> <port number>");
            System.exit(1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        System.out.println("Host: " + host);
        System.out.println("Port: " + port);

        Socket socketClient = new Socket(host, port);

        Thread readerThread = new Thread(
                                new ReaderThread(
                                        socketClient.getInputStream(),
                                        "CLIENT"
                                ));

        Thread writerThread = new Thread(
                                new WriterThread(
                                        socketClient.getOutputStream(),
                                        "CLIENT",
                                        Arrays.asList("Hola", "mundo", "esto", "es", "una", "prueba", "desde", "el" , "cliente")
                                ));

        readerThread.start();
        writerThread.start();
//        try (
//            Socket socketClient = new Socket(host, port);
//            PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);
//            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
//        ) {
//            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//
//            String fromServer;
//            String fromUser;
//
//            System.out.println("Reading message from socket server...");
//            while ((fromServer = in.readLine()) != null) {
//                System.out.println("Server: " + fromServer);
//                if (fromServer.equals("Bye."))
//                    break;

//                fromUser = stdIn.readLine();
//                if (fromUser != null) {
//                    System.out.println("Client: " + fromUser);
//                    out.println(fromUser);
//                }
            }
//        } catch (UnknownHostException e) {
//            System.err.println("Don't know about host " + host);
//            System.exit(1);
//        } catch (IOException e) {
//            System.err.println("Couldn't get I/O for the connection to " + host);
//            System.exit(1);
//        }
//    }
}