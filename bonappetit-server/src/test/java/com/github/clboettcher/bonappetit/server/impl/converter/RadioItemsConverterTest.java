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

import com.github.clboettcher.bonappetit.common.dto.builder.RadioItemDtoBuilder;
import com.github.clboettcher.bonappetit.common.dto.menu.RadioItemDto;
import com.github.clboettcher.bonappetit.server.entity.builder.RadioItemBuilder;
import com.github.clboettcher.bonappetit.server.entity.menu.RadioItem;
import com.github.clboettcher.bonappetit.server.impl.converter.api.RadioItemsConverter;
import com.github.clboettcher.bonappetit.server.impl.converter.impl.RadioItemsConverterImpl;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link RadioItemsConverter}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RadioItemsConverterTest.Context.class)
public class RadioItemsConverterTest {

    @Autowired
    private RadioItemsConverter radioItemsConverter;

    /**
     * Tests the full conversion of all fields of {@link RadioItem}.
     */
    @Test
    public void testFullConversion() throws Exception {
        RadioItem input01 = RadioItemBuilder.aRadioItem()
                .withId(1)
                .withIndex(1)
                .withPriceDiff(new BigDecimal("1.5"))
                .withTitle("Test Radio-Item 01")
                .build();

        RadioItemDto expected01 = RadioItemDtoBuilder.aRadioItemDto()
                .withId(1L)
                .withPriceDiff(new BigDecimal("1.5"))
                .withTitle("Test Radio-Item 01")
                .build();

        final Set<RadioItemDto> expectedDtos = Sets.newHashSet(expected01);
        assertThat(radioItemsConverter.convert(Sets.newLinkedHashSet(Collections.singletonList(input01))), is(expectedDtos));
    }

    /**
     * Tests that the converter returns the converted items in the correct ordering based on {@link RadioItem#getIndex()}.
     */
    @Test
    public void testOrdering() throws Exception {
        RadioItem itemIdx2 = RadioItemBuilder.aRadioItem().withId(10).withIndex(2).build();
        RadioItem itemIdx0 = RadioItemBuilder.aRadioItem().withId(15).withIndex(0).build();
        RadioItem itemIdx1 = RadioItemBuilder.aRadioItem().withId(20).withIndex(1).build();
        final HashSet<RadioItem> inputItems = Sets.newHashSet(itemIdx2, itemIdx0, itemIdx1);

        RadioItemDto expectedIdx2 = RadioItemDtoBuilder.aRadioItemDto().withId(10L).build();
        RadioItemDto expectedIdx0 = RadioItemDtoBuilder.aRadioItemDto().withId(15L).build();
        RadioItemDto expectedIdx1 = RadioItemDtoBuilder.aRadioItemDto().withId(20L).build();

        final ArrayList<RadioItemDto> outputAsList = newArrayList(radioItemsConverter.convert(inputItems));
        assertThat(outputAsList.get(0), is(expectedIdx0));
        assertThat(outputAsList.get(1), is(expectedIdx1));
        assertThat(outputAsList.get(2), is(expectedIdx2));
    }

    @Configuration
    static class Context {
        @Bean
        public RadioItemsConverter radioItemsConverter() {
            return new RadioItemsConverterImpl();
        }
    }
}
