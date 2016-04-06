package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.dto.menu.OptionDto;

public class OptionDtoBuilder {
    private String title;
    private Long id;

    private OptionDtoBuilder() {
    }

    public static OptionDtoBuilder anOptionDto() {
        return new OptionDtoBuilder();
    }

    public OptionDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public OptionDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public OptionDtoBuilder but() {
        return anOptionDto().withTitle(title).withId(id);
    }

    public OptionDto build() {
        OptionDto optionDto = new OptionDto();
        optionDto.setTitle(title);
        optionDto.setId(id);
        return optionDto;
    }
}
