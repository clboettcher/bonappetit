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

import com.github.clboettcher.bonappetit.common.dto.menu.ValueOptionDto;

import java.math.BigDecimal;

public class ValueOptionDtoBuilder {
    private BigDecimal priceDiff;
    private Boolean defaultChecked;
    private String title;
    private Long id;

    private ValueOptionDtoBuilder() {
    }

    public static ValueOptionDtoBuilder aValueOptionDto() {
        return new ValueOptionDtoBuilder();
    }

    public ValueOptionDtoBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public ValueOptionDtoBuilder withDefaultChecked(Boolean defaultChecked) {
        this.defaultChecked = defaultChecked;
        return this;
    }

    public ValueOptionDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ValueOptionDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ValueOptionDtoBuilder but() {
        return aValueOptionDto().withPriceDiff(priceDiff).withDefaultChecked(defaultChecked).withTitle(title).withId(id);
    }

    public ValueOptionDto build() {
        ValueOptionDto valueOptionDto = new ValueOptionDto();
        valueOptionDto.setPriceDiff(priceDiff);
        valueOptionDto.setDefaultChecked(defaultChecked);
        valueOptionDto.setTitle(title);
        valueOptionDto.setId(id);
        return valueOptionDto;
    }
}
