package com.github.clboettcher.bonappetit.server.order.dao.impl;

import com.github.clboettcher.bonappetit.server.order.entity.ItemOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.OrderEntityStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class OrderEntityValidator {

    public static final Predicate<ItemOrderEntity> STATUS_NOT_CREATED_PREDICATE =
            itemOrderEntity -> !itemOrderEntity.getStatus()
                    .equals(OrderEntityStatus.CREATED);

    void assertDeletable(List<ItemOrderEntity> orderEntities) {
        if (orderEntities.stream().anyMatch(STATUS_NOT_CREATED_PREDICATE)) {
            throw new IllegalArgumentException(String.format("Deleting orders with state other than CREATED is " +
                            "not permitted. Offending orders: %s",
                    orderEntities));
        }
    }
}
