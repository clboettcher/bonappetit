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
package com.github.clboettcher.bonappetit.server.impl.converter.impl;

import com.github.clboettcher.bonappetit.common.dto.builder.EventDtoBuilder;
import com.github.clboettcher.bonappetit.common.dto.event.EventDto;
import com.github.clboettcher.bonappetit.server.entity.event.Event;
import com.github.clboettcher.bonappetit.server.impl.converter.api.EventsConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.api.MenusConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.api.StaffListingsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Default impl of {@link EventsConverter}.
 */
@Component
public class EventsConverterImpl implements EventsConverter {

    private StaffListingsConverter staffListingsConverter;
    private MenusConverter menusConverter;

    /**
     * Constructor setting the specified properties.
     *
     * @param staffListingsConverter The converter for {@link StaffListing}.
     * @param menusConverter         The converter for {@link Menu}.
     */
    @Autowired
    public EventsConverterImpl(StaffListingsConverter staffListingsConverter, MenusConverter menusConverter) {
        this.staffListingsConverter = staffListingsConverter;
        this.menusConverter = menusConverter;
    }

    @Override
    public EventDto convertToDto(Event event) {
        return EventDtoBuilder.anEventDto()
                .withId(event.getId())
                .withTitle(event.getTitle())
                .withMenuDto(menusConverter.convertToDto(event.getMenu()))
                .withStaffListingDto(staffListingsConverter.convertToDto(event.getStaffListing()))
                .build();
    }
}
