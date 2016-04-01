package com.github.clboettcher.bonappetit.server.impl;

import com.github.clboettcher.bonappetit.serverentities.event.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * Access to persistent {@link Event}s.
 */
public interface EventsRepository extends CrudRepository<Event, Long> {
}
