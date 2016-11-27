package com.github.clboettcher.bonappetit.server.menu.impl.mapping.todto;

import com.github.clboettcher.bonappetit.server.menu.api.dto.read.RadioItemDto;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.RadioItemEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RadioItemDtoMapper {

    /**
     * @param radioItem The {@link RadioItemEntity}s to map.
     * @return The mapping result.
     */
    Set<RadioItemDto> mapToRadioItemDtos(Set<RadioItemEntity> radioItem);

    /**
     * @param radioItem The {@link RadioItemEntity} to map.
     * @return The mapping result.
     */
    RadioItemDto mapToRadioItemDto(RadioItemEntity radioItem);
}
