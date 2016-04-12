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
package com.github.clboettcher.bonappetit.server.persistence.impl.entity.builder;

import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.RadioItemEntity;

import java.math.BigDecimal;

public class RadioItemEntityBuilder {
    private long id;
    private String title; // 'index' is reserved in mysql
    private Integer index;
    private BigDecimal priceDiff = BigDecimal.ZERO;

    private RadioItemEntityBuilder() {
    }

    public static RadioItemEntityBuilder aRadioItemEntity() {
        return new RadioItemEntityBuilder();
    }

    public RadioItemEntityBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public RadioItemEntityBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public RadioItemEntityBuilder withIndex(Integer index) {
        this.index = index;
        return this;
    }

    public RadioItemEntityBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public RadioItemEntityBuilder but() {
        return aRadioItemEntity().withId(id).withTitle(title).withIndex(index).withPriceDiff(priceDiff);
    }

    public RadioItemEntity build() {
        RadioItemEntity radioItemEntity = new RadioItemEntity();
        radioItemEntity.setId(id);
        radioItemEntity.setTitle(title);
        radioItemEntity.setIndex(index);
        radioItemEntity.setPriceDiff(priceDiff);
        return radioItemEntity;
    }
}
