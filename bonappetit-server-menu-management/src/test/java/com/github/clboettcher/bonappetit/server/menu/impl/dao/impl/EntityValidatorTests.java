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
package com.github.clboettcher.bonappetit.server.menu.impl.dao.impl;

import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.*;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

/**
 * Tests for {@link EntityValidator}.
 */
public class EntityValidatorTests {

    private EntityValidator validator;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        this.validator = new EntityValidator();
    }

    @Test
    public void testMenuWithoutItems() throws Exception {
        MenuEntity menu = new MenuEntity();
        validator.assertNewMenuValid(menu);

        menu.setId(1L);
        exception.expect(IllegalArgumentException.class);
        validator.assertNewMenuValid(menu);
    }

    @Test
    public void testNewItems() throws Exception {
        ArrayList<ItemEntity> items = Lists.<ItemEntity>newArrayList(
                ItemEntity.builder()
                        .title("Pommes")
                        .build(),
                ItemEntity.builder()
                        .title("Cola")
                        .build());
        validator.assertNewItemsValid(items);

        items.get(0).setId(1L);
        items.get(1).setId(2L);
        exception.expect(IllegalArgumentException.class);
        validator.assertNewItemsValid(items);
    }

    @Test
    public void testNewItemsWithOptions() throws Exception {
        ArrayList<ItemEntity> items = Lists.<ItemEntity>newArrayList(
                ItemEntity.builder()
                        .title("Pommes")
                        .options(Lists.newArrayList(
                                ValueOptionEntity.builder()
                                        .title("Ketchup")
                                        .build(),
                                CheckboxOptionEntity.builder()
                                        .title("Extra Salz")
                                        .build()
                        ))
                        .build()
        );
        validator.assertNewItemsValid(items);

        items.get(0).getOptions().get(0).setId(4L);
        exception.expect(IllegalArgumentException.class);
        validator.assertNewItemsValid(items);
    }

    @Test
    public void testNewItemWithRadioOption() throws Exception {
        RadioItemEntity defaultSelected = RadioItemEntity.builder()
                .title("groß")
                .build();

        ArrayList<ItemEntity> items = Lists.<ItemEntity>newArrayList(
                ItemEntity.builder()
                        .title("Augustiner Hell")
                        .options(Lists.newArrayList(
                                RadioOptionEntity.builder()
                                        .title("Größe")
                                        .defaultSelected(defaultSelected)
                                        .radioItems(Lists.newArrayList(
                                                defaultSelected,
                                                RadioItemEntity.builder()
                                                        .title("klein")
                                                        .build()))
                                        .build()
                        ))
                        .build()
        );
        validator.assertNewItemsValid(items);

        RadioOptionEntity radioOption = (RadioOptionEntity) items.get(0).getOptions().get(0);
        radioOption.getRadioItems().get(1).setId(5L);
        exception.expect(IllegalArgumentException.class);
        validator.assertNewItemsValid(items);
    }
}
