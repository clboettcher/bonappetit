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

import com.github.clboettcher.bonappetit.domain.menu.RadioItem;
import com.github.clboettcher.bonappetit.domain.menu.RadioOption;
import com.github.clboettcher.bonappetit.dto.menu.RadioItemDto;
import com.github.clboettcher.bonappetit.dto.menu.RadioOptionDto;
import com.github.clboettcher.bonappetit.server.impl.mapping.RadioOptionDtoMapper;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link com.github.clboettcher.bonappetit.server.impl.mapping.RadioOptionDtoMapper}.
 */
public class RadioOptionDtoMapperTests {

    @Test
    public void testConvertRadioOption() throws Exception {
        // Setup radio items
        RadioItem item1 = RadioItem.builder().id(1).build(); // Selected
        RadioItem item2 = RadioItem.builder().id(2).build();

        // Setup radio option
        RadioOption input = RadioOption.builder()
                .id(1337)
                .index(1)
                .title("Test Radio-Option")
                .radioItems(Sets.newHashSet(item1, item2))
                .defaultSelected(item1)
                .build();

        // Tests
        RadioOptionDto actual = RadioOptionDtoMapper.INSTANCE.mapToRadioOptionDto(input);

        // Verify
        RadioOptionDto expected = new RadioOptionDto();
        expected.setTitle("Test Radio-Option");
        RadioItemDto expectedItem1 = RadioItemDto.builder().id(1L).build();
        RadioItemDto expectedItem2 = RadioItemDto.builder().id(2L).build();

        Set<RadioItemDto> outputItems = Sets.newHashSet(expectedItem1, expectedItem2);
        expected.setRadioItems(outputItems);
        expected.setDefaultSelected(expectedItem1);


        assertThat(actual.getTitle(), is("Test Radio-Option"));
        assertThat(actual.getId(), is(1337L));
        assertThat(actual.getDefaultSelected(), is(expectedItem1));
        assertThat(actual.getRadioItems(), containsInAnyOrder(expectedItem1, expectedItem2));
    }
}
