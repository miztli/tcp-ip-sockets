package com.java.examples.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by miztli on 24/02/17.
 */
public class Utils {

    public static void close(Closeable closable){
        try {
            closable.close();
        } catch (Exception e) {
            System.out.println("Could not close object: " + e.getMessage());
        }
    }

}
