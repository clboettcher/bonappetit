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
package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.menu.RadioItemDto;
import com.github.clboettcher.bonappetit.serverentities.menu.RadioItem;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Converts {@link com.github.clboettcher.bonappetit.serverentities.menu.RadioItem}s to
 * {@link com.github.clboettcher.bonappetit.common.dto.menu.RadioItemDto}s.
 */
@Component
public class RadioItemsConverter {

    /**
     * Compares {@link RadioItem}s by {@link RadioItem#getIndex()}.
     */
    private Comparator<RadioItem> indexComparator = new Comparator<RadioItem>() {
        @Override
        public int compare(RadioItem o1, RadioItem o2) {
            return o1.getIndex().compareTo(o2.getIndex());
        }
    };

    /**
     * Converts the given {@link RadioItem}s to {@link RadioItemDto}s.
     * <p/>
     * The ordering of the resulting {@link LinkedHashSet} is determined by
     * the indexes of the given {@link RadioItem}s as returned by {@link RadioItem#getIndex()}.
     *
     * @param radioItems The {@link RadioItem}s to convert.
     * @return The resulting {@link RadioItemDto}s.
     */
    /* package-private */ Set<RadioItemDto> convert(Set<RadioItem> radioItems) {
        List<RadioItem> inputSorted = new ArrayList<>(radioItems);
        Collections.sort(inputSorted, indexComparator);
        LinkedHashSet<RadioItemDto> result = new LinkedHashSet<>(inputSorted.size());
        for (RadioItem radioItem : inputSorted) {
            result.add(convert(radioItem));
        }
        return result;
    }

    /**
     * Converts the given {@link RadioItem} to a {@link RadioItemDto}.
     *
     * @param radioItem The {@link RadioItem} to convert.
     * @return The resulting {@link RadioItemDto}.
     */
    private static RadioItemDto convert(RadioItem radioItem) {
        return RadioItemDto.newBuilder()
                .id(radioItem.getId())
                .title(radioItem.getTitle())
                .priceDiff(radioItem.getPriceDiff())
                .build();
    }
}
