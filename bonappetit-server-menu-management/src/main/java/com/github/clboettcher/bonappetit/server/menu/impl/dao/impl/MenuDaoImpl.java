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
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.RadioItemEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.RadioOptionEntity;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public MenuEntity save(MenuEntity menuEntity) {
        Set<ItemEntity> items = menuEntity.getItems();
        items.stream()
                .filter(ItemEntity::hasOptions)
                .forEach(item -> item.getOptions()
                        .stream()
                        .filter(option -> option instanceof RadioOptionEntity)
                        .forEach(option -> prepareRadioOption(item, (RadioOptionEntity) option)));
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

    /**
     * Prepares the given {@link RadioOptionEntity} before able to be saved in the db.
     * <p>
     * This method finds the instance of the radio item that is equal to the
     * {@link RadioOptionEntity#getDefaultSelected()} in the list of radio items
     * ({@link RadioOptionEntity#getRadioItems()} and uses it to override the default selected item.
     * If we would not do this, the default selected item and the items in the list of items would be different
     * instances. However the default selected item should be contained in the list of items.
     *
     * @param item   The whole item (for logging).
     * @param option The option to prepare.
     */
    private void prepareRadioOption(ItemEntity item, RadioOptionEntity option) {
        RadioItemEntity defaultSelected = option.getDefaultSelected();
        Set<RadioItemEntity> radioItems = option.getRadioItems();

        List<RadioItemEntity> defaultSelectedCandidates = radioItems.stream().filter(radioItem ->
                radioItem.equals(defaultSelected)).collect(Collectors.toList());

        if (defaultSelectedCandidates.size() != 1) {
            throw new IllegalArgumentException(String.format("Expected exactly one radio item to " +
                            "match the default selected radio" +
                            " item. Found %d. Full item: %s",
                    defaultSelectedCandidates.size(),
                    item));
        }

        option.setDefaultSelected(defaultSelectedCandidates.get(0));
    }
}
