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
package com.github.clboettcher.bonappetit.server.impl.mapping;

/**
 * Tests for {@link OptionDtoMapper}.
 */
public class OptionDtoMapperTests {

    // TODO repair
//    @Rule
//    public ExpectedException exception = ExpectedException.none();
//
//    @Test
//    public void testConvertValueOption() throws Exception {
//        ValueOption input = ValueOption.builder()
//                .id(1337)
//                .defaultValue(2)
//                .priceDiff(new BigDecimal("2.5"))
//                .index(5)
//                .title("Test Value-Option")
//                .build();
//
//        ValueOptionDto expected = ValueOptionDto.builder()
//                .id(1337L)
//                .defaultValue(2)
//                .priceDiff(new BigDecimal("2.5"))
//                .title("Test Value-Option")
//                .build();
//
//        Set<OptionDto> expectedOutput = Sets.<OptionDto>newLinkedHashSet(Lists.newArrayList(expected));
//        assertThat(OptionDtoMapper.INSTANCE.mapToOptionDtos(Sets.<Option>newHashSet(input)), is(expectedOutput));
//    }
//
//    @Test
//    public void testFailOnUnknownOptionType() throws Exception {
//        exception.expect(IllegalArgumentException.class);
//        exception.expectMessage("Unknown");
//        OptionDtoMapper.INSTANCE.mapToOptionDtos(Sets.<Option>newHashSet(new UnknownOption()));
//    }
//
//    /**
//     * Subtype of {@link Option} unknown to the mapper.
//     */
//    static class UnknownOption extends Option {
//    }
}
