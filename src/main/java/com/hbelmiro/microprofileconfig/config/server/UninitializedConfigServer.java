package com.hbelmiro.microprofileconfig.config.server;

import com.hbelmiro.microprofileconfig.config.server.exception.UninitializedConfigServerException;

import java.util.List;

public class UninitializedConfigServer implements ConfigServer {

    @Override
    public List<Configuration> getAllConfigurations() throws UninitializedConfigServerException {
        throw new UninitializedConfigServerException();
    }

    @Override
    public Configuration getConfiguration(String name) throws UninitializedConfigServerException {
        throw new UninitializedConfigServerException();
    }

}
