package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.domain.menu.Item;
import com.github.clboettcher.bonappetit.common.domain.staff.StaffMember;
import com.github.clboettcher.bonappetit.common.dto.order.ItemOrderDto;
import com.github.clboettcher.bonappetit.common.dto.order.OptionOrderDto;
import com.github.clboettcher.bonappetit.common.entity.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class ItemOrderDtoBuilder {
    private Item item;
    private Set<OptionOrderDto> optionOrders;
    private String deliverTo;
    private StaffMember staffMember;
    private Date orderTime;
    private String note;
    private OrderStatus status;
    private int discount;
    private BigDecimal price;
    private Long id;

    private ItemOrderDtoBuilder() {
    }

    public static ItemOrderDtoBuilder anItemOrderDto() {
        return new ItemOrderDtoBuilder();
    }

    public ItemOrderDtoBuilder withItem(Item item) {
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

    public ItemOrderDtoBuilder withStaffMember(StaffMember staffMember) {
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

    public ItemOrderDtoBuilder withStatus(OrderStatus status) {
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
