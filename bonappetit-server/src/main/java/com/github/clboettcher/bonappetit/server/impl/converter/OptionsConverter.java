package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.menu.CheckboxOptionDto;
import com.github.clboettcher.bonappetit.common.dto.menu.IntegerOptionDto;
import com.github.clboettcher.bonappetit.common.dto.menu.OptionDto;
import com.github.clboettcher.bonappetit.serverentities.menu.CheckboxOption;
import com.github.clboettcher.bonappetit.serverentities.menu.IntegerOption;
import com.github.clboettcher.bonappetit.serverentities.menu.Option;
import com.github.clboettcher.bonappetit.serverentities.menu.RadioOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Converts {@link Option} subtypes to {@link OptionDto} subtypes.
 */
@Component
public class OptionsConverter {

    private RadioOptionsConverter radioOptionsConverter;

    /**
     * Constructor setting the specified properties.
     *
     * @param radioOptionsConverter The converter for {@link RadioOption}s.
     */
    @Autowired
    public OptionsConverter(RadioOptionsConverter radioOptionsConverter) {
        this.radioOptionsConverter = radioOptionsConverter;
    }

    /**
     * Converts the given {@link Option}s to {@link OptionDto}s.
     *
     * @param options The {@link Option}s to convert.
     * @return The resulting {@link OptionDto}s.
     */
    /*package-private*/ Set<OptionDto> convert(Set<Option> options) {
        LinkedHashSet<OptionDto> optionDtos = new LinkedHashSet<>(options.size());
        for (Option option : options) {
            optionDtos.add(convert(option));
        }
        return optionDtos;
    }

    /**
     * Converts the given {@link Option} to an {@link OptionDto}.
     *
     * @param option The {@link Option} to convert.
     * @return The resulting {@link OptionDto}.
     */
    private OptionDto convert(Option option) {
        if (option instanceof RadioOption) {
            return radioOptionsConverter.convert((RadioOption) option);
        } else if (option instanceof CheckboxOption) {
            return convertToCheckboxOptionDto((CheckboxOption) option);
        } else if (option instanceof IntegerOption) {
            return convertToIntegerOptionDto((IntegerOption) option);
        } else {
            throw new IllegalArgumentException(String.format("Unknown subtype of %s: %s",
                    Option.class.getName(),
                    option.getClass().getName()));
        }
    }

    /**
     * Converts a {@link CheckboxOption} to a {@link CheckboxOptionDto}.
     *
     * @param checkboxOption The {@link CheckboxOption} to convert.
     * @return The resulting {@link CheckboxOptionDto}.
     */
    private CheckboxOptionDto convertToCheckboxOptionDto(CheckboxOption checkboxOption) {
        CheckboxOptionDto result = new CheckboxOptionDto();
        result.setId(checkboxOption.getId());
        result.setDefaultChecked(checkboxOption.getDefaultChecked());
        result.setPriceDiff(checkboxOption.getPriceDiff());
        result.setTitle(checkboxOption.getTitle());
        return result;
    }

    /**
     * Converts an {@link IntegerOption} to an {@link IntegerOptionDto}.
     *
     * @param integerOption The {@link IntegerOption} to convert.
     * @return The resulting {@link IntegerOptionDto}.
     */
    private IntegerOptionDto convertToIntegerOptionDto(IntegerOption integerOption) {
        IntegerOptionDto result = new IntegerOptionDto();
        result.setId(integerOption.getId());
        result.setDefaultValue(integerOption.getDefaultValue());
        result.setPriceDiff(integerOption.getPriceDiff());
        result.setTitle(integerOption.getTitle());
        return result;
    }
}
