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
package com.github.clboettcher.bonappetit.dto.builder;

import com.github.clboettcher.bonappetit.dto.menu.RadioItemDto;

import java.math.BigDecimal;

public class RadioItemDtoBuilder {
    private String title;
    private BigDecimal priceDiff;
    private Long id;

    private RadioItemDtoBuilder() {
    }

    public static RadioItemDtoBuilder aRadioItemDto() {
        return new RadioItemDtoBuilder();
    }

    public RadioItemDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public RadioItemDtoBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public RadioItemDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RadioItemDtoBuilder but() {
        return aRadioItemDto().withTitle(title).withPriceDiff(priceDiff).withId(id);
    }

    public RadioItemDto build() {
        RadioItemDto radioItemDto = new RadioItemDto();
        radioItemDto.setTitle(title);
        radioItemDto.setPriceDiff(priceDiff);
        radioItemDto.setId(id);
        return radioItemDto;
    }
}
