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
import com.github.clboettcher.bonappetit.server.entity.menu.*;
import com.github.clboettcher.bonappetit.server.entity.event.Event;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * This class provides data for testing and is deleted at some point.
 */
public final class TestdataProvider {

    /**
     * No instance
     */
    private TestdataProvider() {
    }

    public static Event createEvent() {
        return EventBuilder.anEvent()
                .withId(1)
                .withTitle("My awesome Event")
                .withMenu(MenuBuilder.aMenu()
                        .withItems(Sets.newLinkedHashSet(Lists.newArrayList(
                                ItemBuilder.anItem()
                                        .withTitle("Pommes")
                                        .withPrice(new BigDecimal("2.5"))
                                        .withType(ItemType.FOOD)
                                        .withOptions(Sets.newLinkedHashSet(Lists.newArrayList(
                                                createIntegerOption("Ketchup", 2, new BigDecimal("0.5")),
                                                createIntegerOption("Mayonnaise", 0, new BigDecimal("0.8")),
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
                                        .withOptions(Sets.<Option>newLinkedHashSet(Arrays.asList(
                                                createRadioOption()
                                        )))
                                        .build()
                        )))
                        .build())
                .withStaffListing(StaffListingBuilder.aStaffListing()
                        .withStaffMembers(Sets.newHashSet(
                                StaffMemberBuilder.aStaffMember()
                                        .withId(1L)
                                        .withFirstName("John")
                                        .withLastName("Smith")
                                        .build(),
                                StaffMemberBuilder.aStaffMember()
                                        .withId(2L)
                                        .withFirstName("Agathe")
                                        .withLastName("Bauer")
                                        .build()
                        ))
                        .build())
                .build();
    }

    private static CheckboxOption createCheckboxOption(String withTitle, boolean defaultChecked, BigDecimal withPriceDiff) {
        CheckboxOption result = new CheckboxOption();
        result.setId(1L);
        result.setTitle(withTitle);
        result.setDefaultChecked(defaultChecked);
        result.setPriceDiff(withPriceDiff);
        return result;
    }

    private static RadioOption createRadioOption() {
        RadioOption result = new RadioOption();
        result.setId(1L);
        result.setTitle("Größe");
        RadioItem defaultSelected = createRadioItem(1L, "groß", BigDecimal.ZERO);
        result.setRadioItems(Sets.newLinkedHashSet(Arrays.asList(
                defaultSelected,
                createRadioItem(2L, "klein", new BigDecimal("0.5"))
        )));
        result.setDefaultSelected(defaultSelected);
        return result;
    }

    private static RadioItem createRadioItem(long withId, String withTitle, BigDecimal withPriceDiff) {
        return RadioItemBuilder.aRadioItem()
                .withId(withId)
                .withTitle(withTitle)
                .withPriceDiff(withPriceDiff)
                .build();
    }

    private static IntegerOption createIntegerOption(String withTitle, int defaultValue, BigDecimal withPriceDiff) {
        IntegerOption result = new IntegerOption();
        result.setDefaultValue(defaultValue);
        result.setPriceDiff(withPriceDiff);
        result.setTitle(withTitle);
        return result;
    }
}
