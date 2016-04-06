package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.dto.menu.CheckboxOptionDto;

import java.math.BigDecimal;

public class CheckboxOptionDtoBuilder {
    private BigDecimal priceDiff;
    private Boolean defaultChecked;
    private String title;
    private Long id;

    private CheckboxOptionDtoBuilder() {
    }

    public static CheckboxOptionDtoBuilder aCheckboxOptionDto() {
        return new CheckboxOptionDtoBuilder();
    }

    public CheckboxOptionDtoBuilder withPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
        return this;
    }

    public CheckboxOptionDtoBuilder withDefaultChecked(Boolean defaultChecked) {
        this.defaultChecked = defaultChecked;
        return this;
    }

    public CheckboxOptionDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public CheckboxOptionDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CheckboxOptionDtoBuilder but() {
        return aCheckboxOptionDto().withPriceDiff(priceDiff).withDefaultChecked(defaultChecked).withTitle(title).withId(id);
    }

    public CheckboxOptionDto build() {
        CheckboxOptionDto checkboxOptionDto = new CheckboxOptionDto();
        checkboxOptionDto.setPriceDiff(priceDiff);
        checkboxOptionDto.setDefaultChecked(defaultChecked);
        checkboxOptionDto.setTitle(title);
        checkboxOptionDto.setId(id);
        return checkboxOptionDto;
    }
}
