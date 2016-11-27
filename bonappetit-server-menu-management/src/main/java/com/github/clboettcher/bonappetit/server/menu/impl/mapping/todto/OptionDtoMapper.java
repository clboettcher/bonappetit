package com.github.clboettcher.bonappetit.server.menu.impl.mapping.todto;


import com.github.clboettcher.bonappetit.server.menu.api.dto.read.CheckboxOptionDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.read.OptionDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.read.ValueOptionDto;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.AbstractOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.CheckboxOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.RadioOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ValueOptionEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {RadioOptionDtoMapper.class})
public abstract class OptionDtoMapper {

    @Autowired
    private RadioOptionDtoMapper radioOptionDtoMapper;

    /**
     * @param option The {@link AbstractOptionEntity} to map.
     * @return The mapping result.
     */
    public OptionDto mapToOptionDto(AbstractOptionEntity option) {
        if (option instanceof RadioOptionEntity) {
            return radioOptionDtoMapper.mapToRadioOptionDto((RadioOptionEntity) option);
        } else if (option instanceof ValueOptionEntity) {
            return mapToValueOptionDto((ValueOptionEntity) option);
        } else if (option instanceof CheckboxOptionEntity) {
            return mapToCheckboxOptionDto((CheckboxOptionEntity) option);
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype: %s", option.getClass().getName()));
        }
    }

    /**
     * @param option The option to map.
     * @return The mapping result.
     */
    public abstract CheckboxOptionDto mapToCheckboxOptionDto(CheckboxOptionEntity option);

    /**
     * @param options The options to map.
     * @return The mapping result.
     */
    public abstract Set<OptionDto> mapToOptionDtos(Set<AbstractOptionEntity> options);

    /**
     * @param valueOption The {@link ValueOptionEntity} to map.
     * @return The mapping result.
     */
    public abstract ValueOptionDto mapToValueOptionDto(ValueOptionEntity valueOption);
}
