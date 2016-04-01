package com.github.clboettcher.bonappetit.server.api;

import com.github.clboettcher.bonappetit.common.dto.event.EventDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Events API.
 */
@Path("/events")
public interface EventsService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    EventDto getEventById(@PathParam("id") Long id);
}
