package com.github.clboettcher.bonappetit.server.order.mapping;


import com.github.clboettcher.bonappetit.server.order.api.dto.CheckboxOptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.OptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.RadioOptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.ValueOptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.CheckboxOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.ValueOptionOrderEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RadioOptionOrderEntityMapper.class})
public abstract class OptionOrderEntityMapper {

    @Autowired
    private RadioOptionOrderEntityMapper radioOptionOrderEntityMapper;

    /**
     * @param options The dto to map.
     * @return The mapping result.
     */
    public abstract List<AbstractOptionOrderEntity> mapToOptionDtos(List<OptionOrderDto> options);

    /**
     * @param option The {@link OptionOrderDto} to map.
     * @return The mapping result.
     */
    public AbstractOptionOrderEntity mapToOptionOrderEntity(OptionOrderDto option) {
        if (option instanceof RadioOptionOrderDto) {
            return radioOptionOrderEntityMapper.mapToRadioOptionDto((RadioOptionOrderDto) option);
        } else if (option instanceof ValueOptionOrderDto) {
            return mapToValueOptionDto((ValueOptionOrderDto) option);
        } else if (option instanceof CheckboxOptionOrderDto) {
            return mapToCheckboxOptionDto((CheckboxOptionOrderDto) option);
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype: %s", option.getClass().getName()));
        }
    }

    /**
     * @param option The dto to map.
     * @return The mapping result.
     */
    public abstract CheckboxOptionOrderEntity mapToCheckboxOptionDto(CheckboxOptionOrderDto option);

    /**
     * @param valueOption The {@link ValueOptionOrderDto} to map.
     * @return The mapping result.
     */
    public abstract ValueOptionOrderEntity mapToValueOptionDto(ValueOptionOrderDto valueOption);
}
