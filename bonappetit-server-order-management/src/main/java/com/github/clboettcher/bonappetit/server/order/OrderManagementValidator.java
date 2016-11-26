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

import com.github.clboettcher.bonappetit.server.menu.impl.dao.ItemDao;
import com.github.clboettcher.bonappetit.server.menu.impl.dao.OptionDao;
import com.github.clboettcher.bonappetit.server.menu.impl.dao.RadioItemDao;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.AbstractOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.CheckboxOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ValueOptionEntity;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.*;
import com.github.clboettcher.bonappetit.server.staff.dao.StaffMemberDao;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;
import java.util.Collection;
import java.util.List;

@Component
public class OrderManagementValidator {


    @Autowired
    private ItemDao itemDao;

    @Autowired
    private StaffMemberDao staffMemberDao;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private RadioItemDao radioItemDao;


    void assertValid(Collection<ItemOrderCreationDto> orderDtos) {
        if (CollectionUtils.isEmpty(orderDtos)) {
            throw new BadRequestException("Orders to create may not be missing or empty.");
        }

        // Perform precondition checks on all ordered items and ordered options
        orderDtos.forEach(this::assertValid);
    }

    private void assertValid(ItemOrderCreationDto orderDto) {
        assertOrderedItemValid(orderDto.getItemId());
        assertOrderedOptionsValid(orderDto.getOptionOrders());
        assertOrderingStaffMemberValid(orderDto.getStaffMemberId());

        if (StringUtils.isBlank(orderDto.getDeliverTo())) {
            throw new BadRequestException("Property 'deliverTo' may not be blank.");
        }

        if (orderDto.getOrderTime() == null) {
            throw new BadRequestException("Property 'orderTime' may not be missing.");
        }
    }

    private void assertOrderedItemValid(Long orderedItemId) {
        if (orderedItemId == null) {
            throw new BadRequestException("The id of the ordered item may not be blank.");
        }
        if (!itemDao.exists(orderedItemId)) {
            throw new BadRequestException(String.format("Item with ID %d cannot be ordered " +
                            "because it does not exist.",
                    orderedItemId));
        }
    }

    private void assertOrderedOptionsValid(List<OptionOrderCreationDto> optionOrders) {
        if (CollectionUtils.isEmpty(optionOrders)) {
            return;
        }

        optionOrders.forEach(optionOrderDto -> {
            if (optionOrderDto instanceof ValueOptionOrderCreationDto) {
                ValueOptionOrderCreationDto valuOptionOrderDto = (ValueOptionOrderCreationDto) optionOrderDto;
                assertValid(valuOptionOrderDto);
            } else if (optionOrderDto instanceof CheckboxOptionOrderCreationDto) {
                CheckboxOptionOrderCreationDto orderDto = (CheckboxOptionOrderCreationDto) optionOrderDto;
                assertValid(orderDto);
            } else if (optionOrderDto instanceof RadioOptionOrderCreationDto) {
                RadioOptionOrderCreationDto orderDto = (RadioOptionOrderCreationDto) optionOrderDto;
                assertValid(orderDto);
            } else {
                throw new IllegalArgumentException(String.format("Unknown subtype of %s: %s",
                        OptionOrderCreationDto.class.getName(),
                        optionOrderDto.getClass().getName()));
            }
        });

    }

    private void assertValid(ValueOptionOrderCreationDto valueOptionOrderCreationDto) {
        if (valueOptionOrderCreationDto.getValue() == null) {
            throw new BadRequestException("Property 'value' in order for a value option may not be missing.");
        }
        assertValidOptionRef(valueOptionOrderCreationDto.getOptionId(), ValueOptionEntity.class);
    }

    private void assertValid(CheckboxOptionOrderCreationDto checkboxOptionOrderCreationDto) {
        if (checkboxOptionOrderCreationDto.getChecked() == null) {
            throw new BadRequestException("Property 'checked' in order for a checkbox option may not be missing.");
        }
        assertValidOptionRef(checkboxOptionOrderCreationDto.getOptionId(), CheckboxOptionEntity.class);
    }

    private void assertValid(RadioOptionOrderCreationDto radioOptionOrderCreationDto) {
        Long selectedRadioItemId = radioOptionOrderCreationDto.getSelectedRadioItemId();
        if (selectedRadioItemId == null) {
            throw new BadRequestException("Property 'selectedRadioItemId' in order for a radio " +
                    "option may not be missing.");
        }
        if (!radioItemDao.exists(selectedRadioItemId)) {
            throw new BadRequestException(String.format("Radio item with ID %d cannot be ordered " +
                            "because it does not exist.",
                    selectedRadioItemId));
        }
    }

    private <T extends AbstractOptionEntity> void assertValidOptionRef(Long optionId, Class<T> expectedSubType) {
        if (optionId == null) {
            throw new BadRequestException("Property 'optionId' in order for value option may not be missing)");
        }
        AbstractOptionEntity optionEntity = optionDao.getOptionById(optionId);
        if (optionEntity == null) {
            throw new BadRequestException(String.format("Value option with ID %d cannot be ordered " +
                    "because it does not exist.", optionId));
        }
        if (!optionEntity.getClass().isAssignableFrom(expectedSubType)) {
            throw new BadRequestException(String.format("Order for option with ID %d cannot be performed " +
                            "because the referenced option is not of expected type %s but of type %s",
                    optionId,
                    expectedSubType.getSimpleName(),
                    optionEntity.getClass().getSimpleName()));
        }
    }

    private void assertOrderingStaffMemberValid(Long orderingStaffMemberId) {
        if (orderingStaffMemberId == null) {
            throw new BadRequestException("The id of the staff member who took the order may not be blank.");
        }

        if (!staffMemberDao.exists(orderingStaffMemberId)) {
            throw new BadRequestException(String.format("Staff member with ID %d does not exist.",
                    orderingStaffMemberId));
        }
    }
}
