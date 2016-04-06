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

import com.github.clboettcher.bonappetit.common.dto.menu.RadioItemDto;
import com.github.clboettcher.bonappetit.common.dto.menu.RadioOptionDto;

import java.util.Set;

public class RadioOptionDtoBuilder {
    private Long defaultSelectedId;
    private Set<RadioItemDto> radioItemDtos;
    private String title;
    private Long id;

    private RadioOptionDtoBuilder() {
    }

    public static RadioOptionDtoBuilder aRadioOptionDto() {
        return new RadioOptionDtoBuilder();
    }

    public RadioOptionDtoBuilder withDefaultSelectedId(Long defaultSelectedId) {
        this.defaultSelectedId = defaultSelectedId;
        return this;
    }

    public RadioOptionDtoBuilder withRadioItemDtos(Set<RadioItemDto> radioItemDtos) {
        this.radioItemDtos = radioItemDtos;
        return this;
    }

    public RadioOptionDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public RadioOptionDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RadioOptionDtoBuilder but() {
        return aRadioOptionDto().withDefaultSelectedId(defaultSelectedId).withRadioItemDtos(radioItemDtos).withTitle(title).withId(id);
    }

    public RadioOptionDto build() {
        RadioOptionDto radioOptionDto = new RadioOptionDto();
        radioOptionDto.setDefaultSelectedId(defaultSelectedId);
        radioOptionDto.setRadioItemDtos(radioItemDtos);
        radioOptionDto.setTitle(title);
        radioOptionDto.setId(id);
        return radioOptionDto;
    }
}
