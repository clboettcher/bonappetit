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
package com.github.clboettcher.bonappetit.server.persistence.impl.repository;

import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.*;
import com.google.common.collect.Sets;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.assertThat;

/**
 * Tests for {@link MenuRepository}.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//@ContextConfiguration(classes = MenuRepositoryTests.Context.class)
@Ignore // TODO Fix spring context setup to detect the repository w/o a spring boot application.
public class MenuRepositoryTests {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void testSaveMenuItemWithoutOptions() throws Exception {
        // Setup
        ItemEntity item = ItemEntity.builder()
                .title("Item Title")
                .price(new BigDecimal("1.9"))
                .type(ItemEntityType.DRINK_NON_ALCOHOLIC)
                .build();

        MenuEntity menu = MenuEntity.builder()
                .items(Sets.newHashSet(item))
                .build();

        // Test
        menuRepository.save(menu);

        // Verify
        MenuEntity dbMenu = menuRepository.findOne(menu.getId());
        assertThat(dbMenu, CoreMatchers.notNullValue());
        assertThat(dbMenu.getItems(), Matchers.not(Matchers.empty()));
        ItemEntity dbItem = dbMenu.getItems().iterator().next();
        assertThat(dbItem.getTitle(), Matchers.is("Item Title"));
        assertThat(dbItem.getOptions(), Matchers.nullValue());
        assertThat(dbItem.getPrice(), Matchers.is(new BigDecimal("1.9")));
        assertThat(dbItem.getType(), Matchers.is(ItemEntityType.DRINK_NON_ALCOHOLIC));
    }

    @Test
    public void testSaveItemWithValueOption() throws Exception {
        // Setup
        ItemEntity item = ItemEntity.builder()
                .title("Item Title")
                .price(new BigDecimal("1.9"))
                .type(ItemEntityType.DRINK_NON_ALCOHOLIC)
                .options(Sets.<AbstractOptionEntity>newHashSet(
                        ValueOptionEntity.builder()
                                .title("Value Option")
                                .index(17)
                                .defaultChecked(true)
                                .priceDiff(BigDecimal.ONE)
                                .build()
                ))
                .build();

        MenuEntity menu = MenuEntity.builder()
                .items(Sets.newHashSet(item))
                .build();

        // Test
        menuRepository.save(menu);

        // Verify
        MenuEntity dbMenu = menuRepository.findOne(menu.getId());
        ItemEntity dbItem = dbMenu.getItems().iterator().next();
        assertThat(dbItem.getOptions(), Matchers.not(Matchers.empty()));
        AbstractOptionEntity dbOption = dbItem.getOptions().iterator().next();
        assertThat(dbOption, Matchers.is(Matchers.instanceOf(ValueOptionEntity.class)));
        ValueOptionEntity dbValueOption = (ValueOptionEntity) dbOption;
        assertThat(dbValueOption.getTitle(), Matchers.is("Value Option"));
        assertThat(dbValueOption.getIndex(), Matchers.is(17));
        assertThat(dbValueOption.getDefaultChecked(), Matchers.is(true));
        assertThat(dbValueOption.getPriceDiff(), Matchers.is(BigDecimal.ONE));
    }

    @Test
    public void testSaveItemWithRadioOptions() throws Exception {
        RadioItemEntity defaultSelected = RadioItemEntity.builder()
                .title("Default selected")
                .index(0)
                .priceDiff(BigDecimal.ONE)
                .build();

        ItemEntity item = ItemEntity.builder()
                .title("Item Title")
                .price(new BigDecimal("1.9"))
                .type(ItemEntityType.DRINK_NON_ALCOHOLIC)
                .options(Sets.<AbstractOptionEntity>newHashSet(
                        RadioOptionEntity.builder()
                                .title("Radio Option")
                                .index(17)
                                .defaultSelected(defaultSelected)
                                .radioItems(Sets.newHashSet(
                                        defaultSelected,
                                        RadioItemEntity.builder()
                                                .title("Radio Item 2")
                                                .index(1)
                                                .priceDiff(BigDecimal.ONE)
                                                .build()
                                ))
                                .build()
                ))
                .build();

        MenuEntity menu = MenuEntity.builder()
                .items(Sets.newHashSet(item))
                .build();

        menuRepository.save(menu);

        // Verify
        MenuEntity dbMenu = menuRepository.findOne(menu.getId());
        ItemEntity dbItem = dbMenu.getItems().iterator().next();
        assertThat(dbItem.getOptions(), Matchers.not(Matchers.empty()));
        AbstractOptionEntity dbOption = dbItem.getOptions().iterator().next();
        assertThat(dbOption, Matchers.is(Matchers.instanceOf(RadioOptionEntity.class)));
        RadioOptionEntity dbRadioOption = (RadioOptionEntity) dbOption;
        assertThat(dbRadioOption.getTitle(), Matchers.is("Radio Option"));
        assertThat(dbRadioOption.getIndex(), Matchers.is(17));
        RadioItemEntity dbDefaultSelected = dbRadioOption.getDefaultSelected();
        assertThat(dbDefaultSelected.getTitle(), Matchers.is("Default selected"));
        assertThat(dbDefaultSelected.getIndex(), Matchers.is(0));
        assertThat(dbDefaultSelected.getPriceDiff(), Matchers.is(BigDecimal.ONE));
        assertThat(dbDefaultSelected.getIndex(), Matchers.is(0));
        assertThat(dbRadioOption.getRadioItems().size(), Matchers.is(2));
    }

//    @Configuration
//    public static class Context {
//        @Bean
//        public MenuRepository menuRepository() {
//            return Mockito.mock(MenuRepository.class);
//        }
//    }
}
