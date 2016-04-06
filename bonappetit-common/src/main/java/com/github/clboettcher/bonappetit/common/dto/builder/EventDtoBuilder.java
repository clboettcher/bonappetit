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
