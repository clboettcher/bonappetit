package com.github.clboettcher.bonappetit.server.menu.impl.mapping.toentity;

import com.github.clboettcher.bonappetit.server.menu.api.dto.RadioOptionDto;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.RadioOptionEntity;
import org.mapstruct.Mapper;

@Mapper(uses = RadioItemEntityMapper.class, componentModel = "spring")
public interface RadioOptionEntityMapper {

    /**
     * @param radioOption The {@link RadioOptionDto} to map.
     * @return The mapping result.
     */
    RadioOptionEntity mapToRadioOptionEntity(RadioOptionDto radioOption);
}
