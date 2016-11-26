package com.github.clboettcher.bonappetit.server.order.mapping.toentity;


import com.github.clboettcher.bonappetit.server.menu.impl.dao.OptionDao;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.AbstractOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.CheckboxOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ValueOptionEntity;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.CheckboxOptionOrderCreationDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.OptionOrderCreationDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.RadioOptionOrderCreationDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.ValueOptionOrderCreationDto;
import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.CheckboxOptionOrderEntity;
import com.github.clboettcher.bonappetit.server.order.entity.ValueOptionOrderEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OptionOrderEntityMapper {

    @Autowired
    private RadioOptionOrderEntityMapper radioOptionOrderEntityMapper;

    @Autowired
    private OptionDao optionDao;

    public abstract List<AbstractOptionOrderEntity> mapToOptionDtos(List<OptionOrderCreationDto> options);

    public AbstractOptionOrderEntity mapToOptionOrderEntity(OptionOrderCreationDto option) {
        if (option instanceof RadioOptionOrderCreationDto) {
            return radioOptionOrderEntityMapper.mapToRadioOptionDto((RadioOptionOrderCreationDto) option);
        } else if (option instanceof ValueOptionOrderCreationDto) {
            return mapToValueOptionDto((ValueOptionOrderCreationDto) option);
        } else if (option instanceof CheckboxOptionOrderCreationDto) {
            return mapToCheckboxOptionDto((CheckboxOptionOrderCreationDto) option);
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype: %s", option.getClass().getName()));
        }
    }

    public CheckboxOptionOrderEntity mapToCheckboxOptionDto(CheckboxOptionOrderCreationDto option) {
        if (option == null) {
            return null;
        }

        CheckboxOptionOrderEntity result = new CheckboxOptionOrderEntity();
        // Existence and correct type of referenced option have been validated before.
        CheckboxOptionEntity checkboxOption = (CheckboxOptionEntity) optionDao.getOptionById(option.getOptionId());

        result.setCheckboxOption(checkboxOption);
        result.setChecked(option.getChecked());

        return result;
    }

    public ValueOptionOrderEntity mapToValueOptionDto(ValueOptionOrderCreationDto valueOption) {
        if (valueOption == null) {
            return null;
        }

        ValueOptionOrderEntity result = new ValueOptionOrderEntity();

        result.setValue(valueOption.getValue());

        // Existence and correct type of referenced option have been validated before.
        AbstractOptionEntity optionEntity = optionDao.getOptionById(valueOption.getOptionId());
        result.setValueOption((ValueOptionEntity) optionEntity);

        return result;
    }
}
