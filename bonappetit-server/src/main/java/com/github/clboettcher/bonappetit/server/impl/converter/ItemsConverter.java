package com.github.clboettcher.bonappetit.server.impl.converter;

import com.github.clboettcher.bonappetit.common.dto.menu.ItemDto;
import com.github.clboettcher.bonappetit.serverentities.menu.Item;
import com.github.clboettcher.bonappetit.serverentities.menu.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Converts {@link Item}s to {@link ItemDto}s.
 */
@Component
public class ItemsConverter {

    private OptionsConverter optionsConverter;

    /**
     * Constructor setting the specified properties.
     *
     * @param optionsConverter The converter for {@link Option}s.
     */
    @Autowired
    public ItemsConverter(OptionsConverter optionsConverter) {
        this.optionsConverter = optionsConverter;
    }

    /**
     * Converts the given {@link Item}s to {@link ItemDto}s.
     *
     * @param items The items to convert.
     * @return The resulting DTOs.
     */
    /*package-private*/Set<ItemDto> convertToItemDtos(Set<Item> items) {
        Set<ItemDto> itemDtos = new LinkedHashSet<>(items.size());
        for (Item item : items) {
            itemDtos.add(convertToDto(item));
        }
        return itemDtos;
    }

    /**
     * Converts the given {@link Item} to an {@link ItemDto}.
     *
     * @param item The {@link Item} to convert.
     * @return The resulting {@link ItemDto}.
     */
    private ItemDto convertToDto(Item item) {
        final Set<Option> options = item.getOptions();
        return ItemDto.newBuilder()
                .id(item.getId())
                .title(item.getTitle())
                .type(item.getType())
                .price(item.getPrice())
                .optionDtos(CollectionUtils.isEmpty(options)
                        ? null
                        : optionsConverter.convert(options))
                .build();
    }
}
