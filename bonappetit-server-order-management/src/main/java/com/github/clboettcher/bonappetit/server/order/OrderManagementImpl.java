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
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ItemOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.ItemOrderCreationDto;
import com.github.clboettcher.bonappetit.server.order.dao.OptionOrderDao;
import com.github.clboettcher.bonappetit.server.order.dao.OrderDao;
import com.github.clboettcher.bonappetit.server.order.entity.ItemOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.OrderEntityStatus;
import com.github.clboettcher.bonappetit.server.order.mapping.todto.ItemOrderDtoMapper;
import com.github.clboettcher.bonappetit.server.order.mapping.todto.OptionOrderDtoMapper;
import com.github.clboettcher.bonappetit.server.order.mapping.toentity.ItemOrderEntityMapper;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderManagementImpl implements OrderManagement {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderManagementImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OptionOrderDao optionOrderDao;

    @Autowired
    private OrderManagementValidator validator;

    @Autowired
    private ItemOrderEntityMapper toEntityMapper;

    @Autowired
    private ItemOrderDtoMapper toDtoMapper;

    @Autowired
    private OptionOrderDtoMapper optionOrderDtoMapper;

    @Autowired
    private OrderManagementParamParser orderManagementParamParser;

    @Override

    public Response createOrders(Collection<ItemOrderCreationDto> orderDtos) {
        validator.assertValid(orderDtos);
        LOGGER.info(String.format("Creating %d order(s): %s", orderDtos.size(), orderDtos));

        // Map
        Collection<ItemOrderEntity> itemOrderEntities = toEntityMapper.mapToItemOrderEntities(orderDtos);

        // Save
        itemOrderEntities.forEach((itemOrderEntity) -> itemOrderEntity.setStatus(OrderEntityStatus.CREATED));
        List<ItemOrderEntity> saved = orderDao.create(itemOrderEntities);

        LOGGER.info(String.format("Saved %d order(s) in state %s",
                saved.size(), OrderEntityStatus.CREATED));
        // Print
        // TODO: Print orders
        LOGGER.info(String.format("Printing %d order(s)", saved.size()));

        // Update status
        saved.forEach(itemOrderEntity -> itemOrderEntity.setStatus(OrderEntityStatus.PRINTED));
        List<ItemOrderEntity> updated = orderDao.update(saved);

        LOGGER.info(String.format("Updating the order status to %s for %d printed orders.",
                OrderEntityStatus.PRINTED, updated.size()));

        LOGGER.info(String.format("Creating order history entries for %d order(s)", updated.size()));
        // Save order history
        // TODO: save in history
        return Response.noContent().build();
    }

    @Override
    public List<ItemOrderDto> getAllOrders(String orderedAfter, String orderedAt) {
        Optional<DateTime> orderedAfterTimeOpt = orderManagementParamParser.parseOrderedBound(orderedAfter, "orderedAfter");
        Optional<LocalDate> orderedAtDateOpt = orderManagementParamParser.parseOrderedAt(orderedAt, "orderedAt");

        if (orderedAfterTimeOpt.isPresent() && orderedAtDateOpt.isPresent()) {
            throw new BadRequestException("Only one of the parameters orderedAfter and orderedAt may be provided.");
        }

        List<ItemOrderEntity> allOrders = this.orderDao.getAllOrders();
        List<ItemOrderEntity> filtered;

        if (orderedAtDateOpt.isPresent()) {
            filtered = allOrders.stream().filter(itemOrderEntity -> {
                Date orderDate = itemOrderEntity.getOrderTime();
                DateTime orderDateTime = new DateTime(orderDate);
                LocalDate orderLocalDate = orderDateTime.toLocalDate();
                return orderLocalDate.isEqual(orderedAtDateOpt.get());
            }).collect(Collectors.toList());
        } else if (orderedAfterTimeOpt.isPresent()) {
            filtered = allOrders.stream().filter(itemOrderEntity -> {
                Date orderDate = itemOrderEntity.getOrderTime();
                DateTime orderDateTime = new DateTime(orderDate);
                DateTime orderedAfterDateTime = orderedAfterTimeOpt.get();
                return orderDateTime.isEqual(orderedAfterDateTime) || orderDateTime.isAfter(orderedAfterDateTime);
            }).collect(Collectors.toList());
        } else {
            filtered = allOrders;
        }
        LOGGER.info(String.format("Returning %d out of a total of %d order(s) filtered by orderedAfter: %s, " +
                        "orderedAt: %s",
                filtered.size(),
                allOrders.size(),
                orderedAfterTimeOpt.isPresent() ? orderedAfterTimeOpt.get() : "<empty>",
                orderedAtDateOpt.isPresent() ? orderedAtDateOpt.get() : "<empty>"
        ));
        return this.toDtoMapper.mapToItemOrderDtos(filtered);
    }

    @Override
    public ItemOrderDto getOrderById(Long id) {
        ItemOrderEntity orderById = orderDao.getOrderById(id);

        if (orderById == null) {
            throw new NotFoundException(String.format("Could not return order with ID %d because it does not exist.",
                    id));
        }

        return this.toDtoMapper.mapToItemOrderDto(orderById);
    }

    @Override
    public List<OptionOrderDto> getOptionOrders(@ApiParam(value = "The id to look for.") Long id) {
        ItemOrderEntity orderById = orderDao.getOrderById(id);

        if (orderById == null) {
            throw new NotFoundException(String.format("Could not return option orders for order with ID %d because " +
                    "it does not exist.", id));
        }
        return this.optionOrderDtoMapper.mapToOptionOrderDtos(orderById.getOptionOrders());
    }

    @Override
    public List<OptionOrderDto> getAllOptionOrders() {
        return this.optionOrderDtoMapper.mapToOptionOrderDtos(optionOrderDao.getAll());
    }
}
