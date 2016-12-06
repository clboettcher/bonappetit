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

import com.github.clboettcher.bonappetit.server.order.api.dto.read.*;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.math.BigDecimal;

/**
 * Base class for converter tests in the context of printing.
 */
public abstract class AbstractConverterTest {

    protected RadioOptionOrderDto orderForRadioOption(Long selectedRadioItemId,
                                                      String selectedRadioItemTitle,
                                                      BigDecimal selectedRadioItemPriceDiff) {
        return RadioOptionOrderDto.builder()
                .selectedRadioItemId(selectedRadioItemId)
                .selectedRadioItemTitle(selectedRadioItemTitle)
                .selectedRadioItemPriceDiff(selectedRadioItemPriceDiff)
                .build();
    }

    protected ValueOptionOrderDto orderForValueOption(int value, String optionTitle, BigDecimal optionPriceDiff) {
        return ValueOptionOrderDto.builder()
                .value(value)
                .optionTitle(optionTitle)
                .optionPriceDiff(optionPriceDiff)
                .build();
    }

    protected CheckboxOptionOrderDto orderForCheckboxOption(Long checkboxOptionId,
                                                            Boolean checked, String optionTitle,
                                                            BigDecimal optionPriceDiff) {
        return CheckboxOptionOrderDto.builder()
                .checkboxOptionId(checkboxOptionId)
                .checked(checked)
                .optionTitle(optionTitle)
                .optionPriceDiff(optionPriceDiff)
                .build();
    }

    protected ItemOrderDto orderForItem(Long itemId, String note, OptionOrderDto... optionOrders) {
        return ItemOrderDto.builder()
                .itemId(itemId)
                .deliverTo("Tisch 12")
                .note(note)
                .staffMemberFirstName("John")
                .staffMemberLastName("Smith")
                .orderTime(DateTime.now(DateTimeZone.UTC))
                .optionOrders(Lists.newArrayList(optionOrders))
                .build();
    }
}
