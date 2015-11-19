package com.github.clboettcher.bonappetit.server.impl.converter;

/**
 * Tests for {@link com.github.clboettcher.bonappetit.server.impl.converter.MenusConverter}.
 */

import com.github.clboettcher.bonappetit.common.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.common.dto.menu.MenuDto;
import com.github.clboettcher.bonappetit.serverentities.menu.Item;
import com.github.clboettcher.bonappetit.serverentities.menu.Menu;
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
    private ItemsConverter itemsConverterMock;

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
        Menu inputMenu = Menu.newBuilder()
                .id(1)
                .items(inputItems)
                .build();

        MenuDto expectedMenuDto = MenuDto.newBuilder()
                .id(1L)
                .itemDtos(expectedItemDtos)
                .build();

        assertThat(menusConverter.convertToDto(inputMenu), is(expectedMenuDto));
    }

    @Configuration
    static class Context {
        @Bean
        public MenusConverter menusConverter(ItemsConverter itemsConverter) {
            return new MenusConverter(itemsConverter);
        }

        @Bean
        public ItemsConverter itemsConverter() {
            return Mockito.mock(ItemsConverter.class);
        }

        @Bean
        public OptionsConverter optionsConverter() {
            return Mockito.mock(OptionsConverter.class);
        }

        @Bean
        public RadioItemsConverter radioItemsConverter() {
            return Mockito.mock(RadioItemsConverter.class);
        }
    }
}
