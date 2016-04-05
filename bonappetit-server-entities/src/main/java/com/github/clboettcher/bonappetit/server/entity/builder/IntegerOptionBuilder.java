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

import com.github.clboettcher.bonappetit.server.entity.menu.IntegerOption;

import java.math.BigDecimal;

public class IntegerOptionBuilder {
    private BigDecimal priceDiff = BigDecimal.ZERO;
    private Integer defaultValue = 0;
    private long id;
    private String title;
    private Integer index;

    private IntegerOptionBuilder() {
    }

    public static IntegerOptionBuilder anIntegerOption() {
        return new IntegerOptionBuilder();
    }

    public IntegerOptionBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public IntegerOptionBuilder withDefaultValue(Integer defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public IntegerOptionBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public IntegerOptionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public IntegerOptionBuilder withIndex(Integer index) {
        this.index = index;
        return this;
    }

    public IntegerOptionBuilder but() {
        return anIntegerOption().withPriceDiff(priceDiff).withDefaultValue(defaultValue).withId(id).withTitle(title).withIndex(index);
    }

    public IntegerOption build() {
        IntegerOption integerOption = new IntegerOption();
        integerOption.setPriceDiff(priceDiff);
        integerOption.setDefaultValue(defaultValue);
        integerOption.setId(id);
        integerOption.setTitle(title);
        integerOption.setIndex(index);
        return integerOption;
    }
}
