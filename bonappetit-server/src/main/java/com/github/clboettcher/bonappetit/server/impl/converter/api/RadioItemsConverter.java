package com.github.clboettcher.bonappetit.server.impl.converter.api;

import com.github.clboettcher.bonappetit.common.dto.menu.RadioItemDto;
import com.github.clboettcher.bonappetit.server.entity.menu.RadioItem;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Converts {@link RadioItem}s to {@link RadioItemDto}s.
 */
public interface RadioItemsConverter {

    /**
     * Converts the given {@link RadioItem}s to {@link RadioItemDto}s.
     * <p/>
     * The ordering of the resulting {@link LinkedHashSet} is determined by
     * the indexes of the given {@link RadioItem}s as returned by {@link RadioItem#getIndex()}.
     *
     * @param radioItems The {@link RadioItem}s to convert.
     * @return The resulting {@link RadioItemDto}s.
     */
    Set<RadioItemDto> convert(Set<RadioItem> radioItems);
}
