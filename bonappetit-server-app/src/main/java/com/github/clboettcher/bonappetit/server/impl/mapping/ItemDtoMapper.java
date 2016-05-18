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
package com.github.clboettcher.bonappetit.server.impl.mapping;

import com.github.clboettcher.bonappetit.domain.menu.Item;
import com.github.clboettcher.bonappetit.domain.menu.ItemType;
import com.github.clboettcher.bonappetit.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.dto.menu.ItemDtoType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

/**
 * Converts {@link Item}s to {@link ItemDto}s.
 */
@Mapper(uses = OptionDtoMapper.class)
public interface ItemDtoMapper {

    /**
     * A mapper instance.
     */
    ItemDtoMapper INSTANCE = Mappers.getMapper(ItemDtoMapper.class);

    /**
     * Converts the given {@link Item}s to {@link ItemDto}s.
     *
     * @param items The items to map.
     * @return The mapping result.
     */
    Set<ItemDto> mapToItemDtos(Set<Item> items);

    /**
     * @param item The {@link Item} to map.
     * @return The mapping result.
     */
    ItemDto mapToItemDto(Item item);

    /**
     * @param itemType The {@link ItemType} to map.
     * @return The mapping result.
     */
    ItemDtoType mapToItemDtoType(ItemType itemType);
}
