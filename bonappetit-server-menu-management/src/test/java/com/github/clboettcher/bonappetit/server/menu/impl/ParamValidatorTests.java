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
package com.github.clboettcher.bonappetit.server.menu.impl;

import com.github.clboettcher.bonappetit.server.menu.api.dto.common.ItemDtoType;
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.ItemCreationDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.ValueOptionCreationDto;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;

public class ParamValidatorTests {
    private ParamValidator validator;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        this.validator = new ParamValidator();
    }

    @Test
    public void testMenuNull() throws Exception {


    }

    @Test
    public void testMenuBlankTitle() throws Exception {


    }

    @Test
    public void testMenuNoItems() throws Exception {


    }

    @Test
    public void testItemBlankTitle() throws Exception {

    }

    @Test
    public void testItemNoType() throws Exception {

    }

    @Test
    public void testItemNoPrice() throws Exception {

    }

    @Test
    public void testItemNegativePrice() throws Exception {

    }

    @Test
    public void testItemOptionsDuplicateIndex() throws Exception {
        // Setup
        ItemCreationDto input = ItemCreationDto.builder()
                .title("MyItem")
                .type(ItemDtoType.FOOD)
                .price(BigDecimal.ONE)
                .options(Lists.newArrayList(
                        ValueOptionCreationDto.builder()
                                .index(0)
                                .title("MyOption")
                                .priceDiff(BigDecimal.ONE)
                                .defaultValue(0)
                                .build(),
                        ValueOptionCreationDto.builder()
                                .index(0)
                                .title("MyOption")
                                .priceDiff(BigDecimal.ONE)
                                .defaultValue(0)
                                .build(),
                        ValueOptionCreationDto.builder()
                                .index(1)
                                .title("MyOption")
                                .priceDiff(BigDecimal.ONE)
                                .defaultValue(0)
                                .build()
                ))
                .build();

        // Test
        exception.expect(BadRequestException.class);
        exception.expectMessage("The number of distinct option indices must be equal to the number of " +
                "options on item with title 'MyItem'. Number of options: 3, Number of distinct indices: 2. " +
                "Indices: [0, 1]");
        validator.assertValid(input);
    }

    @Test
    public void testItemOptionsIndexStartNotWithZero() throws Exception {
        // Setup
        ItemCreationDto input = ItemCreationDto.builder()
                .title("MyItem")
                .type(ItemDtoType.FOOD)
                .price(BigDecimal.ONE)
                .options(Lists.newArrayList(
                        ValueOptionCreationDto.builder()
                                .index(1)
                                .title("MyOption")
                                .priceDiff(BigDecimal.ONE)
                                .defaultValue(0)
                                .build(),
                        ValueOptionCreationDto.builder()
                                .index(2)
                                .title("MyOption")
                                .priceDiff(BigDecimal.ONE)
                                .defaultValue(0)
                                .build(),
                        ValueOptionCreationDto.builder()
                                .index(3)
                                .title("MyOption")
                                .priceDiff(BigDecimal.ONE)
                                .defaultValue(0)
                                .build()
                ))
                .build();

        // Test
        exception.expect(BadRequestException.class);
        exception.expectMessage("Item with title 'MyItem' contains no option with required index 0");
        validator.assertValid(input);
    }

    @Test
    public void testItemOptionsGapInIndices() throws Exception {
        // Setup
        ItemCreationDto input = ItemCreationDto.builder()
                .title("MyItem")
                .type(ItemDtoType.FOOD)
                .price(BigDecimal.ONE)
                .options(Lists.newArrayList(
                        ValueOptionCreationDto.builder()
                                .index(0)
                                .title("MyOption")
                                .priceDiff(BigDecimal.ONE)
                                .defaultValue(0)
                                .build(),
                        ValueOptionCreationDto.builder()
                                .index(1)
                                .title("MyOption")
                                .priceDiff(BigDecimal.ONE)
                                .defaultValue(0)
                                .build(),
                        ValueOptionCreationDto.builder()
                                .index(3)
                                .title("MyOption")
                                .priceDiff(BigDecimal.ONE)
                                .defaultValue(0)
                                .build()
                ))
                .build();

        // Test
        exception.expect(BadRequestException.class);
        exception.expectMessage("Item with title 'MyItem' contains no option with required index 2");
        validator.assertValid(input);
    }

    @Test
    public void testItemWithoutOptionsOk() throws Exception {

    }

    @Test
    public void testOptionWithoutTitle() throws Exception {

    }

    @Test
    public void testOptionWithoutIndex() throws Exception {

    }

    @Test
    public void testOptionWithNegativeIndex() throws Exception {

    }

    @Test
    public void testValueOptionWithoutDefaultValue() throws Exception {

    }

    @Test
    public void testValueOptionWithoutPriceDiff() throws Exception {

    }

    @Test
    public void testValueOptionOk() throws Exception {

    }

    @Test
    public void testCheckboxOptionWithoutDefaultChecked() throws Exception {

    }

    @Test
    public void testCheckboxOptionOk() throws Exception {

    }

    @Test
    public void testRadioOptionWithoutItems() throws Exception {

    }

    @Test
    public void testRadioOptionWithoutDefaultSelected() throws Exception {

    }

    @Test
    public void testRadioOptionWithDefaultSelectedNotInRadioItems() throws Exception {

    }

    @Test
    public void testRadioOptionDuplicateIndex() throws Exception {

    }

    @Test
    public void testRadioOptionIndicesStartNotWithZero() throws Exception {

    }

    @Test
    public void testRadioOptionGapInIndices() throws Exception {

    }

    @Test
    public void testRadioOptionOk() throws Exception {

    }

    @Test
    public void testUnknownOptionType() throws Exception {

    }

    @Test
    public void testInvalidPriceDiff() throws Exception {

    }
}
