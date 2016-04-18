package com.github.clboettcher.bonappetit.server.impl.mapping;

import com.github.clboettcher.bonappetit.domain.menu.RadioOption;
import com.github.clboettcher.bonappetit.dto.menu.RadioOptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Maps {@link RadioOption}s to {@link RadioOptionDto}s.
 */
@Mapper(uses = RadioItemDtoMapper.class)
public interface RadioOptionDtoMapper {

    /**
     * The mapper instance.
     */
    RadioOptionDtoMapper INSTANCE = Mappers.getMapper(RadioOptionDtoMapper.class);

    /**
     * @param radioOption The {@link RadioOption} to map.
     * @return The mapping result.
     */
    RadioOptionDto mapToRadioOptionDto(RadioOption radioOption);
}
