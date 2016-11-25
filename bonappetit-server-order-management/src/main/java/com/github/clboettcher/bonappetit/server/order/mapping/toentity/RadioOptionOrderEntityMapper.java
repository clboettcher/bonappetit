package com.github.clboettcher.bonappetit.server.order.mapping.toentity;

import com.github.clboettcher.bonappetit.server.order.api.dto.write.RadioOptionOrderCreationDto;
import com.github.clboettcher.bonappetit.server.order.entity.RadioOptionOrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RadioOptionOrderEntityMapper {

    /**
     * @param radioOption The {@link RadioOptionOrderCreationDto} to map.
     * @return The mapping result.
     */
    RadioOptionOrderEntity mapToRadioOptionDto(RadioOptionOrderCreationDto radioOption);
}
