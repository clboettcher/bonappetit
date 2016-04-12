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
package com.github.clboettcher.bonappetit.server.impl.converter.api;

import com.github.clboettcher.bonappetit.domain.menu.RadioItem;
import com.github.clboettcher.bonappetit.dto.menu.RadioItemDto;

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
