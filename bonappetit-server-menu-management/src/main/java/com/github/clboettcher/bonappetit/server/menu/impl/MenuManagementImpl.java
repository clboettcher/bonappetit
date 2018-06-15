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

import com.github.clboettcher.bonappetit.server.menu.api.MenuManagement;
import com.github.clboettcher.bonappetit.server.menu.api.dto.read.MenuDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.read.MenuRefDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.MenuCreationDto;
import com.github.clboettcher.bonappetit.server.menu.impl.dao.ItemDao;
import com.github.clboettcher.bonappetit.server.menu.impl.dao.MenuDao;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.MenuEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.mapping.todto.MenuDtoMapper;
import com.github.clboettcher.bonappetit.server.menu.impl.mapping.todto.MenuRefDtoMapper;
import com.github.clboettcher.bonappetit.server.menu.impl.mapping.toentity.MenuEntityMapper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Set;

/**
 * Default impl of the {@link MenuManagement}.
 */
@Component
public class MenuManagementImpl implements MenuManagement {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuManagementImpl.class);

    @Context
    private UriInfo uri;

    /**
     * The DAO for stored menus.
     */
    @Autowired
    private MenuDao menuDao;

    /**
     * The DAO for stored items.
     */
    @Autowired
    private ItemDao itemDao;

    /**
     * The entity to dto mapper.
     */
    @Autowired
    private MenuDtoMapper menuDtoMapper;

    @Autowired
    private MenuRefDtoMapper menuRefDtoMapper;

    @Autowired
    private ParamValidator validator;

    /**
     * The dto to entity mapper.
     */
    @Autowired
    private MenuEntityMapper menuEntityMapper;

    @Override
    public MenuDto getCurrentMenu() {
        MenuEntity currentMenu = menuDao.getCurrentMenu();

        if (currentMenu == null) {
            throw new InternalServerErrorException("No menu has been configured as current.");
        }

        return menuDtoMapper.mapToMenuDto(currentMenu);
    }

    @Override
    public Response setCurrentMenu(Long menuId) {
        if (menuId == null) {
            throw new BadRequestException("Param menuId may not be blank.");
        }

        validator.assertMenuExists(
                menuId,
                String.format("Menu with ID %d cannot be set as current because it does not exist.", menuId)
        );

        MenuEntity newCurrent = menuDao.getMenuById(menuId);
        menuDao.setCurrent(newCurrent);

        return Response.noContent().build();
    }

    @Override
    public List<MenuRefDto> getAllMenus() {
        List<MenuEntity> allMenus = menuDao.getAllMenus();
        LOGGER.info(String.format("Returning ref(s) for %d menu(s)", allMenus.size()));
        return menuRefDtoMapper.mapToMenuRefDtos(allMenus);
    }

    @Override
    public MenuDto getMenuById(Long id) {
        if (id == null) {
            throw new BadRequestException("Param id may not be blank.");
        }

        MenuEntity menu = menuDao.getMenuById(id);

        if (menu == null) {
            throw new NotFoundException(String.format("Menu with ID %d does not exist.", id));
        }

        return menuDtoMapper.mapToMenuDto(menu);
    }

    @Override
    public Response createMenu(MenuCreationDto menuDto) {
        validator.assertValid(menuDto);

        LOGGER.info(String.format("Creating menu from DTO %s", menuDto));

        MenuEntity menuEntity = this.menuEntityMapper.mapToMenuEntity(menuDto);
        MenuEntity saved = menuDao.create(menuEntity);

        return okWithLocationHeader(MENUS_PATH, saved.getId());
    }

    @Override
    public Response updateMenu(Long id, MenuCreationDto menuCreationDto) {
        validator.assertMenuExists(id);
        validator.assertValid(menuCreationDto);

        LOGGER.info(String.format("Updating menu with id %d from DTO %s", id, menuCreationDto));
        MenuEntity toUpdate = this.menuDao.getMenuById(id);
        MenuEntity updateValues = this.menuEntityMapper.mapToMenuEntity(menuCreationDto);

        toUpdate.setItems(updateValues.getItems());
        toUpdate.setTitle(updateValues.getTitle());
        toUpdate.setLastUpdateTimestamp(DateTime.now(DateTimeZone.UTC).toDate());

        this.menuDao.update(toUpdate);
        return Response.noContent().build();
    }

    @Override
    public MenuDto addItemsToMenu(Long menuId, Set<Long> idsToAdd) {
        this.validator.assertAddItemsPreconditions(menuId, idsToAdd);

        LOGGER.info(String.format("Adding %d item(s) to menu with id %d", idsToAdd.size(), menuId));

        MenuEntity menuToUpdate = this.menuDao.getMenuById(menuId);
        List<ItemEntity> itemsToUpdate = menuToUpdate.getItems();

        // Resolve items to add.
        List<ItemEntity> itemsToAdd = this.itemDao.getItemsByIdList(idsToAdd);

        itemsToUpdate.addAll(itemsToAdd);

        menuToUpdate.setItems(itemsToUpdate);
        menuToUpdate.setLastUpdateTimestamp(DateTime.now(DateTimeZone.UTC).toDate());
        this.menuDao.update(menuToUpdate);

        return this.menuDtoMapper.mapToMenuDto(menuToUpdate);
    }

    @Override
    public MenuDto removeItemsFromMenu(Long menuId, Set<Long> idsToRemove) {
        this.validator.assertRemoveItemsPreconditions(menuId, idsToRemove);

        MenuEntity menuToUpdate = this.menuDao.getMenuById(menuId);
        List<ItemEntity> itemsToUpdate = menuToUpdate.getItems();

        LOGGER.info(String.format("Removing %d item(s) from menu with id %d", idsToRemove.size(), menuId));

        itemsToUpdate.removeIf(itemEntity -> idsToRemove.contains(itemEntity.getId()));

        menuToUpdate.setItems(itemsToUpdate);
        menuToUpdate.setLastUpdateTimestamp(DateTime.now(DateTimeZone.UTC).toDate());
        MenuEntity updated = this.menuDao.update(menuToUpdate);

        return this.menuDtoMapper.mapToMenuDto(updated);
    }

    private Response okWithLocationHeader(String path, Long id) {
        String location = String.format("%s/%d", path, id);
        UriBuilder baseUriBuilder = uri.getBaseUriBuilder().path(location);

        return Response.ok()
                .location(baseUriBuilder.build())
                .build();
    }
}
