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
package com.github.clboettcher.bonappetit.server.impl.converter;

/**
 * Tests for {@link com.github.clboettcher.bonappetit.server.impl.converter.impl.MenusConverterImpl}.
 */

import com.github.clboettcher.bonappetit.common.dto.builder.MenuDtoBuilder;
import com.github.clboettcher.bonappetit.common.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.common.dto.menu.MenuDto;
import com.github.clboettcher.bonappetit.server.entity.builder.MenuBuilder;
import com.github.clboettcher.bonappetit.server.entity.menu.Item;
import com.github.clboettcher.bonappetit.server.entity.menu.Menu;
import com.github.clboettcher.bonappetit.server.impl.converter.api.ItemsConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.api.MenusConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.api.OptionsConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.api.RadioItemsConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.impl.ItemsConverterImpl;
import com.github.clboettcher.bonappetit.server.impl.converter.impl.MenusConverterImpl;
import com.github.clboettcher.bonappetit.server.impl.converter.impl.OptionsConverterImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MenusConverterTest.Context.class)
public class MenusConverterTest {

    @Autowired
    private MenusConverter menusConverter;

    @Autowired
    private ItemsConverterImpl itemsConverterMock;

    private Set<Item> inputItems;
    private LinkedHashSet<ItemDto> expectedItemDtos;

    @Before
    public void setUp() throws Exception {
        inputItems = Sets.newHashSet(Mockito.mock(Item.class));
        expectedItemDtos = Sets.newLinkedHashSet(Lists.newArrayList(Mockito.mock(ItemDto.class)));
        when(itemsConverterMock.convertToItemDtos(inputItems)).thenReturn(expectedItemDtos);
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(itemsConverterMock);
    }

    @Test
    public void testConversion() throws Exception {
        Menu inputMenu = MenuBuilder.aMenu()
                .withId(1)
                .withItems(inputItems)
                .build();

        MenuDto expectedMenuDto = MenuDtoBuilder.aMenuDto()
                .withId(1L)
                .withItemDtos(expectedItemDtos)
                .build();

        assertThat(menusConverter.convertToDto(inputMenu), is(expectedMenuDto));
    }

    @Configuration
    static class Context {
        @Bean
        public MenusConverter menusConverter(ItemsConverter itemsConverter) {
            return new MenusConverterImpl(itemsConverter);
        }

        @Bean
        public ItemsConverter itemsConverter() {
            return Mockito.mock(ItemsConverterImpl.class);
        }

        @Bean
        public OptionsConverter optionsConverter() {
            return Mockito.mock(OptionsConverterImpl.class);
        }

        @Bean
        public RadioItemsConverter radioItemsConverter() {
            return Mockito.mock(RadioItemsConverter.class);
        }
    }
}
