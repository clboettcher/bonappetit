package com.github.clboettcher.bonappetit.server.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Provides management utilities.
 */
@Path("/management")
@Api(value = "management")
public interface ManagementService {

    /**
     * Returns a fixed string to indicate that the application is up and running.
     *
     * @return The string "pong".
     */
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Returns 'pong' to indicate the server is up and running.")
    String ping();
}
