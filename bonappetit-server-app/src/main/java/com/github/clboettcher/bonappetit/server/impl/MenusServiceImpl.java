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
package com.github.clboettcher.bonappetit.server.impl;

import com.github.clboettcher.bonappetit.dto.menu.MenuDto;
import com.github.clboettcher.bonappetit.server.api.MenusService;
import org.springframework.stereotype.Component;

/**
 * Default impl of the {@link MenusService}.
 */
@Component
public class MenusServiceImpl implements MenusService {

    // TODO repair
//    /**
//     * The DAO for stored menus.
//     */
//    private MenuManagement menuManagement;
//
//    /**
//     * Constructor setting the specified properties.
//     *
//     * @param menuManagement see {@link #menuManagement}.
//     */
//    @Autowired
//    public MenusServiceImpl(MenuManagement menuManagement) {
//        this.menuManagement = menuManagement;
//    }

    @Override
    public MenuDto getCurrentMenu() {
//        Menu currentMenu = menuManagement.getCurrentMenu();
//        return MenuDtoMapper.INSTANCE.mapToMenuDto(currentMenu);
        return null;
    }
}
