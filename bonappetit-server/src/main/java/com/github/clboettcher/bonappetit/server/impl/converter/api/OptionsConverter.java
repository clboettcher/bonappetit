package com.github.clboettcher.bonappetit.server.impl.converter.api;

import com.github.clboettcher.bonappetit.common.dto.menu.OptionDto;
import com.github.clboettcher.bonappetit.server.entity.menu.Option;

import java.util.Set;

/**
 * Converts {@link Option} subtypes to {@link OptionDto} subtypes.
 */
public interface OptionsConverter {

    /**
     * Converts the given {@link Option}s to {@link OptionDto}s.
     *
     * @param options The {@link Option}s to convert.
     * @return The resulting {@link OptionDto}s.
     */
    Set<OptionDto> convert(Set<Option> options);
}
