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
package com.github.clboettcher.bonappetit.server.persistence.impl.mapper;

import com.github.clboettcher.bonappetit.domain.menu.Menu;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.MenuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for {@link MenuEntity} to {@link Menu}.
 */
@Mapper
public interface MenuMapper {

    /**
     * The mapper instance.
     */
    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);


    /**
     * Maps the given {@code menuEntity} to {@link Menu}.
     *
     * @param menuEntity The {@code menuEntity} to map.
     * @return The mapping result.
     */
    Menu mapToMenu(MenuEntity menuEntity);
}
