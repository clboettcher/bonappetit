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

import com.github.clboettcher.bonappetit.common.dto.menu.CheckboxOptionDto;

import java.math.BigDecimal;

public class CheckboxOptionDtoBuilder {
    private BigDecimal priceDiff;
    private Boolean defaultChecked;
    private String title;
    private Long id;

    private CheckboxOptionDtoBuilder() {
    }

    public static CheckboxOptionDtoBuilder aCheckboxOptionDto() {
        return new CheckboxOptionDtoBuilder();
    }

    public CheckboxOptionDtoBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public CheckboxOptionDtoBuilder withDefaultChecked(Boolean defaultChecked) {
        this.defaultChecked = defaultChecked;
        return this;
    }

    public CheckboxOptionDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public CheckboxOptionDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CheckboxOptionDtoBuilder but() {
        return aCheckboxOptionDto().withPriceDiff(priceDiff).withDefaultChecked(defaultChecked).withTitle(title).withId(id);
    }

    public CheckboxOptionDto build() {
        CheckboxOptionDto checkboxOptionDto = new CheckboxOptionDto();
        checkboxOptionDto.setPriceDiff(priceDiff);
        checkboxOptionDto.setDefaultChecked(defaultChecked);
        checkboxOptionDto.setTitle(title);
        checkboxOptionDto.setId(id);
        return checkboxOptionDto;
    }
}
