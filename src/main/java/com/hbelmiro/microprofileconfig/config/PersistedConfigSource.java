package com.hbelmiro.microprofileconfig.config;

import com.hbelmiro.microprofileconfig.config.server.ConfigServerProvider;
import com.hbelmiro.microprofileconfig.config.server.Configuration;
import com.hbelmiro.microprofileconfig.config.server.exception.UninitializedConfigServerException;
import org.eclipse.microprofile.config.spi.ConfigSource;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.stream.Collectors;

public class PersistedConfigSource implements ConfigSource {

    private static final String NAMESPACE = "com.hbelmiro.microprofileconfig.config.persisted.";

    @Override
    public Map<String, String> getProperties() {
        try {
            return ConfigServerProvider.getConfigServer()
                                       .getAllConfigurations().stream()
                                       .collect(Collectors.toUnmodifiableMap(Configuration::getId, Configuration::getValue));
        } catch (UninitializedConfigServerException e) {
            return Map.of();
        }
    }

    @Override
    public int getOrdinal() {
        return 90;
    }

    @Override
    public String getValue(String propertyName) {
        if (propertyName.startsWith(NAMESPACE)) {
            try {
                return ConfigServerProvider.getConfigServer().getConfiguration(propertyName).getValue();
            } catch (UninitializedConfigServerException e) {
                return null;
            } catch (WebApplicationException e) {
                if (Response.Status.NOT_FOUND.getStatusCode() == e.getResponse().getStatus()) {
                    return null;
                } else {
                    throw e;
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public String getName() {
        return PersistedConfigSource.class.getName();
    }

}
