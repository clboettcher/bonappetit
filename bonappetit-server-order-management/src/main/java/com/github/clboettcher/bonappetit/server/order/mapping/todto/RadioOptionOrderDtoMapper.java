package com.github.clboettcher.bonappetit.server.order.mapping.todto;

import com.github.clboettcher.bonappetit.server.order.api.dto.RadioOptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.entity.RadioOptionOrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RadioOptionOrderDtoMapper {

    RadioOptionOrderDto mapToRadioOptionDto(RadioOptionOrderEntity radioOption);
}
