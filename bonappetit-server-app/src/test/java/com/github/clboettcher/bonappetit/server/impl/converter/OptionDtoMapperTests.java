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

import com.github.clboettcher.bonappetit.domain.menu.Option;
import com.github.clboettcher.bonappetit.domain.menu.ValueOption;
import com.github.clboettcher.bonappetit.dto.menu.OptionDto;
import com.github.clboettcher.bonappetit.dto.menu.ValueOptionDto;
import com.github.clboettcher.bonappetit.server.impl.mapping.OptionDtoMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link OptionDtoMapper}.
 */
public class OptionDtoMapperTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConvertValueOption() throws Exception {
        ValueOption input = new ValueOption();
        input.setId(1337);
        input.setDefaultChecked(true);
        input.setPriceDiff(new BigDecimal("2.5"));
        input.setIndex(5);
        input.setTitle("Test Value-Option");

        ValueOptionDto expected = new ValueOptionDto();
        expected.setId(1337L);
        expected.setDefaultChecked(true);
        expected.setPriceDiff(new BigDecimal("2.5"));
        expected.setTitle("Test Value-Option");

        Set<OptionDto> expectedOutput = Sets.<OptionDto>newLinkedHashSet(Lists.newArrayList(expected));
        assertThat(OptionDtoMapper.INSTANCE.mapToOptionDtos(Sets.<Option>newHashSet(input)), is(expectedOutput));
    }

    @Test
    public void testFailOnUnknownOptionType() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Unknown");
        OptionDtoMapper.INSTANCE.mapToOptionDtos(Sets.<Option>newHashSet(new UnknownOption()));
    }

    /**
     * Subtype of {@link Option} unknown to the converter.
     */
    static class UnknownOption extends Option {
    }
}
