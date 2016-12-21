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
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ItemEntityType;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    /**
     * The title / name of the ordered item.
     * <p>
     * Use this property rather than {@link #item#getTitle()}
     * because the referenced item might have changed since
     * the order was created.
     */
    @Column(name = "ITEM_TITLE", nullable = false)
    private String itemTitle;

    /**
     * The price of the ordered item.
     * <p>
     * This is the 'raw' price of the ordered item, not considering any
     * options which might have effects on the total price.
     * <p>
     * Use this property rather than {@link #item#getPrice()}
     * because the referenced item might have changed since
     * the order was created.
     *
     * @see #checkboxOptionOrders
     * @see #radioOptionOrders
     * @see #valueOptionOrders
     */
    @Column(name = "ITEM_PRICE", nullable = false)
    private BigDecimal itemPrice;

    /**
     * The type of the ordered item.
     * <p>
     * Use this property rather than {@link #item#getType()}
     * because the referenced item might have changed since
     * the order was created.
     */
    @Column(name = "ITEM_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemEntityType itemType;

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
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customer;

    /**
     * The staff member who took the order.
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "STAFF_MEMBER_ID", nullable = false)
    private StaffMemberEntity staffMember;

    /**
     * The first name of the staff member who took the order.
     * <p>
     * Use this property rather than {@link #staffMember#getFirstName()}
     * because the referenced staff member might have changed since
     * the order was created.
     */
    @Column(name = "STAFF_MEMBER_FIRST_NAME", nullable = false)
    private String staffMemberFirstName;

    /**
     * The last name of the staff member who took the order.
     * <p>
     * Use this property rather than {@link #staffMember#getLastName()}
     * because the referenced staff member might have changed since
     * the order was created.
     */
    @Column(name = "STAFF_MEMBER_LAST_NAME", nullable = false)
    private String staffMemberLastName;

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
    @Enumerated(EnumType.STRING)
    private OrderEntityStatus status;

    /**
     * Constructor setting the specified properties.
     *
     * @param item                 see {@link #item}.
     * @param itemTitle            see {@link #itemTitle}.
     * @param itemPrice            see {@link #itemPrice}.
     * @param itemType             see {@link #itemType}.
     * @param checkboxOptionOrders see {@link #checkboxOptionOrders}.
     * @param customer             see {@link #customer}.
     * @param staffMember          see {@link #staffMember}.
     * @param orderTime            see {@link #orderTime}.
     * @param note                 see {@link #note}.
     * @param status               see {@link #status}.
     */
    @Builder
    public ItemOrderEntity(ItemEntity item,
                           String itemTitle,
                           BigDecimal itemPrice,
                           ItemEntityType itemType,
                           List<CheckboxOptionOrderEntity> checkboxOptionOrders,
                           List<ValueOptionOrderEntity> valueOptionOrders,
                           List<RadioOptionOrderEntity> radioOptionOrders,
                           CustomerEntity customer,
                           StaffMemberEntity staffMember,
                           String staffMemberFirstName,
                           String staffMemberLastName,
                           Date orderTime,
                           String note,
                           OrderEntityStatus status) {
        this.item = item;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.checkboxOptionOrders = checkboxOptionOrders;
        this.valueOptionOrders = valueOptionOrders;
        this.radioOptionOrders = radioOptionOrders;
        this.customer = customer;
        this.staffMember = staffMember;
        this.staffMemberFirstName = staffMemberFirstName;
        this.staffMemberLastName = staffMemberLastName;
        this.orderTime = orderTime;
        this.note = note;
        this.status = status;
    }

    /**
     * Convenience method for retrieving all options.
     * <p>
     * This just collects all options returned by {@link #getCheckboxOptionOrders()},
     * {@link #getValueOptionOrders()} and {@link #getRadioOptionOrders()}.
     *
     * @return All options, may be empty.
     */
    public List<AbstractOptionOrderEntity> getAllOptionOrders() {
        List<AbstractOptionOrderEntity> result = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(this.getCheckboxOptionOrders())) {
            result.addAll(this.getCheckboxOptionOrders());
        }
        if (CollectionUtils.isNotEmpty(this.getValueOptionOrders())) {
            result.addAll(this.getValueOptionOrders());
        }
        if (CollectionUtils.isNotEmpty(this.getRadioOptionOrders())) {
            result.addAll(this.getRadioOptionOrders());
        }

        return result;
    }

}
