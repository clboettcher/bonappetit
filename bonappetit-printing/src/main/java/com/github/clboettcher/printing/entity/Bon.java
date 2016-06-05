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
package com.github.clboettcher.printing.entity;


import com.github.clboettcher.bonappetit.domain.menu.ItemType;

import java.util.GregorianCalendar;
import java.util.Set;

/**
 * An order to be printed.
 */
public class Bon {
    private String note;
    private String itemName;
    private String staffMemberName;
    private String customer;
    private GregorianCalendar orderTime;
    private ItemType itemType;
    private Set<String> defaultOptions;
    private Set<String> emphasisedOptions;

    /**
     * Default noop no-args constructor.
     */
    public Bon() {
    }

    /**
     * Copy constructor.
     *
     * @param bon The bon to copy.
     */
    public Bon(Bon bon) {
        note = bon.getNote();
        itemName = bon.getItemName();
        staffMemberName = bon.getStaffMemberName();
        customer = bon.getCustomer();
        orderTime = bon.getOrderTime();
        itemType = bon.getItemType();
        defaultOptions = bon.getDefaultOptions();
        emphasisedOptions = bon.getEmphasisedOptions();
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setStaffMemberName(String staffMemberName) {
        this.staffMemberName = staffMemberName;
    }

    public String getStaffMemberName() {
        return staffMemberName;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setOrderTime(GregorianCalendar orderTime) {
        this.orderTime = orderTime;
    }

    public GregorianCalendar getOrderTime() {
        return orderTime;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Set<String> getDefaultOptions() {
        return defaultOptions;
    }

    public void setDefaultOptions(Set<String> orderOptionsString) {
        this.defaultOptions = orderOptionsString;
    }

    public Set<String> getEmphasisedOptions() {
        return emphasisedOptions;
    }

    public void setEmphasisedOptions(Set<String> importantOrderOptionsString) {
        this.emphasisedOptions = importantOrderOptionsString;
    }
}
