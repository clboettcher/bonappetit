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
package com.github.clboettcher.bonappetit.serverentities.order;

import com.github.clboettcher.bonappetit.serverentities.AbstractEntity;
import com.github.clboettcher.bonappetit.serverentities.menu.Item;
import com.github.clboettcher.bonappetit.serverentities.staff.StaffMember;

import java.util.Date;
import java.util.Set;

/**
 * Created by c.boettcher on 16.03.2016.
 */
public class ItemOrder extends AbstractEntity{
    private Item item;
    private String customer;
    private StaffMember staffMember;
    private Date orderTime;
    private String note;
    private Set<OptionOrder> optionOrders;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public StaffMember getStaffMember() {
        return staffMember;
    }

    public void setStaffMember(StaffMember staffMember) {
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

    public Set<OptionOrder> getOptionOrders() {
        return optionOrders;
    }

    public void setOptionOrders(Set<OptionOrder> optionOrders) {
        this.optionOrders = optionOrders;
    }
}
