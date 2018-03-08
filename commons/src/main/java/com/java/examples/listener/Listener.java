package com.java.examples.listener;

/**
 * Created by miztli on 7/03/18.
 */
public interface Listener<T> {
    void onEvent(T event);
}
