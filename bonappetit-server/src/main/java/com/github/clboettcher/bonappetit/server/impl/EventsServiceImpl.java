/*
 * Copyright (c) 2016 Claudius Boettcher (pos.bonappetit@gmail.com)
 *
 * This file is part of BonAppetit. BonAppetit is an Android based
 * Point-of-Sale client-server application for small restaurants.
 *
 * BonAppetit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BonAppetit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BonAppetit.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.clboettcher.bonappetit.server.impl;

import com.github.clboettcher.bonappetit.common.dto.event.EventDto;
import com.github.clboettcher.bonappetit.server.api.EventsService;
import com.github.clboettcher.bonappetit.server.entity.event.Event;
import com.github.clboettcher.bonappetit.server.repository.EventRepository;
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
    private EventRepository eventRepository;

    @Override
    public EventDto getEventById(Long id) {
        if (id == null) {
            throw new BadRequestException("Parameter 'id' may not be null");
        }

        Event event = eventRepository.findOne(id);
        if (event == null) {
            throw new NotFoundException(String.format("Event with ID '%s' not found.", id));
        }

        LOGGER.info(String.format("Returning event with ID %d", id));

        return event;
    }
}
