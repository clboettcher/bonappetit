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
package com.github.clboettcher.bonappetit.server.order.mapping.toentity;

import com.github.clboettcher.bonappetit.server.menu.impl.dao.ItemDao;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntity;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.ItemOrderCreationDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.OptionOrderCreationDto;
import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.ItemOrderEntity;
import com.github.clboettcher.bonappetit.server.staff.dao.StaffMemberDao;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@Mapper(uses =
        OptionOrderEntityMapper.class,
        componentModel = "spring")
public abstract class ItemOrderEntityMapper {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private StaffMemberDao staffMemberDao;

    /**
     * Converts the given {@link ItemOrderCreationDto}s to {@link ItemOrderEntity}s.
     *
     * @param orderDtos The dtos to map.
     * @return The mapping result.
     */
    public abstract Collection<ItemOrderEntity> mapToItemOrderEntities(Collection<ItemOrderCreationDto> orderDtos);

    /**
     * @param itemOrderCreationDto The {@link ItemOrderCreationDto} to map.
     * @return The mapping result.
     */
    ItemOrderEntity mapToItemOrderEntity(ItemOrderCreationDto itemOrderCreationDto) {

        ItemEntity item = itemDao.getItem(itemOrderCreationDto.getItemId());
        StaffMemberEntity staffMember = staffMemberDao.getStaffMember(itemOrderCreationDto.getStaffMemberId());

        return ItemOrderEntity.builder()
                .item(item)
                .optionOrders(this.mapToOptionOrderEntities(itemOrderCreationDto.getOptionOrders()))
                .deliverTo(itemOrderCreationDto.getDeliverTo())
                .staffMember(staffMember)
                .orderTime(itemOrderCreationDto.getOrderTime().toDate())
                .note(itemOrderCreationDto.getNote())
                .build();
    }

    /**
     * @param options The dto to map.
     * @return The mapping result.
     */
    public abstract List<AbstractOptionOrderEntity> mapToOptionOrderEntities(List<OptionOrderCreationDto> options);
}
