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

import com.github.clboettcher.bonappetit.server.menu.api.ItemManagement;
import com.github.clboettcher.bonappetit.server.menu.api.dto.read.ItemDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.ItemCreationDto;
import com.github.clboettcher.bonappetit.server.menu.impl.dao.ItemDao;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.mapping.todto.ItemDtoMapper;
import com.github.clboettcher.bonappetit.server.menu.impl.mapping.toentity.ItemEntityMapper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Default impl of the {@link ItemManagement}.
 */
@Component
public class ItemManagementImpl implements ItemManagement {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemManagementImpl.class);

    @Context
    private UriInfo uri;

    /**
     * The DAO for stored items.
     */
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemDtoMapper itemDtoMapper;

    @Autowired
    private ParamValidator validator;

    @Autowired
    private ItemEntityMapper itemEntityMapper;

    @Override
    public Response createItems(List<ItemCreationDto> itemCreationDtos) {
        this.validator.assertValid(itemCreationDtos);
        LOGGER.info(String.format("Creating %d item(s) from dto list: %s", itemCreationDtos.size(), itemCreationDtos));

        List<ItemEntity> itemEntities = this.itemEntityMapper.mapToItemEntity(itemCreationDtos);
        DateTime now = DateTime.now(DateTimeZone.UTC);
        itemEntities.stream().forEach(itemEntity -> itemEntity.setCreationTimestamp(now.toDate()));
        this.itemDao.create(itemEntities);

        return Response.noContent().build();
    }

    @Override
    public List<ItemDto> getItems() {
        return this.itemDtoMapper.mapToItemDtos(this.itemDao.getAll());
    }

    @Override
    public ItemDto getItemById(Long id) {
        ItemEntity item = itemDao.getItem(id);

        if (item == null) {
            throw new NotFoundException(String.format("Item with ID %d does not exist.", id));
        }

        return itemDtoMapper.mapToItemDto(item);
    }

}
