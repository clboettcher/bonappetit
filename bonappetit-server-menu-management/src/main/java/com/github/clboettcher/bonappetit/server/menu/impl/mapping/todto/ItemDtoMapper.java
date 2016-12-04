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
package com.github.clboettcher.bonappetit.server.menu.impl.mapping.todto;

import com.github.clboettcher.bonappetit.server.menu.api.dto.common.ItemDtoType;
import com.github.clboettcher.bonappetit.server.menu.api.dto.read.ItemDto;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntityType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = OptionDtoMapper.class, componentModel = "spring")
public interface ItemDtoMapper {

    /**
     * Converts the given {@link ItemEntity}s to {@link ItemDto}s.
     *
     * @param items The items to map.
     * @return The mapping result.
     */
    List<ItemDto> mapToItemEntityDtos(List<ItemEntity> items);

    /**
     * @param item The {@link ItemEntity} to map.
     * @return The mapping result.
     */
    ItemDto mapToItemEntityDto(ItemEntity item);

    /**
     * @param itemType The {@link ItemEntityType} to map.
     * @return The mapping result.
     */
    ItemDtoType mapToItemEntityDtoType(ItemEntityType itemType);
}
