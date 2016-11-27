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
import com.github.clboettcher.bonappetit.server.order.entity.*;
import com.github.clboettcher.bonappetit.server.staff.dao.StaffMemberDao;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import com.google.common.collect.Lists;
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

    public abstract Collection<ItemOrderEntity> mapToItemOrderEntities(Collection<ItemOrderCreationDto> orderDtos);

    ItemOrderEntity mapToItemOrderEntity(ItemOrderCreationDto itemOrderCreationDto) {

        ItemEntity item = itemDao.getItem(itemOrderCreationDto.getItemId());
        StaffMemberEntity staffMember = staffMemberDao.getStaffMember(itemOrderCreationDto.getStaffMemberId());

        List<AbstractOptionOrderEntity> abstractOptionOrderEntities = this.mapToOptionOrderEntities(
                itemOrderCreationDto.getOptionOrders());
        List<CheckboxOptionOrderEntity> checkboxOptionOrderEntities = Lists.newArrayList();
        List<ValueOptionOrderEntity> valueOptionOrderEntities = Lists.newArrayList();
        List<RadioOptionOrderEntity> radioOptionOrderEntities = Lists.newArrayList();

        abstractOptionOrderEntities.forEach(abstractOptionOrderEntity -> {
            if (abstractOptionOrderEntity instanceof CheckboxOptionOrderEntity) {
                CheckboxOptionOrderEntity checkboxOptionOrderEntity = (CheckboxOptionOrderEntity) abstractOptionOrderEntity;
                checkboxOptionOrderEntities.add(checkboxOptionOrderEntity);
            } else if (abstractOptionOrderEntity instanceof ValueOptionOrderEntity) {
                ValueOptionOrderEntity optionOrderEntity = (ValueOptionOrderEntity) abstractOptionOrderEntity;
                valueOptionOrderEntities.add(optionOrderEntity);
            } else if (abstractOptionOrderEntity instanceof RadioOptionOrderEntity) {
                RadioOptionOrderEntity radioOptionOrderEntity = (RadioOptionOrderEntity) abstractOptionOrderEntity;
                radioOptionOrderEntities.add(radioOptionOrderEntity);
            } else {
                throw new IllegalArgumentException(String.format("Unknown subtype %s of %s",
                        abstractOptionOrderEntity.getClass().getName(),
                        AbstractOptionOrderEntity.class.getName()));
            }
        });

        return ItemOrderEntity.builder()
                .item(item)
                .checkboxOptionOrders(checkboxOptionOrderEntities)
                .valueOptionOrders(valueOptionOrderEntities)
                .radioOptionOrders(radioOptionOrderEntities)
                .deliverTo(itemOrderCreationDto.getDeliverTo())
                .staffMember(staffMember)
                .orderTime(itemOrderCreationDto.getOrderTime().toDate())
                .note(itemOrderCreationDto.getNote())
                .build();
    }

    public abstract List<AbstractOptionOrderEntity> mapToOptionOrderEntities(List<OptionOrderCreationDto> options);
}
