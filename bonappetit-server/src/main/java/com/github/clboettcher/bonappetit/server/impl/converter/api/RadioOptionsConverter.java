package com.github.clboettcher.bonappetit.server.impl.converter.api;

import com.github.clboettcher.bonappetit.common.dto.menu.RadioOptionDto;
import com.github.clboettcher.bonappetit.server.entity.menu.RadioOption;

/**
 * Converts {@link RadioOption}s to {@link RadioOptionDto}s.
 */
public interface RadioOptionsConverter {

    /**
     * Converts a {@link RadioOption} to a {@link RadioOptionDto}.
     *
     * @param option The {@link RadioOption} to convert.
     * @return The resulting {@link RadioOptionDto}.
     */
    RadioOptionDto convert(RadioOption option);
}
