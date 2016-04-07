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
package com.github.clboettcher.bonappetit.server.entity.builder;

import com.github.clboettcher.bonappetit.server.entity.menu.ValueOption;

import java.math.BigDecimal;

public class ValueOptionBuilder {
    private BigDecimal priceDiff = BigDecimal.ZERO;
    private Boolean defaultChecked = false;
    private int defaultValue = 1;
    private long id;
    private String title;
    private Integer index;

    private ValueOptionBuilder() {
    }

    public static ValueOptionBuilder aValueOption() {
        return new ValueOptionBuilder();
    }

    public ValueOptionBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public ValueOptionBuilder withDefaultChecked(Boolean defaultChecked) {
        this.defaultChecked = defaultChecked;
        return this;
    }

    public ValueOptionBuilder withDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public ValueOptionBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ValueOptionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ValueOptionBuilder withIndex(Integer index) {
        this.index = index;
        return this;
    }

    public ValueOptionBuilder but() {
        return aValueOption().withPriceDiff(priceDiff).withDefaultChecked(defaultChecked).withId(id).withTitle(title).withIndex(index);
    }

    public ValueOption build() {
        ValueOption valueOption = new ValueOption();
        valueOption.setPriceDiff(priceDiff);
        valueOption.setDefaultChecked(defaultChecked);
        valueOption.setId(id);
        valueOption.setTitle(title);
        valueOption.setIndex(index);
        return valueOption;
    }
}
