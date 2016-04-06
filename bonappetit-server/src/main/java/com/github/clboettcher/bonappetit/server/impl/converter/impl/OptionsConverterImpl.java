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
package com.github.clboettcher.bonappetit.server.impl.converter.impl;

import com.github.clboettcher.bonappetit.common.dto.menu.CheckboxOptionDto;
import com.github.clboettcher.bonappetit.common.dto.menu.IntegerOptionDto;
import com.github.clboettcher.bonappetit.common.dto.menu.OptionDto;
import com.github.clboettcher.bonappetit.server.entity.menu.CheckboxOption;
import com.github.clboettcher.bonappetit.server.entity.menu.IntegerOption;
import com.github.clboettcher.bonappetit.server.entity.menu.Option;
import com.github.clboettcher.bonappetit.server.entity.menu.RadioOption;
import com.github.clboettcher.bonappetit.server.impl.converter.api.OptionsConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.api.RadioOptionsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Default impl of {@link OptionsConverter}.
 */
@Component
public class OptionsConverterImpl implements OptionsConverter {

    private RadioOptionsConverter radioOptionsConverter;

    /**
     * Constructor setting the specified properties.
     *
     * @param radioOptionsConverter The converter for {@link RadioOption}s.
     */
    @Autowired
    public OptionsConverterImpl(RadioOptionsConverter radioOptionsConverter) {
        this.radioOptionsConverter = radioOptionsConverter;
    }

    @Override
    public Set<OptionDto> convert(Set<Option> options) {
        LinkedHashSet<OptionDto> optionDtos = new LinkedHashSet<>(options.size());
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
    private OptionDto convert(Option option) {
        if (option instanceof RadioOption) {
            return radioOptionsConverter.convert((RadioOption) option);
        } else if (option instanceof CheckboxOption) {
            return convertToCheckboxOptionDto((CheckboxOption) option);
        } else if (option instanceof IntegerOption) {
            return convertToIntegerOptionDto((IntegerOption) option);
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype of %s: %s",
                    Option.class.getName(),
                    option.getClass().getName()));
        }
    }

    /**
     * Converts a {@link CheckboxOption} to a {@link CheckboxOptionDto}.
     *
     * @param checkboxOption The {@link CheckboxOption} to convert.
     * @return The resulting {@link CheckboxOptionDto}.
     */
    private static CheckboxOptionDto convertToCheckboxOptionDto(CheckboxOption checkboxOption) {
        CheckboxOptionDto result = new CheckboxOptionDto();
        result.setId(checkboxOption.getId());
        result.setDefaultChecked(checkboxOption.getDefaultChecked());
        result.setPriceDiff(checkboxOption.getPriceDiff());
        result.setTitle(checkboxOption.getTitle());
        return result;
    }

    /**
     * Converts an {@link IntegerOption} to an {@link IntegerOptionDto}.
     *
     * @param integerOption The {@link IntegerOption} to convert.
     * @return The resulting {@link IntegerOptionDto}.
     */
    private static IntegerOptionDto convertToIntegerOptionDto(IntegerOption integerOption) {
        IntegerOptionDto result = new IntegerOptionDto();
        result.setId(integerOption.getId());
        result.setDefaultValue(integerOption.getDefaultValue());
        result.setPriceDiff(integerOption.getPriceDiff());
        result.setTitle(integerOption.getTitle());
        return result;
    }
}
