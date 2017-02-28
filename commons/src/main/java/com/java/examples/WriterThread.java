package com.java.examples;

import com.java.examples.utils.Utils;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miztli on 24/02/17.
 */
public class WriterThread extends SocketThread {

    private OutputStream outputStream;

    private List<String> messages;

    public WriterThread(OutputStream outputStream, String origin) {
        super(origin);
        this.outputStream = outputStream;
        this.messages = new ArrayList<>();
    }

    public WriterThread(OutputStream outputStream, String origin,  List<String> messages) {
        super(origin);
        this.outputStream = outputStream;
        this.messages = messages;
    }

    @Override
    public void run() {
        System.out.println("Writing messages from: " + getOrigin());

        PrintWriter printWriter = new PrintWriter(getOutputStream(), true);

        for (String message : messages) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Exception: " + ex.getMessage());
            }

            printWriter.println("origin: " + getOrigin() + " message: " + message);
            printWriter.flush();
        }

        printWriter.println("end");
        printWriter.flush();
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
