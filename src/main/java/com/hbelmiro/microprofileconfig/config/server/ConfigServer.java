package com.hbelmiro.microprofileconfig.config.server;

import com.hbelmiro.microprofileconfig.config.server.exception.UninitializedConfigServerException;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/configurations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface ConfigServer {

    @GET
    List<Configuration> getAllConfigurations() throws UninitializedConfigServerException;

    @GET
    @Path("{id}")
    Configuration getConfiguration(@PathParam("id") String name) throws UninitializedConfigServerException;

}
