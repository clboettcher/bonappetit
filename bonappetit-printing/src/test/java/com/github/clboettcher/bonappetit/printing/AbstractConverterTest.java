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
package com.github.clboettcher.bonappetit.printing;

import com.github.clboettcher.bonappetit.domain.menu.*;
import com.github.clboettcher.bonappetit.domain.order.*;
import com.github.clboettcher.bonappetit.domain.staff.StaffMember;
import com.google.common.collect.Sets;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Base class for converter tests in the context of printing.
 */
public abstract class AbstractConverterTest {

    /**
     * @param item The selected item.
     * @return A new {@link RadioOptionOrder} for the given {@code item}.
     */
    protected RadioOptionOrder orderForRadioItem(RadioItem item) {
        return RadioOptionOrder.builder()
                .selectedItem(item)
                .build();
    }

    /**
     * @param title The {@link RadioItem}s title.
     * @return A new {@link RadioItem} with the given {@code title}.
     */
    protected RadioItem radioItem(String title) {
        return RadioItem.builder()
                .title(title)
                .build();
    }

    /**
     * @param title The {@link ValueOption}s title.
     * @return A new {@link ValueOption} with the given {@code title}.
     */
    protected ValueOption valueOption(String title) {
        return ValueOption.builder()
                .title(title)
                .build();
    }

    /**
     * @param option The {@link ValueOption} the order is taken for.
     * @param value  The ordered value.
     * @return A new {@link ValueOptionOrder} for the given {@code option} with the given value.
     */
    protected ValueOptionOrder orderForValueOption(ValueOption option, int value) {
        return ValueOptionOrder.builder()
                .value(value)
                .option(option)
                .build();
    }

    /**
     * @param title The options title.
     * @return A new {@link CheckboxOption} with the given {@code title}.
     */
    protected CheckboxOption checkboxOption(String title) {
        return CheckboxOption.builder()
                .title(title)
                .build();
    }

    /**
     * @param checkboxOption The {@link CheckboxOption} the order is taken for.
     * @return A new {@link CheckboxOptionOrder} for the given {@code checkboxOption}.
     */
    protected CheckboxOptionOrder orderForCheckboxOption(CheckboxOption checkboxOption) {
        return CheckboxOptionOrder.builder()
                .option(checkboxOption)
                .build();
    }

    /**
     * @param name    The item name.
     * @param options The items options.
     * @return A new {@link Item} with the given {@code name} and {@code options}.
     */
    protected Item createItem(String name, Option... options) {
        return Item.builder()
                .title(name)
                .price(BigDecimal.ONE)
                .options(Sets.newHashSet(options))
                .type(ItemType.FOOD)
                .build();
    }

    /**
     * @param item         The ordered item.
     * @param note         A note.
     * @param optionOrders The ordered options.
     * @return A new {@link ItemOrder} for the given {@code item} with the given properties.
     */
    protected ItemOrder orderForItem(Item item, String note, OptionOrder... optionOrders) {
        return ItemOrder.builder()
                .deliverTo("Tisch 12")
                .note(note)
                .staffMember(StaffMember.builder()
                        .firstName("John")
                        .lastName("Smith")
                        .build())
                .item(item)
                .orderTime(DateTime.now())
                .optionOrders(Sets.newHashSet(optionOrders))
                .build();
    }

    /**
     * @param title           The option title.
     * @param defaultSelected The {@link RadioItem} which is selected per default.
     * @param other           The other {@link RadioItem} that constitue the {@link RadioOption} (not selected).
     * @return A new {@link RadioOption} with the given properties.
     */
    public RadioOption radioOption(String title, RadioItem defaultSelected, RadioItem... other) {
        final Set<RadioItem> radioItems = Sets.newHashSet(other);
        radioItems.add(defaultSelected);

        return RadioOption.builder()
                .title(title)
                .id(-1L)
                .radioItems(radioItems)
                .build();
    }
}
