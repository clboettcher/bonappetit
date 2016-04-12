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
package com.github.clboettcher.bonappetit.dto.builder;

import com.github.clboettcher.bonappetit.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.dto.order.ItemOrderDto;
import com.github.clboettcher.bonappetit.dto.order.OptionOrderDto;
import com.github.clboettcher.bonappetit.dto.order.OrderDtoStatus;
import com.github.clboettcher.bonappetit.dto.staff.StaffMemberDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class ItemOrderDtoBuilder {
    private ItemDto item;
    private Set<OptionOrderDto> optionOrders;
    private String deliverTo;
    private StaffMemberDto staffMember;
    private Date orderTime;
    private String note;
    private OrderDtoStatus status;
    private int discount;
    private BigDecimal price;
    private Long id;

    private ItemOrderDtoBuilder() {
    }

    public static ItemOrderDtoBuilder anItemOrderDto() {
        return new ItemOrderDtoBuilder();
    }

    public ItemOrderDtoBuilder withItem(ItemDto item) {
        this.item = item;
        return this;
    }

    public ItemOrderDtoBuilder withOptionOrders(Set<OptionOrderDto> optionOrders) {
        this.optionOrders = optionOrders;
        return this;
    }

    public ItemOrderDtoBuilder withDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
        return this;
    }

    public ItemOrderDtoBuilder withStaffMember(StaffMemberDto staffMember) {
        this.staffMember = staffMember;
        return this;
    }

    public ItemOrderDtoBuilder withOrderTime(Date orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public ItemOrderDtoBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public ItemOrderDtoBuilder withStatus(OrderDtoStatus status) {
        this.status = status;
        return this;
    }

    public ItemOrderDtoBuilder withDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    public ItemOrderDtoBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ItemOrderDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ItemOrderDtoBuilder but() {
        return anItemOrderDto().withItem(item).withOptionOrders(optionOrders).withDeliverTo(deliverTo).withStaffMember(staffMember).withOrderTime(orderTime).withNote(note).withStatus(status).withDiscount(discount).withPrice(price).withId(id);
    }

    public ItemOrderDto build() {
        ItemOrderDto itemOrderDto = new ItemOrderDto();
        itemOrderDto.setItem(item);
        itemOrderDto.setOptionOrders(optionOrders);
        itemOrderDto.setDeliverTo(deliverTo);
        itemOrderDto.setStaffMember(staffMember);
        itemOrderDto.setOrderTime(orderTime);
        itemOrderDto.setNote(note);
        itemOrderDto.setStatus(status);
        itemOrderDto.setDiscount(discount);
        itemOrderDto.setPrice(price);
        itemOrderDto.setId(id);
        return itemOrderDto;
    }
}
