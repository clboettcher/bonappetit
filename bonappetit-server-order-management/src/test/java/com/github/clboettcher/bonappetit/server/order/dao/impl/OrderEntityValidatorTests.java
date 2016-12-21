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
package com.github.clboettcher.bonappetit.server.order.dao.impl;

import com.github.clboettcher.bonappetit.server.order.entity.ItemOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.OrderEntityStatus;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

/**
 * Tests for {@link OrderEntityValidator}
 */
public class OrderEntityValidatorTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private OrderEntityValidator validator;

    @Before
    public void setUp() throws Exception {
        this.validator = new OrderEntityValidator();
    }

    @Test
    public void testAssertDeletableOk() throws Exception {
        // Orders in state CREATED are deletable
        List<ItemOrderEntity> input = Lists.newArrayList(
                ItemOrderEntity.builder()
                        .status(OrderEntityStatus.CREATED)
                        .build(),
                ItemOrderEntity.builder()
                        .status(OrderEntityStatus.CREATED)
                        .build()
        );
        validator.assertDeletable(input);
    }

    @Test
    public void testAssertDeletableNok() throws Exception {
        // Orders in state other thab CREATED are not deletable
        List<ItemOrderEntity> input = Lists.newArrayList(
                ItemOrderEntity.builder()
                        .status(OrderEntityStatus.CREATED)
                        .build(),
                ItemOrderEntity.builder()
                        .status(OrderEntityStatus.PRINTED)
                        .build(),
                ItemOrderEntity.builder()
                        .status(OrderEntityStatus.CREATED)
                        .build()
        );
        exception.expect(IllegalArgumentException.class);
        validator.assertDeletable(input);
    }
}
