package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.dto.menu.IntegerOptionDto;

import java.math.BigDecimal;

public class IntegerOptionDtoBuilder {
    private BigDecimal priceDiff;
    private Integer defaultValue;
    private String title;
    private Long id;

    private IntegerOptionDtoBuilder() {
    }

    public static IntegerOptionDtoBuilder anIntegerOptionDto() {
        return new IntegerOptionDtoBuilder();
    }

    public IntegerOptionDtoBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public IntegerOptionDtoBuilder withDefaultValue(Integer defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public IntegerOptionDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public IntegerOptionDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public IntegerOptionDtoBuilder but() {
        return anIntegerOptionDto().withPriceDiff(priceDiff).withDefaultValue(defaultValue).withTitle(title).withId(id);
    }

    public IntegerOptionDto build() {
        IntegerOptionDto integerOptionDto = new IntegerOptionDto();
        integerOptionDto.setPriceDiff(priceDiff);
        integerOptionDto.setDefaultValue(defaultValue);
        integerOptionDto.setTitle(title);
        integerOptionDto.setId(id);
        return integerOptionDto;
    }
}
