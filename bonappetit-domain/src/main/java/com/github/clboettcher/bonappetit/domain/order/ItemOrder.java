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

import com.github.clboettcher.bonappetit.domain.menu.Item;
import com.github.clboettcher.bonappetit.domain.staff.StaffMember;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Set;

/**
 * An order for an {@link Item}.
 */
@Data
@Builder
public class ItemOrder {

    /**
     * The ID.
     */
    private long id;

    /**
     * The ordered item.
     */
    private Item item;

    /**
     * The ordered options (optional).
     */
    private Set<OptionOrder> optionOrders;

    /**
     * The person or location that this order should be delivered to.
     */
    private String deliverTo;

    /**
     * The {@link StaffMember} who took this order.
     */
    private StaffMember staffMember;

    /**
     * The time this order was taken.
     */
    private DateTime orderTime;

    /**
     * A note further describing this order (optional).
     */
    private String note;

    /**
     * The status of this order.
     */
    private OrderStatus status;

    /**
     * The discount in percent.
     * discount = 10
     * means a 10% discount
     */
    private int discount;

    /**
     * The price of the item and the chosen options.
     * Does NOT contain the discount from the discount variable.
     */
    private BigDecimal price;
}
