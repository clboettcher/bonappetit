package com.github.clboettcher.bonappetit.server.order.mapping.todto;

import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderSummaryDto;
import com.github.clboettcher.bonappetit.server.order.entity.AbstractOptionOrderEntity;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class OptionOrderSummaryDtoMapper {

    private static final Comparator<OptionOrderSummaryDto> OPTION_TITLE_COMPARATOR = (o1, o2) ->
            o1.getOptionTitle().compareTo(o2.getOptionTitle());

    public List<OptionOrderSummaryDto> mapToOptionOrderSummaryDtos(List<AbstractOptionOrderEntity> options) {
        if (options == null) {
            return null;
        }

        List<OptionOrderSummaryDto> list = options.stream()
                .map(this::mapToOptionOrderSummaryDto)
                .collect(Collectors.toList());

        // We need to sort here so that order summaries with the same options are considered equal.
        Collections.sort(list, OPTION_TITLE_COMPARATOR);

        return list;
    }

    public OptionOrderSummaryDto mapToOptionOrderSummaryDto(AbstractOptionOrderEntity option) {
        if (option == null) {
            return null;
        }

        return OptionOrderSummaryDto.builder()
                .optionTitle(option.getOptionTitle())
                .build();
    }
}
