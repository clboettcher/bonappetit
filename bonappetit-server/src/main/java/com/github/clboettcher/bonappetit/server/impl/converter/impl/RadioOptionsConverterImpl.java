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

import com.github.clboettcher.bonappetit.common.dto.menu.RadioOptionDto;
import com.github.clboettcher.bonappetit.server.entity.menu.RadioItem;
import com.github.clboettcher.bonappetit.server.entity.menu.RadioOption;
import com.github.clboettcher.bonappetit.server.impl.converter.api.RadioItemsConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.api.RadioOptionsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Default impl of {@link RadioOptionsConverter}.
 */
@Component
public class RadioOptionsConverterImpl implements RadioOptionsConverter {

    private RadioItemsConverter radioItemsConverter;

    /**
     * Constructor setting the specified properties.
     *
     * @param radioItemsConverter The converter for {@link RadioItem}s.
     */
    @Autowired
    public RadioOptionsConverterImpl(RadioItemsConverter radioItemsConverter) {
        this.radioItemsConverter = radioItemsConverter;
    }

    @Override
    public RadioOptionDto convert(RadioOption option) {
        RadioOptionDto result = new RadioOptionDto();
        result.setRadioItemDtos(radioItemsConverter.convert(option.getRadioItems()));
        result.setDefaultSelectedId(option.getDefaultSelected().getId());
        result.setTitle(option.getTitle());
        return result;
    }
}
