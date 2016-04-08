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

import com.github.clboettcher.bonappetit.common.dto.builder.ItemDtoBuilder;
import com.github.clboettcher.bonappetit.common.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.server.entity.menu.Item;
import com.github.clboettcher.bonappetit.server.entity.menu.Option;
import com.github.clboettcher.bonappetit.server.impl.converter.api.ItemsConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.api.OptionsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Default impl of {@link ItemsConverter}.
 */
@Component
public class ItemsConverterImpl implements ItemsConverter {

    private OptionsConverter optionsConverter;

    /**
     * Constructor setting the specified properties.
     *
     * @param optionsConverter The converter for {@link Option}s.
     */
    @Autowired
    public ItemsConverterImpl(OptionsConverter optionsConverter) {
        this.optionsConverter = optionsConverter;
    }

    @Override
    public Set<ItemDto> convertToItemDtos(Set<Item> items) {
        Set<ItemDto> itemDtos = new LinkedHashSet<ItemDto>(items.size());
        for (Item item : items) {
            itemDtos.add(convertToDto(item));
        }
        return itemDtos;
    }

    /**
     * Converts the given {@link Item} to an {@link ItemDto}.
     *
     * @param item The {@link Item} to convert.
     * @return The resulting {@link ItemDto}.
     */
    private ItemDto convertToDto(Item item) {
        final Set<Option> options = item.getOptions();
        return ItemDtoBuilder.anItemDto()
                .withId(item.getId())
                .withTitle(item.getTitle())
                .withType(item.getType())
                .withPrice(item.getPrice())
                .withOptionDtos(CollectionUtils.isEmpty(options)
                        ? null
                        : optionsConverter.convert(options))
                .build();
    }
}
