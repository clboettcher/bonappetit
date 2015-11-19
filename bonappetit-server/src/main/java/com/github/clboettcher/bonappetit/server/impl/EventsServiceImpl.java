package com.github.clboettcher.bonappetit.server.impl;

import com.github.clboettcher.bonappetit.common.dto.event.EventDto;
import com.github.clboettcher.bonappetit.server.api.EventsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;

/**
 * Default impl of {@link com.github.clboettcher.bonappetit.server.api.EventsService}.
 */
@Component
public class EventsServiceImpl implements EventsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsServiceImpl.class);

    @Autowired
    private EventsDao eventsDao;

    @Override
    public EventDto getEventById(Long id) {
        if (id == null) {
            throw new BadRequestException("Parameter 'id' may not be null");
        }

        LOGGER.info(String.format("Returning event with ID %d", id));

        // ATM we only return static test data.
        return eventsDao.getEventById(id);
    }
}
