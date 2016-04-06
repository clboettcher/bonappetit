package com.github.clboettcher.bonappetit.server.impl.converter.api;

import com.github.clboettcher.bonappetit.common.dto.menu.MenuDto;
import com.github.clboettcher.bonappetit.server.entity.menu.Menu;

/**
 * Converts {@link Menu}s to {@link MenuDto}s.
 */
public interface MenusConverter {
    /**
     * Converts the given {@link Menu} to a {@link MenuDto}.
     *
     * @param menu The {@link Menu} to convert.
     * @return The resulting {@link MenuDto}.
     */
    MenuDto convertToDto(Menu menu);
}
