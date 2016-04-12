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
package com.github.clboettcher.bonappetit.server.persistence.impl.entity.builder;

import com.github.clboettcher.bonappetit.server.persistence.impl.entity.menu.ItemEntity;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.order.AbstractOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.order.ItemOrderEntity;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.order.OrderEntityStatus;
import com.github.clboettcher.bonappetit.server.persistence.impl.entity.staff.StaffMemberEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class ItemOrderEntityBuilder {
    private long id;
    private ItemEntity item;
    private Set<AbstractOptionOrderEntity> optionOrders;
    private String deliverTo;
    private StaffMemberEntity staffMember;
    private Date orderTime;
    private String note;
    private OrderEntityStatus status;
    private int discount;
    private BigDecimal price;

    private ItemOrderEntityBuilder() {
    }

    public static ItemOrderEntityBuilder anItemOrderEntity() {
        return new ItemOrderEntityBuilder();
    }

    public ItemOrderEntityBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ItemOrderEntityBuilder withItem(ItemEntity item) {
        this.item = item;
        return this;
    }

    public ItemOrderEntityBuilder withOptionOrders(Set<AbstractOptionOrderEntity> optionOrders) {
        this.optionOrders = optionOrders;
        return this;
    }

    public ItemOrderEntityBuilder withDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
        return this;
    }

    public ItemOrderEntityBuilder withStaffMember(StaffMemberEntity staffMember) {
        this.staffMember = staffMember;
        return this;
    }

    public ItemOrderEntityBuilder withOrderTime(Date orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public ItemOrderEntityBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public ItemOrderEntityBuilder withStatus(OrderEntityStatus status) {
        this.status = status;
        return this;
    }

    public ItemOrderEntityBuilder withDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    public ItemOrderEntityBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ItemOrderEntityBuilder but() {
        return anItemOrderEntity().withId(id).withItem(item).withOptionOrders(optionOrders).withDeliverTo(deliverTo).withStaffMember(staffMember).withOrderTime(orderTime).withNote(note).withStatus(status).withDiscount(discount).withPrice(price);
    }

    public ItemOrderEntity build() {
        ItemOrderEntity itemOrderEntity = new ItemOrderEntity();
        itemOrderEntity.setId(id);
        itemOrderEntity.setItem(item);
        itemOrderEntity.setOptionOrders(optionOrders);
        itemOrderEntity.setDeliverTo(deliverTo);
        itemOrderEntity.setStaffMember(staffMember);
        itemOrderEntity.setOrderTime(orderTime);
        itemOrderEntity.setNote(note);
        itemOrderEntity.setStatus(status);
        itemOrderEntity.setDiscount(discount);
        itemOrderEntity.setPrice(price);
        return itemOrderEntity;
    }
}
