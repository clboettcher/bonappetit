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
package com.github.clboettcher.bonappetit.server.persistence.impl.entity.builder;

import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.ValueOptionEntity;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.order.ValueOptionOrderEntity;

public class ValueOptionOrderEntityBuilder {
    private ValueOptionEntity option;
    private Boolean checked;
    private int value;
    private long id;

    private ValueOptionOrderEntityBuilder() {
    }

    public static ValueOptionOrderEntityBuilder aValueOptionOrderEntity() {
        return new ValueOptionOrderEntityBuilder();
    }

    public ValueOptionOrderEntityBuilder withOption(ValueOptionEntity option) {
        this.option = option;
        return this;
    }

    public ValueOptionOrderEntityBuilder withChecked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    public ValueOptionOrderEntityBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    public ValueOptionOrderEntityBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ValueOptionOrderEntityBuilder but() {
        return aValueOptionOrderEntity().withOption(option).withChecked(checked).withValue(value).withId(id);
    }

    public ValueOptionOrderEntity build() {
        ValueOptionOrderEntity valueOptionOrderEntity = new ValueOptionOrderEntity();
        valueOptionOrderEntity.setOption(option);
        valueOptionOrderEntity.setChecked(checked);
        valueOptionOrderEntity.setValue(value);
        valueOptionOrderEntity.setId(id);
        return valueOptionOrderEntity;
    }
}
