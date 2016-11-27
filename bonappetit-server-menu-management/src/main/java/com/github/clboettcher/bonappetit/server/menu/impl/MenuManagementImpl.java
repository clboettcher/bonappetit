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
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.MenuCreationDto;
import com.github.clboettcher.bonappetit.server.menu.impl.dao.MenuDao;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.MenuEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.mapping.todto.MenuDtoMapper;
import com.github.clboettcher.bonappetit.server.menu.impl.mapping.toentity.MenuEntityMapper;
import io.swagger.annotations.ApiParam;
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
    private MenuDao menuDao;

    /**
     * The entity to dto mapper.
     */
    @Autowired
    private MenuDtoMapper dtoMapper;

    /**
     * The dto to entity mapper.
     */
    @Autowired
    private MenuEntityMapper menuEntityMapper;

    /**
     * Constructor setting the specified properties.
     *
     * @param menuDao see {@link #menuDao}.
     */
    @Autowired
    public MenuManagementImpl(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public MenuDto getCurrentMenu() {
        MenuEntity currentMenu = menuDao.getCurrentMenu();

        if (currentMenu == null) {
            throw new InternalServerErrorException("No menu has been configured as current.");
        }

        return dtoMapper.mapToMenuDto(currentMenu);
    }

    @Override
    public List<MenuDto> getAllMenus() {
        List<MenuEntity> allMenus = menuDao.getAllMenus();
        LOGGER.info(String.format("Returning %d menu(s)", allMenus.size()));
        return dtoMapper.mapToMenuDtos(allMenus);
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

        return dtoMapper.mapToMenuDto(menu);
    }

    @Override
    public Response createMenu(@ApiParam(value = "The menu to create.", required = true) MenuCreationDto menuDto) {
        if (menuDto == null) {
            throw new BadRequestException("Menu that should be created must be present");
        }

        LOGGER.info(String.format("Creating menu from DTO %s", menuDto));

        MenuEntity menuEntity = this.menuEntityMapper.mapToMenuEntity(menuDto);
        MenuEntity saved = menuDao.create(menuEntity);

        String location = String.format("%s/%d", MENUS_PATH, saved.getId());
        UriBuilder baseUriBuilder = uri.getBaseUriBuilder().path(location);

        return Response.ok()
                .location(baseUriBuilder.build())
                .build();
    }

    @Override
    public Response setCurrentMenu(Long menuId) {
        if (menuId == null) {
            throw new BadRequestException("Param menuId may not be blank.");
        }

        if (!menuDao.exists(menuId)) {
            throw new BadRequestException(String.format("Menu with ID %d cannot be set as current because " +
                    "it does not exist.", menuId
            ));
        }

        MenuEntity newCurrent = menuDao.getMenuById(menuId);
        menuDao.setCurrent(newCurrent);

        return Response.noContent().build();
    }
}
