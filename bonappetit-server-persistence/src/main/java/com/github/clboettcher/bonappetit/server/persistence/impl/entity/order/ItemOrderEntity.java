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
package com.github.clboettcher.bonappetit.server.persistence.impl.entity.order;

import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.ItemEntity;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.staff.StaffMemberEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "ITEM_ORDER")
public class ItemOrderEntity {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ORDER_ID")
    private long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private ItemEntity item;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ORDER_ID")
    private Set<AbstractOptionOrderEntity> optionOrders;

    @Column(name = "DELIVER_TO", nullable = false)
    private String deliverTo;

    @OneToOne(optional = false)
    @JoinColumn(name = "STAFF_MEMBER_ID", nullable = false)
    private StaffMemberEntity staffMember;

    @Column(name = "ORDER_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime;

    @Column(name = "NOTE")
    private String note;

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
     * The price of the item and the chosen options.
     * Does NOT contain the discount from the discount variable.
     */
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    /**
     * @return The ID.
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public String getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
    }

    public StaffMemberEntity getStaffMember() {
        return staffMember;
    }

    public void setStaffMember(StaffMemberEntity staffMember) {
        this.staffMember = staffMember;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<AbstractOptionOrderEntity> getOptionOrders() {
        return optionOrders;
    }

    public void setOptionOrders(Set<AbstractOptionOrderEntity> optionOrders) {
        this.optionOrders = optionOrders;
    }

    public OrderEntityStatus getStatus() {
        return status;
    }

    public void setStatus(OrderEntityStatus status) {
        this.status = status;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("item", item)
                .append("optionOrders", optionOrders)
                .append("deliverTo", deliverTo)
                .append("staffMember", staffMember)
                .append("orderTime", orderTime)
                .append("note", note)
                .append("status", status)
                .append("discount", discount)
                .append("price", price)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        ItemOrderEntity rhs = (ItemOrderEntity) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.item, rhs.item)
                .append(this.optionOrders, rhs.optionOrders)
                .append(this.deliverTo, rhs.deliverTo)
                .append(this.staffMember, rhs.staffMember)
                .append(this.orderTime, rhs.orderTime)
                .append(this.note, rhs.note)
                .append(this.status, rhs.status)
                .append(this.discount, rhs.discount)
                .append(this.price, rhs.price)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(item)
                .append(optionOrders)
                .append(deliverTo)
                .append(staffMember)
                .append(orderTime)
                .append(note)
                .append(status)
                .append(discount)
                .append(price)
                .toHashCode();
    }
}
