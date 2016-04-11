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
package com.github.clboettcher.bonappetit.common.converter;

import com.github.clboettcher.bonappetit.common.domain.menu.Option;
import com.github.clboettcher.bonappetit.common.domain.menu.RadioOption;
import com.github.clboettcher.bonappetit.common.domain.menu.ValueOption;
import com.github.clboettcher.bonappetit.common.dto.menu.OptionDto;

import java.util.LinkedHashSet;
import java.util.Set;

public class OptionsConverter {

    public static Set<OptionDto> convert(Set<Option> options) {
        LinkedHashSet<OptionDto> optionDtos = new LinkedHashSet<OptionDto>(options.size());
        for (Option option : options) {
            optionDtos.add(convert(option));
        }
        return optionDtos;
    }

    /**
     * Converts the given {@link Option} to an {@link OptionDto}.
     *
     * @param option The {@link Option} to convert.
     * @return The resulting {@link OptionDto}.
     */
    private static OptionDto convert(Option option) {
        if (option instanceof RadioOption) {
            return RadioOptionsConverter.convertToDto((RadioOption) option);
        } else if (option instanceof ValueOption) {
            return ValueOptionsConverter.convertToValueOptionDto((ValueOption) option);
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype of %s: %s",
                    Option.class.getName(),
                    option.getClass().getName()));
        }
    }

}
