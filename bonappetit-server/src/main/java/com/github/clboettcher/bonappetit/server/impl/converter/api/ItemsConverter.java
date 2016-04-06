package com.github.clboettcher.bonappetit.server.impl.converter.api;

import com.github.clboettcher.bonappetit.common.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.server.entity.menu.Item;

import java.util.Set;

/**
 * Converts {@link Item}s to {@link ItemDto}s.
 */
public interface ItemsConverter {

    /**
     * Converts the given {@link Item}s to {@link ItemDto}s.
     *
     * @param items The items to convert.
     * @return The resulting DTOs.
     */
    public Set<ItemDto> convertToItemDtos(Set<Item> items);

}
