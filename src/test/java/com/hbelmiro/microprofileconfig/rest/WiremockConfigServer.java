package com.hbelmiro.microprofileconfig.rest;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.hbelmiro.microprofileconfig.config.server.Configuration;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import javax.json.bind.JsonbBuilder;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WiremockConfigServer implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        WireMockConfiguration wireMockConfiguration = wireMockConfig().dynamicPort();

        this.wireMockServer = new WireMockServer(wireMockConfiguration);
        this.wireMockServer.start();

        configureFor(this.wireMockServer.port());

        Configuration configuration = new Configuration(
                "com.hbelmiro.microprofileconfig.config.persisted.language", "pt"
        );

        mockGet("/configurations/com.hbelmiro.microprofileconfig.config.persisted.language", configuration);
        mockGet("/configurations", List.of(configuration));

        return Map.of("com.hbelmiro.microprofileconfig.config.server.mp-rest.url", this.wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (this.wireMockServer != null) {
            this.wireMockServer.stop();
        }
    }

    private void mockGet(String url, Object expectedResponseBody) {
        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(JsonbBuilder.create().toJson(expectedResponseBody))));
    }

}
