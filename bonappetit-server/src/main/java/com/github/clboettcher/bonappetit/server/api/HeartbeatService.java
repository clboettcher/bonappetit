package com.github.clboettcher.bonappetit.server.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Heartbeat API.
 */
@Path("/heartbeat")
public interface HeartbeatService {

    /**
     * Returns "pong" to indicate that the server is up an running.
     *
     * @return constant string "pong".
     */
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    String ping();
}
