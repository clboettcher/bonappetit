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
package com.github.clboettcher.bonappetit.server.order.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.clboettcher.bonappetit.common.BonAppetitResourceUtils;
import com.github.clboettcher.bonappetit.common.JsonUtils;
import com.github.clboettcher.bonappetit.common.ObjectMapperFactory;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.*;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DtoDeserializationTests {

    public static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.create();

    @Test
    public void testDeserializeOrderWithoutOptions() throws Exception {
        String jsonObject = BonAppetitResourceUtils.readFileContentAsString("orders_without_options.json");
        List<ItemOrderCreationDto> orders = JsonUtils.parseJsonArray(jsonObject, OBJECT_MAPPER, ItemOrderCreationDto.class);
        assertThat(orders, notNullValue());
        assertThat(orders.size(), is(1));
        assertThat(orders.get(0).getItemId(), is(123L));
    }

    @Test
    public void testDeserializeOrders() throws Exception {
        String jsonObject = BonAppetitResourceUtils.readFileContentAsString("orders.json");
        List<ItemOrderCreationDto> orders = JsonUtils.parseJsonArray(jsonObject, OBJECT_MAPPER, ItemOrderCreationDto.class);
        assertThat(orders, notNullValue());
        assertThat(orders.size(), is(1));
        ItemOrderCreationDto itemOrderCreationDto = orders.get(0);
        assertThat(itemOrderCreationDto.getOptionOrders().size(), is(3));
        List<OptionOrderCreationDto> optionOrders = itemOrderCreationDto.getOptionOrders();

        // ValueOptionOrder
        assertThat(optionOrders.get(0), is(instanceOf(ValueOptionOrderCreationDto.class)));
        assertThat(((ValueOptionOrderCreationDto) optionOrders.get(0)).getOptionId(), is(10L));
        assertThat(((ValueOptionOrderCreationDto) optionOrders.get(0)).getValue(), is(1));

        // CheckboxOptionOrder
        assertThat(optionOrders.get(1), is(instanceOf(CheckboxOptionOrderCreationDto.class)));
        assertThat(((CheckboxOptionOrderCreationDto) optionOrders.get(1)).getOptionId(), is(11L));
        assertThat(((CheckboxOptionOrderCreationDto) optionOrders.get(1)).getChecked(), is(true));

        // RadioOptionOrder
        assertThat(optionOrders.get(2), is(instanceOf(RadioOptionOrderCreationDto.class)));
        assertThat(((RadioOptionOrderCreationDto) optionOrders.get(2)).getSelectedRadioItemId(), is(12L));
    }
}
