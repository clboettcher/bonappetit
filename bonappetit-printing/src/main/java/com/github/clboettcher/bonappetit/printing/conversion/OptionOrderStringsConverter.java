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
package com.github.clboettcher.bonappetit.printing.conversion;

import com.github.clboettcher.bonappetit.printing.entity.OptionOrderStrings;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderDto;

import java.util.List;

/**
 * Converts {@link OptionOrderDto}s to a form that can be printed easily.
 */
public interface OptionOrderStringsConverter {

    /**
     * Converts the given options to the strings ready to be printed.
     *
     * @param optionOrderDtos The {@link OptionOrderDto}s to convert, may be null or empty.
     * @return The given options as string, empty strings if options is null or empty.
     */
    OptionOrderStrings convert(List<OptionOrderDto> optionOrderDtos);
}
