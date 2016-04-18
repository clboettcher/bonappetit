package com.github.clboettcher.bonappetit.server.impl.mapping;

import com.github.clboettcher.bonappetit.domain.menu.RadioItem;
import com.github.clboettcher.bonappetit.dto.menu.RadioItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface RadioItemDtoMapper {

    /**
     * The mapper instance.
     */
    RadioItemDtoMapper INSTANCE = Mappers.getMapper(RadioItemDtoMapper.class);

    /**
     * @param radioItem The {@link RadioItem}s to map.
     * @return The mapping result.
     */
    Set<RadioItemDto> mapToRadioItemDtos(Set<RadioItem> radioItem);

    /**
     * @param radioItem The {@link RadioItem} to map.
     * @return The mapping result.
     */
    RadioItemDto mapToRadioItemDto(RadioItem radioItem);
}
