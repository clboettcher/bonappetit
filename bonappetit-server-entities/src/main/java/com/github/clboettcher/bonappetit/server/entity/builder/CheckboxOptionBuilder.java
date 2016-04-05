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

import com.github.clboettcher.bonappetit.server.entity.menu.CheckboxOption;

import java.math.BigDecimal;

public class CheckboxOptionBuilder {
    private BigDecimal priceDiff = BigDecimal.ZERO;
    private Boolean defaultChecked = false;
    private long id;
    private String title;
    private Integer index;

    private CheckboxOptionBuilder() {
    }

    public static CheckboxOptionBuilder aCheckboxOption() {
        return new CheckboxOptionBuilder();
    }

    public CheckboxOptionBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public CheckboxOptionBuilder withDefaultChecked(Boolean defaultChecked) {
        this.defaultChecked = defaultChecked;
        return this;
    }

    public CheckboxOptionBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public CheckboxOptionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public CheckboxOptionBuilder withIndex(Integer index) {
        this.index = index;
        return this;
    }

    public CheckboxOptionBuilder but() {
        return aCheckboxOption().withPriceDiff(priceDiff).withDefaultChecked(defaultChecked).withId(id).withTitle(title).withIndex(index);
    }

    public CheckboxOption build() {
        CheckboxOption checkboxOption = new CheckboxOption();
        checkboxOption.setPriceDiff(priceDiff);
        checkboxOption.setDefaultChecked(defaultChecked);
        checkboxOption.setId(id);
        checkboxOption.setTitle(title);
        checkboxOption.setIndex(index);
        return checkboxOption;
    }
}
