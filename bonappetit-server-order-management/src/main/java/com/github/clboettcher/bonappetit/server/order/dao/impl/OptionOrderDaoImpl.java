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
