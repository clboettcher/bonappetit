package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.domain.menu.ValueOption;
import com.github.clboettcher.bonappetit.common.dto.order.ValueOptionOrderDto;

public class ValueOptionOrderDtoBuilder {
    private ValueOption option;
    private Boolean checked;
    private int value;
    private Long id;

    private ValueOptionOrderDtoBuilder() {
    }

    public static ValueOptionOrderDtoBuilder aValueOptionOrderDto() {
        return new ValueOptionOrderDtoBuilder();
    }

    public ValueOptionOrderDtoBuilder withOption(ValueOption option) {
        this.option = option;
        return this;
    }

    public ValueOptionOrderDtoBuilder withChecked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    public ValueOptionOrderDtoBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    public ValueOptionOrderDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ValueOptionOrderDtoBuilder but() {
        return aValueOptionOrderDto().withOption(option).withChecked(checked).withValue(value).withId(id);
    }

    public ValueOptionOrderDto build() {
        ValueOptionOrderDto valueOptionOrderDto = new ValueOptionOrderDto();
        valueOptionOrderDto.setOption(option);
        valueOptionOrderDto.setChecked(checked);
        valueOptionOrderDto.setValue(value);
        valueOptionOrderDto.setId(id);
        return valueOptionOrderDto;
    }
}
