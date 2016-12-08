package com.github.clboettcher.bonappetit.server.menu.impl.mapping.toentity;


import com.github.clboettcher.bonappetit.server.menu.api.dto.read.CheckboxOptionDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.read.OptionDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.read.RadioOptionDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.read.ValueOptionDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.CheckboxOptionCreationDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.OptionCreationDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.RadioOptionCreationDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.ValueOptionCreationDto;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.AbstractOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.CheckboxOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ValueOptionEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OptionEntityMapper {

    @Autowired
    private RadioOptionEntityMapper radioOptionEntityMapper;

    public abstract List<AbstractOptionEntity> mapToOptionEntities(List<OptionCreationDto> options);

    public AbstractOptionEntity mapToOptionEntity(OptionCreationDto option) {
        if (option instanceof RadioOptionCreationDto) {
            return radioOptionEntityMapper.mapToRadioOptionEntity((RadioOptionCreationDto) option);
        } else if (option instanceof ValueOptionCreationDto) {
            return mapToValueOptionEntity((ValueOptionCreationDto) option);
        } else if (option instanceof CheckboxOptionCreationDto) {
            return mapToCheckboxOptionEntity((CheckboxOptionCreationDto) option);
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype: %s", option.getClass().getName()));
        }
    }

    public AbstractOptionEntity mapToOptionEntity(OptionDto option) {
        if (option instanceof RadioOptionDto) {
            return radioOptionEntityMapper.mapToRadioOptionEntity((RadioOptionDto) option);
        } else if (option instanceof ValueOptionDto) {
            return mapToValueOptionEntity((ValueOptionDto) option);
        } else if (option instanceof CheckboxOptionDto) {
            return mapToCheckboxOptionEntity((CheckboxOptionDto) option);
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype: %s", option.getClass().getName()));
        }
    }

    public abstract CheckboxOptionEntity mapToCheckboxOptionEntity(CheckboxOptionCreationDto option);

    public abstract CheckboxOptionEntity mapToCheckboxOptionEntity(CheckboxOptionDto option);

    public abstract ValueOptionEntity mapToValueOptionEntity(ValueOptionCreationDto valueOption);

    public abstract ValueOptionEntity mapToValueOptionEntity(ValueOptionDto valueOption);
}
