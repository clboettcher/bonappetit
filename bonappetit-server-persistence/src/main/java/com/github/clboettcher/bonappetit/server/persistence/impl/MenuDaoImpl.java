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
package com.github.clboettcher.bonappetit.server.persistence.impl;

import com.github.clboettcher.bonappetit.domain.menu.Menu;
import com.github.clboettcher.bonappetit.server.persistence.api.MenuDao;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.config.MenuConfig;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.MenuEntity;
import com.github.clboettcher.bonappetit.server.persistence.impl.mapper.MenuMapper;
import com.github.clboettcher.bonappetit.server.persistence.impl.repository.MenuConfigRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Default impl of {@link MenuDao}.
 */
@Component
public class MenuDaoImpl implements MenuDao {

    /**
     * The DAO for {@link MenuConfig}.
     */
    private MenuConfigRepository menuConfigRepository;

    /**
     * Constructor setting the specified properties.
     *
     * @param menuConfigRepository see {@link #menuConfigRepository}.
     */
    @Autowired
    public MenuDaoImpl(MenuConfigRepository menuConfigRepository) {
        this.menuConfigRepository = menuConfigRepository;
    }

    @Override
    public Menu getCurrentMenu() {
        Iterable<MenuConfig> all = menuConfigRepository.findAll();
        List<MenuConfig> menuConfigs = Lists.newArrayList(all);
        if (menuConfigs.size() > 1) {
            throw new IllegalStateException(String.format("Found more than one %s in the database.",
                    MenuConfig.class.getSimpleName()));
        }

        MenuEntity current = menuConfigs.get(0).getCurrent();

        return MenuMapper.INSTANCE.mapToMenu(current);
    }
}
