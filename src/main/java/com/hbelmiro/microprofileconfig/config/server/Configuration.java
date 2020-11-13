package com.hbelmiro.microprofileconfig.config.server;

public class Configuration {

    private String id;

    private String value;

    @SuppressWarnings("unused")
    public Configuration() {

    }

    public Configuration(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
