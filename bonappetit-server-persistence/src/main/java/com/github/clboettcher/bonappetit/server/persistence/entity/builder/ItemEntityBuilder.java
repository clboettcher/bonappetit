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
package com.github.clboettcher.bonappetit.server.persistence.entity.builder;

import com.github.clboettcher.bonappetit.server.persistence.entity.menu.AbstractOptionEntity;
import com.github.clboettcher.bonappetit.server.persistence.entity.menu.ItemEntity;
import com.github.clboettcher.bonappetit.server.persistence.entity.menu.ItemEntityType;

import java.math.BigDecimal;
import java.util.Set;

public class ItemEntityBuilder {
    private long id;
    private String title;
    private BigDecimal price;
    private ItemEntityType type;
    private Set<AbstractOptionEntity> options;

    private ItemEntityBuilder() {
    }

    public static ItemEntityBuilder anItemEntity() {
        return new ItemEntityBuilder();
    }

    public ItemEntityBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ItemEntityBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemEntityBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ItemEntityBuilder withType(ItemEntityType type) {
        this.type = type;
        return this;
    }

    public ItemEntityBuilder withOptions(Set<AbstractOptionEntity> options) {
        this.options = options;
        return this;
    }

    public ItemEntityBuilder but() {
        return anItemEntity().withId(id).withTitle(title).withPrice(price).withType(type).withOptions(options);
    }

    public ItemEntity build() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(id);
        itemEntity.setTitle(title);
        itemEntity.setPrice(price);
        itemEntity.setType(type);
        itemEntity.setOptions(options);
        return itemEntity;
    }
}
