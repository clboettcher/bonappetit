package com.github.clboettcher.bonappetit.server.repository;

import com.github.clboettcher.bonappetit.server.entity.event.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
