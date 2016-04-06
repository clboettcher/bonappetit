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

import com.github.clboettcher.bonappetit.common.dto.builder.MenuDtoBuilder;
import com.github.clboettcher.bonappetit.common.dto.menu.MenuDto;
import com.github.clboettcher.bonappetit.server.entity.menu.Menu;
import com.github.clboettcher.bonappetit.server.impl.converter.api.ItemsConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.api.MenusConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Default impl of {@link MenusConverter}.
 */
@Component
public class MenusConverterImpl implements MenusConverter {

    private ItemsConverter itemsConverter;

    @Autowired
    public MenusConverterImpl(ItemsConverter itemsConverter) {
        this.itemsConverter = itemsConverter;
    }

    @Override
    public MenuDto convertToDto(Menu menu) {
        return MenuDtoBuilder.aMenuDto()
                .withId(menu.getId())
                .withItemDtos(itemsConverter.convertToItemDtos(menu.getItems()))
                .build();
    }
}
