package com.java.examples;

import com.java.examples.utils.SocketUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by miztli on 24/02/17.
 *
 * Thead that writes directly to the output stream of a socket instance
 */
public class ReaderThread extends SocketThread{

    public ReaderThread(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        System.out.println("Reading messages from socket server");

        try(
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getSocket().getInputStream()))
            ) {
                String fromServer;

                while ((fromServer = bufferedReader.readLine()) != null) {
                    System.out.println("Server says: " + fromServer);
                    if (fromServer.equals("end")) break;
                }
        } catch (IOException e) {
            System.out.println("Cannot read from the input stream: " + e.getMessage());
        } finally {
            System.out.println("Closing connection with socket server");
            SocketUtils.close(getSocket());
        }



    }
}
