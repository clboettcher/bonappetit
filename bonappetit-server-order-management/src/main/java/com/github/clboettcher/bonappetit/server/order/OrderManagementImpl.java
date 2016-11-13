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
package com.github.clboettcher.bonappetit.server.order;

import com.github.clboettcher.bonappetit.server.order.api.OrderManagement;
import com.github.clboettcher.bonappetit.server.order.api.dto.ItemOrderDto;
import com.github.clboettcher.bonappetit.server.order.dao.OrderDao;
import com.github.clboettcher.bonappetit.server.order.entity.ItemOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.OrderEntityStatus;
import com.github.clboettcher.bonappetit.server.order.mapping.ItemOrderEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.Collection;

@Component
public class OrderManagementImpl implements OrderManagement {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderManagementImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ItemOrderEntityMapper mapper;

    @Override
    public Response createOrders(Collection<ItemOrderDto> orderDtos) {
        LOGGER.info(String.format("Creating orders: %s", orderDtos));

        // Map
        Collection<ItemOrderEntity> itemOrderEntities = mapper.mapToItemOrderEntities(orderDtos);

        // Save
        itemOrderEntities.forEach((itemOrderEntity) -> itemOrderEntity.setStatus(OrderEntityStatus.CREATED));
        Iterable<ItemOrderEntity> saved = orderDao.save(itemOrderEntities);

        // Print
        // TODO: Print orders

        // Update status
        saved.forEach(itemOrderEntity -> itemOrderEntity.setStatus(OrderEntityStatus.PRINTED));
        orderDao.update(saved);

        // Save order history
        // TODO: save in history

        return Response.noContent().build();
    }
}
