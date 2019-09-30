package com.tw;

import java.util.HashMap;
import java.util.Map;

public class Connector {
    private String name;
    private Map<String, String> config;

    public Connector(String name) {
        this.name = name;
        this.config = new HashMap<>();
    }

    public void addConfigItem (String key, String value) {
        this.config.put(key, value);
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getConfig() {
        return config;
    }
}
