package com.hbelmiro.microprofileconfig.config.server.exception;

@SuppressWarnings("CdiInjectionPointsInspection")
public class ConfigServerProviderInitializationException extends RuntimeException {

    public ConfigServerProviderInitializationException(Throwable cause) {
        super("An error occurred while initializing the ConfigServer provider.", cause);
    }

}
