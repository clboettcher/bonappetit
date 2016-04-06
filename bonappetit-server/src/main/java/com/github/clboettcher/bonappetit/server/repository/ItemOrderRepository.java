package com.github.clboettcher.bonappetit.server.repository;

import com.github.clboettcher.bonappetit.server.entity.order.ItemOrder;
import org.springframework.data.repository.CrudRepository;

public interface ItemOrderRepository extends CrudRepository<ItemOrder, Long> {
}
