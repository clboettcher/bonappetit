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
package com.github.clboettcher.printing.conversion.option;

import com.github.clboettcher.bonappetit.domain.order.OptionOrder;
import com.github.clboettcher.printing.entity.OrderOptionStrings;

import java.util.Set;

/**
 * Converts order options to a form that can be further processed more
 * easily.
 */
public interface OrderOptionConverter {

    /**
     * Converts the given options to the strings ready to be printed.
     *
     * @param optionOrders The {@link OptionOrder}s to convert, may be null or empty.
     * @return The given options as string, empty strings if options is null or empty.
     */
    OrderOptionStrings convert(Set<OptionOrder> optionOrders);
}
