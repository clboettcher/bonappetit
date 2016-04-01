package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.menu.MenuDto;
import com.github.clboettcher.bonappetit.serverentities.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Converts {@link Menu}s to {@link MenuDto}s.
 */
@Component
public class MenusConverter {

    private ItemsConverter itemsConverter;

    @Autowired
    public MenusConverter(ItemsConverter itemsConverter) {
        this.itemsConverter = itemsConverter;
    }

    /**
     * Converts the given {@link Menu} to a {@link MenuDto}.
     *
     * @param menu The {@link Menu} to convert.
     * @return The resulting {@link MenuDto}.
     */
    /*package-private*/ MenuDto convertToDto(Menu menu) {
        return MenuDto.newBuilder()
                .id(menu.getId())
                .itemDtos(itemsConverter.convertToItemDtos(menu.getItems()))
                .build();
    }
}
