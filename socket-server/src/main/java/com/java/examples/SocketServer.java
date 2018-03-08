/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author miztli
 */
public class SocketServer {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.err.println(
                    "Please insert arguments: <port number>");
            System.exit(1);
        }

        try (ServerSocket listener = new ServerSocket(Integer.parseInt(args[0]))) {

            System.out.println("Initiating socket server");
            System.out.println("Port: " + args[0]);

            System.out.println("Waiting for a connection to be made");
//            try (Socket socket = listener.accept()) {

            Socket socket = listener.accept();
//                Thread writerThread = new Thread(
//                                            new WriterThread(
//                                                    socket.getOutputStream(),
//                                                    "SERVER",
//                                                    Arrays.asList("This", "is", "a", "message", "from", "the", "server", "socket")
//                                            ));
//
                Thread readerThread = new Thread(
                                            new ReaderThread(
                                                    socket.getInputStream(),
                                                    "SERVER"
                                            ));
//
//                writerThread.start();
                readerThread.start();



//                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//                out.println("Socket: Hello world!");
//                out.flush();
//
//                for (int i = 0; i < 20; i++) {
//                    System.out.println("socket detenido por 3 segundos");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        System.out.println("Exception: " + ex.getMessage());
//                    }
//
//                    out.println("Socket: mensaje "+i);
//                    out.flush();
//                }
//
//                out.println("end");
//                out.flush();
//            }
        }
    }
}
