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
package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.domain.menu.Item;
import com.github.clboettcher.bonappetit.common.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.common.dto.menu.OptionDto;
import com.github.clboettcher.bonappetit.common.entity.ItemType;

import java.math.BigDecimal;
import java.util.Set;

public class ItemDtoBuilder {
    private String title;
    private BigDecimal price;
    private ItemType type;
    private Set<OptionDto> optionDtos;
    private Set<Item> sideDishes;
    private Long id;

    private ItemDtoBuilder() {
    }

    public static ItemDtoBuilder anItemDto() {
        return new ItemDtoBuilder();
    }

    public ItemDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemDtoBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ItemDtoBuilder withType(ItemType type) {
        this.type = type;
        return this;
    }

    public ItemDtoBuilder withOptionDtos(Set<OptionDto> optionDtos) {
        this.optionDtos = optionDtos;
        return this;
    }

    public ItemDtoBuilder withSideDishes(Set<Item> sideDishes) {
        this.sideDishes = sideDishes;
        return this;
    }

    public ItemDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ItemDtoBuilder but() {
        return anItemDto().withTitle(title).withPrice(price).withType(type).withOptionDtos(optionDtos).withSideDishes(sideDishes).withId(id);
    }

    public ItemDto build() {
        ItemDto itemDto = new ItemDto();
        itemDto.setTitle(title);
        itemDto.setPrice(price);
        itemDto.setType(type);
        itemDto.setOptionDtos(optionDtos);
        itemDto.setSideDishes(sideDishes);
        itemDto.setId(id);
        return itemDto;
    }
}
