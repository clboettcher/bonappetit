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
package com.github.clboettcher.bonappetit.server.order.dao.impl;

import com.github.clboettcher.bonappetit.server.order.dao.OptionOrderDao;
import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OptionOrderDaoImpl implements OptionOrderDao {

    @Autowired
    private OptionOrderRepository repository;

    @Override
    public List<AbstractOptionOrderEntity> getAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public AbstractOptionOrderEntity getOptionOrderById(Long id) {
        return repository.findOne(id);
    }
}
