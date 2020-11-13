package com.hbelmiro.microprofileconfig.config.server.exception;

public class UninitializedConfigServerException extends Exception {

    public UninitializedConfigServerException() {
        super("The config-server was not initialized.");
    }

}
