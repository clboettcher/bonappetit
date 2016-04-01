package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.common.dto.menu.OptionDto;
import com.github.clboettcher.bonappetit.common.entity.ItemType;
import com.github.clboettcher.bonappetit.serverentities.menu.Item;
import com.github.clboettcher.bonappetit.serverentities.menu.Option;
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

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link com.github.clboettcher.bonappetit.server.impl.converter.ItemsConverter}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ItemsConverterTest.Context.class)
public class ItemsConverterTest {

    @Autowired
    private ItemsConverter itemsConverter;

    @Autowired
    private OptionsConverter optionsConverterMock;

    private LinkedHashSet<Option> inputOptions;
    private LinkedHashSet<OptionDto> expectedOptionDtos;


    @Before
    public void setUp() throws Exception {
        inputOptions = Sets.newLinkedHashSet(Lists.newArrayList(Mockito.mock(Option.class)));
        expectedOptionDtos = Sets.newLinkedHashSet(Lists.newArrayList(Mockito.mock(OptionDto.class)));
        Mockito.when(optionsConverterMock.convert(inputOptions)).thenReturn(expectedOptionDtos);
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(optionsConverterMock);
    }

    @Test
    public void testConversion() throws Exception {
        Set<Item> inputItems = Sets.newHashSet(
                Item.newBuilder()
                        .id(1)
                        .title("Test Item w/ options")
                        .price(new BigDecimal("1.5"))
                        .type(ItemType.FOOD)
                        .options(inputOptions)
                        .build(),
                Item.newBuilder()
                        .id(2)
                        .title("Test Item w/o options")
                        .price(new BigDecimal("2.5"))
                        .type(ItemType.DRINK_ALCOHOLIC)
                        .options(null)
                        .build()
        );

        Set<ItemDto> expectedItemDtos = Sets.newHashSet(
                ItemDto.newBuilder()
                        .id(1L)
                        .title("Test Item w/ options")
                        .price(new BigDecimal("1.5"))
                        .type(ItemType.FOOD)
                        .optionDtos(expectedOptionDtos)
                        .build(),
                ItemDto.newBuilder()
                        .id(2L)
                        .title("Test Item w/o options")
                        .price(new BigDecimal("2.5"))
                        .type(ItemType.DRINK_ALCOHOLIC)
                        .optionDtos(null)
                        .build()
        );

        assertThat(itemsConverter.convertToItemDtos(inputItems), is(expectedItemDtos));
    }

    @Configuration
    static class Context {
        @Bean
        public ItemsConverter itemsConverter(OptionsConverter optionsConverter) {
            return new ItemsConverter(optionsConverter);
        }

        @Bean
        public OptionsConverter optionsConverter() {
            return Mockito.mock(OptionsConverter.class);
        }
    }
}
