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
package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.event.EventDto;
import com.github.clboettcher.bonappetit.serverentities.event.Event;
import com.github.clboettcher.bonappetit.serverentities.menu.Menu;
import com.github.clboettcher.bonappetit.serverentities.staff.StaffListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Converts {@link Event}s to {@link EventDto}s.
 */
@Component
public class EventsConverter {

    private StaffListingsConverter staffListingsConverter;
    private MenusConverter menusConverter;

    /**
     * Constructor setting the specified properties.
     *
     * @param staffListingsConverter The converter for {@link StaffListing}.
     * @param menusConverter         The converter for {@link Menu}.
     */
    @Autowired
    public EventsConverter(StaffListingsConverter staffListingsConverter, MenusConverter menusConverter) {
        this.staffListingsConverter = staffListingsConverter;
        this.menusConverter = menusConverter;
    }

    /**
     * Converts the given {@link Event} to an {@link EventDto}.
     *
     * @param event The {@link Event} to convert.
     * @return The resulting {@link EventDto}.
     */
    public EventDto convertToDto(Event event) {
        return EventDto.newBuilder()
                .id(event.getId())
                .title(event.getTitle())
                .menuDto(menusConverter.convertToDto(event.getMenu()))
                .staffListingDto(staffListingsConverter.convertToDto(event.getStaffListing()))
                .build();
    }
}
