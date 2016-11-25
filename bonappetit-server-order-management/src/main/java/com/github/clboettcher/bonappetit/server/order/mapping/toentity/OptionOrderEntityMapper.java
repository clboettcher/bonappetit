package com.github.clboettcher.bonappetit.server.order.mapping.toentity;


import com.github.clboettcher.bonappetit.server.menu.impl.dao.OptionDao;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.CheckboxOptionEntity;
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

@Mapper(componentModel = "spring", uses = {RadioOptionOrderEntityMapper.class})
public abstract class OptionOrderEntityMapper {

    @Autowired
    private RadioOptionOrderEntityMapper radioOptionOrderEntityMapper;

    @Autowired
    private OptionDao optionDao;

    /**
     * @param options The dto to map.
     * @return The mapping result.
     */
    public abstract List<AbstractOptionOrderEntity> mapToOptionDtos(List<OptionOrderCreationDto> options);

    /**
     * @param option The {@link OptionOrderCreationDto} to map.
     * @return The mapping result.
     */
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

    /**
     * @param option The dto to map.
     * @return The mapping result.
     */
    public CheckboxOptionOrderEntity mapToCheckboxOptionDto(CheckboxOptionOrderCreationDto option) {
        if (option == null) {
            return null;
        }

        CheckboxOptionOrderEntity result = new CheckboxOptionOrderEntity();
        CheckboxOptionEntity checkboxOption = (CheckboxOptionEntity) optionDao.getOptionById(option.getOptionId());

        result.setCheckboxOption(checkboxOption);
        result.setChecked(option.getChecked());

        return result;
    };

    /**
     * @param valueOption The {@link ValueOptionOrderCreationDto} to map.
     * @return The mapping result.
     */
    public abstract ValueOptionOrderEntity mapToValueOptionDto(ValueOptionOrderCreationDto valueOption);
}
