package com.github.clboettcher.bonappetit.server.impl.converter.api;

import com.github.clboettcher.bonappetit.common.dto.event.EventDto;
import com.github.clboettcher.bonappetit.server.entity.event.Event;

/**
 * Converts {@link Event}s to {@link EventDto}s.
 */
public interface EventsConverter {

    /**
     * Converts the given {@link Event} to an {@link EventDto}.
     *
     * @param event The {@link Event} to convert.
     * @return The resulting {@link EventDto}.
     */
    EventDto convertToDto(Event event);
}
