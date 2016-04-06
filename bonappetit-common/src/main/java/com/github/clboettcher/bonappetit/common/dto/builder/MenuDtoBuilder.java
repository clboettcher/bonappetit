package com.github.clboettcher.bonappetit.common.dto.builder;

import com.github.clboettcher.bonappetit.common.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.common.dto.menu.MenuDto;

import java.util.Set;

public class MenuDtoBuilder {
    private Set<ItemDto> itemDtos;
    private Long id;

    private MenuDtoBuilder() {
    }

    public static MenuDtoBuilder aMenuDto() {
        return new MenuDtoBuilder();
    }

    public MenuDtoBuilder withItemDtos(Set<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
        return this;
    }

    public MenuDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public MenuDtoBuilder but() {
        return aMenuDto().withItemDtos(itemDtos).withId(id);
    }

    public MenuDto build() {
        MenuDto menuDto = new MenuDto();
        menuDto.setItemDtos(itemDtos);
        menuDto.setId(id);
        return menuDto;
    }
}
