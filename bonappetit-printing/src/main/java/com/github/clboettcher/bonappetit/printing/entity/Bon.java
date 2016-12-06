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


import com.github.clboettcher.bonappetit.server.menu.api.dto.common.ItemDtoType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * An order to be printed.
 */
@Data
@NoArgsConstructor
public class Bon {

    /**
     * A note (optional).
     */
    private String note;

    /**
     * The title of the item which is printed.
     */
    private String itemTitle;

    /**
     * The name of the staff member that took the order.
     */
    private String staffMemberName;

    /**
     * The person or place to deliver the order to.
     */
    private String deliverTo;

    /**
     * The time the order was taken.
     */
    private DateTime orderTime;

    /**
     * The type of the ordered item.
     */
    private ItemDtoType itemType;

    /**
     * The options which should be printed normally (not emphasised).
     */
    private List<String> defaultOptions = new ArrayList<>();

    /**
     * The options which should be printed in an emphasised way.
     */
    private List<String> emphasisedOptions = new ArrayList<>();

    /**
     * Constructor setting the specified properties.
     *
     * @param note              see {@link #note}.
     * @param itemTitle         see {@link #itemTitle}.
     * @param staffMemberName   see {@link #staffMemberName}.
     * @param deliverTo         see {@link #deliverTo}.
     * @param orderTime         see {@link #orderTime}.
     * @param itemType          see {@link #itemType}.
     * @param defaultOptions    see {@link #defaultOptions}.
     * @param emphasisedOptions see {@link #emphasisedOptions}.
     */
    @Builder
    public Bon(String note,
               String itemTitle,
               String staffMemberName,
               String deliverTo,
               DateTime orderTime,
               ItemDtoType itemType,
               List<String> defaultOptions,
               List<String> emphasisedOptions) {
        this.note = note;
        this.itemTitle = itemTitle;
        this.staffMemberName = staffMemberName;
        this.deliverTo = deliverTo;
        this.orderTime = orderTime;
        this.itemType = itemType;
        this.defaultOptions = defaultOptions;
        this.emphasisedOptions = emphasisedOptions;
    }

    /**
     * Copy constructor.
     *
     * @param toCopy The bon to copy.
     */
    public Bon(Bon toCopy) {
        this(toCopy.getNote(),
                toCopy.getItemTitle(),
                toCopy.getStaffMemberName(),
                toCopy.getDeliverTo(),
                toCopy.getOrderTime(),
                toCopy.getItemType(),
                toCopy.getDefaultOptions(),
                toCopy.getEmphasisedOptions());
    }
}
