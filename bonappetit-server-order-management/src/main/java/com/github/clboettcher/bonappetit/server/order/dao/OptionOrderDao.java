package com.github.clboettcher.bonappetit.server.order.dao;

import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;

import java.util.List;

public interface OptionOrderDao {

    List<AbstractOptionOrderEntity> getAll();

    AbstractOptionOrderEntity getOptionOrderById(Long id);
}
