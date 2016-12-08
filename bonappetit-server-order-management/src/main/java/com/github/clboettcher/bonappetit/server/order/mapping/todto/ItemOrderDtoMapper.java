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
package com.github.clboettcher.bonappetit.server.order.mapping.todto;

import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.mapping.todto.ItemDtoMapper;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ItemOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OrderStatusDto;
import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.ItemOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.OrderEntityStatus;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(uses =
        OptionOrderDtoMapper.class,
        componentModel = "spring")
public abstract class ItemOrderDtoMapper {

    @Autowired
    private ItemDtoMapper itemDtoMapper;

    public abstract List<ItemOrderDto> mapToItemOrderDtos(List<ItemOrderEntity> orderEntities);

    public ItemOrderDto mapToItemOrderDto(ItemOrderEntity itemOrder) {

        ItemEntity item = itemOrder.getItem();
        StaffMemberEntity staffMember = itemOrder.getStaffMember();

        List<AbstractOptionOrderEntity> optionOrders = Lists.newArrayList();
        optionOrders.addAll(itemOrder.getCheckboxOptionOrders());
        optionOrders.addAll(itemOrder.getValueOptionOrders());
        optionOrders.addAll(itemOrder.getRadioOptionOrders());

        return ItemOrderDto.builder()
                .id(itemOrder.getId())
                .itemId(item.getId())
                .itemTitle(itemOrder.getItemTitle())
                .itemPrice(itemOrder.getItemPrice())
                .itemType(itemDtoMapper.mapToItemDtoType(itemOrder.getItemType()))
                .optionOrders(this.mapToOptionOrderDtos(optionOrders))
                .deliverTo(itemOrder.getDeliverTo())
                .staffMemberId(staffMember.getId())
                .staffMemberFirstName(itemOrder.getStaffMemberFirstName())
                .staffMemberLastName(itemOrder.getStaffMemberLastName())
                .orderTime(new DateTime(itemOrder.getOrderTime()))
                .note(itemOrder.getNote())
                .orderStatus(this.mapToOrderStatusDto(itemOrder.getStatus()))
                .build();
    }

    public abstract List<OptionOrderDto> mapToOptionOrderDtos(List<AbstractOptionOrderEntity> options);

    public abstract OrderStatusDto mapToOrderStatusDto(OrderEntityStatus status);
}
