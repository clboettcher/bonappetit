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

import com.github.clboettcher.bonappetit.common.dto.menu.IntegerOptionDto;

import java.math.BigDecimal;

public class IntegerOptionDtoBuilder {
    private BigDecimal priceDiff;
    private Integer defaultValue;
    private String title;
    private Long id;

    private IntegerOptionDtoBuilder() {
    }

    public static IntegerOptionDtoBuilder anIntegerOptionDto() {
        return new IntegerOptionDtoBuilder();
    }

    public IntegerOptionDtoBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public IntegerOptionDtoBuilder withDefaultValue(Integer defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public IntegerOptionDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public IntegerOptionDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public IntegerOptionDtoBuilder but() {
        return anIntegerOptionDto().withPriceDiff(priceDiff).withDefaultValue(defaultValue).withTitle(title).withId(id);
    }

    public IntegerOptionDto build() {
        IntegerOptionDto integerOptionDto = new IntegerOptionDto();
        integerOptionDto.setPriceDiff(priceDiff);
        integerOptionDto.setDefaultValue(defaultValue);
        integerOptionDto.setTitle(title);
        integerOptionDto.setId(id);
        return integerOptionDto;
    }
}
