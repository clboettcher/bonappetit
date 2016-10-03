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
package com.github.clboettcher.bonappetit.server.order.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * An order for a menu item.
 */
@Entity
@Table(name = "ITEM_ORDER")
@Data
@NoArgsConstructor
public class ItemOrderEntity {

    /**
     * The ID.
     */
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ORDER_ID")
    private long id;

    /**
     * The ID of the ordered item.
     */
    @Column(name = "ITEM_ID", nullable = false)
    private Long itemId;

    /**
     * The ordered options.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ORDER_ID")
    private Set<AbstractOptionOrderEntity> optionOrders;

    /**
     * The person or place (eg table) that the order should be delivered to.
     */
    @Column(name = "DELIVER_TO", nullable = false)
    private String deliverTo;

    /**
     * The staff member who took the order.
     */
    @Column(name = "STAFF_MEMBER_ID", nullable = false)
    private Long staffMemberId;

    /**
     * The timestamp that the order was taken.
     */
    @Column(name = "ORDER_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime;

    /**
     * An optional note.
     */
    @Column(name = "NOTE")
    private String note;

    /**
     * The status of this order.
     */
    @Column(name = "STATUS", nullable = false)
    private OrderEntityStatus status;

    /**
     * The discount in percent.
     * discount = 10
     * means a 10% discount
     */
    @Column(name = "DISCOUNT", nullable = false)
    private int discount;

    /**
     * The calculated price of the item and the chosen options.
     * Does NOT contain the discount from the discount variable.
     */
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    /**
     * Constructor setting the specified properties.
     *
     * @param itemId        see {@link #itemId}.
     * @param optionOrders  see {@link #optionOrders}.
     * @param deliverTo     see {@link #deliverTo}.
     * @param staffMemberId see {@link #staffMemberId}.
     * @param orderTime     see {@link #orderTime}.
     * @param note          see {@link #note}.
     * @param status        see {@link #status}.
     * @param discount      see {@link #discount}.
     * @param price         see {@link #price}.
     */
    @Builder
    public ItemOrderEntity(Long itemId, Set<AbstractOptionOrderEntity> optionOrders,
                           String deliverTo, Long staffMemberId, Date orderTime,
                           String note, OrderEntityStatus status, int discount, BigDecimal price) {
        this.itemId = itemId;
        this.optionOrders = optionOrders;
        this.deliverTo = deliverTo;
        this.staffMemberId = staffMemberId;
        this.orderTime = orderTime;
        this.note = note;
        this.status = status;
        this.discount = discount;
        this.price = price;
    }
}
