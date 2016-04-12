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
import com.github.clboettcher.bonappetit.dto.order.RadioOptionOrderDto;

public class RadioOptionOrderDtoBuilder {
    private RadioItemDto selectedItem;
    private Long id;

    private RadioOptionOrderDtoBuilder() {
    }

    public static RadioOptionOrderDtoBuilder aRadioOptionOrderDto() {
        return new RadioOptionOrderDtoBuilder();
    }

    public RadioOptionOrderDtoBuilder withSelectedItem(RadioItemDto selectedItem) {
        this.selectedItem = selectedItem;
        return this;
    }

    public RadioOptionOrderDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RadioOptionOrderDtoBuilder but() {
        return aRadioOptionOrderDto().withSelectedItem(selectedItem).withId(id);
    }

    public RadioOptionOrderDto build() {
        RadioOptionOrderDto radioOptionOrderDto = new RadioOptionOrderDto();
        radioOptionOrderDto.setSelectedItem(selectedItem);
        radioOptionOrderDto.setId(id);
        return radioOptionOrderDto;
    }
}
