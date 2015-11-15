package com.github.clboettcher.bonappetit.server.impl;

import com.github.clboettcher.bonappetit.common.dto.event.EventDto;
import com.github.clboettcher.bonappetit.common.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.common.dto.menu.MenuDto;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffListingDto;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffMemberDto;
import com.github.clboettcher.bonappetit.common.entity.ItemType;
import com.github.clboettcher.bonappetit.server.api.EventsService;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;

/**
 * Default impl of {@link com.github.clboettcher.bonappetit.server.api.EventsService}.
 */
@Component
public class EventsServiceImpl implements EventsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsServiceImpl.class);

    @Override
    public EventDto getEventById(Long id) {
        if (id == null) {
            throw new BadRequestException("Parameter 'id' may not be null");
        }

        LOGGER.info(String.format("Returning event with ID %d", id));

        // ATM we only return static test data.
        return EventDto.newBuilder()
                .id(1)
                .title("Friday Lounge Junge Erwachsene")
                .menuDto(MenuDto.newBuilder()
                        .items(Sets.newHashSet(
                                ItemDto.newBuilder()
                                        .title("Pommes")
                                        .price(new BigDecimal("2.5"))
                                        .type(ItemType.FOOD)
                                        .build(),
                                ItemDto.newBuilder()
                                        .title("Cola")
                                        .price(new BigDecimal("2.2"))
                                        .type(ItemType.DRINK_NON_ALCOHOLIC)
                                        .build()
                        ))
                        .build())
                .staffListingDto(StaffListingDto.newBuilder()
                        .staffMembers(Sets.newHashSet(
                                StaffMemberDto.newBuilder().name("Ranjid").build(),
                                StaffMemberDto.newBuilder().name("Sieglinde").build()
                        ))
                        .build())
                .build();
    }
}
