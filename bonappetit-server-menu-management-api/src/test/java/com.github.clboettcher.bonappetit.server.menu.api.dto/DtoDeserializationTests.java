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
package com.github.clboettcher.bonappetit.server.menu.api.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.clboettcher.bonappetit.common.BonAppetitResourceUtils;
import com.github.clboettcher.bonappetit.common.JsonUtils;
import com.github.clboettcher.bonappetit.common.ObjectMapperFactory;
import com.github.clboettcher.bonappetit.server.menu.api.dto.read.MenuDto;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DtoDeserializationTests {

    public static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.create();

    @Test
    public void testDeserializeMenuWithoutItems() throws Exception {
        String jsonObject = BonAppetitResourceUtils.readFileContentAsString("menu_without_items.json");
        MenuDto menuDto = JsonUtils.parseJsonObject(jsonObject, OBJECT_MAPPER, MenuDto.class);
        assertThat(menuDto, notNullValue());
        assertThat(menuDto.getId(), is(1L));
    }

    @Test
    public void testDeserializeMenuWithItems() throws Exception {
        String jsonObject = BonAppetitResourceUtils.readFileContentAsString("menu.json");
        MenuDto menuDto = JsonUtils.parseJsonObject(jsonObject, OBJECT_MAPPER, MenuDto.class);
        assertThat(menuDto, notNullValue());
        assertThat(menuDto.getId(), is(1L));
        assertThat(menuDto.getItems(), Matchers.hasSize(4));
        System.out.println(menuDto);
    }
}
