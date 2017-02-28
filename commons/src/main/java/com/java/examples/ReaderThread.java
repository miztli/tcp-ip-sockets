package com.java.examples;

import com.java.examples.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by miztli on 24/02/17.
 *
 * Thead that writes directly to the output stream of a socket instance
 */
public class ReaderThread extends SocketThread{

    private InputStream inputStream;

    public ReaderThread(InputStream inputStream, String origin) {
        super(origin);
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        System.out.println("Reading messages from: " + getOrigin());
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getInputStream()));
            String fromServer;

            while ((fromServer = bufferedReader.readLine()) != null) {
                System.out.println(fromServer);
                if (fromServer.equals("end")) break;
            }

        } catch (IOException e) {
            System.out.println("Cannot read from the input stream: " + e.getMessage());
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
