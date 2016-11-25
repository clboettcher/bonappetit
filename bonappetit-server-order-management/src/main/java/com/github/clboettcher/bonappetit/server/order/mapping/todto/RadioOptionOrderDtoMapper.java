package com.github.clboettcher.bonappetit.server.order.mapping.todto;

import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.RadioItemEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.mapping.todto.RadioItemDtoMapper;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.RadioOptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.entity.RadioOptionOrderEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring"
)
public abstract class RadioOptionOrderDtoMapper {

    @Autowired
    private RadioItemDtoMapper radioItemDtoMapper;

    // Sadly mapstruct does not use mappers from other modules out of the box so we do it manually for now.
    RadioOptionOrderDto mapToRadioOptionDto(RadioOptionOrderEntity radioOption) {
        if (radioOption == null) {
            return null;
        }

        RadioOptionOrderDto result = new RadioOptionOrderDto();

        RadioItemEntity selectedRadioItem = radioOption.getSelectedRadioItem();
        result.setSelectedRadioItem(radioItemDtoMapper.mapToRadioItemDto(selectedRadioItem));

        return result;
    }
}
