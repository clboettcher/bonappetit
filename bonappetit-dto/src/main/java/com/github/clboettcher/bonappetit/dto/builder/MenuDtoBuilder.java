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
package com.github.clboettcher.bonappetit.dto.builder;

import com.github.clboettcher.bonappetit.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.dto.menu.MenuDto;

import java.util.Set;

public class MenuDtoBuilder {
    private Set<ItemDto> itemDtos;
    private Long id;

    private MenuDtoBuilder() {
    }

    public static MenuDtoBuilder aMenuDto() {
        return new MenuDtoBuilder();
    }

    public MenuDtoBuilder withItemDtos(Set<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
        return this;
    }

    public MenuDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public MenuDtoBuilder but() {
        return aMenuDto().withItemDtos(itemDtos).withId(id);
    }

    public MenuDto build() {
        MenuDto menuDto = new MenuDto();
        menuDto.setItemDtos(itemDtos);
        menuDto.setId(id);
        return menuDto;
    }
}
