package com.java.examples.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by miztli on 7/03/18.
 */
public class TestContainer {
    private Map<Integer, Map<String, String>> tests;

    public TestContainer() {
        init();
    }

    private void init(){
        tests = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> descriptions = new HashMap<String, String>(1);
                descriptions.put("description: " + i, "TEST " + i);
            tests.put(i, descriptions);
        }
    }

    public Map<Integer, Map<String, String>> getTests() {
        return tests;
    }
}
