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
