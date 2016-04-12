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

import com.github.clboettcher.bonappetit.domain.menu.ValueOption;
import com.github.clboettcher.bonappetit.domain.order.ValueOptionOrder;

public class ValueOptionOrderBuilder {
    private ValueOption option;
    private Boolean checked;
    private int value;
    private long id;

    private ValueOptionOrderBuilder() {
    }

    public static ValueOptionOrderBuilder aValueOptionOrder() {
        return new ValueOptionOrderBuilder();
    }

    public ValueOptionOrderBuilder withOption(ValueOption option) {
        this.option = option;
        return this;
    }

    public ValueOptionOrderBuilder withChecked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    public ValueOptionOrderBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    public ValueOptionOrderBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ValueOptionOrderBuilder but() {
        return aValueOptionOrder().withOption(option).withChecked(checked).withValue(value).withId(id);
    }

    public ValueOptionOrder build() {
        ValueOptionOrder valueOptionOrder = new ValueOptionOrder();
        valueOptionOrder.setOption(option);
        valueOptionOrder.setChecked(checked);
        valueOptionOrder.setValue(value);
        valueOptionOrder.setId(id);
        return valueOptionOrder;
    }
}
