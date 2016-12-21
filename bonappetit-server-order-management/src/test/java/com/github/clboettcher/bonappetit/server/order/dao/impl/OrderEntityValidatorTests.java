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
