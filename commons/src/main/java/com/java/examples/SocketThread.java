package com.java.examples;

/**
 * Created by miztli on 28/02/17.
 */
public abstract class SocketThread implements Runnable {
    /**
     *
     */
    private String origin;

    /**
     *
     * @param origin
     */
    public SocketThread(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
