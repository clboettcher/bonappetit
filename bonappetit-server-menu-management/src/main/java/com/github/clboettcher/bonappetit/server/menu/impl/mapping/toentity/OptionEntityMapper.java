package com.github.clboettcher.bonappetit.server.menu.impl.mapping.toentity;


import com.github.clboettcher.bonappetit.server.menu.api.dto.CheckboxOptionDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.OptionDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.RadioOptionDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.ValueOptionDto;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.AbstractOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.CheckboxOptionEntity;
import com.github.clboettcher.bonappetit.server.menu.impl.entity.menu.ValueOptionEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class OptionEntityMapper {

    @Autowired
    private RadioOptionEntityMapper radioOptionEntityMapper;

    /**
     * @param options The options to map.
     * @return The mapping result.
     */
    public abstract Set<AbstractOptionEntity> mapToOptionEntities(Set<OptionDto> options);

    /**
     * @param option The {@link OptionDto} to map.
     * @return The mapping result.
     */
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

    /**
     * @param option The option to map.
     * @return The mapping result.
     */
    public abstract CheckboxOptionEntity mapToCheckboxOptionEntity(CheckboxOptionDto option);

    /**
     * @param valueOption The {@link ValueOptionDto} to map.
     * @return The mapping result.
     */
    public abstract ValueOptionEntity mapToValueOptionEntity(ValueOptionDto valueOption);
}
