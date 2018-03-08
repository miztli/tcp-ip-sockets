//package com.java.examples.listener;
//
//import com.java.examples.server.ClientConnection;
//
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//
///**
// * Created by miztli on 7/03/18.
// */
//public class ClientConnectionListener implements Listener<ClientConnection> {
//
//    private BlockingQueue<ClientConnection> queue = new LinkedBlockingQueue<>();
//
//    @Override
//    public void onEvent(ClientConnection event) {
//        try {
//            queue.put(event);
//        } catch (InterruptedException e) {
//            System.out.println("The client connection couldn't be added to the queue: " + e.getMessage());
//            throw new IllegalStateException("The client connection couldn't be added to the queue: " + e.getMessage());
//        }
//    }
//
//    public BlockingQueue<ClientConnection> getQueue() {
//        return queue;
//    }
//}
