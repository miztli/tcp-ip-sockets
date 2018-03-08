package com.java.examples.server;

import com.java.examples.listener.Listener;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by miztli on 7/03/18.
 */
public class MessagesSenderWorker implements Runnable, Listener<String>{

    private boolean active = true;
    private Socket socket;
    private Listener<ClientConnection> clientConnectionListener;
    private BlockingQueue<String> messages = new LinkedBlockingQueue<>();

    public MessagesSenderWorker(Socket socket, Listener<ClientConnection> clientConnectionListener) {
        this.socket = socket;
        this.clientConnectionListener = clientConnectionListener;
    }

    @Override
    public void run() {
        try {
            System.out.println("Simulating ON_CONNECTION event");
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            clientConnectionListener.onEvent(
                                        new ClientConnection(
                                                String.format("%s:%d",
                                                        socket.getInetAddress().getHostAddress(),
                                                        socket.getPort()),
                                                ConnectionEvent.ON_CONNECT));

            while (active){
                String message = messages.poll(7L, TimeUnit.SECONDS);
                if (message != null){
                    System.out.println("Sendig message by socket: " + message);
                    printWriter.println(message);
                    printWriter.flush();
                    clientConnectionListener.onEvent(new ClientConnection(
                                                            String.format("%s:%d",
                                                                    socket.getInetAddress().getHostAddress(),
                                                                    socket.getPort()),
                                                            ConnectionEvent.IS_ALIVE));

                }else{
                    active = false;
                    System.out.println("Simulating ON_CLOSE event");
                    clientConnectionListener.onEvent(new ClientConnection(
                                                            String.format("%s:%d",
                                                                    socket.getInetAddress().getHostAddress(),
                                                                    socket.getPort()),
                                                            ConnectionEvent.ON_CLOSE));
                }
            }
        }catch (InterruptedException e){
            System.out.println("Something went bad while polling messages from thread: " + Thread.currentThread().getName() + " queue: " + e.getMessage());
            clientConnectionListener.onEvent(new ClientConnection(
                                                    String.format("%s:%d",
                                                            socket.getInetAddress().getHostAddress(),
                                                            socket.getPort()),
                                                    ConnectionEvent.ON_CLOSE));
        } catch (IOException e) {
            System.out.println("Couldn't write to socket output stream thread: " + Thread.currentThread().getName());
            clientConnectionListener.onEvent(new ClientConnection(
                                                    String.format("%s:%d",
                                                            socket.getInetAddress().getHostAddress(),
                                                            socket.getPort()),
                                                    ConnectionEvent.ON_CLOSE));
        }
    }

    @Override
    public void onEvent(String event) {
        try {
            messages.put(event);
        } catch (InterruptedException e) {
            System.out.println("Message couldn't be added to Thread: " + Thread.currentThread().getName() + " queue: " + e.getMessage());
        }
    }
}
