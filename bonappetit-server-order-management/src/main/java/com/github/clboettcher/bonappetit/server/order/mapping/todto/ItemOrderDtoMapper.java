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
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ItemOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OrderStatusDto;
import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.ItemOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.OrderEntityStatus;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import org.joda.time.DateTime;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses =
        OptionOrderDtoMapper.class,
        componentModel = "spring")
public abstract class ItemOrderDtoMapper {

    public abstract List<ItemOrderDto> mapToItemOrderDtos(List<ItemOrderEntity> orderEntities);

    public ItemOrderDto mapToItemOrderDto(ItemOrderEntity itemOrderEntity) {

        ItemEntity item = itemOrderEntity.getItem();
        StaffMemberEntity staffMember = itemOrderEntity.getStaffMember();

        return ItemOrderDto.builder()
                .id(itemOrderEntity.getId())
                .itemId(item.getId())
                .optionOrders(this.mapToOptionOrderDtos(itemOrderEntity.getOptionOrders()))
                .deliverTo(itemOrderEntity.getDeliverTo())
                .staffMemberId(staffMember.getId())
                .orderTime(new DateTime(itemOrderEntity.getOrderTime()))
                .note(itemOrderEntity.getNote())
                .orderStatus(this.mapToOrderStatusDto(itemOrderEntity.getStatus()))
                .build();
    }

    public abstract List<OptionOrderDto> mapToOptionOrderDtos(List<AbstractOptionOrderEntity> options);

    public abstract OrderStatusDto mapToOrderStatusDto(OrderEntityStatus status);
}
