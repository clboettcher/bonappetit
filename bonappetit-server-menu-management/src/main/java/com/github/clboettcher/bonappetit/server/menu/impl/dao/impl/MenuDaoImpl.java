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
package com.github.clboettcher.bonappetit.server.menu.impl.dao.impl;

import com.github.clboettcher.bonappetit.server.menu.impl.dao.MenuDao;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.config.MenuConfig;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.MenuEntity;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Default impl of {@link MenuDao}.
 */
@Component
@Profile("default")
public class MenuDaoImpl implements MenuDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuDaoImpl.class);

    /**
     * The DAO for {@link MenuConfig}.
     */
    @Autowired
    private MenuConfigRepository menuConfigRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private EntityValidator entityValidator;

    @Autowired
    private EntityPreprocessor preprocessor;

    @Override
    public MenuEntity getCurrentMenu() {
        Iterable<MenuConfig> all = menuConfigRepository.findAll();
        List<MenuConfig> menuConfigs = Lists.newArrayList(all);
        if (menuConfigs.size() > 1) {
            throw new IllegalStateException(String.format("Found more than one %s in the database.",
                    MenuConfig.class.getSimpleName()));
        } else if (CollectionUtils.isEmpty(menuConfigs)) {
            return null;
        }

        return menuConfigs.get(0).getCurrent();
    }

    @Override
    public List<MenuEntity> getAllMenus() {
        return Lists.newArrayList(menuRepository.findAll());
    }

    @Override
    public MenuEntity getMenuById(Long id) {
        return menuRepository.findOne(id);
    }

    @Override
    public MenuEntity create(MenuEntity menuEntity) {
        entityValidator.assertNewMenuValid(menuEntity);
        menuEntity.setCreationTimestamp(DateTime.now(DateTimeZone.UTC).toDate());
        List<ItemEntity> items = menuEntity.getItems();
        items.stream().forEach(preprocessor::prepareOptions);
        return menuRepository.save(menuEntity);
    }

    @Override
    public boolean exists(Long id) {
        return menuRepository.exists(id);
    }


    @Override
    public void setCurrent(MenuEntity menuEntity) {
        Preconditions.checkNotNull(menuEntity, "menuEntity");
        List<MenuConfig> configs = Lists.newArrayList(this.menuConfigRepository.findAll());

        MenuConfig cfg;

        if (CollectionUtils.isEmpty(configs)) {
            LOGGER.info(String.format("Creating new menu config for current menu %s", menuEntity));
            cfg = MenuConfig.builder()
                    .current(menuEntity)
                    .build();
        } else {
            cfg = configs.get(0);
            cfg.setCurrent(menuEntity);
            LOGGER.info(String.format("Updating menu config with ID %d new current menu %s",
                    cfg.getId(),
                    menuEntity));
        }

        this.menuConfigRepository.save(cfg);
    }
}
