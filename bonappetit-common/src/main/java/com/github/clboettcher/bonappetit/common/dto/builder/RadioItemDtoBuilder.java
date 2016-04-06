package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.dto.menu.RadioItemDto;

import java.math.BigDecimal;

public class RadioItemDtoBuilder {
    private String title;
    private BigDecimal priceDiff;
    private Long id;

    private RadioItemDtoBuilder() {
    }

    public static RadioItemDtoBuilder aRadioItemDto() {
        return new RadioItemDtoBuilder();
    }

    public RadioItemDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public RadioItemDtoBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public RadioItemDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RadioItemDtoBuilder but() {
        return aRadioItemDto().withTitle(title).withPriceDiff(priceDiff).withId(id);
    }

    public RadioItemDto build() {
        RadioItemDto radioItemDto = new RadioItemDto();
        radioItemDto.setTitle(title);
        radioItemDto.setPriceDiff(priceDiff);
        radioItemDto.setId(id);
        return radioItemDto;
    }
}
