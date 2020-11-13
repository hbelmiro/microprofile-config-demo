package com.hbelmiro.microprofileconfig.config.server;

import com.hbelmiro.microprofileconfig.config.server.exception.ConfigServerProviderInitializationException;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class ConfigServerProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServerProvider.class);

    private static ConfigServer CONFIG_SERVER = new UninitializedConfigServer();

    static {
        ConfigProvider.getConfig()
                      .getOptionalValue("com.hbelmiro.microprofileconfig.config.server.mp-rest.url", String.class)
                      .ifPresentOrElse(configServerUrl -> {
                          try {
                              CONFIG_SERVER = RestClientBuilder.newBuilder()
                                                               .baseUri(new URI(configServerUrl))
                                                               .build(ConfigServer.class);
                          } catch (URISyntaxException e) {
                              throw new ConfigServerProviderInitializationException(e);
                          }
                      }, () -> LOGGER.warn("The config-server was not initialized."));
    }

    public static ConfigServer getConfigServer() {
        return CONFIG_SERVER;
    }

}
