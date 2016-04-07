package com.github.clboettcher.bonappetit.server.entity.builder;

import com.github.clboettcher.bonappetit.common.entity.OrderStatus;
import com.github.clboettcher.bonappetit.server.entity.menu.Item;
import com.github.clboettcher.bonappetit.server.entity.order.ItemOrder;
import com.github.clboettcher.bonappetit.server.entity.order.OptionOrder;
import com.github.clboettcher.bonappetit.server.entity.staff.StaffMember;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class ItemOrderBuilder {
    private long id;
    private Item item;
    private Set<OptionOrder> optionOrders;
    private String deliverTo;
    private StaffMember staffMember;
    private Date orderTime;
    private String note;
    private OrderStatus status;
    private int discount;
    private BigDecimal price;

    private ItemOrderBuilder() {
    }

    public static ItemOrderBuilder anItemOrder() {
        return new ItemOrderBuilder();
    }

    public ItemOrderBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ItemOrderBuilder withItem(Item item) {
        this.item = item;
        return this;
    }

    public ItemOrderBuilder withOptionOrders(Set<OptionOrder> optionOrders) {
        this.optionOrders = optionOrders;
        return this;
    }

    public ItemOrderBuilder withDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
        return this;
    }

    public ItemOrderBuilder withStaffMember(StaffMember staffMember) {
        this.staffMember = staffMember;
        return this;
    }

    public ItemOrderBuilder withOrderTime(Date orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public ItemOrderBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public ItemOrderBuilder withStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public ItemOrderBuilder withDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    public ItemOrderBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ItemOrderBuilder but() {
        return anItemOrder().withId(id).withItem(item).withOptionOrders(optionOrders).withDeliverTo(deliverTo).withStaffMember(staffMember).withOrderTime(orderTime).withNote(note).withStatus(status).withDiscount(discount).withPrice(price);
    }

    public ItemOrder build() {
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setId(id);
        itemOrder.setItem(item);
        itemOrder.setOptionOrders(optionOrders);
        itemOrder.setDeliverTo(deliverTo);
        itemOrder.setStaffMember(staffMember);
        itemOrder.setOrderTime(orderTime);
        itemOrder.setNote(note);
        itemOrder.setStatus(status);
        itemOrder.setDiscount(discount);
        itemOrder.setPrice(price);
        return itemOrder;
    }
}
