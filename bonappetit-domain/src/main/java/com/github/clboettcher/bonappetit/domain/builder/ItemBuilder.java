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
package com.github.clboettcher.bonappetit.domain.builder;

import com.github.clboettcher.bonappetit.domain.menu.Item;
import com.github.clboettcher.bonappetit.domain.menu.ItemType;
import com.github.clboettcher.bonappetit.domain.menu.Option;

import java.math.BigDecimal;
import java.util.Set;

public class ItemBuilder {
    private long id;
    private String title;
    private BigDecimal price;
    private ItemType type;
    private Set<Option> options;

    private ItemBuilder() {
    }

    public static ItemBuilder anItem() {
        return new ItemBuilder();
    }

    public ItemBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ItemBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ItemBuilder withType(ItemType type) {
        this.type = type;
        return this;
    }

    public ItemBuilder withOptions(Set<Option> options) {
        this.options = options;
        return this;
    }

    public ItemBuilder but() {
        return anItem().withId(id).withTitle(title).withPrice(price).withType(type).withOptions(options);
    }

    public Item build() {
        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        item.setType(type);
        item.setOptions(options);
        return item;
    }
}
