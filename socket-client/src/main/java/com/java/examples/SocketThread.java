package com.java.examples;

import java.net.Socket;

/**
 * Created by miztli on 24/02/17.
 */
public abstract class SocketThread implements Runnable {

    /**
     * Socket instance
     */
    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
