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

import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntity;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private Long id;

    /**
     * The ordered item.
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private ItemEntity item;

    // We need to model the ordered options for each type separately so that hibernate
    // is able to query the it via WHEN clause on the discriminator value. Otherwise
    // hibernate fails with unexpected class errors when loading the option that is
    // referenced by the option order.

    /**
     * The ordered checkbox options.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ORDER_ID")
    private List<CheckboxOptionOrderEntity> checkboxOptionOrders;

    /**
     * The ordered value options.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ORDER_ID")
    private List<ValueOptionOrderEntity> valueOptionOrders;

    /**
     * The ordered radio options.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ORDER_ID")
    private List<RadioOptionOrderEntity> radioOptionOrders;


    /**
     * The person or place (eg table) that the order should be delivered to.
     */
    @Column(name = "DELIVER_TO", nullable = false)
    private String deliverTo;

    /**
     * The staff member who took the order.
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "STAFF_MEMBER_ID", nullable = false)
    private StaffMemberEntity staffMember;

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
     * Constructor setting the specified properties.
     *
     * @param item                 see {@link #item}.
     * @param checkboxOptionOrders see {@link #checkboxOptionOrders}.
     * @param deliverTo            see {@link #deliverTo}.
     * @param staffMember          see {@link #staffMember}.
     * @param orderTime            see {@link #orderTime}.
     * @param note                 see {@link #note}.
     * @param status               see {@link #status}.
     */
    @Builder
    public ItemOrderEntity(ItemEntity item,
                           List<CheckboxOptionOrderEntity> checkboxOptionOrders,
                           List<ValueOptionOrderEntity> valueOptionOrders,
                           List<RadioOptionOrderEntity> radioOptionOrders,
                           String deliverTo,
                           StaffMemberEntity staffMember,
                           Date orderTime,
                           String note,
                           OrderEntityStatus status) {
        this.item = item;
        this.checkboxOptionOrders = checkboxOptionOrders;
        this.valueOptionOrders = valueOptionOrders;
        this.radioOptionOrders = radioOptionOrders;
        this.deliverTo = deliverTo;
        this.staffMember = staffMember;
        this.orderTime = orderTime;
        this.note = note;
        this.status = status;
    }
}
