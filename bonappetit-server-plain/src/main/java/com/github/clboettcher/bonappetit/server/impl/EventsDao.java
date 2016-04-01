package com.github.clboettcher.bonappetit.server.impl;

import com.github.clboettcher.bonappetit.common.dto.event.EventDto;

/**
 * Contract for a bean that provides access to stored events.
 */
public interface EventsDao {

    /**
     * Returns the event with the given ID.
     *
     * @param id The ID of the event to return.
     * @return The event.
     */
    EventDto getEventById(Long id);
}
