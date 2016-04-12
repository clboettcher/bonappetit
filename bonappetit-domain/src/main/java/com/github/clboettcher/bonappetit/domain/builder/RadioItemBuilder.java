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

import com.github.clboettcher.bonappetit.domain.menu.RadioItem;

import java.math.BigDecimal;

public class RadioItemBuilder {
    private long id;
    private String title;
    private Integer index;
    private BigDecimal priceDiff;

    private RadioItemBuilder() {
    }

    public static RadioItemBuilder aRadioItem() {
        return new RadioItemBuilder();
    }

    public RadioItemBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public RadioItemBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public RadioItemBuilder withIndex(Integer index) {
        this.index = index;
        return this;
    }

    public RadioItemBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public RadioItemBuilder but() {
        return aRadioItem().withId(id).withTitle(title).withIndex(index).withPriceDiff(priceDiff);
    }

    public RadioItem build() {
        RadioItem radioItem = new RadioItem();
        radioItem.setId(id);
        radioItem.setTitle(title);
        radioItem.setIndex(index);
        radioItem.setPriceDiff(priceDiff);
        return radioItem;
    }
}
