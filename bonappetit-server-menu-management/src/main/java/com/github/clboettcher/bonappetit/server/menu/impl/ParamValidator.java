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
package com.github.clboettcher.bonappetit.server.menu.impl;

import com.github.clboettcher.bonappetit.server.menu.api.dto.write.*;
import com.github.clboettcher.bonappetit.server.menu.impl.dao.ItemDao;
import com.github.clboettcher.bonappetit.server.menu.impl.dao.MenuDao;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.MenuEntity;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ParamValidator {

    private MenuDao menuDao;
    private ItemDao itemDao;

    @Autowired
    public ParamValidator(MenuDao menuDao, ItemDao itemDao) {
        this.menuDao = menuDao;
        this.itemDao = itemDao;
    }

    void assertValid(MenuCreationDto menuDto) {
        if (menuDto == null) {
            throw new BadRequestException("Menu that should be created must be present");
        }
        if (StringUtils.isBlank(menuDto.getTitle())) {
            throw new BadRequestException("Menu must have a non blank title.");
        }
        if (CollectionUtils.isEmpty(menuDto.getItemIds())) {
            throw new BadRequestException("Menu must have at least one item.");
        }
        // Verify: all item ids exist
        List<Long> nonExistentItems = menuDto.getItemIds()
                .stream()
                .filter(itemId -> !itemDao.exists(itemId)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(nonExistentItems)) {
            throw new BadRequestException(String.format("Menu cannot be created because not all items exist. " +
                    "Missing item ids: %s", nonExistentItems));
        }
    }

    void assertValid(List<ItemCreationDto> items) {
        if (CollectionUtils.isEmpty(items)) {
            throw new BadRequestException("Menu must have at least one item.");
        }
        items.forEach(this::assertValid);
    }

    void assertValid(ItemCreationDto dto) {
        if (StringUtils.isBlank(dto.getTitle())) {
            throw new BadRequestException(String.format(
                    "Property title of item must not be blank. Dto: %s",
                    dto
            ));
        }
        if (dto.getType() == null) {
            throw new BadRequestException(String.format(
                    "Property type of item with title '%s' must be present",
                    dto.getTitle()
            ));
        }
        if (dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException(String.format("Property price of item with title '%s' must be present and " +
                    "greater or equal to zero.", dto.getTitle()));
        }
        if (CollectionUtils.isNotEmpty(dto.getOptions())) {
            dto.getOptions().forEach(optionDto -> assertValid(optionDto, dto.getTitle()));
            Set<Integer> indices = dto.getOptions().stream().map(OptionCreationDto::getIndex)
                    .collect(Collectors.toSet());
            if (dto.getOptions().size() != indices.size()) {
                throw new BadRequestException(String.format(
                        "The number of distinct option indices must be " +
                                "equal to the number of options on item with title '%s'. " +
                                "Number of options: %d, Number of distinct indices: %d. Indices: %s",
                        dto.getTitle(),
                        dto.getOptions().size(),
                        indices.size(),
                        indices
                ));
            }
            for (int i = 0; i < dto.getOptions().size(); i++) {
                if (!indices.contains(i)) {
                    throw new BadRequestException(String.format("Item with title '%s' contains no option with " +
                                    "required index %d", dto.getTitle(),
                            i
                    ));
                }
            }
        }
    }

    void assertValid(OptionCreationDto dto, String itemTitle) {
        if (StringUtils.isBlank(dto.getTitle())) {
            throw new BadRequestException(String.format("Property title of option of item with title '%s' " +
                    "must not be blank.", itemTitle));
        }
        if (dto.getIndex() == null || dto.getIndex() < 0) {
            throw new BadRequestException(String.format("Property index of option of item with title '%s' " +
                    "must not be blank or less than zero.", itemTitle));
        }
        if (dto instanceof CheckboxOptionCreationDto) {
            assertValidCheckboxOption((CheckboxOptionCreationDto) dto, itemTitle);
        } else if (dto instanceof ValueOptionCreationDto) {
            assertValidValueOption((ValueOptionCreationDto) dto, itemTitle);
        } else if (dto instanceof RadioOptionCreationDto) {
            assertValidRadioOption((RadioOptionCreationDto) dto, itemTitle);
        } else {
            throw new BadRequestException(String.format(
                    "Unknown option type on item with title '%s': %s",
                    itemTitle,
                    dto.getClass().getName()
            ));
        }
    }

    void assertAddItemsPreconditions(Long menuId, Set<Long> idsToAdd) {
        assertMenuExists(menuId);
        if (idsToAdd == null || idsToAdd.isEmpty()) {
            throw new BadRequestException(String.format(
                    "Menu with id %d cannot be updated with empty item list.",
                    menuId
            ));
        }

        MenuEntity menuToUpdate = this.menuDao.getMenuById(menuId);
        List<ItemEntity> itemsToUpdate = menuToUpdate.getItems();

        // Check if the menu already contains at least one of the items
        Set<Long> currentItems = itemsToUpdate.stream().map(ItemEntity::getId).collect(Collectors.toSet());
        Sets.SetView<Long> intersection = Sets.intersection(currentItems, idsToAdd);
        if (!intersection.isEmpty()) {
            throw new BadRequestException(String.format(
                    "Could not add %d item(s) to menu with id %d: Some of the items from the request are already " +
                            "associated with this menu. Intersection: %s.",
                    idsToAdd.size(),
                    menuId,
                    intersection
            ));
        }

        // Resolve items to add.
        List<ItemEntity> itemsToAdd = this.itemDao.getItemsByIdList(idsToAdd);
        Set<Long> existingIdsToAdd = itemsToAdd.stream().map(ItemEntity::getId).collect(Collectors.toSet());
        Sets.SetView<Long> diffRequestedExistingIds = Sets.difference(idsToAdd, existingIdsToAdd);
        if (!diffRequestedExistingIds.isEmpty()) {
            throw new BadRequestException(String.format(
                    "Could not add %d item(s) to menu with id %d: Could not find item(s) for " +
                            "at least one of the id(s) from the request. Difference: %s.",
                    idsToAdd.size(),
                    menuId,
                    diffRequestedExistingIds
            ));
        }
    }

    void assertRemoveItemsPreconditions(Long menuId, Set<Long> idsToRemove) {
        assertMenuExists(menuId);
        if (idsToRemove == null || idsToRemove.isEmpty()) {
            throw new BadRequestException(String.format(
                    "Menu with id %d cannot be updated with empty item list.",
                    menuId
            ));
        }

        MenuEntity menuToUpdate = this.menuDao.getMenuById(menuId);
        List<ItemEntity> itemsToUpdate = menuToUpdate.getItems();

        // Check if the menu contains all of the requested items.
        Set<Long> currentItemIds = itemsToUpdate.stream().map(ItemEntity::getId).collect(Collectors.toSet());
        Sets.SetView<Long> difference = Sets.difference(idsToRemove, currentItemIds);
        if (!difference.isEmpty()) {
            throw new BadRequestException(String.format(
                    "Could not remove %d item(s) from menu with id %d: Some of the items from the request are not " +
                            "associated with this menu. Difference of toRemoveIds - containedIds = %s.",
                    idsToRemove.size(),
                    menuId,
                    difference
            ));
        }
    }

    void assertMenuExists(Long menuId) {
        assertMenuExists(menuId, String.format("Menu with id %d does not exist.", menuId));
    }

    void assertMenuExists(Long menuId, String errorMsg) {
        if (!this.menuDao.exists(menuId)) {
            throw new BadRequestException(errorMsg);
        }
    }

    private void assertValidCheckboxOption(CheckboxOptionCreationDto dto, String itemTitle) {
        assertValidPriceDiff(dto.getPriceDiff(), itemTitle, "checkboxOptionCreationDto");
        if (dto.getDefaultChecked() == null) {
            throw new BadRequestException(String.format("Property defaultChecked of checkboxOptionCreationDto " +
                    "on item with title '%s' must be present.", itemTitle));
        }
    }

    private void assertValidValueOption(ValueOptionCreationDto dto, String itemTitle) {
        this.assertValidPriceDiff(dto.getPriceDiff(), itemTitle, "valueOptionCreationDto");
        if (dto.getDefaultValue() == null || dto.getDefaultValue() < 0) {
            throw new BadRequestException(String.format("Property defaultValue of option with type " +
                    "valueOptionCreationDto of item with title '%s' must be present and greater " +
                    "than or equal to zero.", itemTitle));
        }
    }

    private void assertValidRadioOption(RadioOptionCreationDto radioOptionDto, String itemTitle) {
        if (CollectionUtils.isEmpty(radioOptionDto.getRadioItems())) {
            throw new BadRequestException(String.format("Property radioItems of radioOptionCreationDto of " +
                    "item with title '%s' must be present and not empty.", itemTitle));
        }
        // Assert radio items have all required properties
        radioOptionDto.getRadioItems().forEach(radioItem -> this.assertValid(radioItem, itemTitle));
        // Validate: Default selected in radio items
        if (radioOptionDto.getDefaultSelected() == null) {
            throw new BadRequestException(String.format(
                    "Property defaultSelected of radioOptionCreationDto " +
                            "with title '%s' of item with title '%s' must be present",
                    radioOptionDto.getTitle(),
                    itemTitle
            ));
        }
        if (!radioOptionDto.getRadioItems().contains(radioOptionDto.getDefaultSelected())) {
            throw new BadRequestException(String.format(
                    "The default selected radio item is not contained in the " +
                            "radio items on option with title '%s' of item with title '%s'",
                    radioOptionDto.getTitle(),
                    itemTitle
            ));
        }

        // Validate indices
        Set<Integer> indices = radioOptionDto.getRadioItems().stream().map(RadioItemCreationDto::getIndex)
                .collect(Collectors.toSet());
        if (radioOptionDto.getRadioItems().size() != indices.size()) {
            throw new BadRequestException(String.format(
                    "The number of distinct radio item indices must be " +
                            "equal to the number of radio items on option with title '%s' of item with title '%s'. " +
                            "Number of radio items: %d, Number of distinct indices: %d. Indices: %s",
                    radioOptionDto.getTitle(),
                    itemTitle,
                    radioOptionDto.getRadioItems().size(),
                    indices.size(),
                    indices
            ));
        }
        for (int i = 0; i < radioOptionDto.getRadioItems().size(); i++) {
            if (!indices.contains(i)) {
                throw new BadRequestException(String.format(
                        "Option with title '%s' of item with title '%s' " +
                                "contains no radio item with required index %d",
                        radioOptionDto.getTitle(),
                        itemTitle,
                        i
                ));
            }
        }
    }

    private void assertValid(RadioItemCreationDto dto, String itemTitle) {

        if (StringUtils.isBlank(dto.getTitle())) {
            throw new BadRequestException(String.format(
                    "Property title of radioItemCreationDto of item with title '%s' " +
                            "must not be blank.",
                    itemTitle
            ));
        }
        if (dto.getIndex() == null || dto.getIndex() < 0) {
            throw new BadRequestException(String.format("Property index of radioItemCreationDto of item with title " +
                    "'%s'" +
                    " must be present and greater than or equal to zero", itemTitle));
        }
        this.assertValidPriceDiff(dto.getPriceDiff(), itemTitle, "radioItemCreationDto");
    }

    private void assertValidPriceDiff(BigDecimal priceDiff, String itemTitle, String entityType) {
        if (priceDiff == null) {
            throw new BadRequestException(String.format("Property priceDiff of DTO with type '%s' " +
                    "of item with title '%s' must be present.", entityType, itemTitle));
        }
    }

}
