package com.java.examples;

import com.java.examples.utils.SocketUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by miztli on 24/02/17.
 */
public class WriterThread extends SocketThread {

    public WriterThread(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        System.out.println("Writing messages to socket server");

        //Try with resources
        try (PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true)) {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " detenido por 3 segundos");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }

                out.println("mensaje del cliente: "+i);
                out.flush();
            }

        }catch (IOException e) {
            System.out.println("Cannot write to the output stream: " + e.getMessage());
        }
    }


}
