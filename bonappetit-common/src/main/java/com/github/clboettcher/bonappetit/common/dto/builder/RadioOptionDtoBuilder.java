package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.dto.menu.RadioItemDto;
import com.github.clboettcher.bonappetit.common.dto.menu.RadioOptionDto;

import java.util.Set;

public class RadioOptionDtoBuilder {
    private Long defaultSelectedId;
    private Set<RadioItemDto> radioItemDtos;
    private String title;
    private Long id;

    private RadioOptionDtoBuilder() {
    }

    public static RadioOptionDtoBuilder aRadioOptionDto() {
        return new RadioOptionDtoBuilder();
    }

    public RadioOptionDtoBuilder withDefaultSelectedId(Long defaultSelectedId) {
        this.defaultSelectedId = defaultSelectedId;
        return this;
    }

    public RadioOptionDtoBuilder withRadioItemDtos(Set<RadioItemDto> radioItemDtos) {
        this.radioItemDtos = radioItemDtos;
        return this;
    }

    public RadioOptionDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public RadioOptionDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RadioOptionDtoBuilder but() {
        return aRadioOptionDto().withDefaultSelectedId(defaultSelectedId).withRadioItemDtos(radioItemDtos).withTitle(title).withId(id);
    }

    public RadioOptionDto build() {
        RadioOptionDto radioOptionDto = new RadioOptionDto();
        radioOptionDto.setDefaultSelectedId(defaultSelectedId);
        radioOptionDto.setRadioItemDtos(radioItemDtos);
        radioOptionDto.setTitle(title);
        radioOptionDto.setId(id);
        return radioOptionDto;
    }
}
