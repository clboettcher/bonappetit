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
package com.github.clboettcher.bonappetit.printing.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Contains sets of categorized order options.
 */
@Data
@NoArgsConstructor
public class OrderOptionStrings {

    /**
     * The options which should be printed in an emphasised way.
     */
    private Set<String> emphasisedOptions;

    /**
     * The options which should be printed normally, not emphasised.
     */
    private Set<String> defaultOptions;

    /**
     * Constructor setting the specified properties.
     *
     * @param emphasisedOptions see {@link #emphasisedOptions}.
     * @param defaultOptions    see {@link #defaultOptions}.
     */
    @Builder
    public OrderOptionStrings(Set<String> emphasisedOptions, Set<String> defaultOptions) {
        this.emphasisedOptions = emphasisedOptions;
        this.defaultOptions = defaultOptions;
    }
}
