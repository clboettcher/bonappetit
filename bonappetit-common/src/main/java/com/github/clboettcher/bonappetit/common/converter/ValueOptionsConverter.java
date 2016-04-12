package com.github.clboettcher.bonappetit.common.converter;

import com.github.clboettcher.bonappetit.common.domain.menu.ValueOption;
import com.github.clboettcher.bonappetit.common.dto.menu.ValueOptionDto;

public class ValueOptionsConverter {
    /**
     * Converts a {@link ValueOption} to a {@link ValueOptionDto}.
     *
     * @param valueOption The {@link ValueOption} to convert.
     * @return The resulting {@link ValueOptionDto}.
     */
    public static ValueOptionDto convertToValueOptionDto(ValueOption valueOption) {
        ValueOptionDto result = new ValueOptionDto();
        result.setId(valueOption.getId());
        result.setDefaultChecked(valueOption.getDefaultChecked());
        result.setDefaultValue(valueOption.getDefaultValue());
        result.setPriceDiff(valueOption.getPriceDiff());
        result.setTitle(valueOption.getTitle());
        return result;
    }
}
