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

import com.github.clboettcher.bonappetit.server.persistence.entity.menu.ValueOptionEntity;

import java.math.BigDecimal;

public class ValueOptionEntityBuilder {
    private BigDecimal priceDiff = BigDecimal.ZERO;
    private Boolean defaultChecked;
    private int defaultValue;
    private long id;
    private String title;
    // 'index' is reserved in mysql
    private Integer index;

    private ValueOptionEntityBuilder() {
    }

    public static ValueOptionEntityBuilder aValueOptionEntity() {
        return new ValueOptionEntityBuilder();
    }

    public ValueOptionEntityBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public ValueOptionEntityBuilder withDefaultChecked(Boolean defaultChecked) {
        this.defaultChecked = defaultChecked;
        return this;
    }

    public ValueOptionEntityBuilder withDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public ValueOptionEntityBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ValueOptionEntityBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ValueOptionEntityBuilder withIndex(Integer index) {
        this.index = index;
        return this;
    }

    public ValueOptionEntityBuilder but() {
        return aValueOptionEntity().withPriceDiff(priceDiff).withDefaultChecked(defaultChecked).withDefaultValue(defaultValue).withId(id).withTitle(title).withIndex(index);
    }

    public ValueOptionEntity build() {
        ValueOptionEntity valueOptionEntity = new ValueOptionEntity();
        valueOptionEntity.setPriceDiff(priceDiff);
        valueOptionEntity.setDefaultChecked(defaultChecked);
        valueOptionEntity.setDefaultValue(defaultValue);
        valueOptionEntity.setId(id);
        valueOptionEntity.setTitle(title);
        valueOptionEntity.setIndex(index);
        return valueOptionEntity;
    }
}
