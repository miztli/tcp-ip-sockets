package com.java.examples.server;

import com.java.examples.listener.Listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by miztli on 7/03/18.
 */
public class ServerConnectionManager {
    private int maxConnections = 10;
    private AtomicInteger usedConnections = new AtomicInteger(0);
    private int port = 9000;
    private boolean started = true;
    private Map<String, Future<?>> connectedClients;
    private Map<String, Listener<String>> messages = new HashMap<>();
    private ExecutorService executorService;
    private ServerSocket socketServer;

    public ServerConnectionManager() {
    }

    public void start(){

        enableTestMessageThread(); // comment if no needed

        try {
            executorService = Executors.newFixedThreadPool(maxConnections);
            socketServer = new ServerSocket(port);
            while (started) {
                if (usedConnections.get() <= maxConnections) {
                    Listener<ClientConnection> clientConnectionListener = new Listener<ClientConnection>() {
                        @Override
                        public void onEvent(ClientConnection event) {
                            switch (event.getEvent()){
                                case ON_CLOSE:
                                    System.out.println("client " + event.getClientId() + " closed the connection, realising resource, used connections: " + usedConnections.decrementAndGet());
                                    break;
                                case IS_ALIVE:
                                    System.out.println("client " + event.getClientId() + " reported is alive");
                                    break;
                                case ON_CONNECT:
                                    System.out.println("client " + event.getClientId() + " connected to server, using one resource, used connections: " + usedConnections.incrementAndGet());
                                    break;
                            }
                        }
                    };
                    System.out.println("Waiting for connection on port: " + port);
                    Socket socket = socketServer.accept();
                    MessagesSenderWorker messagesSenderWorker = new MessagesSenderWorker(socket, clientConnectionListener);
                    messages.put(String.format("%s:%d",
                                            socket.getInetAddress().getHostAddress(),
                                            socket.getPort()),
                                            messagesSenderWorker);
                    executorService.submit(messagesSenderWorker);
                } else {
                    System.out.println("Max connections reached, waiting until new connections are available");
                    Thread.sleep(3000L);
                }
            }
        }catch (InterruptedException e){
            System.out.println("Something wrong happened while waiting for available connections: " + e.getMessage());
        }catch (IOException e){
            System.out.println("Something wrong happened while opening the server socket: " + e.getMessage());
        }
    }

    private void enableTestMessageThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    for (String s : messages.keySet()) {
                        messages.get(s).onEvent(("message simulation from API: " + i));
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Something wrong occured while emitting test messages");
                    }
                }

            }
        });
        thread.start();
    }

    public void stop(){
        System.out.println("Shutting down executor service gracefully");
        executorService.shutdown();
    }

    public static void main(String[] args) {
        ServerConnectionManager manager = new ServerConnectionManager();
            manager.start();
    }
}
