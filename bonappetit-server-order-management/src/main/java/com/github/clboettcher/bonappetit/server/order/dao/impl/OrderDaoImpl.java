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

import com.github.clboettcher.bonappetit.server.order.dao.OrderDao;
import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.ItemOrderEntity;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Default impl of {@link com.github.clboettcher.bonappetit.server.order.dao.OrderDao}.
 */
@Component
@Profile("default")
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Autowired
    private ItemOrderRepository repository;

    @Autowired
    private OrderEntityValidator orderEntityValidator;

    @Override
    public List<ItemOrderEntity> create(Collection<ItemOrderEntity> orders) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(orders), "orders empty");
        // Make sure that we do not update existing entities by checking if the ID field is set.
        orders.forEach(itemOrderEntity -> {
            if (itemOrderEntity.getId() != null) {
                throw new IllegalArgumentException(String.format("New item orders to be " +
                        "saved may not contain ids: %s", itemOrderEntity));
            }
            List<AbstractOptionOrderEntity> newOptionOrders = Lists.newArrayList();
            newOptionOrders.addAll(itemOrderEntity.getCheckboxOptionOrders());
            newOptionOrders.addAll(itemOrderEntity.getValueOptionOrders());
            newOptionOrders.addAll(itemOrderEntity.getRadioOptionOrders());

            if (CollectionUtils.isNotEmpty(newOptionOrders)) {
                newOptionOrders.forEach(optionOrder -> {
                    if (optionOrder.getId() != null) {
                        throw new IllegalArgumentException(String.format("New option orders to " +
                                "be saved may not contain ids: %s", optionOrder));
                    }
                });
            }
        });
        LOGGER.info(String.format("Saving %d order(s): %s", orders.size(), orders));
        return Lists.newArrayList(repository.save(orders));
    }

    @Override
    public List<ItemOrderEntity> update(Collection<ItemOrderEntity> itemOrderEntities) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(itemOrderEntities), "itemOrderEntities empty");
        // Make sure that we don't create entities by checking if all id values are set.
        itemOrderEntities.forEach(itemOrderEntity -> {
            if (itemOrderEntity.getId() == null) {
                throw new IllegalArgumentException(String.format("Item order cannot be updated because " +
                        "it does not exist in the db. Create the order first: %s", itemOrderEntity
                ));
            }

            List<AbstractOptionOrderEntity> updatedOptionOrders = Lists.newArrayList();
            updatedOptionOrders.addAll(itemOrderEntity.getCheckboxOptionOrders());
            updatedOptionOrders.addAll(itemOrderEntity.getValueOptionOrders());
            updatedOptionOrders.addAll(itemOrderEntity.getRadioOptionOrders());

            if (CollectionUtils.isNotEmpty(updatedOptionOrders)) {
                updatedOptionOrders.forEach(updatedOptionOrder -> {
                    if (updatedOptionOrder.getId() == null) {
                        throw new IllegalArgumentException(String.format("Option order cannot be updated because " +
                                        "it does not exist in the db. Create the option order first: %s",
                                updatedOptionOrder));
                    }
                });
            }
        });
        LOGGER.info(String.format("Updating %d order(s): %s", itemOrderEntities.size(), itemOrderEntities));
        return Lists.newArrayList(repository.save(itemOrderEntities));
    }


    @Override
    public List<ItemOrderEntity> getAllOrders() {
        return Lists.newArrayList(this.repository.findAll());
    }

    @Override
    public ItemOrderEntity getOrderById(Long id) {
        Preconditions.checkNotNull(id, "id");
        return repository.findOne(id);
    }

    @Override
    public void delete(List<ItemOrderEntity> orderEntities) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(orderEntities), "orderEntities empty");
        orderEntityValidator.assertDeletable(orderEntities);
        LOGGER.info(String.format("Deleting %d order(s).", orderEntities.size()));
        repository.delete(orderEntities);
    }

}
