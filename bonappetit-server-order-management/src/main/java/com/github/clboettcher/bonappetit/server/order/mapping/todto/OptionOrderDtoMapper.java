package com.github.clboettcher.bonappetit.server.order.mapping.todto;


import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.CheckboxOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ValueOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.mapping.todto.OptionDtoMapper;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.CheckboxOptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ValueOptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.OptionOrderCreationDto;
import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.CheckboxOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.RadioOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.ValueOptionOrderEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OptionOrderDtoMapper {

    @Autowired
    private RadioOptionOrderDtoMapper radioOptionOrderDtoMapper;

    @Autowired
    private OptionDtoMapper optionDtoMapper;

    /**
     * @param abstractOptionOrderEntities The dto to map.
     * @return The mapping result.
     */
    public abstract List<OptionOrderDto> mapToOptionOrderDtos(List<AbstractOptionOrderEntity> abstractOptionOrderEntities);

    /**
     * @param abstractOptionOrderEntity The {@link OptionOrderCreationDto} to map.
     * @return The mapping result.
     */
    public OptionOrderDto mapToOptionOrderDto(AbstractOptionOrderEntity abstractOptionOrderEntity) {
        if (abstractOptionOrderEntity instanceof RadioOptionOrderEntity) {
            return radioOptionOrderDtoMapper.mapToRadioOptionDto((RadioOptionOrderEntity) abstractOptionOrderEntity);
        } else if (abstractOptionOrderEntity instanceof ValueOptionOrderEntity) {
            return mapToValueOptionDto((ValueOptionOrderEntity) abstractOptionOrderEntity);
        } else if (abstractOptionOrderEntity instanceof CheckboxOptionOrderEntity) {
            return mapToCheckboxOptionOrderDto((CheckboxOptionOrderEntity) abstractOptionOrderEntity);
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype: %s",
                    abstractOptionOrderEntity.getClass().getName()));
        }
    }

    // Sadly mapstruct does not use mappers from other modules out of the box so we do it manually for now.
    public CheckboxOptionOrderDto mapToCheckboxOptionOrderDto(CheckboxOptionOrderEntity checkboxOptionOrderEntity) {
        if (checkboxOptionOrderEntity == null) {
            return null;
        }

        CheckboxOptionOrderDto result = new CheckboxOptionOrderDto();

        result.setChecked(checkboxOptionOrderEntity.getChecked());
        CheckboxOptionEntity checkboxOption = checkboxOptionOrderEntity.getCheckboxOption();
        result.setCheckboxOptionId(checkboxOption.getId());
        result.setOptionTitle(checkboxOptionOrderEntity.getOptionTitle());
        result.setOptionPriceDiff(checkboxOptionOrderEntity.getOptionPriceDiff());

        return result;
    }

    // Sadly mapstruct does not use mappers from other modules out of the box so we do it manually for now.
    public ValueOptionOrderDto mapToValueOptionDto(ValueOptionOrderEntity valueOptionOrderEntity) {
        if (valueOptionOrderEntity == null) {
            return null;
        }

        ValueOptionOrderDto result = new ValueOptionOrderDto();

        ValueOptionEntity valueOption = valueOptionOrderEntity.getValueOption();
        result.setValueOptionId(valueOption.getId());
        result.setValue(valueOptionOrderEntity.getValue());
        result.setOptionTitle(valueOptionOrderEntity.getOptionTitle());
        result.setOptionPriceDiff(valueOptionOrderEntity.getOptionPriceDiff());


        return result;
    }
}
