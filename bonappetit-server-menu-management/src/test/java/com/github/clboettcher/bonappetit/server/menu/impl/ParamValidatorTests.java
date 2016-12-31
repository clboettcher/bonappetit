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
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.*;
import com.github.clboettcher.bonappetit.server.menu.impl.dao.ItemDao;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;

public class ParamValidatorTests {

    private ParamValidator validator;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        this.validator = new ParamValidator(Mockito.mock(ItemDao.class));
    }

    @Test
    public void testMenuNull() throws Exception {
        exception.expect(BadRequestException.class);
        exception.expectMessage("Menu that should be created must be present");
        this.validator.assertValid((MenuCreationDto) null);
    }

    @Test
    public void testMenuBlankTitle() throws Exception {
        MenuCreationDto input = MenuCreationDto.builder()
                .title("  ")
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Menu must have a non blank title.");
        this.validator.assertValid(input);
    }

    @Test
    public void testMenuNoItems() throws Exception {
        MenuCreationDto input = MenuCreationDto.builder()
                .title("My menu")
                .itemIds(Lists.newArrayList())
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Menu must have at least one item.");
        this.validator.assertValid(input);
    }

    @Test
    public void testItemBlankTitle() throws Exception {
        ItemCreationDto input = ItemCreationDto.builder()
                .title("  ")
                .build();
        exception.expect(BadRequestException.class);
        exception.expectMessage("Property title of item must not be " +
                "blank. Dto: ItemCreationDto(title=  , price=null, type=null, options=null, note=null)");
        this.validator.assertValid(input);
    }

    @Test
    public void testItemNoType() throws Exception {
        ItemCreationDto input = ItemCreationDto.builder()
                .title("MyItem")
                .build();
        exception.expect(BadRequestException.class);
        exception.expectMessage("Property type of item with title 'MyItem' must be present");
        this.validator.assertValid(input);
    }

    @Test
    public void testItemNoPrice() throws Exception {
        ItemCreationDto input = ItemCreationDto.builder()
                .title("MyItem")
                .type(ItemDtoType.FOOD)
                .build();
        exception.expect(BadRequestException.class);
        exception.expectMessage("Property price of item with title 'MyItem' must be present and " +
                "greater or equal to zero.");
        this.validator.assertValid(input);
    }

    @Test
    public void testItemNegativePrice() throws Exception {
        ItemCreationDto input = ItemCreationDto.builder()
                .title("MyItem")
                .type(ItemDtoType.FOOD)
                .price(new BigDecimal("-2.50"))
                .build();
        exception.expect(BadRequestException.class);
        exception.expectMessage("Property price of item with title 'MyItem' must be present and " +
                "greater or equal to zero.");
        this.validator.assertValid(input);
    }


    @Test
    public void testItemWithoutOptionsOk() throws Exception {
        ItemCreationDto input = ItemCreationDto.builder()
                .title("MyItem")
                .type(ItemDtoType.FOOD)
                .price(new BigDecimal("2.50"))
                .build();
        this.validator.assertValid(input);
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
    public void testOptionWithoutTitle() throws Exception {
        ValueOptionCreationDto input = ValueOptionCreationDto.builder().build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Property title of option of item with title 'MyItemTitle' must not be blank.");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testOptionWithoutIndex() throws Exception {
        ValueOptionCreationDto input = ValueOptionCreationDto.builder()
                .title("MyValueOption")
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Property index of option of item with title 'MyItemTitle' must " +
                "not be blank or less than zero.");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testOptionWithNegativeIndex() throws Exception {
        ValueOptionCreationDto input = ValueOptionCreationDto.builder()
                .title("MyValueOption")
                .index(-1)
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Property index of option of item with title 'MyItemTitle' must " +
                "not be blank or less than zero.");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testValueOptionWithoutPriceDiff() throws Exception {
        ValueOptionCreationDto input = ValueOptionCreationDto.builder()
                .title("MyValueOption")
                .index(0)
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Property priceDiff of DTO with type 'valueOptionCreationDto' of " +
                "item with title 'MyItemTitle' must be present.");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testValueOptionWithoutDefaultValue() throws Exception {
        ValueOptionCreationDto input = ValueOptionCreationDto.builder()
                .title("MyValueOption")
                .priceDiff(BigDecimal.ZERO)
                .index(0)
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Property defaultValue of option with type valueOptionCreationDto " +
                "of item with title 'MyItemTitle' must be present and greater than or equal to zero.");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testValueOptionWithNegativeDefaultValue() throws Exception {
        ValueOptionCreationDto input = ValueOptionCreationDto.builder()
                .title("MyValueOption")
                .priceDiff(BigDecimal.ZERO)
                .index(0)
                .defaultValue(-1)
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Property defaultValue of option with type valueOptionCreationDto " +
                "of item with title 'MyItemTitle' must be present and greater than or equal to zero.");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testValueOptionOk() throws Exception {
        ValueOptionCreationDto input = ValueOptionCreationDto.builder()
                .title("MyValueOption")
                .priceDiff(BigDecimal.ZERO)
                .index(0)
                .defaultValue(0)
                .build();

        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testCheckboxOptionWithoutPriceDiff() throws Exception {
        CheckboxOptionCreationDto input = CheckboxOptionCreationDto.builder()
                .title("MyCheckboxOption")
                .index(0)
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Property priceDiff of DTO with type 'checkboxOptionCreationDto' of " +
                "item with title 'MyItemTitle' must be present.");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testCheckboxOptionWithoutDefaultChecked() throws Exception {
        CheckboxOptionCreationDto input = CheckboxOptionCreationDto.builder()
                .title("MyCheckboxOption")
                .index(0)
                .priceDiff(BigDecimal.ZERO)
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Property defaultChecked of checkboxOptionCreationDto on " +
                "item with title 'MyItemTitle' must be present.");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testCheckboxOptionOk() throws Exception {
        CheckboxOptionCreationDto input = CheckboxOptionCreationDto.builder()
                .title("MyCheckboxOption")
                .index(0)
                .priceDiff(new BigDecimal("-4"))
                .defaultChecked(false)
                .build();

        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testRadioOptionWithoutItems() throws Exception {
        RadioOptionCreationDto input = RadioOptionCreationDto.builder()
                .title("MyRadioOption")
                .index(0)
                .radioItems(Lists.newArrayList())
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Property radioItems of radioOptionCreationDto of item with title " +
                "'MyItemTitle' must be present and not empty.");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testRadioOptionWithoutDefaultSelected() throws Exception {
        RadioOptionCreationDto input = RadioOptionCreationDto.builder()
                .title("MyRadioOption")
                .index(0)
                .radioItems(Lists.newArrayList(
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem")
                                .index(0)
                                .priceDiff(BigDecimal.ZERO)
                                .build()
                ))
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Property defaultSelected of radioOptionCreationDto with title " +
                "'MyRadioOption' of item with title 'MyItemTitle' must be present");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testRadioOptionWithDefaultSelectedNotInRadioItems() throws Exception {
        RadioOptionCreationDto input = RadioOptionCreationDto.builder()
                .title("MyRadioOption")
                .index(0)
                .defaultSelected(RadioItemCreationDto.builder()
                        .title("MyRadioItem_03")
                        .index(2)
                        .priceDiff(BigDecimal.ZERO)
                        .build())
                .radioItems(Lists.newArrayList(
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_01")
                                .index(0)
                                .priceDiff(BigDecimal.ZERO)
                                .build(),
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_02")
                                .index(1)
                                .priceDiff(BigDecimal.ZERO)
                                .build()
                ))
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("The default selected radio item is not contained in the radio " +
                "items on option with title 'MyRadioOption' of item with title 'MyItemTitle'");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testRadioOptionDuplicateIndex() throws Exception {
        RadioOptionCreationDto input = RadioOptionCreationDto.builder()
                .title("MyRadioOption")
                .index(0)
                .defaultSelected(RadioItemCreationDto.builder()
                        .title("MyRadioItem_01")
                        .index(0)
                        .priceDiff(BigDecimal.ZERO)
                        .build())
                .radioItems(Lists.newArrayList(
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_01")
                                .index(0)
                                .priceDiff(BigDecimal.ZERO)
                                .build(),
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_02")
                                .index(0)
                                .priceDiff(BigDecimal.ZERO)
                                .build(),
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_03")
                                .index(1)
                                .priceDiff(BigDecimal.ZERO)
                                .build()
                ))
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("The number of distinct radio item indices must be equal to the number of " +
                "radio items on option with title 'MyRadioOption' of item with title 'MyItemTitle'. Number of radio " +
                "items: 3, Number of distinct indices: 2. Indices: [0, 1]");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testRadioOptionIndicesStartNotWithZero() throws Exception {
        RadioOptionCreationDto input = RadioOptionCreationDto.builder()
                .title("MyRadioOption")
                .index(0)
                .defaultSelected(RadioItemCreationDto.builder()
                        .title("MyRadioItem_01")
                        .index(1)
                        .priceDiff(BigDecimal.ZERO)
                        .build())
                .radioItems(Lists.newArrayList(
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_01")
                                .index(1)
                                .priceDiff(BigDecimal.ZERO)
                                .build(),
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_02")
                                .index(2)
                                .priceDiff(BigDecimal.ZERO)
                                .build(),
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_03")
                                .index(3)
                                .priceDiff(BigDecimal.ZERO)
                                .build()
                ))
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Option with title 'MyRadioOption' of item with title " +
                "'MyItemTitle' contains no radio item with required index 0");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testRadioOptionGapInIndices() throws Exception {
        RadioOptionCreationDto input = RadioOptionCreationDto.builder()
                .title("MyRadioOption")
                .index(0)
                .defaultSelected(RadioItemCreationDto.builder()
                        .title("MyRadioItem_01")
                        .index(0)
                        .priceDiff(BigDecimal.ZERO)
                        .build())
                .radioItems(Lists.newArrayList(
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_01")
                                .index(0)
                                .priceDiff(BigDecimal.ZERO)
                                .build(),
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_02")
                                .index(1)
                                .priceDiff(BigDecimal.ZERO)
                                .build(),
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_03")
                                .index(3)
                                .priceDiff(BigDecimal.ZERO)
                                .build()
                ))
                .build();

        exception.expect(BadRequestException.class);
        exception.expectMessage("Option with title 'MyRadioOption' of item with title " +
                "'MyItemTitle' contains no radio item with required index 2");
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testRadioOptionOk() throws Exception {
        RadioOptionCreationDto input = RadioOptionCreationDto.builder()
                .title("MyRadioOption")
                .index(0)
                .defaultSelected(RadioItemCreationDto.builder()
                        .title("MyRadioItem_01")
                        .index(0)
                        .priceDiff(BigDecimal.ZERO)
                        .build())
                .radioItems(Lists.newArrayList(
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_01")
                                .index(0)
                                .priceDiff(BigDecimal.ZERO)
                                .build(),
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_02")
                                .index(1)
                                .priceDiff(BigDecimal.ZERO)
                                .build(),
                        RadioItemCreationDto.builder()
                                .title("MyRadioItem_03")
                                .index(2)
                                .priceDiff(BigDecimal.ZERO)
                                .build()
                ))
                .build();

        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testUnknownOptionType() throws Exception {
        exception.expect(BadRequestException.class);
        exception.expectMessage("Unknown option type on item with title 'MyItemTitle': " +
                "com.github.clboettcher.bonappetit.server.menu.impl.ParamValidatorTests$1");
        OptionCreationDto input = new OptionCreationDto() {
            @Override
            public String getTitle() {
                return "MyOtherOption";
            }

            @Override
            public Integer getIndex() {
                return 0;
            }
        };
        validator.assertValid(input, "MyItemTitle");
    }

    @Test
    public void testInvalidPriceDiff() throws Exception {

    }
}
