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
import com.github.clboettcher.bonappetit.common.dto.menu.*;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffListingDto;
import com.github.clboettcher.bonappetit.common.dto.staff.StaffMemberDto;
import com.github.clboettcher.bonappetit.common.entity.ItemType;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Default impl of {@link com.github.clboettcher.bonappetit.server.impl.EventsDao}.
 */
@Component
public class EventsDaoImpl implements EventsDao {
    @Override
    public EventDto getEventById(Long id) {

        return EventDto.newBuilder()
                .id(1)
                .title("My awesome Event")
                .menuDto(MenuDto.newBuilder()
                        .itemDtos(Sets.newLinkedHashSet(Lists.newArrayList(
                                ItemDto.newBuilder()
                                        .title("Pommes")
                                        .price(new BigDecimal("2.5"))
                                        .type(ItemType.FOOD)
                                        .optionDtos(Sets.newLinkedHashSet(Lists.newArrayList(
                                                createIntegerOption("Ketchup", 2, new BigDecimal("0.5")),
                                                createIntegerOption("Mayonnaise", 0, new BigDecimal("0.8")),
                                                createCheckboxOption("Extra Salz", false, BigDecimal.ZERO)
                                        )))
                                        .build(),
                                ItemDto.newBuilder()
                                        .title("Nachos")
                                        .price(new BigDecimal("2.0"))
                                        .type(ItemType.FOOD)
                                        .optionDtos(Sets.<OptionDto>newLinkedHashSet(Lists.newArrayList(
                                                createCheckboxOption("Käsesoße", true, new BigDecimal("0.5"))
                                        )))
                                        .build(),
                                ItemDto.newBuilder()
                                        .title("Mineralwasser")
                                        .price(new BigDecimal("1.9"))
                                        .type(ItemType.DRINK_NON_ALCOHOLIC)
                                        .build(),
                                ItemDto.newBuilder()
                                        .title("Augustinger")
                                        .price(new BigDecimal("2.2"))
                                        .type(ItemType.DRINK_ALCOHOLIC)
                                        .optionDtos(Sets.<OptionDto>newLinkedHashSet(Arrays.asList(
                                                createRadioOption()
                                        )))
                                        .build()
                        )))
                        .build())
                .staffListingDto(StaffListingDto.newBuilder()
                        .staffMembers(Sets.newHashSet(
                                StaffMemberDto.newBuilder().id(1L).name("Ranjid").build(),
                                StaffMemberDto.newBuilder().id(2L).name("Sieglinde").build()
                        ))
                        .build())
                .build();
    }

    private static CheckboxOptionDto createCheckboxOption(String title, boolean defaultChecked, BigDecimal priceDiff) {
        CheckboxOptionDto result = new CheckboxOptionDto();
        result.setId(1L);
        result.setTitle(title);
        result.setDefaultChecked(defaultChecked);
        result.setPriceDiff(priceDiff);
        return result;
    }

    private static RadioOptionDto createRadioOption() {
        RadioOptionDto result = new RadioOptionDto();
        result.setId(1L);
        result.setTitle("Größe");
        result.setRadioItemDtos(Sets.newLinkedHashSet(Arrays.asList(
                createRadioItemDto(1L, "groß", BigDecimal.ZERO),
                createRadioItemDto(2L, "klein", new BigDecimal("0.5"))
        )));
        result.setDefaultSelectedId(1L);
        return result;
    }

    private static RadioItemDto createRadioItemDto(long id, String title, BigDecimal priceDiff) {
        return RadioItemDto.newBuilder()
                .id(id)
                .title(title)
                .priceDiff(priceDiff)
                .build();
    }

    private static IntegerOptionDto createIntegerOption(String title, int defaultValue, BigDecimal priceDiff) {
        IntegerOptionDto result = new IntegerOptionDto();
        result.setDefaultValue(defaultValue);
        result.setPriceDiff(priceDiff);
        result.setTitle(title);
        return result;
    }
}
