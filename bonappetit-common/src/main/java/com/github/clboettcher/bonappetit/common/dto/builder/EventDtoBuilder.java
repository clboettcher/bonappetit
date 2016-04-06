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
package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.dto.event.EventDto;
import com.github.clboettcher.bonappetit.common.dto.menu.MenuDto;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffListingDto;

public class EventDtoBuilder {
    private String title;
    private MenuDto menuDto;
    private StaffListingDto staffListingDto;
    private Long id;

    private EventDtoBuilder() {
    }

    public static EventDtoBuilder anEventDto() {
        return new EventDtoBuilder();
    }

    public EventDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public EventDtoBuilder withMenuDto(MenuDto menuDto) {
        this.menuDto = menuDto;
        return this;
    }

    public EventDtoBuilder withStaffListingDto(StaffListingDto staffListingDto) {
        this.staffListingDto = staffListingDto;
        return this;
    }

    public EventDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public EventDtoBuilder but() {
        return anEventDto().withTitle(title).withMenuDto(menuDto).withStaffListingDto(staffListingDto).withId(id);
    }

    public EventDto build() {
        EventDto eventDto = new EventDto();
        eventDto.setTitle(title);
        eventDto.setMenuDto(menuDto);
        eventDto.setStaffListingDto(staffListingDto);
        eventDto.setId(id);
        return eventDto;
    }
}
