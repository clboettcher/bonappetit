package com.github.clboettcher.bonappetit.server.menu.impl.mapping.toentity;

import com.github.clboettcher.bonappetit.server.menu.api.dto.RadioItemDto;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.RadioItemEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RadioItemEntityMapper {

    /**
     * @param radioItem The {@link RadioItemDto}s to map.
     * @return The mapping result.
     */
    Set<RadioItemEntity> mapToRadioItemEntites(Set<RadioItemDto> radioItem);

    /**
     * @param radioItem The {@link RadioItemDto} to map.
     * @return The mapping result.
     */
    RadioItemEntity mapToRadioItemEntity(RadioItemDto radioItem);
}
