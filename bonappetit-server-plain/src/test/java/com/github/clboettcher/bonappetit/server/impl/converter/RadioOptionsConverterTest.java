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

import com.github.clboettcher.bonappetit.common.dto.menu.RadioItemDto;
import com.github.clboettcher.bonappetit.common.dto.menu.RadioOptionDto;
import com.github.clboettcher.bonappetit.serverentities.menu.RadioItem;
import com.github.clboettcher.bonappetit.serverentities.menu.RadioOption;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link OptionsConverter}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RadioOptionsConverterTest.Context.class)
public class RadioOptionsConverterTest {

    @Autowired
    private RadioOptionsConverter radioOptionsConverter;

    @Autowired
    private RadioItemsConverter radioItemsConverterMock;

    @After
    public void tearDown() throws Exception {
        Mockito.reset(radioItemsConverterMock);
    }

    @Test
    public void testConvertRadioOption() throws Exception {
        // Setup radio items
        RadioItem itemIdx2 = RadioItem.newBuilder().id(10).index(2).title("Test Radio-Item 01").build();
        RadioItem itemIdx0 = RadioItem.newBuilder().id(15).index(0).title("Test Radio-Item 02").build(); // Selected
        RadioItem itemIdx1 = RadioItem.newBuilder().id(20).index(1).title("Test Radio-Item 03").build();
        final HashSet<RadioItem> inputItems = Sets.newHashSet(itemIdx2, itemIdx0, itemIdx1);

        RadioItemDto outputIdx2 = RadioItemDto.newBuilder().id(10L).title("Test Radio-Item 01").build();
        RadioItemDto outputIdx0 = RadioItemDto.newBuilder().id(10L).title("Test Radio-Item 01").build();
        RadioItemDto outputIdx1 = RadioItemDto.newBuilder().id(10L).title("Test Radio-Item 01").build();
        LinkedHashSet<RadioItemDto> outputItems = Sets.newLinkedHashSet(Lists.newArrayList(outputIdx0, outputIdx1, outputIdx2));

        when(radioItemsConverterMock.convert(inputItems)).thenReturn(outputItems);

        // Setup radio option
        RadioOption input = new RadioOption();
        input.setIndex(1);
        input.setTitle("Test Radio-Option");
        input.setRadioItems(inputItems);
        input.setDefaultSelected(itemIdx0);

        RadioOptionDto expected = new RadioOptionDto();
        expected.setTitle("Test Radio-Option");
        expected.setRadioItemDtos(outputItems);
        expected.setDefaultSelectedId(15L);

        assertThat(radioOptionsConverter.convert(input), is(expected));
    }

    @Configuration
    static class Context {
        @Bean
        public RadioOptionsConverter optionsConverter(RadioItemsConverter radioItemsConverter) {
            return new RadioOptionsConverter(radioItemsConverter);
        }

        @Bean
        public RadioItemsConverter radioItemsConverter() {
            return Mockito.mock(RadioItemsConverter.class);
        }
    }
}
