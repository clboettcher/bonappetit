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
import com.github.clboettcher.bonappetit.domain.order.RadioOptionOrder;

public class RadioOptionOrderBuilder {
    private RadioItem selectedItem;
    private long id;

    private RadioOptionOrderBuilder() {
    }

    public static RadioOptionOrderBuilder aRadioOptionOrder() {
        return new RadioOptionOrderBuilder();
    }

    public RadioOptionOrderBuilder withSelectedItem(RadioItem selectedItem) {
        this.selectedItem = selectedItem;
        return this;
    }

    public RadioOptionOrderBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public RadioOptionOrderBuilder but() {
        return aRadioOptionOrder().withSelectedItem(selectedItem).withId(id);
    }

    public RadioOptionOrder build() {
        RadioOptionOrder radioOptionOrder = new RadioOptionOrder();
        radioOptionOrder.setSelectedItem(selectedItem);
        radioOptionOrder.setId(id);
        return radioOptionOrder;
    }
}
