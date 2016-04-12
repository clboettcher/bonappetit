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

import com.github.clboettcher.bonappetit.common.domain.menu.ValueOption;
import com.github.clboettcher.bonappetit.common.dto.order.ValueOptionOrderDto;

public class ValueOptionOrderDtoBuilder {
    private ValueOption option;
    private Boolean checked;
    private int value;
    private Long id;

    private ValueOptionOrderDtoBuilder() {
    }

    public static ValueOptionOrderDtoBuilder aValueOptionOrderDto() {
        return new ValueOptionOrderDtoBuilder();
    }

    public ValueOptionOrderDtoBuilder withOption(ValueOption option) {
        this.option = option;
        return this;
    }

    public ValueOptionOrderDtoBuilder withChecked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    public ValueOptionOrderDtoBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    public ValueOptionOrderDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ValueOptionOrderDtoBuilder but() {
        return aValueOptionOrderDto().withOption(option).withChecked(checked).withValue(value).withId(id);
    }

    public ValueOptionOrderDto build() {
        ValueOptionOrderDto valueOptionOrderDto = new ValueOptionOrderDto();
        valueOptionOrderDto.setOption(option);
        valueOptionOrderDto.setChecked(checked);
        valueOptionOrderDto.setValue(value);
        valueOptionOrderDto.setId(id);
        return valueOptionOrderDto;
    }
}
