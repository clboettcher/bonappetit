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

import com.github.clboettcher.bonappetit.server.menu.impl.dao.ItemDao;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntity;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Default impl of {@link ItemDao}.
 */
@Component
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private EntityValidator entityValidator;

    @Autowired
    private EntityPreprocessor preprocessor;

    @Override
    public ItemEntity getItem(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<ItemEntity> getAll() {
        return Lists.newArrayList(this.repository.findAll());
    }

    @Override
    public List<ItemEntity> getItemsByIdList(List<Long> itemIds) {
        return Lists.newArrayList(repository.findAll(itemIds));
    }

    @Override
    public boolean exists(Long id) {
        return repository.exists(id);
    }

    @Override
    public List<ItemEntity> create(List<ItemEntity> itemEntities) {
        Preconditions.checkNotNull(itemEntities, "itemEntities");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(itemEntities), "itemEntities empty");
        this.entityValidator.assertNewItemsValid(itemEntities);
        itemEntities.stream().forEach(preprocessor::prepareOptions);
        return Lists.newArrayList(this.repository.save(itemEntities));
    }
}
