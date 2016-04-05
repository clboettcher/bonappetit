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
import com.github.clboettcher.bonappetit.server.entity.menu.*;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffListing;
import com.github.clboettcher.bonappetit.server.repository.EventRepository;
import com.github.clboettcher.bonappetit.server.repository.MenuRepository;
import com.github.clboettcher.bonappetit.server.repository.StaffListingRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class bootstraps some testdata and will be deleted at some point.
 */
@Component
public class Bootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    @Autowired
    public Bootstrap(EventRepository eventRepository,
                     MenuRepository menuRepository,
                     StaffListingRepository staffListingRepository) {
        LOGGER.info("Bootstrapping db");

        // Staff
//        StaffListing staffListing = createStaffListing();
//        staffListingRepository.save(staffListing);

        // Menu
//        Menu menu = createMenu();
//        menuRepository.save(menu);

//        Item testItem = ItemBuilder.anItem()
//                .withTitle("Title")
//                .withPrice(new BigDecimal("1.9"))
//                .withType(ItemType.DRINK_NON_ALCOHOLIC)
//                .build();
//
//        itemRepository.save(testItem);

        // Event
//        Event event = createEvent(staffListing, menu);

//        eventRepository.save(event);

//        StaffMember staffMember = new StaffMember();
//        staffMember.setFirstName("Max");
//        staffMember.setLastName("Musterman");
//
//        staffMemberRepository.save(staffMember);
//
//        LOGGER.info(String.format("Saved %s", staffMember));
    }

    private Menu createMenu() {
        return MenuBuilder.aMenu()
                .withItems(Sets.newLinkedHashSet(Lists.newArrayList(
                        ItemBuilder.anItem()
                                .withTitle("Pommes")
                                .withPrice(new BigDecimal("2.5"))
                                .withType(ItemType.FOOD)
                                .withOptions(Sets.newLinkedHashSet(Lists.newArrayList(
                                        createIntegerOption("Ketchup", 2, new BigDecimal("0.5"), 0),
                                        createIntegerOption("Mayonnaise", 0, new BigDecimal("0.8"), 1),
                                        createCheckboxOption("Extra Salz", false, BigDecimal.ZERO)
                                )))
                                .build(),
                        ItemBuilder.anItem()
                                .withTitle("Nachos")
                                .withPrice(new BigDecimal("2.0"))
                                .withType(ItemType.FOOD)
                                .withOptions(Sets.<Option>newLinkedHashSet(Lists.newArrayList(
                                        createCheckboxOption("Käsesoße", true, new BigDecimal("0.5"))
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
                                .withOptions(Sets.<Option>newLinkedHashSet(Collections.singletonList(
                                        createRadioOption()
                                )))
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

    public static Event createEvent(StaffListing staffListing, Menu menu) {
        return EventBuilder.anEvent()
                .withTitle("My awesome Event")
                .withMenu(menu)
                .withStaffListing(staffListing)
                .build();
    }

    private static CheckboxOption createCheckboxOption(String withTitle, boolean defaultChecked, BigDecimal withPriceDiff) {

//        return CheckboxOptionBuilder.aCheckboxOption()
//                .build();

        CheckboxOption result = new CheckboxOption();
        result.setTitle(withTitle);
        result.setDefaultChecked(defaultChecked);
        result.setPriceDiff(withPriceDiff);
        return result;
    }

    private static RadioOption createRadioOption() {
        RadioOption result = new RadioOption();
        result.setTitle("Größe");
        RadioItem defaultSelected = createRadioItem("groß", BigDecimal.ZERO);
        result.setRadioItems(Sets.newLinkedHashSet(Arrays.asList(
                defaultSelected,
                createRadioItem("klein", new BigDecimal("0.5"))
        )));
        result.setDefaultSelected(defaultSelected);
        return result;
    }

    private static RadioItem createRadioItem(String withTitle, BigDecimal withPriceDiff) {
        return RadioItemBuilder.aRadioItem()
                .withTitle(withTitle)
                .withPriceDiff(withPriceDiff)
                .build();
    }

    private static IntegerOption createIntegerOption(String withTitle, int defaultValue, BigDecimal withPriceDiff, Integer index) {
        return IntegerOptionBuilder.anIntegerOption()
                .withIndex(index)
                .withDefaultValue(defaultValue)
                .withPriceDiff(withPriceDiff)
                .withTitle(withTitle)
                .build();
    }
}
