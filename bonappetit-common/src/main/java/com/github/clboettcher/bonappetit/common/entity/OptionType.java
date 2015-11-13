/*
* Copyright (c) 2015 Claudius Boettcher (pos.bonappetit@gmail.com)
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
package com.github.clboettcher.bonappetit.common.entity;

/**
 * The type of an item option.
 */
public enum OptionType {

    /**
     * An option that contains multiple choices of which exactly one must be selected.
     */
    RADIO,

    /**
     * An option that consists of a single integer value.
     */
    INTEGER,

    /**
     * An option that consists of a boolean checkbox value.
     */
    CHECKBOX,

    /**
     * An option similar to {@link #RADIO} that should be displayed in a dropdown window.
     */
    RADIO_DROPDOWN
}
