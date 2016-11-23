package com.github.clboettcher.bonappetit.server.order.mapping.todto;


import com.github.clboettcher.bonappetit.server.order.api.dto.common.CheckboxOptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.common.OptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.common.ValueOptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.CheckboxOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.RadioOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.ValueOptionOrderEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RadioOptionOrderDtoMapper.class})
public abstract class OptionOrderDtoMapper {

    @Autowired
    private RadioOptionOrderDtoMapper radioOptionOrderDtoMapper;

    /**
     * @param options The dto to map.
     * @return The mapping result.
     */
    public abstract List<OptionOrderDto> mapToOptionDtos(List<AbstractOptionOrderEntity> options);

    /**
     * @param option The {@link OptionOrderDto} to map.
     * @return The mapping result.
     */
    public OptionOrderDto mapToOptionOrderDto(AbstractOptionOrderEntity option) {
        if (option instanceof RadioOptionOrderEntity) {
            return radioOptionOrderDtoMapper.mapToRadioOptionDto((RadioOptionOrderEntity) option);
        } else if (option instanceof ValueOptionOrderEntity) {
            return mapToValueOptionDto((ValueOptionOrderEntity) option);
        } else if (option instanceof CheckboxOptionOrderEntity) {
            return mapToCheckboxOptionDto((CheckboxOptionOrderEntity) option);
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype: %s", option.getClass().getName()));
        }
    }

    public abstract CheckboxOptionOrderDto mapToCheckboxOptionDto(CheckboxOptionOrderEntity option);

    public abstract ValueOptionOrderDto mapToValueOptionDto(ValueOptionOrderEntity valueOption);
}
