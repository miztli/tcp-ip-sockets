package com.java.examples.server;

/**
 * Created by miztli on 7/03/18.
 */
public class ClientConnection {
    private String clientId;
    private ConnectionEvent event;

    public ClientConnection() {
    }

    public ClientConnection(String clientId, ConnectionEvent event) {
        this.clientId = clientId;
        this.event = event;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public ConnectionEvent getEvent() {
        return event;
    }

    public void setEvent(ConnectionEvent event) {
        this.event = event;
    }
}
