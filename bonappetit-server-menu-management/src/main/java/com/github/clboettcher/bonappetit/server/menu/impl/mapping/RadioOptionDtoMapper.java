package com.github.clboettcher.bonappetit.server.menu.impl.mapping;

import com.github.clboettcher.bonappetit.server.menu.api.dto.RadioOptionDto;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.RadioOptionEntity;
import org.mapstruct.Mapper;

@Mapper(uses = RadioItemDtoMapper.class, componentModel = "spring")
public interface RadioOptionDtoMapper {

    /**
     * @param radioOption The {@link RadioOptionEntity} to map.
     * @return The mapping result.
     */
    RadioOptionDto mapToRadioOptionDto(RadioOptionEntity radioOption);
}
