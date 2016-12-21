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

import com.github.clboettcher.bonappetit.printing.api.PrintManager;
import com.github.clboettcher.bonappetit.server.order.api.OrderManagement;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ItemOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.SummaryDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.ItemOrderCreationDto;
import com.github.clboettcher.bonappetit.server.order.dao.OptionOrderDao;
import com.github.clboettcher.bonappetit.server.order.dao.OrderDao;
import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.ItemOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.OrderEntityStatus;
import com.github.clboettcher.bonappetit.server.order.mapping.todto.ItemOrderDtoMapper;
import com.github.clboettcher.bonappetit.server.order.mapping.todto.ItemOrderSummaryDtoMapper;
import com.github.clboettcher.bonappetit.server.order.mapping.todto.OptionOrderDtoMapper;
import com.github.clboettcher.bonappetit.server.order.mapping.toentity.ItemOrderEntityMapper;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.PrintException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.Collection;
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
    private ItemOrderDtoMapper toDtitemOrderDtoMapperMapper;

    @Autowired
    private ItemOrderSummaryDtoMapper itemOrderSummaryDtoMapper;

    @Autowired
    private OptionOrderDtoMapper optionOrderDtoMapper;

    @Autowired
    private OrderManagementParamParser paramParser;

    @Autowired
    private PrintManager printManager;

    @Override
    public Response createOrders(Collection<ItemOrderCreationDto> orderDtos) {
        LOGGER.info(String.format("Attempting to create %d order(s): %s", orderDtos.size(), orderDtos));
        validator.assertValid(orderDtos);

        // Map
        Collection<ItemOrderEntity> itemOrderEntities = toEntityMapper.mapToItemOrderEntities(orderDtos);

        // Save
        itemOrderEntities.forEach((itemOrderEntity) -> itemOrderEntity.setStatus(OrderEntityStatus.CREATED));
        List<ItemOrderEntity> saved = orderDao.create(itemOrderEntities);

        LOGGER.info(String.format("Saved %d order(s) in state %s",
                saved.size(), OrderEntityStatus.CREATED));
        // Print
        LOGGER.info(String.format("Trying to print %d order(s)", saved.size()));
        try {
            printManager.print(toDtitemOrderDtoMapperMapper.mapToItemOrderDtos(saved));
        } catch (PrintException e) {
            LOGGER.error(String.format("Printing bons for %d order(s) failed", saved.size()), e);
            // Update status
            saved.forEach(itemOrderEntity -> itemOrderEntity.setStatus(OrderEntityStatus.PRINT_FAILED));
            return Response.noContent().build();
        }

        // Update status
        saved.forEach(itemOrderEntity -> itemOrderEntity.setStatus(OrderEntityStatus.PRINTED));
        List<ItemOrderEntity> updated = orderDao.update(saved);

        LOGGER.info(String.format("Updated the order status to %s for %d orders.",
                OrderEntityStatus.PRINTED, updated.size()));

        return Response.noContent().build();
    }

    @Override
    public List<ItemOrderDto> getAllOrders(String orderedBefore, String orderedAfter, String orderedAt) {
        List<ItemOrderEntity> filtered = getOrdersFilterBy(orderedBefore, orderedAfter, orderedAt);
        return this.toDtitemOrderDtoMapperMapper.mapToItemOrderDtos(filtered);
    }

    @Override
    public ItemOrderDto getOrderById(Long id) {
        ItemOrderEntity orderById = orderDao.getOrderById(id);

        if (orderById == null) {
            throw new NotFoundException(String.format("Could not return order with ID %d because it does not exist.",
                    id));
        }

        return this.toDtitemOrderDtoMapperMapper.mapToItemOrderDto(orderById);
    }

    @Override
    public List<OptionOrderDto> getOptionOrdersForOrder(Long id) {
        ItemOrderEntity itemOrderEntity = orderDao.getOrderById(id);

        if (itemOrderEntity == null) {
            throw new NotFoundException(String.format("Could not return option orders for order with ID %d because " +
                    "no order exists with this id.", id));
        }

        List<AbstractOptionOrderEntity> optionOrders = Lists.newArrayList();
        optionOrders.addAll(itemOrderEntity.getCheckboxOptionOrders());
        optionOrders.addAll(itemOrderEntity.getValueOptionOrders());
        optionOrders.addAll(itemOrderEntity.getRadioOptionOrders());


        List<AbstractOptionOrderEntity> abstractOptionOrderEntities = Lists.newArrayList(optionOrders);
        return this.optionOrderDtoMapper.mapToOptionOrderDtos(abstractOptionOrderEntities);
    }

    @Override
    public List<OptionOrderDto> getAllOptionOrders() {
        return this.optionOrderDtoMapper.mapToOptionOrderDtos(optionOrderDao.getAll());
    }

    @Override
    public SummaryDto getSummary(String orderedBefore, String orderedAfter, String orderedAt) {
        List<ItemOrderEntity> filtered = getOrdersFilterBy(orderedBefore, orderedAfter, orderedAt);
        return itemOrderSummaryDtoMapper.mapToSummaryDto(filtered);
    }

    @Override
    public Response createPrintSummaryRequest(String orderedBefore, String orderedAfter, String orderedAt) {
        List<ItemOrderEntity> filtered = getOrdersFilterBy(orderedBefore, orderedAfter, orderedAt);

        try {
            printManager.print(itemOrderSummaryDtoMapper.mapToSummaryDto(filtered));
        } catch (PrintException e) {
            LOGGER.error(String.format("Printing the summary for %d order(s) failed", filtered.size()), e);
            throw new InternalServerErrorException(String.format("Printing the summary for %d " +
                    "order(s) failed: %s", filtered.size(), e.getMessage()));
        }

        return Response.noContent().build();
    }


    private List<ItemOrderEntity> getOrdersFilterBy(String orderedBefore, String orderedAfter, String orderedAt) {
        Optional<DateTime> orderedBeforeTimeOpt = paramParser.parseOrderedBound(orderedBefore, "orderedBefore");
        Optional<DateTime> orderedAfterTimeOpt = paramParser.parseOrderedBound(orderedAfter, "orderedAfter");
        Optional<LocalDate> orderedAtDateOpt = paramParser.parseOrderedAt(orderedAt, "orderedAt");
        validator.assertValidParamCombination(orderedBeforeTimeOpt, orderedAfterTimeOpt, orderedAtDateOpt);

        List<ItemOrderEntity> allOrders = this.orderDao.getAllOrders();
        List<ItemOrderEntity> filtered = allOrders.stream()
                .filter(OrderManagementUtils.getOrderedAtPredicate(orderedAtDateOpt))
                .filter(OrderManagementUtils.getOrderedBeforePredicate(orderedBeforeTimeOpt))
                .filter(OrderManagementUtils.getOrderedAfterPredicate(orderedAfterTimeOpt))
                .collect(Collectors.toList());

        LOGGER.info(String.format("Working with %d out of a total of %d order(s) filtered by orderedBefore: %s, " +
                        "orderedAfter: %s, orderedAt: %s",
                filtered.size(),
                allOrders.size(),
                orderedBeforeTimeOpt.isPresent() ? orderedBeforeTimeOpt.get() : "<empty>",
                orderedAfterTimeOpt.isPresent() ? orderedAfterTimeOpt.get() : "<empty>",
                orderedAtDateOpt.isPresent() ? orderedAtDateOpt.get() : "<empty>"
        ));
        return filtered;
    }
}
