package com.github.clboettcher.bonappetit.server.impl.mapping;


import com.github.clboettcher.bonappetit.domain.menu.Option;
import com.github.clboettcher.bonappetit.domain.menu.RadioOption;
import com.github.clboettcher.bonappetit.domain.menu.ValueOption;
import com.github.clboettcher.bonappetit.dto.menu.OptionDto;
import com.github.clboettcher.bonappetit.dto.menu.ValueOptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

/**
 * Maps {@link Option}s to {@link OptionDto}s.
 */
@Mapper
public abstract class OptionDtoMapper {

    /**
     * The mapper instance.
     */
    public static final OptionDtoMapper INSTANCE = Mappers.getMapper(OptionDtoMapper.class);

    /**
     * @param option The {@link Option} to map.
     * @return The mapping result.
     */
    public OptionDto mapToOptionDto(Option option) {
        if (option instanceof RadioOption) {
            return RadioOptionDtoMapper.INSTANCE.mapToRadioOptionDto((RadioOption) option);
        } else if (option instanceof ValueOption) {
            return mapToValueOptionDto((ValueOption) option);
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype: %s", option.getClass().getName()));
        }
    }

    /**
     * @param option The options to map.
     * @return The mapping result.
     */
    public abstract Set<OptionDto> mapToOptionDtos(Set<Option> option);

    /**
     * @param valueOption The {@link ValueOption} to map.
     * @return The mapping result.
     */
    public abstract ValueOptionDto mapToValueOptionDto(ValueOption valueOption);
}
