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
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.*;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.*;
import com.github.clboettcher.bonappetit.server.staff.dao.StaffMemberDao;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class OrderManagementValidator {


    /**
     * Predicate that can be used to filter for a subtype {@link RadioOptionEntity}.
     */
    private static final Predicate<AbstractOptionEntity> RADIO_OPTION_PREDICATE =
            abstractOptionEntity -> abstractOptionEntity instanceof RadioOptionEntity;
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
        ItemEntity orderedItem = itemDao.getItem(orderDto.getItemId());
        assertOrderedOptionsValid(orderDto.getOptionOrders(), orderedItem);
        assertOrderingStaffMemberValid(orderDto.getStaffMemberId());

        assertValid(orderDto.getCustomer());

        if (orderDto.getOrderTime() == null) {
            throw new BadRequestException("Property 'orderTime' may not be missing.");
        }
    }

    private void assertValid(CustomerCreationDto customerCreationDto) {
        if (customerCreationDto == null) {
            throw new BadRequestException("Customer may not be missing.");
        }

        if (customerCreationDto instanceof FreeTextCustomerCreationDto) {
            FreeTextCustomerCreationDto dto = (FreeTextCustomerCreationDto) customerCreationDto;
            if (StringUtils.isBlank(dto.getValue())) {
                throw new BadRequestException("Customer free text value may not be blank.");
            }
        } else if (customerCreationDto instanceof TableCustomerCreationDto) {
            TableCustomerCreationDto dto = (TableCustomerCreationDto) customerCreationDto;
            if (dto.getTableNumber() == null) {
                throw new BadRequestException("Table number may not be null for table customer.");
            }
        } else if (customerCreationDto instanceof StaffMemberCustomerCreationDto) {
            StaffMemberCustomerCreationDto dto = (StaffMemberCustomerCreationDto) customerCreationDto;
            if (dto.getStaffMemberId() == null) {
                throw new BadRequestException("Staff member id may not be null for a staff member customer.");
            }
            if (!staffMemberDao.exists(dto.getStaffMemberId())) {
                throw new BadRequestException(String.format("Order for staff member with id %s cannot be taken " +
                                "because the staff member does not exist.",
                        dto.getStaffMemberId()));
            }
        } else {
            throw new BadRequestException(String.format("Unknown subtype of %s: %s",
                    CustomerCreationDto.class.getName(),
                    customerCreationDto.getClass().getName()));
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

    private void assertOrderedOptionsValid(List<OptionOrderCreationDto> optionOrders, ItemEntity orderedItem) {
        if (CollectionUtils.isEmpty(optionOrders)) {
            return;
        }

        optionOrders.forEach(optionOrderDto -> {
            if (optionOrderDto instanceof ValueOptionOrderCreationDto) {
                ValueOptionOrderCreationDto valueOptionOrderDto = (ValueOptionOrderCreationDto) optionOrderDto;
                assertValid(valueOptionOrderDto, orderedItem);
            } else if (optionOrderDto instanceof CheckboxOptionOrderCreationDto) {
                CheckboxOptionOrderCreationDto orderDto = (CheckboxOptionOrderCreationDto) optionOrderDto;
                assertValid(orderDto, orderedItem);
            } else if (optionOrderDto instanceof RadioOptionOrderCreationDto) {
                RadioOptionOrderCreationDto orderDto = (RadioOptionOrderCreationDto) optionOrderDto;
                assertValid(orderDto, orderedItem);
            } else {
                throw new IllegalArgumentException(String.format("Unknown subtype of %s: %s",
                        OptionOrderCreationDto.class.getName(),
                        optionOrderDto.getClass().getName()));
            }
        });

    }

    private void assertValid(ValueOptionOrderCreationDto valueOptionOrderCreationDto, ItemEntity orderedItem) {
        if (valueOptionOrderCreationDto.getValue() == null) {
            throw new BadRequestException("Property 'value' in order for a value option may not be missing.");
        }
        assertValidOptionRef(valueOptionOrderCreationDto.getOptionId(), ValueOptionEntity.class, orderedItem);
    }

    private void assertValid(CheckboxOptionOrderCreationDto checkboxOptionOrderCreationDto, ItemEntity orderedItem) {
        if (checkboxOptionOrderCreationDto.getChecked() == null) {
            throw new BadRequestException("Property 'checked' in order for a checkbox option may not be missing.");
        }
        assertValidOptionRef(checkboxOptionOrderCreationDto.getOptionId(), CheckboxOptionEntity.class, orderedItem);
    }

    private void assertValid(RadioOptionOrderCreationDto radioOptionOrderCreationDto, ItemEntity orderedItem) {
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

        // Make sure that the ordered radio item belongs to a radio option that belongs to the ordered item.
        Set<Long> radioItemIds = new HashSet<>();
        orderedItem.getOptions().stream().filter(RADIO_OPTION_PREDICATE).forEach(abstractOptionEntity -> {
            RadioOptionEntity radioOptionEntity = (RadioOptionEntity) abstractOptionEntity;
            List<RadioItemEntity> radioItems = radioOptionEntity.getRadioItems();
            radioItemIds.addAll(radioItems
                    .stream()
                    .map(RadioItemEntity::getId)
                    .collect(Collectors.toList()));
        });
        if (!radioItemIds.contains(selectedRadioItemId)) {
            throw new BadRequestException(String.format("Radio item with ID %d cannot be ordered because it does not " +
                            "belong to the ordered item with ID %d", selectedRadioItemId,
                    orderedItem.getId()));
        }
    }

    private <T extends AbstractOptionEntity> void assertValidOptionRef(Long optionId,
                                                                       Class<T> expectedSubType,
                                                                       ItemEntity orderedItem) {
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
        assertOrderedOptionBelongsToItem(orderedItem, optionEntity);
    }

    private void assertOrderedOptionBelongsToItem(ItemEntity orderedItem,
                                                  AbstractOptionEntity orderedOption) {
        List<AbstractOptionEntity> orderedItemOptions = orderedItem.getOptions();
        if (!orderedItemOptions.contains(orderedOption)) {
            throw new BadRequestException(String.format("Order for option with ID %d is invalid because " +
                            "the referenced option does not belong to the ordered item (id=%d).",
                    orderedOption.getId(),
                    orderedItem.getId()));
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

    void assertValidParamCombination(Optional<DateTime> orderedBeforeTimeOpt,
                                     Optional<DateTime> orderedAfterTimeOpt,
                                     Optional<LocalDate> orderedAtDateOpt) {
        if ((orderedBeforeTimeOpt.isPresent() || orderedAfterTimeOpt.isPresent()) && orderedAtDateOpt.isPresent()) {
            throw new BadRequestException("Only the params orderedBefore and orderedAfter " +
                    "can be used together. The orderedAt param must be used without orderedBefore and " +
                    "orderedAfter.");
        }
        if (orderedBeforeTimeOpt.isPresent() && orderedAfterTimeOpt.isPresent()) {
            if (orderedBeforeTimeOpt.get().isAfter(orderedAfterTimeOpt.get())) {
                throw new BadRequestException(String.format("Parameter orderedBefore (%s) cannot be after " +
                                "parameter orderedAfter (%s)",
                        orderedBeforeTimeOpt.get(),
                        orderedAfterTimeOpt.get()
                ));
            }
        }
    }
}
