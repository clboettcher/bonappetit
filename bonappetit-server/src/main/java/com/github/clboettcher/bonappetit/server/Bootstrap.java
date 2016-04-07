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
package com.github.clboettcher.bonappetit.server;

import com.github.clboettcher.bonappetit.common.entity.ItemType;
import com.github.clboettcher.bonappetit.server.entity.builder.*;
import com.github.clboettcher.bonappetit.server.entity.event.Event;
import com.github.clboettcher.bonappetit.server.entity.menu.Menu;
import com.github.clboettcher.bonappetit.server.entity.menu.Option;
import com.github.clboettcher.bonappetit.server.entity.menu.RadioItem;
import com.github.clboettcher.bonappetit.server.entity.menu.RadioOption;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffListing;
import com.github.clboettcher.bonappetit.server.repository.EventRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * This class bootstraps some testdata and will be deleted at some point.
 */
@Component
public class Bootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    @Autowired
    public Bootstrap(EventRepository eventRepository) {
        LOGGER.info("Bootstrapping db");

        Menu menu = createMenu();
        StaffListing staffListing = createStaffListing();

        Event event = EventBuilder.anEvent()
                .withTitle("Test Event")
                .withCreated(new Date())
                .withMenu(menu)
                .withStaffListing(staffListing)
                .build();

        eventRepository.save(event);
    }

    private Menu createMenu() {
        return MenuBuilder.aMenu()
                .withItems(Sets.newLinkedHashSet(Lists.newArrayList(
                        ItemBuilder.anItem()
                                .withTitle("Pommes")
                                .withPrice(new BigDecimal("2.5"))
                                .withType(ItemType.FOOD)
                                .withOptions(Sets.<Option>newLinkedHashSet(Lists.newArrayList(
                                        ValueOptionBuilder.aValueOption()
                                                .withTitle("Extra Salz")
                                                .withIndex(2)
                                                .withDefaultChecked(false)
                                                .withPriceDiff(BigDecimal.ZERO)
                                                .build()
                                )))
                                .build(),
                        ItemBuilder.anItem()
                                .withTitle("Nachos")
                                .withPrice(new BigDecimal("2.0"))
                                .withType(ItemType.FOOD)
                                .withOptions(Sets.<Option>newLinkedHashSet(Lists.newArrayList(
                                        ValueOptionBuilder.aValueOption()
                                                .withTitle("Käsesoße")
                                                .withIndex(0)
                                                .withDefaultChecked(true)
                                                .withPriceDiff(new BigDecimal("0.5"))
                                                .build()
                                )))
                                .build(),
                        ItemBuilder.anItem()
                                .withTitle("Mineralwasser")
                                .withPrice(new BigDecimal("1.9"))
                                .withType(ItemType.DRINK_NON_ALCOHOLIC)
                                .build(),
                        ItemBuilder.anItem()
                                .withTitle("Augustinger")
                                .withPrice(new BigDecimal("2.2"))
                                .withType(ItemType.DRINK_ALCOHOLIC)
                                .withOptions(Sets.<Option>newHashSet(
                                        createRadioOption("Größe", 0,
                                                RadioItemBuilder.aRadioItem()
                                                        .withTitle("groß")
                                                        .withIndex(0)
                                                        .withPriceDiff(BigDecimal.ZERO)
                                                        .build(),
                                                RadioItemBuilder.aRadioItem()
                                                        .withTitle("klein")
                                                        .withIndex(1)
                                                        .withPriceDiff(new BigDecimal("0.5"))
                                                        .build())
                                ))
                                .build()
                )))
                .build();
    }

    private StaffListing createStaffListing() {
        return StaffListingBuilder.aStaffListing()
                .withStaffMembers(Sets.newHashSet(
                        StaffMemberBuilder.aStaffMember()
                                .withFirstName("John")
                                .withLastName("Smith")
                                .build(),
                        StaffMemberBuilder.aStaffMember()
                                .withFirstName("Agathe")
                                .withLastName("Bauer")
                                .build()
                ))
                .build();
    }

    private static RadioOption createRadioOption(String title, int index, RadioItem defaultSelected, RadioItem... otherItems) {
        Set<RadioItem> items = Sets.newHashSet(otherItems);
        items.add(defaultSelected);

        return RadioOptionBuilder.aRadioOption()
                .withIndex(index)
                .withDefaultSelected(defaultSelected)
                .withTitle(title)
                .withRadioItems(items)
                .build();
    }
}
