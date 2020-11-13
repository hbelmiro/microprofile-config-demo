package com.hbelmiro.microprofileconfig.rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @Inject
    @ConfigProperty(name = "com.hbelmiro.microprofileconfig.config.persisted.language")
    Provider<String> language;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        switch (this.language.get()) {
            case "pt":
                return "Olá";
            case "it":
                return "Ciao";
            default:
                return "Hello";
        }
    }

}