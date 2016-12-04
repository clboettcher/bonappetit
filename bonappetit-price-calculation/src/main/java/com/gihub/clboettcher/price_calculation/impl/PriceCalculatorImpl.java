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
package com.gihub.clboettcher.price_calculation.impl;

import com.gihub.clboettcher.price_calculation.api.PriceCalculator;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.*;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Default impl of {@link PriceCalculator}.
 */
public class PriceCalculatorImpl implements PriceCalculator {

    @Override
    public BigDecimal calculateTotalPrice(ItemOrderDto itemOrder) {
        Preconditions.checkNotNull(itemOrder, "itemOrder");
        Preconditions.checkNotNull(itemOrder.getItemPrice(), "itemOrder.getPrice()");
        BigDecimal result = itemOrder.getItemPrice();

        final List<OptionOrderDto> optionOrders = itemOrder.getOptionOrders();
        if (CollectionUtils.isNotEmpty(optionOrders)) {
            for (OptionOrderDto optionOrder : optionOrders) {
                if (optionOrder instanceof ValueOptionOrderDto) {
                    ValueOptionOrderDto valueOptionOrder = (ValueOptionOrderDto) optionOrder;
                    final BigDecimal valueOptionOrderPrice = valueOptionOrder.getOptionPriceDiff().multiply(
                            new BigDecimal(String.valueOf(valueOptionOrder.getValue())));
                    result = result.add(valueOptionOrderPrice);
                } else if (optionOrder instanceof RadioOptionOrderDto) {
                    RadioOptionOrderDto radioOptionOrder = (RadioOptionOrderDto) optionOrder;
                    result = result.add(radioOptionOrder.getOptionPriceDiff());
                } else if (optionOrder instanceof CheckboxOptionOrderDto) {
                    CheckboxOptionOrderDto checkboxOptionOrder = (CheckboxOptionOrderDto) optionOrder;
                    if (checkboxOptionOrder.getChecked()) {
                        result = result.add(checkboxOptionOrder.getOptionPriceDiff());
                    }
                } else {
                    throw new IllegalArgumentException(String.format("Could not calculate price for option " +
                            "order with unknown type %s", (optionOrder != null
                            ? optionOrder.getClass().getSimpleName() : "<null>")));
                }
            }
        }

        return result.setScale(2, RoundingMode.HALF_UP);
    }
}
