package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.domain.menu.RadioItem;
import com.github.clboettcher.bonappetit.common.dto.order.RadioOptionOrderDto;

public class RadioOptionOrderDtoBuilder {
    private RadioItem selectedItem;
    private Long id;

    private RadioOptionOrderDtoBuilder() {
    }

    public static RadioOptionOrderDtoBuilder aRadioOptionOrderDto() {
        return new RadioOptionOrderDtoBuilder();
    }

    public RadioOptionOrderDtoBuilder withSelectedItem(RadioItem selectedItem) {
        this.selectedItem = selectedItem;
        return this;
    }

    public RadioOptionOrderDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RadioOptionOrderDtoBuilder but() {
        return aRadioOptionOrderDto().withSelectedItem(selectedItem).withId(id);
    }

    public RadioOptionOrderDto build() {
        RadioOptionOrderDto radioOptionOrderDto = new RadioOptionOrderDto();
        radioOptionOrderDto.setSelectedItem(selectedItem);
        radioOptionOrderDto.setId(id);
        return radioOptionOrderDto;
    }
}
