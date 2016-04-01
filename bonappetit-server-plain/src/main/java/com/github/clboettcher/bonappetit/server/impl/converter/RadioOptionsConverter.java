package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.menu.RadioOptionDto;
import com.github.clboettcher.bonappetit.serverentities.menu.RadioItem;
import com.github.clboettcher.bonappetit.serverentities.menu.RadioOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Converts {@link RadioOption}s to {@link RadioOptionDto}s.
 */
@Component
public class RadioOptionsConverter {

    private RadioItemsConverter radioItemsConverter;

    /**
     * Constructor setting the specified properties.
     *
     * @param radioItemsConverter The converter for {@link RadioItem}s.
     */
    @Autowired
    public RadioOptionsConverter(RadioItemsConverter radioItemsConverter) {
        this.radioItemsConverter = radioItemsConverter;
    }

    /**
     * Converts a {@link RadioOption} to a {@link RadioOptionDto}.
     *
     * @param option The {@link RadioOption} to convert.
     * @return The resulting {@link RadioOptionDto}.
     */
    /* package-private */ RadioOptionDto convert(RadioOption option) {
        RadioOptionDto result = new RadioOptionDto();
        result.setRadioItemDtos(radioItemsConverter.convert(option.getRadioItems()));
        result.setDefaultSelectedId(option.getDefaultSelected().getId());
        result.setTitle(option.getTitle());
        return result;
    }
}
