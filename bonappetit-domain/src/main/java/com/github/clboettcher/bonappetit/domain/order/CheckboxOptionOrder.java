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
package com.github.clboettcher.bonappetit.domain.order;

import com.github.clboettcher.bonappetit.domain.menu.CheckboxOption;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CheckboxOptionOrder extends OptionOrder {

    /**
     * The ordered option.
     */
    private CheckboxOption option;

    /**
     * Whether the option has been checked or not.
     */
    private Boolean checked;

    /**
     * Constructor setting the specified properties.
     *
     * @param id      see {@link #getId()}.
     * @param option  see {@link #getOption()}.
     * @param checked see {@link #getChecked()}.
     */
    @Builder
    public CheckboxOptionOrder(long id, CheckboxOption option, Boolean checked) {
        super(id);
        this.option = option;
        this.checked = checked;
    }
}
